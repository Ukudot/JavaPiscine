package	edu.Roma42.spring.service.repositories;
import	edu.Roma42.spring.service.models.User;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.SQLWarning;
import	java.sql.ResultSet;
import	java.sql.SQLException;
import	java.util.List;
import	java.util.ArrayList;
import	java.util.Optional;

public class	UsersRepositoryJdbcImpl implements UsersRepository {
	private	DataSource	ds;
	private	Connection	con;

	public	UsersRepositoryJdbcImpl(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	@Override
	public User	findById(Long id) {
		User		user = null;
		Statement	stm;
		ResultSet	rs;
		SQLWarning	warning;

		try {
			stm = this.con.createStatement();
			rs = stm.executeQuery("SELECT * FROM my_user WHERE id = " + id.toString());
			warning	= stm.getWarnings();
			while (warning != null) {
				System.out.println(">> log: " + warning.getMessage());
				warning = warning.getNextWarning();
			}
			user = new User(null, null);
			if (rs.next()) {
				user.setId(new Long(rs.getLong("id")));
				user.setEmail(rs.getString("email"));
			}
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return (user);
	}

	@Override
	public List<User>	findAll() {
		List<User>	all = new ArrayList<User>();
		User		user;
		Statement	stm;
		ResultSet	rs;
		SQLWarning	warning;

		try {
			stm = this.con.createStatement();
			rs = stm.executeQuery("SELECT * FROM my_user");
			warning	= stm.getWarnings();
			while (warning != null) {
				System.out.println(">> log: " + warning.getMessage());
				warning = warning.getNextWarning();
			}
			while (rs.next()) {
				user = new User(null, null);
				user.setId(new Long(rs.getLong("id")));
				user.setEmail(rs.getString("email"));
				all.add(user);
			}
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return(all);
	}

	@Override
	public void	save(User entity) {}

	@Override
	public void	update(User entity) {}

	@Override
	public void	delete(Long id) {}

	@Override
	public Optional<User>	findByEmail(String email) {return(null);}
}
