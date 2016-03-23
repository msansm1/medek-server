package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.admin.JsonConfParam;
import bzh.medek.server.persistence.entities.Configuration;

/**
 * DAO for CONFIGURATION table
 * 
 * @author msansm1
 *
 */
@Stateless
public class ConfigurationDAO extends Dao {

    public List<Configuration> getAllConfigurations() {
        TypedQuery<Configuration> q = em.createQuery("from Configuration", Configuration.class);
        return q.getResultList();
    }

    public Configuration getConfiguration(String config) {
        return em.find(Configuration.class, config);
    }

    public void saveConfiguration(Configuration config) {
        em.persist(config);
    }

    public void updateConfiguration(Configuration config) {
        em.merge(config);
    }

	public List<JsonConfParam> getJsonConf() {
		TypedQuery<JsonConfParam> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.admin.JsonConfParam(c.parameter, c.value)"
						+ "from Configuration c",
						JsonConfParam.class);
		return q.getResultList();
	}

}
