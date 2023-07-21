package	edu.Roma42.models;
import	java.util.Objects;

public class	User {
	private Long	id;
	private String	login;
	private String	password;
	private boolean	authentication;

	public	User(Long id, String login, String password, boolean authentication) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.authentication = authentication;
	}

	public Long	getID() {
		return (this.id);
	}

	public String	getLogin() {
		return (this.login);
	}

	public String	getPassword() {
		return (this.password);
	}

	public boolean	getAuthentication() {
		return (this.authentication);
	}

	public void	setID(Long id) {
		this.id = id;
	}

	public void	setLogin(String login) {
		this.login = login;
	}

	public void	setPassword(String password) {
		this.password = password;
	}

	public void	setAuthentication(boolean authentication) {
		this.authentication = authentication;
	}

	@Override
	public String	toString() {
		return ("User: " + this.login + " (" + this.id + "), password: "
				+ this.password + (this.authentication ? " online" : " offline"));
	}

	@Override
	public boolean	equals(Object obj) {
		User	tmpUser;

		if (this == obj) {
			return (true);
		} else if (!(obj instanceof User)){
			return (false);
		}
		tmpUser = (User) obj;
		if (this.id.equals(tmpUser.getID()) && this.login.equals(tmpUser.getLogin())
				&& this.password.equals(tmpUser.getPassword())) {
			if (this.authentication && tmpUser.getAuthentication()) {
				return (true);
			} else if (!this.authentication && !tmpUser.getAuthentication()) {
				return (true);
			}
		}
		return (false);
	}

	@Override
	public int	hashCode() {
		return (Objects.hash(this.id, this.login, this.password, this.authentication));
	}
}
