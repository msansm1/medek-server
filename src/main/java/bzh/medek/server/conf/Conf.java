package bzh.medek.server.conf;

import javax.ejb.Stateless;
import javax.inject.Inject;

import bzh.medek.server.persistence.dao.ConfigurationDAO;

/**
 * Stateless bean for configuration
 * 
 * @author msansm1
 *
 */
@Stateless
public class Conf {
    
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
        return configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_ALBUM + "/";
    }

    /**
     * Return the file system path for books
     * 
     * @return
     */
    public String getBookFS() {
        return configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_BOOK + "/";
    }

    /**
     * Return the file system path for movies
     * 
     * @return
     */
    public String getMovieFS() {
        return configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_MOVIE + "/";
    }

    /**
     * Return the file system path for tvshows
     * 
     * @return
     */
    public String getTvshowsFS() {
        return configurationDAO.getConfiguration(PARAM_FS).getValue() + FS_TVSHOW + "/";
    }

    /**
     * Return the cliet ID for Gracenote API
     * 
     * @return
     */
    public String getGracenoteClientID() {
        return configurationDAO.getConfiguration(PARAM_CLIENT_ID).getValue();
    }

    /**
     * Return the cliet tag for Gracenote API
     * 
     * @return
     */
    public String getGracenoteClientTag() {
        return configurationDAO.getConfiguration(PARAM_CLIENT_TAG).getValue();
    }
    
}