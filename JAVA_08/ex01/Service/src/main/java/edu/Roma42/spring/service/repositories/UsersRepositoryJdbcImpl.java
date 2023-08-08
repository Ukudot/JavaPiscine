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
import	java.util.StringJoiner;

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
				user.setId(id);
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
	public void	save(User entity) {
		Statement		stm;
		SQLWarning		warning;
		StringJoiner	query;

		try {
			query = new StringJoiner(", ", "INSERT INTO my_user(id, email) VALUES(", ")");
			stm = this.con.createStatement();
			query.add(entity.getId().toString());
			query.add(entity.getEmail() == null ? null : "'" + entity.getEmail() + "'");
			stm.executeUpdate(query.toString());
			warning = stm.getWarnings();
			while (warning != null) {
				System.out.println(warning.getMessage());
				warning = warning.getNextWarning();
			}
		} catch(SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public void	update(User entity) {
		Statement		stm;
		SQLWarning		warning;
		StringJoiner	query;

		try {
			query = new StringJoiner(", ", "UPDATE my_user SET ", " WHERE id = " + entity.getId());
			stm = this.con.createStatement();
			query.add("email = " + (entity.getEmail() == null ? null : "'" + entity.getEmail() + "'"));
			stm.executeUpdate(query.toString());
			warning = stm.getWarnings();
			while (warning != null) {
				System.out.println(warning.getMessage());
				warning = warning.getNextWarning();
			}
		} catch(SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public void	delete(Long id) {
		Statement		stm;
		SQLWarning		warning;

		try {
			stm = this.con.createStatement();
			stm.executeUpdate("DELETE FROM my_user WHERE id = " + id.toString());
			warning = stm.getWarnings();
			while (warning != null) {
				System.out.println(warning.getMessage());
				warning = warning.getNextWarning();
			}
		} catch(SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public Optional<User>	findByEmail(String email) {
		User		user = null;
		Statement	stm;
		ResultSet	rs;
		SQLWarning	warning;

		try {
			stm = this.con.createStatement();
			rs = stm.executeQuery("SELECT * FROM my_user WHERE email = " + (email == null ? null : "'" + email + "'"));
			warning	= stm.getWarnings();
			while (warning != null) {
				System.out.println(">> log: " + warning.getMessage());
				warning = warning.getNextWarning();
			}
			user = new User(null, null);
			if (rs.next()) {
				user.setId(new Long(rs.getLong("id")));
				user.setEmail(email);
			}
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return (Optional.of(user));
	}
}
