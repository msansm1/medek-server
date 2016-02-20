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

import bzh.medek.server.json.JsonLang;
import bzh.medek.server.persistence.dao.LangDAO;
import bzh.medek.server.persistence.entities.Lang;
import bzh.medek.server.utils.Constants;

@Stateless
@ApplicationPath("/services")
@Path(value = "/langs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LangService extends Application {

    private static final Logger LOGGER = Logger.getLogger(LangService.class);
    
    @Inject
    LangDAO langDao;
	
	public LangService () {
	}

    /**
     *  GET /langs : retrieve all langs
     * 
     * @return
     */
    @GET
    public List<JsonLang> getAll() {
    	List<Lang> langs = langDao.getLangs();
    	LOGGER.info("find "+langs.size()+" langs in the database");
    	ArrayList<JsonLang> ll = new ArrayList<JsonLang>();
    	for (Lang l:langs) {
    		ll.add(new JsonLang(l.getId(), l.getName()));
    	}
    	return ll;
    }

    /**
     *  GET /langs/{id} : retrieve one lang
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonLang getOne(@PathParam(value = "id") Integer id) {
    	Lang l = langDao.getLang(id);
    	LOGGER.info("find "+l.getName()+" lang in the database");
    	return new JsonLang(l.getId(), l.getName());
    }

    /**
     *  POST /langs : create / update one lang
     * 
     * @param JsonBooktype lang
     * @return
     */
    @POST
    public JsonLang createUpdateOne(JsonLang lang) {
    	JsonLang jlang = lang;
    	if (lang.getId() == null) {
	    	Lang l = new Lang();
	    	l.setName(lang.getName());
	    	langDao.saveLang(l);
	    	jlang.setId(l.getId());
    	} else {
    		if (lang.getName().equalsIgnoreCase(Constants.DELETED)) {
    			langDao.removeLang(langDao.getLang(lang.getId()));
    		} else {
	        	Lang l = langDao.getLang(lang.getId());
		    	l.setName(lang.getName());
	        	langDao.updateLang(l);
    		}
    	}
    	return jlang;
    }
	
}
