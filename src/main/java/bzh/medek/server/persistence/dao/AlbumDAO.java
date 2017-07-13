package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.home.AlbumStats;
import bzh.medek.server.persistence.entities.Album;
import bzh.medek.server.persistence.entities.Albumartist;

/**
 * DAO for ALBUM table
 * 
 * @author msansm1
 * 
 */
@Stateless
public class AlbumDAO extends Dao {

    public void updateAlbum(Album album) {
        em.merge(album);
    }

    public void saveAlbum(Album album) {
        em.persist(album);
        em.refresh(album);
    }

    public void removeAlbum(Album album) {
        em.remove(em.merge(album));
        em.flush();
    }

    public List<Album> getAlbums() {
        return em.createQuery("from Album", Album.class).getResultList();
    }

    public Album getAlbum(Integer id) {
        return em.find(Album.class, id);
    }

    public List<JsonAlbum> getUsersAlbums(Integer id) {
        TypedQuery<JsonAlbum> q = em.createQuery("SELECT NEW bzh.medek.server.json.album.JsonAlbum(a.id, a.title, "
                + "a.cover)" + "from Album a, Useralbum ua, User u "
                + "where a.id=ua.id.album and ua.id.user=u.id and u.id=:param1", JsonAlbum.class);
        q.setParameter("param1", id);
        return q.getResultList();
    }

    public List<Album> getAlbumsForList(int index, int limit, String orderBy, String orderDir) {
        String dir = "DESC";
        if (orderDir != null) {
            dir = orderDir;
        }
        return em.createQuery("FROM Album ORDER BY " + orderBy + " " + dir, Album.class).setFirstResult(index)
                .setMaxResults(limit).getResultList();
    }

    public List<JsonAlbum> getUserAlbumsForList(int index, int limit, String orderBy, String orderDir, Integer userId) {
        String dir = "DESC";
        if (orderDir != null) {
            dir = orderDir;
        }
        return em
                .createQuery("SELECT NEW bzh.medek.server.json.album.JsonAlbum(a.id, a.title, "
                        + "a.cover, a.releasedate, a.genreBean.name, a.genreBean.id, "
                        + "a.supportBean.name, a.supportBean.id, a.nbtracks, true, ua.rating, ua.issigned) "
                        + "FROM Album a INNER JOIN a.useralbums ua " + "where ua.id.user=:param1 ORDER BY " + orderBy
                        + " " + dir, JsonAlbum.class)
                .setParameter("param1", userId).setFirstResult(index).setMaxResults(limit).getResultList();
    }

    public AlbumStats getUserStats(Integer userId) {
        TypedQuery<AlbumStats> q = em
                .createQuery("SELECT NEW bzh.medek.server.json.home.AlbumStats(COUNT(ua.albumBean.id))"
                        + "from Useralbum ua " + "where ua.id.user=:userId", AlbumStats.class);
        q.setParameter("userId", userId);
        return q.getSingleResult();
    }

    public List<Albumartist> getAlbumArtists(Integer albumId) {
        return em.createQuery("from Albumartist aa where aa.id.album=:param1", Albumartist.class)
                .setParameter("param1", albumId).getResultList();
    }
}
