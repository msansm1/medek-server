package bzh.medek.server.conf;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

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
    
}