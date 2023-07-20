package	edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.User;
import	edu.Roma42.chat.models.Message;
import	edu.Roma42.chat.models.Chatroom;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.ResultSet;
import	java.sql.SQLException;
import	java.util.List;
import	java.util.ArrayList;

SELECT u.id, u.login, u.password, m.room, r.name, r.owner, r2.id roomsIDOwnedBy, r2.name roomsNameOwnedBy FROM chat.user u LEFT JOIN chat.message m ON u.id = m.author LEFT JOIN chat.chatroom r on m.room = r.owner LEFT JOIN chat.chatroom r2 ON u.id = r2.owner ORDER BY u.id;

public class	UsersRepositoryJdbcImpl implements UsersRepository {
	private static final String	query = "SELECT u.id, u.login, u.password, m.room, r.name, r.owner, r2.id roomsIDOwnedBy, r2.name roomsNameOwnedBy FROM chat.user u LEFT JOIN chat.message m ON u.id = m.author LEFT JOIN chat.chatroom r on m.room = r.owner LEFT JOIN chat.chatroom r2 ON u.id = r2.owner ORDER BY u.id;
";
	public DataSource			ds;
	public Connection			con;

	public	UsersRepositoryJdbcImpl(DataSource ds) {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	public List<User>	findAll(int page, int size) {
		int				start;
		int				end;
		long			currentUser;
		String			currentLogin;
		String			currentPassword;
		Statement		stm;
		ResultSet		rs;
		List<User>		users;
		List<Chatroom>	ownedRoom;
		List<Chatroom>	rooms;

		start = page * size;
		end = start + size;
		users = new ArrayList<User>();
		stm = this.con.createStatement();
		rs = stm.executeQuery(query);
		if (!rs.absolute(start)) {
			return (null);
		}
		while (start != end) {
			currentUser = rs.getLong("u.id");
			currentLogin = rs.getString("u.login");
			currentPassword = rs.getString("u.password");
			ownedRoom = getOwnedRoom(rs);//
			rooms = getRooms(rs, new ArrayList<Chatroom>());//
			while (!rs.next() && currentUser == rs.getLong("u.id")) {
				rooms = getRooms(rs, new ArrayList<Chatroom>());//	
			}
			users.add(new User(currentUser, currentLogin, currentPassword, ownedRoom, ))
		}
		return (users);
	}
	public void	closeCon() throws SQLException {
		this.con.close();
	}
}
