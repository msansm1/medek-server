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

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import bzh.medek.server.conf.Conf;
import bzh.medek.server.gracenote.GracenoteException;
import bzh.medek.server.gracenote.GracenoteMetadata;
import bzh.medek.server.gracenote.GracenoteWebAPI;
import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.album.JsonAddSearch;
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.album.JsonMyAlbum;
import bzh.medek.server.json.album.JsonTrack;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.dao.AlbumartistDAO;
import bzh.medek.server.persistence.dao.ArtistDAO;
import bzh.medek.server.persistence.dao.ArtisttypeDAO;
import bzh.medek.server.persistence.dao.GenreDAO;
import bzh.medek.server.persistence.dao.SupportDAO;
import bzh.medek.server.persistence.dao.TrackDAO;
import bzh.medek.server.persistence.dao.TrackartistDAO;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.dao.UseralbumDAO;
import bzh.medek.server.persistence.entities.Album;
import bzh.medek.server.persistence.entities.Albumartist;
import bzh.medek.server.persistence.entities.AlbumartistPK;
import bzh.medek.server.persistence.entities.Artist;
import bzh.medek.server.persistence.entities.Track;
import bzh.medek.server.persistence.entities.Trackartist;
import bzh.medek.server.persistence.entities.TrackartistPK;
import bzh.medek.server.persistence.entities.Useralbum;
import bzh.medek.server.persistence.entities.UseralbumPK;
import bzh.medek.server.utils.Constants;

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
	@Inject
	UserDAO userDAO;
	@Inject
	UseralbumDAO useralbumDao;
	@Inject
	ArtisttypeDAO artisttypeDAO;
	@Inject
	TrackartistDAO trackartistDAO;

	public AlbumService() {
	}

	/**
	 * GET /albums/loguser/{id} : retrieve all albums with logged user
	 * 
	 * @return
	 */
	@GET
	@Path(value = "loguser/{id}")
	public List<JsonAlbum> getAll(@PathParam(value = "id") Integer userId) {
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
			Useralbum mya = useralbumDao.getUseralbum(a.getId(), userId);
			la.add(new JsonAlbum(a.getId(), a.getTitle(), a.getCover(), a
					.getReleasedate(), (a.getGenreBean() != null) ? a
					.getGenreBean().getName() : "",
					(a.getGenreBean() != null) ? a.getGenreBean().getId()
							: null, a.getNbtracks(),
					(a.getSupportBean() != null) ? a.getSupportBean().getName()
							: "", (a.getSupportBean() != null) ? a
							.getSupportBean().getId() : null, artistName, artistId,
							(mya!=null)?true:false, (mya!=null)?mya.getRating():0, 
							(mya!=null)?mya.getIssigned():false, new ArrayList<JsonTrack>()));
		}
		return la;
	}

	/**
	 * GET /albums : retrieve all albums
	 * 
	 * 
	 * @return
	 */
	@GET
	public List<JsonAlbum> getAllWithParams(@Context HttpServletRequest request, 
			@QueryParam("from") int from, @QueryParam("limit") int limit,
			@QueryParam("orderBy") String orderBy, @QueryParam("orderDir") String orderDir) {
		List<Album> albums = albumDao.getAlbumsForList(from, limit, orderBy, orderDir);
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
			Useralbum mya = useralbumDao.getUseralbum(a.getId(), request.getHeader(Constants.HTTP_HEADER_TOKEN));
			List<Track> tracks = trackDao.getTracksForAlbum(a.getId());
			List<JsonTrack> lt = new ArrayList<JsonTrack>();
			for (Track t : tracks) {
				lt.add(new JsonTrack(t.getId(), a.getId(), t.getTitle(), t
						.getNumber(), t.getLength(), t.getTrackartists().get(0).getArtistBean().getName(),
						t.getTrackartists().get(0).getArtistBean().getId()));
			}
			la.add(new JsonAlbum(a.getId(), a.getTitle(), a.getCover(), a
					.getReleasedate(), (a.getGenreBean() != null) ? a
					.getGenreBean().getName() : "",
					(a.getGenreBean() != null) ? a.getGenreBean().getId()
							: null, a.getCds(), a.getNbtracks(),
					(a.getSupportBean() != null) ? a.getSupportBean().getName()
							: "", (a.getSupportBean() != null) ? a
							.getSupportBean().getId() : null, artistName, artistId,
							(mya!=null)?true:false, (mya!=null)?mya.getRating():0, 
							(mya!=null)?mya.getIssigned():false, lt));
		}
		return la;
	}

	/**
	 * GET /albums/{id}/loguser/{userid} : retrieve one album
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "{id}/loguser/{userid}")
	public JsonAlbum getOne(@PathParam(value = "id") Integer id, @PathParam(value = "userid") Integer userId) {
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
		Useralbum mya = useralbumDao.getUseralbum(id, userId);
		return new JsonAlbum(a.getId(), a.getTitle(), a.getCover(),
				a.getReleasedate(), (a.getGenreBean() != null) ? a
						.getGenreBean().getName() : "",
				(a.getGenreBean() != null) ? a.getGenreBean().getId() : null,
				tracks.size(), (a.getSupportBean() != null) ? a
						.getSupportBean().getName() : "",
				(a.getSupportBean() != null) ? a.getSupportBean().getId()
						: null, artistName, artistId, 
						(mya!=null)?true:false, (mya!=null)?mya.getRating():0, 
						(mya!=null)?mya.getIssigned():false, lt);
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
			a.setCds(ja.getCds());
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
			Artist albumArtist = artistDao.findArtistByName(ja.getArtist());
			if (ja.getArtistId() != null) {
				albumArtist = artistDao.getArtist(album.getArtistId());
				aaid.setArtist(album.getArtistId().intValue());
				aa.setId(aaid);
				aa.setAlbumBean(a);
				aa.setArtistBean(artistDao.getArtist(album.getArtistId()));
			} else {
				if (albumArtist ==null) {
					albumArtist = new Artist();
					albumArtist.setName(ja.getArtist());
					albumArtist.setArtisttype(artisttypeDAO.getArtisttype(1));
					artistDao.saveArtist(albumArtist);
				}
				aaid.setArtist(albumArtist.getId().intValue());
				aa.setId(aaid);
				aa.setAlbumBean(a);
				aa.setArtistBean(albumArtist);
			}
			albumArtistDao.saveAlbumartist(aa);
			a.addAlbumartist(aa);
			albumDao.updateAlbum(a);
			ja.setId(a.getId());
			for (JsonTrack t : ja.getTracks()) {
				Track track = new Track();
				track.setCd(t.getCd());
				track.setTitle(t.getTitle());
				track.setAlbumBean(a);
				track.setLength(t.getLength());
				track.setNumber(t.getTrackNb());
				trackDao.saveTrack(track);
				Trackartist ta = new Trackartist();
				TrackartistPK taid = new TrackartistPK();
				taid.setTrack(track.getId().intValue());
				if (t.getArtistId() != null) {
					taid.setArtist(t.getArtistId().intValue());
					ta.setId(taid);
					ta.setTrackBean(track);
					ta.setArtistBean(artistDao.getArtist(album.getArtistId()));
				} else {
					if (!t.getArtist().isEmpty()) {
						if (t.getArtist().equalsIgnoreCase(albumArtist.getName())) {
							taid.setArtist(albumArtist.getId().intValue());
							ta.setId(taid);
							ta.setTrackBean(track);
							ta.setArtistBean(albumArtist);
						} else {
							Artist artist = artistDao.findArtistByName(t.getArtist());
							if (artist == null) {
								artist = new Artist();
								artist.setName(t.getArtist());
								artist.setArtisttype(artisttypeDAO.getArtisttype(1));
								artistDao.saveArtist(artist);
							}
							taid.setArtist(artist.getId().intValue());
							ta.setId(taid);
							ta.setTrackBean(track);
							ta.setArtistBean(artist);
						}
					} else {
						taid.setArtist(albumArtist.getId().intValue());
						ta.setId(taid);
						ta.setTrackBean(track);
						ta.setArtistBean(albumArtist);
					}
				}
				trackartistDAO.saveTrackartist(ta);
				track.addTrackartist(ta);
				trackDao.updateTrack(track);
			}
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
			AlbumartistPK aaid = new AlbumartistPK();
			aaid.setAlbum(a.getId().intValue());
			aaid.setArtist(album.getArtistId().intValue());
			if (albumArtistDao.getAlbumartist(aaid) == null) {
				Albumartist aa = new Albumartist();
				aa.setId(aaid);
				aa.setAlbumBean(a);
				aa.setArtistBean(artistDao.getArtist(album.getArtistId()));
				a.addAlbumartist(aa);
				albumArtistDao.saveAlbumartist(aa);
			} else {
				
			}
			albumDao.updateAlbum(a);
		}
		return ja;
	}

	/**
	 * GET /albums/user : retrieve albums for one user
	 * 
	 * 
	 * @return
	 */
	@GET
	@Path(value = "user")
	public List<JsonAlbum> getUserAlbums(@Context HttpServletRequest request, 
			@QueryParam("from") int from, @QueryParam("limit") int limit,
			@QueryParam("orderBy") String orderBy, @QueryParam("orderDir") String orderDir,
			@QueryParam("userId") Integer userId) {
		List<JsonAlbum> albums = albumDao.getUserAlbumsForList(from, limit, orderBy, orderDir, userId);
		LOGGER.info("find " + albums.size() + " albums in the database");
		String artistName;
		Integer artistId;
		List<Albumartist> aartists;
		for (JsonAlbum a : albums) {
			a.setMycollec(true);
			aartists = albumDao.getAlbumArtists(a.getId());
			if (!aartists.isEmpty()) {
				artistName = aartists.get(0).getArtistBean()
						.getName();
				if (aartists.get(0).getArtistBean()
								.getFirstname() != null) {
					artistName += " " + aartists.get(0).getArtistBean()
								.getFirstname();
				}
				artistId = aartists.get(0).getArtistBean().getId();
			} else {
				artistName = "";
				artistId = 0;
			}
			a.setArtist(artistName);
			a.setArtistId(artistId);
			List<Track> tracks = trackDao.getTracksForAlbum(a.getId());
			List<JsonTrack> lt = new ArrayList<JsonTrack>();
			for (Track t : tracks) {
				lt.add(new JsonTrack(t.getId(), a.getId(), t.getTitle(), t
						.getNumber(), t.getLength(), t.getTrackartists().get(0)
						.getArtistBean().getName(), t.getTrackartists().get(0)
						.getArtistBean().getId()));
			}
			a.setTracks(lt);
		}
		return albums;
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

	/**
	 * POST /addtocollec : add album to user's collection
	 * 
	 * @return
	 */
	@POST
	@Path("addtocollec")
	public Response addToCollection(JsonMyAlbum album) {
		Useralbum ua = new Useralbum();
		UseralbumPK uaid = new UseralbumPK();
		uaid.setAlbum(album.getAlbumId().intValue());
		uaid.setUser(album.getUserId().intValue());
		ua.setId(uaid);
		ua.setAlbumBean(albumDao.getAlbum(album.getAlbumId()));
		ua.setUserBean(userDAO.getUser(album.getUserId()));
		ua.setIssigned(album.getSigned());
		ua.setComment(album.getComment());
		ua.setRating(album.getRating());
		useralbumDao.saveUseralbum(ua);
		return Response.ok(new JsonSimpleResponse(true),
				MediaType.APPLICATION_JSON).build();
	}

	/**
	 * POST /add/search : search an album from gracenote database
	 * 
	 * @return
	 */
	@POST
	@Path("add/search")
	public Response searchNewAlbum(JsonAddSearch album) {
	    String clientID  = conf.getGracenoteClientID(); // Put your clientID here.
	    String clientTag = conf.getGracenoteClientTag(); // Put your clientTag here.
	    
		try {
			GracenoteWebAPI api = new GracenoteWebAPI(clientID, clientTag);
			 // If you have a userID, you can specify it as the third parameter to constructor.
	        String userID = api.register();
	        System.out.println("UserID = " + userID);
	        
	        GracenoteMetadata results = api.searchAlbum(album.getArtist(), album.getName());
	        
	        for (Map<String, Object> a:results.getAlbums()) {
	        	a.get("album_gnid");
	        	a.get("album_artist_name");
	        	a.get("album_title");
	        	a.get("album_year");
	        	a.get("genre");
	        	GracenoteMetadata details = api.fetchAlbum(a.get("album_gnid").toString());
	        	details.getAlbum(0);
	        }
	        
	        
		} catch (GracenoteException e) {
			LOGGER.error("Whil searching album in gracenote : ", e);
		}
		return Response.ok(new JsonSimpleResponse(false),
				MediaType.APPLICATION_JSON).build();
	}

}
