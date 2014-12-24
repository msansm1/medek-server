package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ALBUM database table.
 * 
 */
@Entity
@Table(name="ALBUM")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="COVER", length=45)
	private String cover;

	@Column(name="ISSIGNED", nullable=false)
	private Boolean issigned;

	@Column(name="NBTRACKS")
	private Integer nbtracks;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RELEASEDATE")
	private Date releasedate;

	@Column(name="TITLE", nullable=false, length=45)
	private String title;

	//bi-directional many-to-one association to Genre
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GENRE")
	private Genre genreBean;

	//bi-directional many-to-one association to Support
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUPPORT")
	private Support supportBean;

	//bi-directional many-to-one association to Albumartist
	@OneToMany(mappedBy="albumBean")
	private List<Albumartist> albumartists;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="albumBean")
	private List<Loan> loans;

	//bi-directional many-to-one association to Track
	@OneToMany(mappedBy="albumBean")
	private List<Track> tracks;

	//bi-directional many-to-one association to Useralbum
	@OneToMany(mappedBy="albumBean")
	private List<Useralbum> useralbums;

	public Album() {
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

	public Boolean getIssigned() {
		return this.issigned;
	}

	public void setIssigned(Boolean issigned) {
		this.issigned = issigned;
	}

	public Integer getNbtracks() {
		return this.nbtracks;
	}

	public void setNbtracks(Integer nbtracks) {
		this.nbtracks = nbtracks;
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

	public Genre getGenreBean() {
		return this.genreBean;
	}

	public void setGenreBean(Genre genreBean) {
		this.genreBean = genreBean;
	}

	public Support getSupportBean() {
		return this.supportBean;
	}

	public void setSupportBean(Support supportBean) {
		this.supportBean = supportBean;
	}

	public List<Albumartist> getAlbumartists() {
		return this.albumartists;
	}

	public void setAlbumartists(List<Albumartist> albumartists) {
		this.albumartists = albumartists;
	}

	public Albumartist addAlbumartist(Albumartist albumartist) {
		getAlbumartists().add(albumartist);
		albumartist.setAlbumBean(this);

		return albumartist;
	}

	public Albumartist removeAlbumartist(Albumartist albumartist) {
		getAlbumartists().remove(albumartist);
		albumartist.setAlbumBean(null);

		return albumartist;
	}

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setAlbumBean(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setAlbumBean(null);

		return loan;
	}

	public List<Track> getTracks() {
		return this.tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public Track addTrack(Track track) {
		getTracks().add(track);
		track.setAlbumBean(this);

		return track;
	}

	public Track removeTrack(Track track) {
		getTracks().remove(track);
		track.setAlbumBean(null);

		return track;
	}

	public List<Useralbum> getUseralbums() {
		return this.useralbums;
	}

	public void setUseralbums(List<Useralbum> useralbums) {
		this.useralbums = useralbums;
	}

	public Useralbum addUseralbum(Useralbum useralbum) {
		getUseralbums().add(useralbum);
		useralbum.setAlbumBean(this);

		return useralbum;
	}

	public Useralbum removeUseralbum(Useralbum useralbum) {
		getUseralbums().remove(useralbum);
		useralbum.setAlbumBean(null);

		return useralbum;
	}

}