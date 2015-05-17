package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TRACK database table.
 * 
 */
@Entity
@Table(name="TRACK")
@NamedQuery(name="Track.findAll", query="SELECT t FROM Track t")
public class Track implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="LENGTH", length=45)
	private String length;

	@Column(name="NUMBER")
	private Integer number;

	@Column(name="TITLE", nullable=false, length=45)
	private String title;

	//bi-directional many-to-one association to Album
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ALBUM", nullable=false)
	private Album albumBean;

	//bi-directional many-to-one association to Trackartist
	@OneToMany(mappedBy="trackBean")
	private List<Trackartist> trackartists;

	public Track() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Album getAlbumBean() {
		return this.albumBean;
	}

	public void setAlbumBean(Album albumBean) {
		this.albumBean = albumBean;
	}

	public List<Trackartist> getTrackartists() {
		return this.trackartists;
	}

	public void setTrackartists(List<Trackartist> trackartists) {
		this.trackartists = trackartists;
	}

	public Trackartist addTrackartist(Trackartist trackartist) {
		getTrackartists().add(trackartist);
		trackartist.setTrackBean(this);

		return trackartist;
	}

	public Trackartist removeTrackartist(Trackartist trackartist) {
		getTrackartists().remove(trackartist);
		trackartist.setTrackBean(null);

		return trackartist;
	}

}