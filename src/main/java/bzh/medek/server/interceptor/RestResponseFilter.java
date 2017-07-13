package bzh.medek.server.interceptor;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import bzh.medek.server.utils.Constants;

@Provider
public class RestResponseFilter implements ContainerResponseFilter {

    private final static Logger LOGGER = Logger.getLogger(RestResponseFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {

        LOGGER.trace("Filtering REST Response");

        responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*"); // You may further limit certain client IPs
                                                                          // with Access-Control-Allow-Origin instead of
                                                                          // '*'
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        responseCtx.getHeaders().add("Access-Control-Allow-Headers", Constants.HTTP_HEADER_TOKEN);
    }
}
