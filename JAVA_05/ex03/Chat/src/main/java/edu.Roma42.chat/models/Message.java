package	edu.Roma42.chat.models;
import	edu.Roma42.chat.exceptions.WrongParameterException;
import	java.util.List;
import	java.util.Objects;

public class	Message {
	private long		id;
	private User		author;
	private Chatroom	room;
	private String		text;
	private String		time;

	public	Message(long id, User author, Chatroom room, String text, String time) {
		this.id = id;
		this.author = author;
		this.room = room;
		this.text = text;
		this.time = time;
	}

	public	Message(String id, User author, Chatroom room, String text, String time) throws WrongParameterException {
		if (id == null) {
			this.id = -1;
		} else {
			throw new WrongParameterException(id + " isn't a valid id");
		}
		this.author = author;
		this.room = room;
		this.text = text;
		this.time = time;
	}

	public long	getID() {
		return (this.id);
	}

	public User	getAuthor() {
		return (this.author);
	}

	public Chatroom	getRoom() {
		return (this.room);
	}

	public String	getText() {
		if (this.text != null) {
			return ("'" + this.text + "'");
		}
		return (this.text);
	}

	public String	getTime() {
		if (this.time != null) {
			return ("'" + this.time + "'");
		}
		return (this.time);
	}

	public void	setID(long id) {
		this.id = id;
	}

	public void	setAuthor(User author) {
		this.author = author;
	}

	public void	setRoom(Chatroom room) {
		this.room = room;
	}

	public void	setText(String text) {
		this.text = text;
	}

	public void	setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		if (this.id == -1) {
			return ("{Message: " + this.text + " (null), from: " + this.author + " in chatroom: " + this.room + "}");
		} else {
			return ("{Message: " + this.text + " (" + this.id + "), from: " + this.author + " in chatroom: " + this.room + "}");
		}
	}

	@Override
	public boolean	equals(Object obj) {
		Message	tmpMessage;
		if (this == obj) {
			return (true);
		} else if (!(obj instanceof Message)) {
			return false;
		}
		tmpMessage = (Message) obj;
		if (this.id == tmpMessage.getID() && this.author.equals(tmpMessage.getAuthor()) && this.room.equals(tmpMessage.getRoom()) &&
				this.text.equals(tmpMessage.getText()) && this.time.equals(tmpMessage.getTime())) {
			return (true);
		}
		return (false);
	}

	@Override
	public int hashCode() {
		return (Objects.hash(this.id, this.author, this.room, this.text, this.time));
	}

}	
