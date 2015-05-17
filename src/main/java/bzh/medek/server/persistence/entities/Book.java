package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BOOK database table.
 * 
 */
@Entity
@Table(name="BOOK")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="BOOKNB")
	private Integer booknb;

	@Column(name="COVER", length=45)
	private String cover;

	@Column(name="DESCRIPTION", length=150)
	private String description;

	@Column(name="ISSERIEDONE")
	private Boolean isseriedone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUBLICATIONDATE")
	private Date publicationdate;

	@Column(name="SERIES", length=45)
	private String series;

	@Column(name="TITLE", nullable=false, length=45)
	private String title;

	//bi-directional many-to-one association to Booktype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE")
	private Booktype booktype;

	//bi-directional many-to-one association to Collection
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COLLECTION")
	private Collection collectionBean;

	//bi-directional many-to-one association to Editor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EDITOR")
	private Editor editorBean;

	//bi-directional many-to-one association to Lang
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LANG")
	private Lang langBean;

	//bi-directional many-to-one association to Storygenre
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GENRE")
	private Storygenre storygenre;

	//bi-directional many-to-one association to Bookartist
	@OneToMany(mappedBy="bookBean")
	private List<Bookartist> bookartists;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="bookBean")
	private List<Loan> loans;

	//bi-directional many-to-one association to Userbook
	@OneToMany(mappedBy="bookBean")
	private List<Userbook> userbooks;

	public Book() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBooknb() {
		return this.booknb;
	}

	public void setBooknb(Integer booknb) {
		this.booknb = booknb;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsseriedone() {
		return this.isseriedone;
	}

	public void setIsseriedone(Boolean isseriedone) {
		this.isseriedone = isseriedone;
	}

	public Date getPublicationdate() {
		return this.publicationdate;
	}

	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Booktype getBooktype() {
		return this.booktype;
	}

	public void setBooktype(Booktype booktype) {
		this.booktype = booktype;
	}

	public Collection getCollectionBean() {
		return this.collectionBean;
	}

	public void setCollectionBean(Collection collectionBean) {
		this.collectionBean = collectionBean;
	}

	public Editor getEditorBean() {
		return this.editorBean;
	}

	public void setEditorBean(Editor editorBean) {
		this.editorBean = editorBean;
	}

	public Lang getLangBean() {
		return this.langBean;
	}

	public void setLangBean(Lang langBean) {
		this.langBean = langBean;
	}

	public Storygenre getStorygenre() {
		return this.storygenre;
	}

	public void setStorygenre(Storygenre storygenre) {
		this.storygenre = storygenre;
	}

	public List<Bookartist> getBookartists() {
		return this.bookartists;
	}

	public void setBookartists(List<Bookartist> bookartists) {
		this.bookartists = bookartists;
	}

	public Bookartist addBookartist(Bookartist bookartist) {
		getBookartists().add(bookartist);
		bookartist.setBookBean(this);

		return bookartist;
	}

	public Bookartist removeBookartist(Bookartist bookartist) {
		getBookartists().remove(bookartist);
		bookartist.setBookBean(null);

		return bookartist;
	}

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setBookBean(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setBookBean(null);

		return loan;
	}

	public List<Userbook> getUserbooks() {
		return this.userbooks;
	}

	public void setUserbooks(List<Userbook> userbooks) {
		this.userbooks = userbooks;
	}

	public Userbook addUserbook(Userbook userbook) {
		getUserbooks().add(userbook);
		userbook.setBookBean(this);

		return userbook;
	}

	public Userbook removeUserbook(Userbook userbook) {
		getUserbooks().remove(userbook);
		userbook.setBookBean(null);

		return userbook;
	}

}