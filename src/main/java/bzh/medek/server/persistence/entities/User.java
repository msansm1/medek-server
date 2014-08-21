package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private int id;

	@Column(name="EMAIL", length=45)
	private String email;

	@Column(name="FIRSTNAME", length=45)
	private String firstname;

	@Column(name="LASTNAME", length=45)
	private String lastname;

	@Column(name="LOGIN", nullable=false, length=15)
	private String login;

	@Column(name="PASSWORD", nullable=false, length=45)
	private String password;

	//bi-directional many-to-one association to Friend
	@OneToMany(mappedBy="user1", fetch=FetchType.EAGER)
	private List<Friend> friends1;

	//bi-directional many-to-one association to Friend
	@OneToMany(mappedBy="user2", fetch=FetchType.EAGER)
	private List<Friend> friends2;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="user1", fetch=FetchType.EAGER)
	private List<Loan> loans1;

	//bi-directional many-to-one association to Loan
	@OneToMany(mappedBy="user2", fetch=FetchType.EAGER)
	private List<Loan> loans2;

	//bi-directional many-to-one association to Useralbum
	@OneToMany(mappedBy="userBean", fetch=FetchType.EAGER)
	private List<Useralbum> useralbums;

	//bi-directional many-to-one association to Userbook
	@OneToMany(mappedBy="userBean", fetch=FetchType.EAGER)
	private List<Userbook> userbooks;

	//bi-directional many-to-one association to Usermovie
	@OneToMany(mappedBy="userBean", fetch=FetchType.EAGER)
	private List<Usermovie> usermovies;

	//bi-directional many-to-one association to Usertv
	@OneToMany(mappedBy="userBean", fetch=FetchType.EAGER)
	private List<Usertv> usertvs;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Friend> getFriends1() {
		return this.friends1;
	}

	public void setFriends1(List<Friend> friends1) {
		this.friends1 = friends1;
	}

	public Friend addFriends1(Friend friends1) {
		getFriends1().add(friends1);
		friends1.setUser1(this);

		return friends1;
	}

	public Friend removeFriends1(Friend friends1) {
		getFriends1().remove(friends1);
		friends1.setUser1(null);

		return friends1;
	}

	public List<Friend> getFriends2() {
		return this.friends2;
	}

	public void setFriends2(List<Friend> friends2) {
		this.friends2 = friends2;
	}

	public Friend addFriends2(Friend friends2) {
		getFriends2().add(friends2);
		friends2.setUser2(this);

		return friends2;
	}

	public Friend removeFriends2(Friend friends2) {
		getFriends2().remove(friends2);
		friends2.setUser2(null);

		return friends2;
	}

	public List<Loan> getLoans1() {
		return this.loans1;
	}

	public void setLoans1(List<Loan> loans1) {
		this.loans1 = loans1;
	}

	public Loan addLoans1(Loan loans1) {
		getLoans1().add(loans1);
		loans1.setUser1(this);

		return loans1;
	}

	public Loan removeLoans1(Loan loans1) {
		getLoans1().remove(loans1);
		loans1.setUser1(null);

		return loans1;
	}

	public List<Loan> getLoans2() {
		return this.loans2;
	}

	public void setLoans2(List<Loan> loans2) {
		this.loans2 = loans2;
	}

	public Loan addLoans2(Loan loans2) {
		getLoans2().add(loans2);
		loans2.setUser2(this);

		return loans2;
	}

	public Loan removeLoans2(Loan loans2) {
		getLoans2().remove(loans2);
		loans2.setUser2(null);

		return loans2;
	}

	public List<Useralbum> getUseralbums() {
		return this.useralbums;
	}

	public void setUseralbums(List<Useralbum> useralbums) {
		this.useralbums = useralbums;
	}

	public Useralbum addUseralbum(Useralbum useralbum) {
		getUseralbums().add(useralbum);
		useralbum.setUserBean(this);

		return useralbum;
	}

	public Useralbum removeUseralbum(Useralbum useralbum) {
		getUseralbums().remove(useralbum);
		useralbum.setUserBean(null);

		return useralbum;
	}

	public List<Userbook> getUserbooks() {
		return this.userbooks;
	}

	public void setUserbooks(List<Userbook> userbooks) {
		this.userbooks = userbooks;
	}

	public Userbook addUserbook(Userbook userbook) {
		getUserbooks().add(userbook);
		userbook.setUserBean(this);

		return userbook;
	}

	public Userbook removeUserbook(Userbook userbook) {
		getUserbooks().remove(userbook);
		userbook.setUserBean(null);

		return userbook;
	}

	public List<Usermovie> getUsermovies() {
		return this.usermovies;
	}

	public void setUsermovies(List<Usermovie> usermovies) {
		this.usermovies = usermovies;
	}

	public Usermovie addUsermovy(Usermovie usermovy) {
		getUsermovies().add(usermovy);
		usermovy.setUserBean(this);

		return usermovy;
	}

	public Usermovie removeUsermovy(Usermovie usermovy) {
		getUsermovies().remove(usermovy);
		usermovy.setUserBean(null);

		return usermovy;
	}

	public List<Usertv> getUsertvs() {
		return this.usertvs;
	}

	public void setUsertvs(List<Usertv> usertvs) {
		this.usertvs = usertvs;
	}

	public Usertv addUsertv(Usertv usertv) {
		getUsertvs().add(usertv);
		usertv.setUserBean(this);

		return usertv;
	}

	public Usertv removeUsertv(Usertv usertv) {
		getUsertvs().remove(usertv);
		usertv.setUserBean(null);

		return usertv;
	}

}