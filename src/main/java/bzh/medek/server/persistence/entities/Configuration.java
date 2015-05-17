package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONFIGURATION database table.
 * 
 */
@Entity
@Table(name="CONFIGURATION")
@NamedQuery(name="Configuration.findAll", query="SELECT c FROM Configuration c")
public class Configuration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PARAMETER", unique=true, nullable=false, length=20)
	private String parameter;

	@Column(name="VALUE", nullable=false, length=60)
	private String value;

	public Configuration() {
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}