package	edu.Roma42.orm;
import	edu.Roma42.models.User;
import	edu.Roma42.managers.OrmManager;
import	java.util.Scanner;
import	javax.sql.DataSource;
import	java.sql.SQLException;
import	com.zaxxer.hikari.HikariConfig;
import	com.zaxxer.hikari.HikariDataSource;

public class	Program	{
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

	public static void	main(String args[]) {
		User		user;
		OrmManager	ormManager;
		DataSource	ds;
		Scanner		scanner;

		scanner = new Scanner(System.in);
		try {
			ds = createDataSource();
			ormManager = new OrmManager(ds);
			user = new User(new Long(1), "Giovanni", "Panico", 24, new Double(2.5), new Boolean(true));
			ormManager.save(user);
			user = new User(new Long(2), "aaa", "bbb", 2, new Double(10.2), new Boolean(true));
			ormManager.save(user);
			user = new User(new Long(3), "ccc", "ddd", 3, new Double(3.33), new Boolean(false));
			ormManager.save(user);
			System.out.println("Press enter to continue");
			scanner.nextLine();
			user = new User(new Long(3), "fff", "eee", 3, new Double(4.44), new Boolean(true));
			ormManager.update(user);
			user = ormManager.findById(new Long(1), User.class);
			System.out.println(user.toString());
			user = ormManager.findById(new Long(2), User.class);
			System.out.println(user.toString());
			user = ormManager.findById(new Long(3), User.class);
			System.out.println(user.toString());
			user = ormManager.findById(new Long(4), User.class);
			System.out.println(user.toString());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
