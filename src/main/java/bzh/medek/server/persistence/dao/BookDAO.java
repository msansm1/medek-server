package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.book.JsonBook;
import bzh.medek.server.json.home.BookStats;
import bzh.medek.server.persistence.entities.Book;

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
}
