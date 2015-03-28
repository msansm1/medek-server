package bzh.medek.server.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.utils.Constants;


@Provider
@PreMatching
public class RestRequestFilter implements ContainerRequestFilter {

    private final static Logger LOGGER = Logger.getLogger(RestRequestFilter.class);

    @Inject
    private UserDAO userDao;

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {

        String path = requestCtx.getUriInfo().getPath();
        LOGGER.trace("Filtering request path: " + path);

        // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for
        // this case before validating the headers (CORS stuff)
        if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {
            requestCtx.abortWith(Response.status(Response.Status.OK).build());
            return;
        }

        // For any other methods besides login, the authToken must be verified
        if (path.startsWith("/admin")) {
            String authToken = requestCtx.getHeaderString(Constants.HTTP_HEADER_TOKEN);
            Integer userId = userDao.tokenExists(authToken);

            // if it isn't valid, just kick them out.
            if (userId == null) {
                requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            } 
//            else {
//                if (!userDao.isAdmin(userId)) {
//                    requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//                }
//            }
        } else if (!path.equals("/auth/login") && !path.equals("/auth/lostpasswd")) {
            String authToken = requestCtx.getHeaderString(Constants.HTTP_HEADER_TOKEN);
            Integer userId = userDao.tokenExists(authToken);

            // if it isn't valid, just kick them out.
            if (userId == null) {
                requestCtx.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
}
