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
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.album.JsonTrack;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.dao.AlbumartistDAO;
import bzh.medek.server.persistence.dao.ArtistDAO;
import bzh.medek.server.persistence.dao.GenreDAO;
import bzh.medek.server.persistence.dao.SupportDAO;
import bzh.medek.server.persistence.dao.TrackDAO;
import bzh.medek.server.persistence.entities.Album;
import bzh.medek.server.persistence.entities.Albumartist;
import bzh.medek.server.persistence.entities.AlbumartistPK;
import bzh.medek.server.persistence.entities.Track;

@Stateless
@ApplicationPath("/services")
@Path(value = "/albums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumService extends Application {

	private static final Logger LOGGER = Logger.getLogger(AlbumService.class);

	@Inject
	AlbumDAO albumDao;
	@Inject
	TrackDAO trackDao;
	@Inject
	GenreDAO genreDAO;
	@Inject
	SupportDAO supportDAO;
	@Inject
	ArtistDAO artistDao;
	@Inject
	AlbumartistDAO albumArtistDao;
	@Inject
	Conf conf;

	public AlbumService() {
	}

	/**
	 * GET /albums : retrieve all albums
	 * 
	 * @return
	 */
	@GET
	public List<JsonAlbum> getAll() {
		List<Album> albums = albumDao.getAlbums();
		LOGGER.info("find " + albums.size() + " albums in the database");
		ArrayList<JsonAlbum> la = new ArrayList<JsonAlbum>();
		String artistName = "";
		Integer artistId = 0;
		for (Album a : albums) {
			if (!a.getAlbumartists().isEmpty()) {
				artistName = a.getAlbumartists().get(0).getArtistBean()
						.getName();
				if (a.getAlbumartists().get(0).getArtistBean()
								.getFirstname() != null) {
					artistName += " " + a.getAlbumartists().get(0).getArtistBean()
								.getFirstname();
				}
				artistId = a.getAlbumartists().get(0).getArtistBean().getId();
			} else {
				artistName = "";
				artistId = 0;
			}
			la.add(new JsonAlbum(a.getId(), a.getTitle(), a.getCover(), a
					.getReleasedate(), (a.getGenreBean() != null) ? a
					.getGenreBean().getName() : "",
					(a.getGenreBean() != null) ? a.getGenreBean().getId()
							: null, a.getNbtracks(),
					(a.getSupportBean() != null) ? a.getSupportBean().getName()
							: "", (a.getSupportBean() != null) ? a
							.getSupportBean().getId() : null, artistName,
					artistId, new ArrayList<JsonTrack>()));
		}
		return la;
	}

	/**
	 * GET /albums/{id} : retrieve one album
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "/{id}")
	public JsonAlbum getOne(@PathParam(value = "id") Integer id) {
		Album a = albumDao.getAlbum(id);
		LOGGER.info("find " + a.getTitle() + " album in the database");
		List<Track> tracks = trackDao.getTracksForAlbum(id);
		LOGGER.info("find " + tracks.size() + " tracks for album : " + id);
		List<JsonTrack> lt = new ArrayList<JsonTrack>();
		for (Track t : tracks) {
			lt.add(new JsonTrack(t.getId(), id, t.getTitle(), t
					.getNumber(), t.getLength(), t.getTrackartists().get(0)
					.getArtistBean().getName(), t.getTrackartists().get(0)
					.getArtistBean().getId()));
		}
		String artistName = "";
		Integer artistId = 0;
		if (!a.getAlbumartists().isEmpty()) {
			artistName = a.getAlbumartists().get(0).getArtistBean().getName();
			if (a.getAlbumartists().get(0).getArtistBean().getFirstname() != null) {
				artistName = " "
						+ a.getAlbumartists().get(0).getArtistBean()
								.getFirstname();
			}
			artistId = a.getAlbumartists().get(0).getArtistBean().getId();
		}
		return new JsonAlbum(a.getId(), a.getTitle(), a.getCover(),
				a.getReleasedate(), (a.getGenreBean() != null) ? a
						.getGenreBean().getName() : "",
				(a.getGenreBean() != null) ? a.getGenreBean().getId() : null,
				a.getNbtracks(), (a.getSupportBean() != null) ? a
						.getSupportBean().getName() : "",
				(a.getSupportBean() != null) ? a.getSupportBean().getId()
						: null, artistName, artistId, lt);
	}

	/**
	 * POST /albums : create / update one album
	 * 
	 * @param id
	 * @return
	 */
	@POST
	public JsonAlbum createUpdateOne(JsonAlbum album) {
		JsonAlbum ja = album;
		if (album.getId() == null) {
			Album a = new Album();
			a.setTitle(album.getTitle());
			a.setCover(album.getCover());
			a.setReleasedate(album.getReleaseDate());
			if (album.getGenreId() != null) {
				a.setGenreBean(genreDAO.getGenre(album.getGenreId()));
			}
			if (album.getSupportId() != null) {
				a.setSupportBean(supportDAO.getSupport(album.getSupportId()));
			}
			albumDao.saveAlbum(a);
			Albumartist aa = new Albumartist();
			AlbumartistPK aaid = new AlbumartistPK();
			aaid.setAlbum(a.getId().intValue());
			aaid.setArtist(album.getArtistId().intValue());
			aa.setId(aaid);
			aa.setAlbumBean(a);
			aa.setArtistBean(artistDao.getArtist(album.getArtistId()));
			albumArtistDao.saveAlbumartist(aa);
			a.addAlbumartist(aa);
			albumDao.updateAlbum(a);
			ja.setId(a.getId());
		} else {
			Album a = albumDao.getAlbum(album.getId());
			LOGGER.info("find " + a.getTitle()
					+ " album in the database to update");
			a.setTitle(album.getTitle());
			a.setCover(album.getCover());
			a.setReleasedate(album.getReleaseDate());
			if (album.getGenreId() != null) {
				a.setGenreBean(genreDAO.getGenre(album.getGenreId()));
			}
			if (album.getSupportId() != null) {
				a.setSupportBean(supportDAO.getSupport(album.getSupportId()));
			}
			Albumartist aa = new Albumartist();
			AlbumartistPK aaid = new AlbumartistPK();
			aaid.setAlbum(a.getId().intValue());
			aaid.setArtist(album.getArtistId().intValue());
			aa.setId(aaid);
			aa.setAlbumBean(a);
			aa.setArtistBean(artistDao.getArtist(album.getArtistId()));
			a.addAlbumartist(aa);
			albumArtistDao.saveAlbumartist(aa);
			albumDao.updateAlbum(a);
		}
		return ja;
	}

	/**
	 * GET /albums/user/{id} : retrieve albums for one user
	 * 
	 * @param id
	 *            - user ID
	 * @return
	 */
	@GET
	@Path(value = "/user/{id}")
	public List<JsonAlbum> getUserAlbums(@PathParam(value = "id") Integer id) {
		return albumDao.getUsersAlbums(id);
	}

	/**
	 * GET /albums/{albumId}/tracks : retrieve all tracks for one album
	 * 
	 * @return
	 */
	@GET
	@Path("/{albumId}/tracks")
	public List<JsonTrack> getAlbumTracks(
			@PathParam(value = "albumId") Integer albumId) {
		List<Track> tracks = trackDao.getTracksForAlbum(albumId);
		LOGGER.info("find " + tracks.size() + " tracks for album : " + albumId);
		List<JsonTrack> lt = new ArrayList<JsonTrack>();
		for (Track t : tracks) {
			lt.add(new JsonTrack(t.getId(), albumId, t.getTitle(), t
					.getNumber(), t.getLength(), t.getTrackartists().get(0)
					.getArtistBean().getName(), t.getTrackartists().get(0)
					.getArtistBean().getId()));
		}
		return lt;
	}

	/**
	 * POST : upload new cover for album
	 * 
	 * @param newcover
	 * @return
	 */
	@POST
	@Path("{id}/coverupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadAttach(@PathParam("id") Integer id,
			MultipartFormDataInput newcover) {
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
				List<String> contDisp = inputPart.getHeaders().get(
						"Content-Disposition");
				for (String cd : contDisp) {
					if (cd.contains("filename")) {
						filename = "cover.jpg";
						LOGGER.info("FILENAME : " + filename);
					}
				}
				String path = conf.getAlbumFS() + id + "/";
				File pathtest = new File(path);
				if (!pathtest.exists()) {
					if (!pathtest.mkdirs()) {
						LOGGER.error("While saving cover : "
								+ "unable to create repository tmp dir => "
								+ path);
					}
				}
				File up = new File(path + filename);
				if (!up.createNewFile()) {
					if (up.exists()) {
						up.delete();
						if (!up.createNewFile()) {
							LOGGER.error("While saving cover : "
									+ "unable to overwrite existing file => "
									+ up.getAbsolutePath());
						}
					} else {
						LOGGER.error("While saving cover : "
								+ "unable to create new file => "
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
				return Response.ok(new JsonSimpleResponse(false),
						MediaType.APPLICATION_JSON).build();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						LOGGER.error(
								"While saving cover - closing inputstream : ",
								e);
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						LOGGER.error(
								"While saving cover - closing outputstream : ",
								e);
					}
				}
			}
		}
		return Response.ok(new JsonSimpleResponse(true),
				MediaType.APPLICATION_JSON).build();
	}

}
