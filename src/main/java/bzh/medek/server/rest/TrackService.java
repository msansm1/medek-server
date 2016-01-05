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

import bzh.medek.server.json.album.JsonTrack;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.dao.ArtistDAO;
import bzh.medek.server.persistence.dao.TrackDAO;
import bzh.medek.server.persistence.dao.TrackartistDAO;
import bzh.medek.server.persistence.entities.Track;
import bzh.medek.server.persistence.entities.Trackartist;
import bzh.medek.server.persistence.entities.TrackartistPK;

@Stateless
@ApplicationPath("/services")
@Path(value = "/tracks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrackService extends Application {

	private static final Logger LOGGER = Logger.getLogger(TrackService.class);

	@Inject
	TrackDAO trackDao;
	@Inject
	AlbumDAO albumDao;
	@Inject
	ArtistDAO artistDao;
	@Inject
	TrackartistDAO trackartistDAO;

	public TrackService() {
	}

	/**
	 * GET /tracks/album/{albumId} : retrieve all tracks for one album
	 * 
	 * @return
	 */
	@GET
	@Path("/album/{albumId}")
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
	 * GET /tracks/{id} : retrieve one track
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "/{id}")
	public JsonTrack getOne(@PathParam(value = "id") Integer id) {
		Track t = trackDao.getTrack(id);
		LOGGER.info("find " + t.getTitle() + " track in the database");
		return new JsonTrack(t.getId(), t.getAlbumBean().getId(), t.getTitle(),
				t.getNumber(), t.getLength(), "", null);
	}

	/**
	 * POST /tracks : create / update one track
	 * 
	 * @param id
	 * @return
	 */
	@POST
	public JsonTrack createUpdateOne(JsonTrack track) {
		JsonTrack jt = track;
		if (track.getId() == null) {
			Track t = new Track();
			t.setTitle(track.getTitle());
			t.setLength(track.getLength());
			t.setNumber(track.getTrackNb());
			if (track.getAlbumId() != null) {
				t.setAlbumBean(albumDao.getAlbum(track.getAlbumId()));
			}
			trackDao.saveTrack(t);
			Trackartist ta = new Trackartist();
			TrackartistPK taid = new TrackartistPK();
			taid.setTrack(t.getId().intValue());
			taid.setArtist(track.getArtistId().intValue());
			ta.setId(taid);
			ta.setTrackBean(t);
			ta.setArtistBean(artistDao.getArtist(track.getArtistId()));
			trackartistDAO.saveTrackartist(ta);
			t.addTrackartist(ta);
			trackDao.updateTrack(t);
			jt.setId(t.getId());
		} else {
			Track t = trackDao.getTrack(track.getId());
			LOGGER.info("find " + t.getTitle()
					+ " track in the database to update");
			t.setTitle(track.getTitle());
			t.setLength(track.getLength());
			t.setNumber(track.getTrackNb());
			if (track.getAlbumId() != null) {
				t.setAlbumBean(albumDao.getAlbum(track.getAlbumId()));
			}
			TrackartistPK taid = new TrackartistPK();
			taid.setTrack(t.getId().intValue());
			taid.setArtist(track.getArtistId().intValue());
			if (trackartistDAO.getTrackartist(taid) == null) {
				Trackartist ta = new Trackartist();
				ta.setId(taid);
				ta.setTrackBean(t);
				ta.setArtistBean(artistDao.getArtist(track.getArtistId()));
				trackartistDAO.saveTrackartist(ta);
				t.addTrackartist(ta);
			} else {
				
			}
			trackDao.updateTrack(t);
		}
		return jt;
	}

}
