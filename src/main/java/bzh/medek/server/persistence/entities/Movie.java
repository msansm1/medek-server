package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MOVIE database table.
 * 
 */
@Entity
@Table(name="MOVIE")
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="COVER", length=45)
	private String cover;

	@Column(name="DESCRIPTION", length=150)
	private String description;

	@Column(name="ISCOLLECTOR", nullable=false)
	private Boolean iscollector;

	@Column(name="LENGTH", length=5)
	private String length;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RELEASEDATE")
	private Date releasedate;

	@Column(name="TITLE", nullable=false, length=45)
	private String title;

	//bi-directional many-to-many association to Lang
	@ManyToMany(mappedBy="movies1")
	private List<Lang> langs1;

	//bi-directional many-to-many association to Lang
	@ManyToMany(mappedBy="movies2")
	private List<Lang> langs2;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="movieBean")
	private List<Loan> loans;

	//bi-directional many-to-many association to Lang
	@ManyToMany
	@JoinTable(
		name="MOVIELANG"
		, joinColumns={
			@JoinColumn(name="MOVIE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="LANG", nullable=false)
			}
		)
	private List<Lang> langs3;

	//bi-directional many-to-one association to Storygenre
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GENRE")
	private Storygenre storygenre;

	//bi-directional many-to-one association to Support
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUPPORT")
	private Support supportBean;

	//bi-directional many-to-one association to Movieartist
	@OneToMany(mappedBy="movieBean")
	private List<Movieartist> movieartists;

	//bi-directional many-to-one association to Usermovie
	@OneToMany(mappedBy="movieBean")
	private List<Usermovie> usermovies;

	public Movie() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getIscollector() {
		return this.iscollector;
	}

	public void setIscollector(Boolean iscollector) {
		this.iscollector = iscollector;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Date getReleasedate() {
		return this.releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Lang> getLangs1() {
		return this.langs1;
	}

	public void setLangs1(List<Lang> langs1) {
		this.langs1 = langs1;
	}

	public List<Lang> getLangs2() {
		return this.langs2;
	}

	public void setLangs2(List<Lang> langs2) {
		this.langs2 = langs2;
	}

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setMovieBean(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setMovieBean(null);

		return loan;
	}

	public List<Lang> getLangs3() {
		return this.langs3;
	}

	public void setLangs3(List<Lang> langs3) {
		this.langs3 = langs3;
	}

	public Storygenre getStorygenre() {
		return this.storygenre;
	}

	public void setStorygenre(Storygenre storygenre) {
		this.storygenre = storygenre;
	}

	public Support getSupportBean() {
		return this.supportBean;
	}

	public void setSupportBean(Support supportBean) {
		this.supportBean = supportBean;
	}

	public List<Movieartist> getMovieartists() {
		return this.movieartists;
	}

	public void setMovieartists(List<Movieartist> movieartists) {
		this.movieartists = movieartists;
	}

	public Movieartist addMovieartist(Movieartist movieartist) {
		getMovieartists().add(movieartist);
		movieartist.setMovieBean(this);

		return movieartist;
	}

	public Movieartist removeMovieartist(Movieartist movieartist) {
		getMovieartists().remove(movieartist);
		movieartist.setMovieBean(null);

		return movieartist;
	}

	public List<Usermovie> getUsermovies() {
		return this.usermovies;
	}

	public void setUsermovies(List<Usermovie> usermovies) {
		this.usermovies = usermovies;
	}

	public Usermovie addUsermovy(Usermovie usermovy) {
		getUsermovies().add(usermovy);
		usermovy.setMovieBean(this);

		return usermovy;
	}

	public Usermovie removeUsermovy(Usermovie usermovy) {
		getUsermovies().remove(usermovy);
		usermovy.setMovieBean(null);

		return usermovy;
	}

}