package edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.Message;
import	edu.Roma42.chat.models.User;
import	edu.Roma42.chat.models.Chatroom;
import	java.util.Optional<T>;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.SQLException;
import	java.sql.Statement;
import	java.sql.ResultState;
import	java.sql.TimeStamp;

class	MessagesRepostoryJdbcImpl implements MessagesRepository {
	DataSource	ds;
	Connection	con;

	public	MessagesRepostoryJdbcImpl(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	@Override
	Optional<Message>	findById(long id) {
		Statement	stm;
		ResultSet	rsMessage;
		ResultSet	rsAuthor;
		ResultSet	rsChatroom;
		Long		messageId;
		Long		authorId;
		Long		roomId;
		String		text;
		TimeStamp	ts;
		User		author = null;
		Chatroom	room = null;

		try {
			stm = this.con.createStatement();
			rsMessage = stm.executeQuery("SELECT * FROM chat.Message WHERE id=" + id);
			if (rsMessage.first()) {
				authorId = rsMessage.getLong("author");
				roomId = rsMessage.getLong("room");
				text = rsMessage.getText("text");
				ts = rs.Message.getTimestamp("time");
				rsAuthor = stm.executeQuery("SELECT * FROM chat.User WHERE id=" + authorId);
				if (rsAuthor.first()) {
					author = new User(authorId, rsAuthor.getString("login"), rsAuthor.getString("password"), null, null);
				}
				rsChatroom = stm.executeQuery("SELECT * FROM chat.Chatroom WHERE id=" + roomId);
				if (rsChatroom.first()) {
					room = new Chatroom(roomId, rsChatroom.getString("name"), rsChatroom.getLong("owner"), null);
				}
				return (new Message(id, author, room, text, ts));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return (null);

	}
}
