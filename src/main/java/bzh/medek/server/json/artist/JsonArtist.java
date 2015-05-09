package bzh.medek.server.json.artist;

public class JsonArtist {
	private Integer id;
	private String name;
	private String firstname;
	private String type;
	private Integer typeId;
	private String nationality;
	private String biolink;
	
	public JsonArtist() {
		super();
	}

	public JsonArtist(Integer id, String name, String firstname, String type,
			Integer typeId, String nationality, String biolink) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.type = type;
		this.typeId = typeId;
		this.nationality = nationality;
		this.biolink = biolink;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBiolink() {
		return biolink;
	}

	public void setBiolink(String biolink) {
		this.biolink = biolink;
	}
	
}
