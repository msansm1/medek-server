package bzh.medek.server.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import bzh.medek.server.conf.Conf;
import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.persistence.dao.TvshowDAO;
import bzh.medek.server.persistence.entities.Tvshow;

@Stateless
@ApplicationPath("/services")
@Path(value = "/tvshows")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TVShowService extends Application {

	private static final Logger LOGGER = Logger.getLogger(TVShowService.class);

	@Inject
	TvshowDAO showDao;
	@Inject
	Conf conf;

	public TVShowService() {
	}

	/**
	 * GET /shows : retrieve all shows
	 * 
	 * @return
	 */
	@GET
	public List<JsonShow> getAll() {
		List<Tvshow> shows = showDao.getTvshows();
		LOGGER.info("find " + shows.size() + " shows in the database");
		ArrayList<JsonShow> ls = new ArrayList<JsonShow>();
		for (Tvshow s : shows) {
			ls.add(new JsonShow(s.getId(), s.getTitle(), s.getDescription(), s
					.getReleasedate(), s.getCover(),
					(s.getSupportBean() != null) ? s.getSupportBean().getName()
							: "", (s.getSupportBean() != null) ? s
							.getSupportBean().getId() : null, (s
							.getStorygenre() != null) ? s.getStorygenre()
							.getName() : "", (s.getStorygenre() != null) ? s
							.getStorygenre().getId() : null, s.getLength(), s
							.getSeason(), s.getSeries(), s.getIsseriedone(),
					null, null));
		}
		return ls;
	}

	/**
	 * GET /shows/{id} : retrieve one show
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "/{id}")
	public JsonShow getOne(@PathParam(value = "id") Integer id) {
		Tvshow s = showDao.getTvshow(id);
		LOGGER.info("find " + s.getTitle() + " show in the database");
		return new JsonShow(s.getId(), s.getTitle(), s.getDescription(),
				s.getReleasedate(), s.getCover(),
				(s.getSupportBean() != null) ? s.getSupportBean().getName()
						: "", (s.getSupportBean() != null) ? s.getSupportBean()
						.getId() : null, (s.getStorygenre() != null) ? s
						.getStorygenre().getName() : "",
				(s.getStorygenre() != null) ? s.getStorygenre().getId() : null,
				s.getLength(), s.getSeason(), s.getSeries(),
				s.getIsseriedone(), null, null);
	}

	/**
	 * POST /shows : create / update one show
	 * 
	 * @param id
	 * @return
	 */
	@POST
	public JsonShow createOne(JsonShow show) {
		JsonShow jshow = show;
		if (show.getId() == null) {
			Tvshow s = new Tvshow();
			s.setCover(show.getCover());
			s.setDescription(show.getDescription());
			s.setTitle(show.getTitle());
			showDao.saveTvshow(s);
			jshow.setId(s.getId());
		} else {
			Tvshow s = showDao.getTvshow(show.getId());
			s.setCover(show.getCover());
			s.setDescription(show.getDescription());
			s.setTitle(show.getTitle());
			showDao.updateTvshow(s);
		}
		return jshow;
	}

	/**
	 * GET /shows/user/{id} : retrieve shows for one user
	 * 
	 * @param id
	 *            - user ID
	 * @return
	 */
	@GET
	@Path(value = "/user/{id}")
	public List<JsonShow> getUserShows(@PathParam(value = "id") Integer id) {
		return showDao.getUsersTvshows(id);
	}

    /**
     * POST : upload new cover for show
     * 
     * @param newcover
     * @return
     */
    @POST
    @Path("{id}/coverupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadAttach(@PathParam("id") Integer id, MultipartFormDataInput newcover) {
        Map<String, List<InputPart>> uploadForm = newcover.getFormDataMap();
        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("file");
        String filename = null;
        for (InputPart inputPart : inputParts) {
            // convert the uploaded file to inputstream and write it to disk
            InputStream inputStream = null;
            OutputStream out = null;
            try {
                inputStream = inputPart.getBody(InputStream.class, null);
                List<String> contDisp = inputPart.getHeaders().get("Content-Disposition");
                for (String cd : contDisp) {
                    if (cd.contains("filename")) {
                        filename = "cover.jpg";
                        LOGGER.info("FILENAME : " + filename);
                    }
                }
                String path = conf.getTvshowsFS() + id + "/";
                File pathtest = new File(path);
                if (!pathtest.exists()) {
                    if (!pathtest.mkdirs()) {
                        LOGGER.error("While saving cover : "
                                + "unable to create repository tmp dir => " + path);
                    }
                }
                File up = new File(path + filename);
                if (!up.createNewFile()) {
                	if (up.exists()) {
                		up.delete();
                		if (!up.createNewFile()) {
                            LOGGER.error("While saving cover : " + "unable to overwrite existing file => "
                                    + up.getAbsolutePath());
                		}
                	} else {
                    LOGGER.error("While saving cover : " + "unable to create new file => "
                            + up.getAbsolutePath());
                	}
                }
                out = new FileOutputStream(up);

                int read = 0;
                byte[] bytes = new byte[2048];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("While saving cover : ", e);
                return Response.ok(new JsonSimpleResponse(false), MediaType.APPLICATION_JSON).build();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        LOGGER.error("While saving cover - closing inputstream : ", e);
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        LOGGER.error("While saving cover - closing outputstream : ", e);
                    }
                }
            }
        }
        return Response.ok(new JsonSimpleResponse(true), MediaType.APPLICATION_JSON).build();
    }

}
