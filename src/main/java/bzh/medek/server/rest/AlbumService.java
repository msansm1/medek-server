package bzh.medek.server.rest;

import java.util.ArrayList;
import java.util.List;

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

import org.apache.log4j.Logger;

import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.album.JsonTrack;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.dao.TrackDAO;
import bzh.medek.server.persistence.entities.Album;
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
		for (Album a : albums) {
			la.add(new JsonAlbum(a.getId(), a.getTitle(), a.getCover(), a
					.getReleasedate() + "", (a.getGenreBean() != null) ? a
					.getGenreBean().getName() : "", a.getNbtracks(), (a
					.getSupportBean() != null) ? a.getSupportBean().getName()
					: "", new ArrayList<JsonTrack>()));
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
		LOGGER.info("find " + tracks.size() + " tracks for album : "+id);
		List<JsonTrack> lt = new ArrayList<JsonTrack>();
		for (Track t : tracks) {
			lt.add(new JsonTrack(t.getId(), id, t.getTitle(), t.getNumber(), t.getLength(), null));
		}
		return new JsonAlbum(a.getId(), a.getTitle(), a.getCover(),
				a.getReleasedate() + "", (a.getGenreBean() != null) ? a
						.getGenreBean().getName() : "",
				(a.getGenreBean() != null) ? a.getGenreBean().getId() : null,
				a.getNbtracks(), (a.getSupportBean() != null) ? a
						.getSupportBean().getName() : "",
				(a.getSupportBean() != null) ? a.getSupportBean().getId()
						: null, lt);
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
			a.setIssigned(false);
			albumDao.saveAlbum(a);
			ja.setId(a.getId());
		} else {
			Album a = albumDao.getAlbum(album.getId());
			LOGGER.info("find " + a.getTitle()
					+ " album in the database to update");
			a.setTitle(album.getTitle());
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
	public List<JsonTrack> getAlbumTracks(@PathParam(value = "albumId") Integer albumId) {
		List<Track> tracks = trackDao.getTracksForAlbum(albumId);
		LOGGER.info("find " + tracks.size() + " tracks for album : "+albumId);
		List<JsonTrack> lt = new ArrayList<JsonTrack>();
		for (Track t : tracks) {
			lt.add(new JsonTrack(t.getId(), albumId, t.getTitle(), t.getNumber(), t.getLength(), null));
		}
		return lt;
	}

}
