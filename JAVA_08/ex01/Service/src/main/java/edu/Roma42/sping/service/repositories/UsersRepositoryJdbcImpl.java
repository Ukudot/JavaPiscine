package	edu.Roma42.spring.service.repositories;
import	edu.Roma42.spring.service.models.User;
import	javax.sql.DataSource;
import	java.sql.Statement;
import	java.sql.SQLWarning;
import	java.sql.ResultSet;
import	java.sql.SQLException;

public class	UsersRepositoryJdbcImpl implements UsersRepository {
	private	this.ds;
	private	this.con;

	public	UsersRepositoryJdbcImpl(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	@Override
	public User	findById(Long id) {
		Statement	stm;
		ResultSet	rs;
		SQLWarning	warning;

		stm = this.con.createStatement();
		rs = stm.executeQuery("SELECT * FROM user WHERE id = " + id.toString());
		warning	= stm.getWarnings();
		while (warning != null) {
			System.out.println(">> log: " + warning.getMessage());
			warning = warning.getNextWarning();
		}
	}
}
