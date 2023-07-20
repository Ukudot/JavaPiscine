package edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.Message;
import	edu.Roma42.chat.models.User;
import	edu.Roma42.chat.models.Chatroom;
import	edu.Roma42.chat.exceptions.NotSavedSubEntityException;
import	java.util.Optional;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.SQLException;
import	java.sql.Statement;
import	java.sql.ResultSet;
import	java.sql.Timestamp;

public class	MessagesRepositoryJdbcImpl implements MessagesRepository {
	DataSource	ds;
	Connection	con;

	public	MessagesRepositoryJdbcImpl(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	@Override
	public Optional<Message>	findById(long id) {
		Statement	stm;
		ResultSet	rsMessage;
		ResultSet	rsAuthor;
		ResultSet	rsChatroom;
		Long		messageId;
		Long		authorId;
		Long		roomId;
		String		text;
		String		ts;
		User		author = null;
		Chatroom	room = null;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rsMessage = stm.executeQuery("SELECT * FROM chat.Message WHERE id=" + id);
			if (rsMessage.first()) {
				authorId = rsMessage.getLong("author");
				roomId = rsMessage.getLong("room");
				text = rsMessage.getString("text");
				ts = rsMessage.getString("time");
				rsAuthor = stm.executeQuery("SELECT * FROM chat.User WHERE id=" + authorId);
				if (rsAuthor.first()) {
					author = new User(authorId, rsAuthor.getString("login"), rsAuthor.getString("password"), null, null);
				}
				rsChatroom = stm.executeQuery("SELECT * FROM chat.Chatroom WHERE id=" + roomId);
				if (rsChatroom.first()) {
					room = new Chatroom(roomId, rsChatroom.getString("name"), null, null);
				}
				return (Optional.of(new Message(id, author, room, text, ts)));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return (Optional.empty());
	}

	public void	save(Message message) throws NotSavedSubEntityException {	
		Statement	stm;
		ResultSet	rs;
		long		authorID;
		long		roomID;

		if (message.getID() != -1) {
			System.err.println("Cannot assign a valid id to message before saving");
			return ;
		}
		if (message.getAuthor() == null || message.getRoom() == null) {
			System.err.println("Error: author/chatroom cannot be null");
			return ;
		}
		authorID = message.getAuthor().getID();
		roomID = message.getRoom().getID();
		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stm.executeQuery("SELECT * FROM chat.User WHERE id=" + authorID);
			if (!rs.next()) {
				throw new	NotSavedSubEntityException("User id not found in db");
			}
			rs = stm.executeQuery("SELECT * FROM chat.Chatroom WHERE id=" + roomID);
			if (!rs.next()) {
				throw new	NotSavedSubEntityException("Chatroom id not found in db");
			}
			stm.execute("INSERT INTO chat.Message(author, room, text, time) VALUES (" + authorID + ", " + roomID + ", '"
					+ message.getText() + "', '" + message.getTime() + "')");
			rs = stm.executeQuery("SELECT * FROM chat.Message");
			rs.last();
			message.setID(rs.getLong("id"));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void	closeCon() throws SQLException {
		this.con.close();
	}
}
