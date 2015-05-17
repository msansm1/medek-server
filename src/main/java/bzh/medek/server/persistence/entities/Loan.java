package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the LOAN database table.
 * 
 */
@Entity
@Table(name="LOAN")
@NamedQuery(name="Loan.findAll", query="SELECT l FROM Loan l")
public class Loan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENDDATE")
	private Date enddate;

	@Column(name="NAME", length=45)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="STARTDATE", nullable=false)
	private Date startdate;

	//bi-directional many-to-one association to Album
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ALBUM")
	private Album albumBean;

	//bi-directional many-to-one association to Book
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BOOK")
	private Book bookBean;

	//bi-directional many-to-one association to Movie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MOVIE")
	private Movie movieBean;

	//bi-directional many-to-one association to Tvshow
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TVSHOW")
	private Tvshow tvshowBean;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FRIEND", nullable=false)
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", nullable=false)
	private User user2;

	public Loan() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public Album getAlbumBean() {
		return this.albumBean;
	}

	public void setAlbumBean(Album albumBean) {
		this.albumBean = albumBean;
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

	public Tvshow getTvshowBean() {
		return this.tvshowBean;
	}

	public void setTvshowBean(Tvshow tvshowBean) {
		this.tvshowBean = tvshowBean;
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

}