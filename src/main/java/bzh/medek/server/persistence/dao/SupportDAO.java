package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Support;

/**
 * DAO for SUPPORT table
 * 
 * @author msansm1
 *
 */
@Stateless
public class SupportDAO extends Dao {

	public void updateSupport(Support support) {
		em.merge(support);
	}

	public void saveSupport(Support support) {
		em.persist(support);
		em.refresh(support);
	}

	public void removeSupport(Support support) {
		em.remove(em.merge(support));
		em.flush();
	}

	public List<Support> getSupports() {
		return em.createQuery("from Support", Support.class)
				.getResultList();
	}

	public Support getSupport(Integer id) {
		return em.find(Support.class, id);
	}
}
