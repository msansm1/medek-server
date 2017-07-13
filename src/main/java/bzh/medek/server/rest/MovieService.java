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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import bzh.medek.server.conf.Conf;
import bzh.medek.server.json.JsonLang;
import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.json.movie.JsonMyMovie;
import bzh.medek.server.persistence.dao.MovieDAO;
import bzh.medek.server.persistence.dao.StorygenreDAO;
import bzh.medek.server.persistence.dao.SupportDAO;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.dao.UsermovieDAO;
import bzh.medek.server.persistence.entities.Lang;
import bzh.medek.server.persistence.entities.Movie;
import bzh.medek.server.persistence.entities.Movieartist;
import bzh.medek.server.persistence.entities.Usermovie;
import bzh.medek.server.persistence.entities.UsermoviePK;
import bzh.medek.server.utils.Constants;

@Stateless
@ApplicationPath("/services")
@Path(value = "/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieService extends Application {

    private static final Logger LOGGER = Logger.getLogger(MovieService.class);

    @Inject
    MovieDAO movieDao;
    @Inject
    SupportDAO supportDAO;
    @Inject
    StorygenreDAO storygenreDAO;
    @Inject
    Conf conf;
    @Inject
    UsermovieDAO usermovieDAO;
    @Inject
    UserDAO userDAO;

    public MovieService() {
    }

    /**
     * GET /movies/loguser/{id} : retrieve all movies with logged user
     * 
     * @return
     */
    @GET
    @Path(value = "loguser/{id}")
    public List<JsonMovie> getAll(@PathParam(value = "id") Integer userId) {
        List<Movie> movies = movieDao.getMovies();
        LOGGER.info("find " + movies.size() + " movies in the database");
        ArrayList<JsonMovie> lm = new ArrayList<JsonMovie>();
        String artistName = "";
        Integer artistId = 0;
        for (Movie m : movies) {
            if (!m.getMovieartists().isEmpty()) {
                artistName = m.getMovieartists().get(0).getArtistBean().getName() + " "
                        + m.getMovieartists().get(0).getArtistBean().getFirstname();
                artistId = m.getMovieartists().get(0).getArtistBean().getId();
            } else {
                artistName = "";
                artistId = 0;
            }
            Usermovie mym = usermovieDAO.getUsermovie(m.getId(), userId);
            lm.add(new JsonMovie(m.getId(), m.getTitle(), m.getDescription(), m.getReleasedate(), m.getCover(),
                    m.getSupportBean().getName(), m.getSupportBean().getId(), m.getStorygenre().getName(),
                    m.getStorygenre().getId(), m.getLength(), m.getIscollector(), artistName, artistId, "", null, "",
                    null, new ArrayList<JsonLang>(), new ArrayList<JsonLang>(), (mym != null) ? true : false,
                    (mym != null) ? mym.getRating() : 0));
        }
        return lm;
    }

    /**
     * GET /movies : retrieve all movies
     * 
     * @return
     */
    @GET
    public List<JsonMovie> getAllWithParams(@Context HttpServletRequest request, @QueryParam("from") int from,
            @QueryParam("limit") int limit, @QueryParam("orderBy") String orderBy,
            @QueryParam("orderDir") String orderDir) {
        List<Movie> movies = movieDao.getMoviesForList(from, limit, orderBy, orderDir);
        LOGGER.info("find " + movies.size() + " movies in the database");
        ArrayList<JsonMovie> lm = new ArrayList<JsonMovie>();
        String artistName = "";
        Integer artistId = 0;
        for (Movie m : movies) {
            if (!m.getMovieartists().isEmpty()) {
                artistName = m.getMovieartists().get(0).getArtistBean().getName() + " "
                        + m.getMovieartists().get(0).getArtistBean().getFirstname();
                artistId = m.getMovieartists().get(0).getArtistBean().getId();
            } else {
                artistName = "";
                artistId = 0;
            }
            Usermovie mym = usermovieDAO.getUsermovie(m.getId(), request.getHeader(Constants.HTTP_HEADER_TOKEN));
            lm.add(new JsonMovie(m.getId(), m.getTitle(), m.getDescription(), m.getReleasedate(), m.getCover(),
                    m.getSupportBean().getName(), m.getSupportBean().getId(), m.getStorygenre().getName(),
                    m.getStorygenre().getId(), m.getLength(), m.getIscollector(), artistName, artistId, "", null, "",
                    null, new ArrayList<JsonLang>(), new ArrayList<JsonLang>(), (mym != null) ? true : false,
                    (mym != null) ? mym.getRating() : 0));
        }
        return lm;
    }

    /**
     * GET /movies/{id}/loguser/{userid} : retrieve one movie
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}/loguser/{userid}")
    public JsonMovie getOne(@PathParam(value = "id") Integer id, @PathParam(value = "userid") Integer userId) {
        JsonMovie jm = movieDao.getJsonMovie(id);
        LOGGER.info("find " + jm.getTitle() + " movie in the database");
        Movie m = movieDao.getMovie(id);
        List<JsonLang> ll = new ArrayList<JsonLang>();
        for (Lang l : m.getLangs2()) {
            ll.add(new JsonLang(l.getId(), l.getName()));
        }
        jm.setLangs(ll);
        List<JsonLang> ls = new ArrayList<JsonLang>();
        for (Lang l : m.getLangs1()) {
            ls.add(new JsonLang(l.getId(), l.getName()));
        }
        jm.setSubtitles(ls);
        Usermovie mym = usermovieDAO.getUsermovie(m.getId(), userId);
        jm.setMycollec((mym != null) ? true : false);
        jm.setRating((mym != null) ? mym.getRating() : 0);
        return jm;
    }

    /**
     * POST /movies : create / update one movie
     * 
     * @param id
     * @return
     */
    @POST
    public JsonMovie createUpdateOne(JsonMovie movie) {
        JsonMovie jmovie = movie;
        if (movie.getId() == null) {
            Movie m = new Movie();
            m.setTitle(movie.getTitle());
            m.setDescription(movie.getDescription());
            m.setReleasedate(movie.getReleaseDate());
            m.setLength(movie.getLength());
            m.setIscollector(movie.getIsCollector());
            m.setSupportBean(supportDAO.getSupport(movie.getSupportId()));
            m.setStorygenre(storygenreDAO.getStorygenre(movie.getGenreId()));
            movieDao.saveMovie(m);
            jmovie.setId(m.getId());
        } else {
            Movie m = movieDao.getMovie(movie.getId());
            m.setTitle(movie.getTitle());
            m.setDescription(movie.getDescription());
            m.setReleasedate(movie.getReleaseDate());
            m.setLength(movie.getLength());
            m.setIscollector(movie.getIsCollector());
            m.setSupportBean(supportDAO.getSupport(movie.getSupportId()));
            m.setStorygenre(storygenreDAO.getStorygenre(movie.getGenreId()));
            movieDao.updateMovie(m);
        }
        return jmovie;
    }

    /**
     * GET /movies/user : retrieve movie for one user
     * 
     * @param id
     *            - user ID
     * @return
     */
    @GET
    @Path(value = "user")
    public List<JsonMovie> getAllWithParams(@Context HttpServletRequest request, @QueryParam("from") int from,
            @QueryParam("limit") int limit, @QueryParam("orderBy") String orderBy,
            @QueryParam("orderDir") String orderDir, @QueryParam("userId") Integer userId) {
        List<JsonMovie> movies = movieDao.getUserMovies(from, limit, orderBy, orderDir, userId);
        LOGGER.info("find " + movies.size() + " movies in the database");
        String artistName = "";
        Integer artistId = 0;
        List<Movieartist> martists = null;
        for (JsonMovie m : movies) {
            martists = movieDao.getMovieArtists(m.getId());
            if (!martists.isEmpty()) {
                artistName = martists.get(0).getArtistBean().getName() + " "
                        + martists.get(0).getArtistBean().getFirstname();
                artistId = martists.get(0).getArtistBean().getId();
            } else {
                artistName = "";
                artistId = 0;
            }
            m.setRealisator(artistName);
            m.setRealisatorId(artistId);
        }
        return movies;
    }

    /**
     * POST : upload new cover for movie
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
                String path = conf.getMovieFS() + id + "/";
                File pathtest = new File(path);
                if (!pathtest.exists()) {
                    if (!pathtest.mkdirs()) {
                        LOGGER.error("While saving cover : " + "unable to create repository tmp dir => " + path);
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
                        LOGGER.error("While saving cover : " + "unable to create new file => " + up.getAbsolutePath());
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
     * POST /addtocollec : add movie to user's collection
     * 
     * @return
     */
    @POST
    @Path("addtocollec")
    public Response addToCollection(JsonMyMovie movie) {
        Usermovie um = new Usermovie();
        UsermoviePK umid = new UsermoviePK();
        umid.setMovie(movie.getMovieId().intValue());
        umid.setUser(movie.getUserId().intValue());
        um.setId(umid);
        um.setMovieBean(movieDao.getMovie(movie.getMovieId()));
        um.setUserBean(userDAO.getUser(movie.getUserId()));
        um.setComment(movie.getComment());
        um.setRating(movie.getRating());
        usermovieDAO.saveUsermovie(um);
        return Response.ok(new JsonSimpleResponse(true), MediaType.APPLICATION_JSON).build();
    }

}
