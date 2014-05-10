package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Storygenre;

/**
 * DAO for STORYGENRE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class StorygenreDAO extends Dao {

	public void updateStorygenre(Storygenre storygenre) {
		em.merge(storygenre);
	}

	public void saveStorygenre(Storygenre storygenre) {
		em.persist(storygenre);
		em.refresh(storygenre);
	}

	public void removeStorygenre(Storygenre storygenre) {
		em.remove(em.merge(storygenre));
		em.flush();
	}

	public List<Storygenre> getStorygenres() {
		return em.createQuery("from Storygenre", Storygenre.class)
				.getResultList();
	}

	public Storygenre getStorygenre(Integer id) {
		return em.find(Storygenre.class, id);
	}
}
