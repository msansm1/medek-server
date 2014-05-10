package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

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
}
