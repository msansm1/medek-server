package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.book.JsonBook;
import bzh.medek.server.json.home.BookStats;
import bzh.medek.server.persistence.entities.Book;
import bzh.medek.server.persistence.entities.Bookartist;

/**
 * DAO for BOOK table
 * 
 * @author msansm1
 *
 */
@Stateless
public class BookDAO extends Dao {

	public void updateBook(Book book) {
		em.merge(book);
	}

	public void saveBook(Book book) {
		em.persist(book);
		em.refresh(book);
	}

	public void removeBook(Book book) {
		em.remove(em.merge(book));
		em.flush();
	}

	public List<Book> getBooks() {
		return em.createQuery("from Book", Book.class)
				.getResultList();
	}

	public Book getBook(Integer id) {
		return em.find(Book.class, id);
	}

	public List<JsonBook> getUsersBooks(Integer id) {
		TypedQuery<JsonBook> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.book.JsonBook(b.id, b.title)"
						+ "from Book b, Userbook ub, User u "
						+ "where b.id=ub.id.book and ub.id.user=u.id and u.id=:param1",
						JsonBook.class);
		q.setParameter("param1", id);
		return q.getResultList();
	}

	public List<Book> getBooksForList(int index, int limit, String orderBy,
			String orderDir) {
		String dir = "DESC";
		if (orderDir != null) {
			dir = orderDir;
		}
		return em.createQuery("FROM Book ORDER BY "+orderBy+" "+dir, Book.class)
				.setFirstResult(index)
				.setMaxResults(limit)
				.getResultList();
	}

	public BookStats getUserStats(Integer userId) {
		TypedQuery<BookStats> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.home.BookStats(COUNT(ub.bookBean.id))"
						+ "from Userbook ub "
						+ "where ub.id.user=:userId",
						BookStats.class);
		q.setParameter("userId", userId);
		return q.getSingleResult();
	}

	public List<JsonBook> getUserBooksForList(int index, int limit,
			String orderBy, String orderDir, Integer userId) {
		String dir = "DESC";
		if (orderDir != null) {
			dir = orderDir;
		}
		return em.createQuery("SELECT NEW bzh.medek.server.json.book.JsonBook(b.id, b.title, "
				+ "b.editorBean.name, b.editorBean.id, b.collectionBean.name, b.collectionBean.id, "
				+ "b.cover, b.description, b.publicationdate, b.storygenre.name, b.storygenre.id, "
				+ "b.booktype.name, b.booktype.id, b.langBean.name, b.langBean.id, "
				+ "b.series, b.booknb, b.isseriedone, "
				+ "true, ub.rating, ub.issigned) "
				+ "FROM Book b INNER JOIN b.userbooks ub "
				+ "where ub.id.user=:param1 ORDER BY "+orderBy+" "+dir, JsonBook.class)
				.setParameter("param1", userId)
				.setFirstResult(index)
				.setMaxResults(limit)
				.getResultList();
	}

	public List<Bookartist> getBookArtists(Integer bookId) {
		return em.createQuery("from Bookartist ba where ba.id.book=:param1", Bookartist.class)
				.setParameter("param1", bookId)
				.getResultList();
	}
}
