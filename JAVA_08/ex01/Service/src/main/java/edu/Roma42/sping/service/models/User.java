package	edu.Roma42.spring.service.models;
import	java.util.StringJoiner;
import	java.util.Objects;

public class	User {
	private	Long	id;
	private String	email;

	public	User() {}

	public	User(Long id, String email) {
		this.id = id;
		this.email = email;
	}

	public Long	getId() {
		return (this.id);
	}

	public String	getEmail() {
		return (this.email);
	}

	public void	setId(Long id) {
		this.id = id;
	}

	public void	setEmail(String email) {
		this.email = email;
	}

	@Override
	public String	toString() {
		StringJoiner	sj;

		sj = new StringJoiner(", ", "User{", "}");
		sj.add("id = " + this.id);
		sj.add("email = " + this.email);
		return (sj.toString());
	}

	@Override
	public boolean	equals(Object obj) {
		User	tmpUser;
		if (this == obj) {
			return (true);
		} else if (!(obj instanceof User)) {
			return (false);
		}
		tmpUser = (User) obj;
		return (this.id.equals(tmpUser.getId()) && this.email.equals(tmpUser.getEmail()));
	}
	
	@Override
	public int	hashCode() {
		return (Objects.hash(this.id, this.email));
	}
}
