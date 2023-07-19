package	edu.Roma42.chat.models;
import	java.util.List;
import	java.util.Objects;
import	java.util.ArrayList;

class	Chatroom {
	private long			id;
	private String			name;
	private User			owner;
	private	List<Message>	messages;

	public	Chatroom(int id, String name, User owner, List<Message> messages) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		if (messages == null) {
			this.messages = messages;
		} else {
			this.messages = new ArrayList<Message>();
		}
	}

	public long	getID() {
		return (this.id);
	}

	public String	getName() {
		return (this.name);
	}

	public User	getOwner() {
		return (this.owner);
	}

	public List<Message> getMessages() {
		return (this.messages);
	}

	@Override
	public String toString() {
		return ("{Chatroom: " + this.name + " (" + this.id + "), messages: " + this.messages.size() + "}");
	}

	@Override
	public boolean	equals(Object obj) {
		Chatroom	tmpChatroom;
		if (this == obj) {
			return (true);
		} else if (!(obj instanceof Chatroom)) {
			return false;
		}
		tmpChatroom = (Chatroom) obj;
		if (this.id == tmpChatroom.getID() && this.name.equals(tmpChatroom.getName()) && this.owner.equals(tmpChatroom.getOwner()) &&
				this.messages.equals(tmpChatroom.getMessages())) {
			return (true);
		}
		return (false);
	}

	@Override
	public int hashCode() {
		return (Objects.hash(this.id, this.name, this.owner));
	}

}	
