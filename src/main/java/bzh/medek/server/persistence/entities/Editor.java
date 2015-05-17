package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EDITOR database table.
 * 
 */
@Entity
@Table(name="EDITOR")
@NamedQuery(name="Editor.findAll", query="SELECT e FROM Editor e")
public class Editor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="editorBean")
	private List<Book> books;

	public Editor() {
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
		book.setEditorBean(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setEditorBean(null);

		return book;
	}

}