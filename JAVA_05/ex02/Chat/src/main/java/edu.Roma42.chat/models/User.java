package	edu.Roma42.chat.models;
import	java.util.List;
import	java.util.Objects;
import	java.util.ArrayList;

public class	User {
	private long			id;
	private String			login;
	private String			password;
	private	List<Chatroom>	ownedRooms;
	private List<Chatroom>	rooms;

	public	User(long id, String login, String password, List<Chatroom> ownedRooms, List<Chatroom> rooms) {
		this.id = id;
		this.login = login;
		this.password = password;
		if (ownedRooms == null) {
			this.ownedRooms = new ArrayList<Chatroom>();
		} else {
			this.ownedRooms = ownedRooms;
		}
		if (rooms == null) {
			this.rooms = new ArrayList<Chatroom>();
		} else {
			this.rooms = rooms;
		}
	}

	public long	getID() {
		return (this.id);
	}

	public String	getLogin() {
		return (this.login);
	}

	public String	getPassword() {
		return (this.password);
	}

	public List<Chatroom> getOwnedRooms() {
		return (this.ownedRooms);
	}

	public List<Chatroom> getRooms() {
		return (this.rooms);
	}

	@Override
	public String toString() {
		return ("{User: " + this.login + " (" + this.id + "), password: " + this.password +
				", created rooms: " + this.ownedRooms.size() + ", rooms: " + this.rooms.size() + "}");
	}

	@Override
	public boolean	equals(Object obj) {
		User	tmpUser;
		if (this == obj) {
			return (true);
		} else if (!(obj instanceof User)) {
			return false;
		}
		tmpUser = (User) obj;
		if (this.id == tmpUser.getID() && this.login.equals(tmpUser.getLogin()) && this.password.equals(tmpUser.getPassword()) &&
				this.ownedRooms.equals(tmpUser.getOwnedRooms()) && this.rooms.equals(tmpUser.getRooms())) {
			return (true);
		}
		return (false);
	}

	@Override
	public int hashCode() {
		return (Objects.hash(this.id, this.login, this.password));
	}

}	
