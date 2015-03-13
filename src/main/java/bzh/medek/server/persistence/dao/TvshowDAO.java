package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.persistence.entities.Tvshow;

/**
 * DAO for TVSHOW table
 * 
 * @author msansm1
 * 
 */
@Stateless
public class TvshowDAO extends Dao {

	public void updateTvshow(Tvshow tvshow) {
		em.merge(tvshow);
	}

	public void saveTvshow(Tvshow tvshow) {
		em.persist(tvshow);
		em.refresh(tvshow);
	}

	public void removeTvshow(Tvshow tvshow) {
		em.remove(em.merge(tvshow));
		em.flush();
	}

	public List<Tvshow> getTvshows() {
		return em.createQuery("from Tvshow", Tvshow.class).getResultList();
	}

	public Tvshow getTvshow(Integer id) {
		return em.find(Tvshow.class, id);
	}

	public List<JsonShow> getUsersTvshows(Integer id) {
		TypedQuery<JsonShow> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.tvshow.JsonShow(tv.id, tv.title, "
						+ "tv.cover, tv.description)"
						+ "from Tvshow tv, Usertv ut, User u "
						+ "where tv.id=ut.id.tvshow and ut.id.user=u.id and u.id=:param1",
				JsonShow.class);
		q.setParameter("param1", id);
		return q.getResultList();
	}
}
