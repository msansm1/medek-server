package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Artisttype;

/**
 * DAO for ARTISTTYPE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class ArtisttypeDAO extends Dao {

	public void updateArtisttype(Artisttype artisttype) {
		em.merge(artisttype);
	}

	public void saveArtisttype(Artisttype artisttype) {
		em.persist(artisttype);
		em.refresh(artisttype);
	}

	public void removeArtisttype(Artisttype artisttype) {
		em.remove(em.merge(artisttype));
		em.flush();
	}

	public List<Artisttype> getArtisttypes() {
		return em.createQuery("from Artisttype", Artisttype.class)
				.getResultList();
	}

	public Artisttype getArtisttype(Integer id) {
		return em.find(Artisttype.class, id);
	}
}
