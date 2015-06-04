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
import bzh.medek.server.json.tvshow.JsonMyShow;
import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.persistence.dao.StorygenreDAO;
import bzh.medek.server.persistence.dao.SupportDAO;
import bzh.medek.server.persistence.dao.TvshowDAO;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.dao.UsertvDAO;
import bzh.medek.server.persistence.entities.Tvshow;
import bzh.medek.server.persistence.entities.Usertv;
import bzh.medek.server.persistence.entities.UsertvPK;

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
	StorygenreDAO storygenreDao;
	@Inject
	SupportDAO supportDao;
	@Inject
	Conf conf;
	@Inject
	UsertvDAO usertvDAO;
	@Inject
	UserDAO userDAO;

	public TVShowService() {
	}

	/**
	 * GET /shows/loguser/{id} : retrieve all shows with logged user
	 * 
	 * @return
	 */
	@GET
	@Path(value = "loguser/{id}")
	public List<JsonShow> getAll(@PathParam(value = "id") Integer userId) {
		List<Tvshow> shows = showDao.getTvshows();
		LOGGER.info("find " + shows.size() + " shows in the database");
		ArrayList<JsonShow> ls = new ArrayList<JsonShow>();
		for (Tvshow s : shows) {
			Usertv mys = usertvDAO.getUsertv(s.getId(), userId);
			ls.add(new JsonShow(s.getId(), s.getTitle(), s.getDescription(), s
					.getReleasedate(), s.getCover(),
					(s.getSupportBean() != null) ? s.getSupportBean().getName()
							: "", (s.getSupportBean() != null) ? s
							.getSupportBean().getId() : null, (s
							.getStorygenre() != null) ? s.getStorygenre()
							.getName() : "", (s.getStorygenre() != null) ? s
							.getStorygenre().getId() : null, s.getLength(), s
							.getSeason(), s.getSeries(), s.getIsseriedone(),
					null, null, (mys!=null)?true:false, (mys!=null)?mys.getRating():0));
		}
		return ls;
	}

	/**
	 * GET /shows/{id}/loguser/{userid} : retrieve one show
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "/{id}/loguser/{userid}")
	public JsonShow getOne(@PathParam(value = "id") Integer id, @PathParam(value = "userid") Integer userId) {
		Tvshow s = showDao.getTvshow(id);
		LOGGER.info("find " + s.getTitle() + " show in the database");
		Usertv mys = usertvDAO.getUsertv(s.getId(), userId);
		return new JsonShow(s.getId(), s.getTitle(), s.getDescription(),
				s.getReleasedate(), s.getCover(),
				(s.getSupportBean() != null) ? s.getSupportBean().getName()
						: "", (s.getSupportBean() != null) ? s.getSupportBean()
						.getId() : null, (s.getStorygenre() != null) ? s
						.getStorygenre().getName() : "",
				(s.getStorygenre() != null) ? s.getStorygenre().getId() : null,
				s.getLength(), s.getSeason(), s.getSeries(),
				s.getIsseriedone(), null, null, (mys!=null)?true:false, 
						(mys!=null)?mys.getRating():0);
	}

	/**
	 * POST /shows : create / update one show
	 * 
	 * @param id
	 * @return
	 */
	@POST
	public JsonShow createUpdateOne(JsonShow show) {
		JsonShow jshow = show;
		if (show.getId() == null) {
			Tvshow s = new Tvshow();
			s.setCover(show.getCover());
			s.setDescription(show.getDescription());
			s.setTitle(show.getTitle());
			s.setIsseriedone(show.getIsSeriesDone());
			s.setLength(show.getLength());
			s.setReleasedate(show.getReleaseDate());
			s.setSeason(show.getSeason());
			s.setSeries(show.getSeries());
			s.setStorygenre(storygenreDao.getStorygenre(show.getGenreId()));
			s.setSupportBean(supportDao.getSupport(show.getSupportId()));
			showDao.saveTvshow(s);
			jshow.setId(s.getId());
		} else {
			Tvshow s = showDao.getTvshow(show.getId());
			s.setCover(show.getCover());
			s.setDescription(show.getDescription());
			s.setTitle(show.getTitle());
			s.setIsseriedone(show.getIsSeriesDone());
			s.setLength(show.getLength());
			s.setReleasedate(show.getReleaseDate());
			s.setSeason(show.getSeason());
			s.setSeries(show.getSeries());
			s.setStorygenre(storygenreDao.getStorygenre(show.getGenreId()));
			s.setSupportBean(supportDao.getSupport(show.getSupportId()));
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

	/**
	 * POST /addtocollec : add tvshow to user's collection
	 * 
	 * @return
	 */
	@POST
	@Path("addtocollec")
	public Response addToCollection(JsonMyShow show) {
		Usertv ut = new Usertv();
		UsertvPK umid = new UsertvPK();
		umid.setTvshow(show.getSerieId().intValue());
		umid.setUser(show.getUserId().intValue());
		ut.setId(umid);
		ut.setTvshowBean(showDao.getTvshow(show.getSerieId()));
		ut.setUserBean(userDAO.getUser(show.getUserId()));
		ut.setComment(show.getComment());
		ut.setRating(show.getRating());
		usertvDAO.saveUsertv(ut);
		return Response.ok(new JsonSimpleResponse(true),
				MediaType.APPLICATION_JSON).build();
	}

}
