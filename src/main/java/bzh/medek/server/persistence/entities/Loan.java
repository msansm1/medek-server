package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the loan database table.
 * 
 */
@Entity
@Table(name="LOAN")
@NamedQuery(name="Loan.findAll", query="SELECT l FROM Loan l")
public class Loan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;

	@Column(length=45)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date startdate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER", nullable=false)
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="FRIEND")
	private User user2;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="BOOK")
	private Book bookBean;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="MOVIE")
	private Movie movieBean;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="ALBUM")
	private Album albumBean;

	//bi-directional many-to-one association to Tvshow
	@ManyToOne
	@JoinColumn(name="TVSHOW")
	private Tvshow tvshowBean;

	public Loan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public Book getBookBean() {
		return this.bookBean;
	}

	public void setBookBean(Book bookBean) {
		this.bookBean = bookBean;
	}

	public Movie getMovieBean() {
		return this.movieBean;
	}

	public void setMovieBean(Movie movieBean) {
		this.movieBean = movieBean;
	}

	public Album getAlbumBean() {
		return this.albumBean;
	}

	public void setAlbumBean(Album albumBean) {
		this.albumBean = albumBean;
	}

	public Tvshow getTvshowBean() {
		return this.tvshowBean;
	}

	public void setTvshowBean(Tvshow tvshowBean) {
		this.tvshowBean = tvshowBean;
	}

}