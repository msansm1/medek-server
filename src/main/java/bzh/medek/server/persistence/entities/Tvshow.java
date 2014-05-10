package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tvshow database table.
 * 
 */
@Entity
@Table(name="TVSHOW")
@NamedQuery(name="Tvshow.findAll", query="SELECT t FROM Tvshow t")
public class Tvshow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String cover;

	@Column(length=150)
	private String description;

	private Boolean isseriedone;

	@Column(length=5)
	private String length;

	@Temporal(TemporalType.TIMESTAMP)
	private Date releasedate;

	private Integer season;

	@Column(length=45)
	private String series;

	@Column(nullable=false, length=45)
	private String title;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="tvshowBean", fetch=FetchType.EAGER)
	private List<Loan> loans;

	//bi-directional many-to-one association to Tvartist
	@OneToMany(mappedBy="tvshowBean", fetch=FetchType.EAGER)
	private List<Tvartist> tvartists;

	//bi-directional many-to-many association to Lang
	@ManyToMany(mappedBy="tvshows1", fetch=FetchType.EAGER)
	private List<Lang> langs1;

	//bi-directional many-to-one association to Support
	@ManyToOne
	@JoinColumn(name="SUPPORT")
	private Support supportBean;

	//bi-directional many-to-one association to Storygenre
	@ManyToOne
	@JoinColumn(name="GENRE")
	private Storygenre storygenre;

	//bi-directional many-to-many association to Lang
	@ManyToMany(mappedBy="tvshows2", fetch=FetchType.EAGER)
	private List<Lang> langs2;

	//bi-directional many-to-one association to Usertv
	@OneToMany(mappedBy="tvshowBean", fetch=FetchType.EAGER)
	private List<Usertv> usertvs;

	public Tvshow() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public Boolean getIsseriedone() {
		return this.isseriedone;
	}

	public void setIsseriedone(Boolean isseriedone) {
		this.isseriedone = isseriedone;
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

	public Integer getSeason() {
		return this.season;
	}

	public void setSeason(Integer season) {
		this.season = season;
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

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setTvshowBean(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setTvshowBean(null);

		return loan;
	}

	public List<Tvartist> getTvartists() {
		return this.tvartists;
	}

	public void setTvartists(List<Tvartist> tvartists) {
		this.tvartists = tvartists;
	}

	public Tvartist addTvartist(Tvartist tvartist) {
		getTvartists().add(tvartist);
		tvartist.setTvshowBean(this);

		return tvartist;
	}

	public Tvartist removeTvartist(Tvartist tvartist) {
		getTvartists().remove(tvartist);
		tvartist.setTvshowBean(null);

		return tvartist;
	}

	public List<Lang> getLangs1() {
		return this.langs1;
	}

	public void setLangs1(List<Lang> langs1) {
		this.langs1 = langs1;
	}

	public Support getSupportBean() {
		return this.supportBean;
	}

	public void setSupportBean(Support supportBean) {
		this.supportBean = supportBean;
	}

	public Storygenre getStorygenre() {
		return this.storygenre;
	}

	public void setStorygenre(Storygenre storygenre) {
		this.storygenre = storygenre;
	}

	public List<Lang> getLangs2() {
		return this.langs2;
	}

	public void setLangs2(List<Lang> langs2) {
		this.langs2 = langs2;
	}

	public List<Usertv> getUsertvs() {
		return this.usertvs;
	}

	public void setUsertvs(List<Usertv> usertvs) {
		this.usertvs = usertvs;
	}

	public Usertv addUsertv(Usertv usertv) {
		getUsertvs().add(usertv);
		usertv.setTvshowBean(this);

		return usertv;
	}

	public Usertv removeUsertv(Usertv usertv) {
		getUsertvs().remove(usertv);
		usertv.setTvshowBean(null);

		return usertv;
	}

}