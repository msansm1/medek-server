package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COLLECTION database table.
 * 
 */
@Entity
@Table(name="COLLECTION")
@NamedQuery(name="Collection.findAll", query="SELECT c FROM Collection c")
public class Collection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="collectionBean")
	private List<Book> books;

	public Collection() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
		book.setCollectionBean(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setCollectionBean(null);

		return book;
	}

}