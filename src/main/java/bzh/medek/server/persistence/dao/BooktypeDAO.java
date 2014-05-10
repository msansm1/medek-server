package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Booktype;

/**
 * DAO for BOOKTYPE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class BooktypeDAO extends Dao {

	public void updateBooktype(Booktype booktype) {
		em.merge(booktype);
	}

	public void saveBooktype(Booktype booktype) {
		em.persist(booktype);
		em.refresh(booktype);
	}

	public void removeBooktype(Booktype booktype) {
		em.remove(em.merge(booktype));
		em.flush();
	}

	public List<Booktype> getBooktypes() {
		return em.createQuery("from Booktype", Booktype.class)
				.getResultList();
	}

	public Booktype getBooktype(Integer id) {
		return em.find(Booktype.class, id);
	}
}
