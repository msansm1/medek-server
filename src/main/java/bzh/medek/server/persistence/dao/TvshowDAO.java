package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.home.SerieStats;
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

	public List<Tvshow> getTvshowsForList(int index, int limit, String orderBy,
			String orderDir) {
		String dir = "DESC";
		if (orderDir != null) {
			dir = orderDir;
		}
		return em.createQuery("FROM Tvshow ORDER BY "+orderBy+" "+dir, Tvshow.class)
				.setFirstResult(index)
				.setMaxResults(limit)
				.getResultList();
	}

	public SerieStats getUserStats(Integer userId) {
		TypedQuery<SerieStats> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.home.SerieStats(COUNT(ut.tvshowBean.id))"
						+ "from Usertv ut "
						+ "where ut.id.user=:userId",
						SerieStats.class);
		q.setParameter("userId", userId);
		return q.getSingleResult();
	}

	public List<JsonShow> getUserTvshows(int index, int limit, String orderBy,
			String orderDir, Integer userId) {
		String dir = "DESC";
		if (orderDir != null) {
			dir = orderDir;
		}
		return em.createQuery("SELECT NEW bzh.medek.server.json.tvshow.JsonShow(t.id, t.title, "
				+ "t.description, t.releasedate, t.cover, t.supportBean.name, t.supportBean.id, "
				+ "t.storygenre.name, t.storygenre.id, t.length, t.season, t.series, "
				+ "t.isseriedone, true, ut.rating) "
				+ "FROM Tvshow t INNER JOIN t.usertvs ut "
				+ "where ut.id.user=:param1 ORDER BY "+orderBy+" "+dir, JsonShow.class)
				.setParameter("param1", userId)
				.setFirstResult(index)
				.setMaxResults(limit)
				.getResultList();
	}
}
