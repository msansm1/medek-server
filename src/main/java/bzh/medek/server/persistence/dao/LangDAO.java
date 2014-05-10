package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Lang;

/**
 * DAO for LANG table
 * 
 * @author msansm1
 *
 */
@Stateless
public class LangDAO extends Dao {

	public void updateLang(Lang lang) {
		em.merge(lang);
	}

	public void saveLang(Lang lang) {
		em.persist(lang);
		em.refresh(lang);
	}

	public void removeLang(Lang lang) {
		em.remove(em.merge(lang));
		em.flush();
	}

	public List<Lang> getLangs() {
		return em.createQuery("from Lang", Lang.class)
				.getResultList();
	}

	public Lang getLang(Integer id) {
		return em.find(Lang.class, id);
	}
}
