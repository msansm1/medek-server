package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Usertv;

/**
 * DAO for USERTV table
 * 
 * @author msansm1
 *
 */
@Stateless
public class UsertvDAO extends Dao {

	public void updateUsertv(Usertv usertv) {
		em.merge(usertv);
	}

	public void saveUsertv(Usertv usertv) {
		em.persist(usertv);
		em.refresh(usertv);
	}

	public void removeUsertv(Usertv usertv) {
		em.remove(em.merge(usertv));
		em.flush();
	}

	public List<Usertv> getUsertvs() {
		return em.createQuery("from Usertv", Usertv.class)
				.getResultList();
	}

	public Usertv getUsertv(Integer id) {
		return em.find(Usertv.class, id);
	}
}
