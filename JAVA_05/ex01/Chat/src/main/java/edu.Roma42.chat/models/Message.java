package	edu.Roma42.chat.models;
import	java.util.List;
import	java.util.Objects;

class	Message {
	private long		id;
	private User		author;
	private Chatroom	room;
	private String		text;
	private String		time;

	public	Message(int id, User author, Chatroom room, String text, String time) {
		this.id = id;
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
		return (this.text);
	}

	public String	getTime() {
		return (this.time);
	}

	@Override
	public String toString() {
		return ("Message: " + this.text + " (" + this.id + "), from: " + this.author + " in chatroom: " + this.room);
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
