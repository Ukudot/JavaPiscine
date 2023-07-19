package edu.Roma42.chat.app;
import	edu.Roma42.chat.models.*;
import	edu.Roma42.chat.repositories.*;
import	java.io.BufferedReader;
import	java.io.InputStreamReader;
import	java.io.InputStream;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.SQLException;
import	java.util.Scanner;
import	com.zaxxer.hikari.HikariConfig;
import	com.zaxxer.hikari.HikariDataSource;

class	Program {
	private static final String	DB_URL = "jdbc:postgresql://localhost:5432/";
	private static final String	DB_USR = "gpanico";
	private static final String	DB_PWD = "1234";
	private static final String	DB_SCHEMA "schema.sql";
	private static final String	DB_DATA = "data.sql";

	private static DataSource	createDataSource() {
		HikariDataSource	ds;
		HikariConfig		config;

		config = new HikariConfig();
		config.setJdbcUrl(Program.DB_URL);
		config.setUsername(Program.DB_USR);
		config.setPassword(Program.DB_PWD);
		ds = new HikariDataSource(config);
		return (ds);
	}

	private static void	executeQueryFile(Statement stm, String filename) {
		BufferedReader	in;

		try {
			in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename)));
			line = in.readLine().replace(';', '');
			while (line) {
				stm.execute(line);
				line = in.readLine().replace(';', '');
			}
			in.close();
		} catch (Exception e) {
			in.close();
			System.err.println("Error: cannot access database");
			System.exit(-1);
		}
	}

	private static void	fillDataBase(DataSource ds) throws SQLException {
		Connection	con;
		String		line;
		Statement	stm;

		con = ds.getConnection();
		stm = con.createStatement();
		Program.executeQueryFile(stm, Program.DB_SCHEMA);
		Program.executeQueryFile(stm, Program.DB_DATA);
		con.close();
	}

	public static void	main(String args[]) {
		DataSource	ds;
		Message		msg;
		long		id;
		Scanner		scanner;

		ds = Program.createDataSource();
		Program.fillDataBase();
		scanner = new Scanner(System.in);
		System.out.print("-> ");
		try {
			id = scanner.nextLong();
		} catch (Exception e) {
			System.out.prinltn(e.getMessage());
			System.exit(-1);
		}
		msg = new MessagesRepositoryJdbcImpl(ds).findById(id);
		System.out.println(msg);
	}
}
