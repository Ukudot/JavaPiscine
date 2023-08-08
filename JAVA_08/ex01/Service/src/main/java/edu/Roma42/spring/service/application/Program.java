package	edu.Roma42.spring.service.application;
import	edu.Roma42.spring.service.models.User;
import	edu.Roma42.spring.service.repositories.UsersRepository;
import	edu.Roma42.spring.service.repositories.UsersRepositoryJdbcImpl;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.SQLException;
import	java.util.List;
import	com.zaxxer.hikari.HikariConfig;
import	com.zaxxer.hikari.HikariDataSource;

public class	Program {
	private static final String	DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String	DB_USR = "postgres";
	private static final String	DB_PWD = "password";

	private static DataSource	createDataSource() {
		HikariDataSource	ds;
		HikariConfig		config;

		config = new HikariConfig();
		config.setJdbcUrl(DB_URL);
		config.setUsername(Program.DB_USR);
		config.setPassword(Program.DB_PWD);
		ds = new HikariDataSource(config);
		return (ds);
	}

	private	static void			initDataBase(DataSource ds) throws SQLException {
		Connection	con;
		Statement	stm;

		con = ds.getConnection();
		stm = con.createStatement();
		stm.executeUpdate("DROP TABLE IF EXISTS my_user");
		stm.executeUpdate("CREATE TABLE my_user (id BIGINT, email VARCHAR(50))");
		stm.executeUpdate("INSERT INTO my_user(id, email) VALUES(1, '1234@mail.com')");
		stm.executeUpdate("INSERT INTO my_user(id, email) VALUES(2, 'ciao@mail.com')");
		stm.executeUpdate("INSERT INTO my_user(id, email) VALUES(3, '567@mail.com')");
		stm.executeUpdate("INSERT INTO my_user(id, email) VALUES(4, '89@mail.com')");
		stm.close();
		con.close();
	}

	public static void	main(String args[]) {
		DataSource		ds;	
		User			user;
		UsersRepository	ur;
		List<User>		all;

		try {
			ds = createDataSource();
			initDataBase(ds);
			ur = new UsersRepositoryJdbcImpl(ds);
			user = ur.findById(new Long(1));
			System.out.println(user.toString());
			all = ur.findAll();
			System.out.println(all);
			user = new User(new Long(5), "aaa@mail.com");
			ur.save(user);
			all = ur.findAll();
			System.out.println(all);
			user.setEmail("bbb@mail.com");
			ur.update(user);
			all = ur.findAll();
			System.out.println(all);
			ur.delete(new Long(5));
			all = ur.findAll();
			System.out.println(all);
			ur = new UsersRepositoryJdbcImpl(ds);
			user = ur.findByEmail("ciao@mail.com").get();
			System.out.println(user.toString());
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
