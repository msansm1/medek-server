package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BOOKTYPE database table.
 * 
 */
@Entity
@Table(name="BOOKTYPE")
@NamedQuery(name="Booktype.findAll", query="SELECT b FROM Booktype b")
public class Booktype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private int id;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="booktype", fetch=FetchType.EAGER)
	private List<Book> books;

	public Booktype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setBooktype(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setBooktype(null);

		return book;
	}

}