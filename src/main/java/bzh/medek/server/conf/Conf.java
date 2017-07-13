package bzh.medek.server.conf;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import bzh.medek.server.persistence.dao.ConfigurationDAO;

/**
 * Stateless bean for configuration
 * 
 * @author msansm1
 *
 */
@Stateless
public class Conf {
    private static final Logger LOGGER = Logger.getLogger(Conf.class.getName());

    private static final String PARAM_FS = "filesystem";
    private static final String FS_ALBUM = "album";
    private static final String FS_BOOK = "book";
    private static final String FS_MOVIE = "movie";
    private static final String FS_TVSHOW = "tvshows";
    private static final String PARAM_CLIENT_ID = "clientId";
    private static final String PARAM_CLIENT_TAG = "clientTag";

    @Inject
    private ConfigurationDAO configurationDAO;

    /**
     * Return the file system path for albums
     * 
     * @return
     */
    public String getAlbumFS() {
        String fs = configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_ALBUM + "/";
        return fs;
    }

    /**
     * Return the file system path for books
     * 
     * @return
     */
    public String getBookFS() {
        String fs = configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_BOOK + "/";
        return fs;
    }

    /**
     * Return the file system path for movies
     * 
     * @return
     */
    public String getMovieFS() {
        String fs = configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_MOVIE + "/";
        return fs;
    }

    /**
     * Return the file system path for tvshows
     * 
     * @return
     */
    public String getTvshowsFS() {
        String fs = configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_TVSHOW + "/";
        return fs;
    }

    /**
     * Return the cliet ID for Gracenote API
     * 
     * @return
     */
    public String getGracenoteClientID() {
        String id = configurationDAO.getConfiguration(PARAM_CLIENT_ID).getValue();
        return id;
    }

    /**
     * Return the cliet tag for Gracenote API
     * 
     * @return
     */
    public String getGracenoteClientTag() {
        String tag = configurationDAO.getConfiguration(PARAM_CLIENT_TAG).getValue();
        return tag;
    }

}