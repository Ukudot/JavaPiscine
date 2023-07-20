package edu.Roma42.chat.app;
import	edu.Roma42.chat.models.*;
import	edu.Roma42.chat.repositories.*;
import	java.io.BufferedReader;
import	java.io.InputStreamReader;
import	java.io.InputStream;
import	java.io.IOException;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.SQLException;
import	java.util.Scanner;
import	java.util.Optional;
import	java.time.LocalDateTime;
import	com.zaxxer.hikari.HikariConfig;
import	com.zaxxer.hikari.HikariDataSource;

class	Program {
	private static final String	DB_URL = "jdbc:postgresql://localhost:5432/chatrooms";
	private static final String	DB_USR = "postgres";
	private static final String	DB_PWD = "password";
	private static final String	DB_SCHEMA = "schema.sql";
	private static final String	DB_DATA = "data.sql";

	private static String	buildSQL(BufferedReader in) throws IOException {
		String			line;
		StringBuilder	sb;

		sb = new StringBuilder();
		line = in.readLine();
		if (line == null) {
			return (null);
		}
		while (!line.endsWith(";")) {
			sb.append(line);
			line = in.readLine();
		}
		line = sb.append(line).toString();
		return (line);
	}

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

	private void	executeQueryFile(Statement stm, String filename) {
		BufferedReader	in;
		String			line;

		try {
			in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
						.getResourceAsStream(filename)));
			line = Program.buildSQL(in);
			while (line != null) {
				line = line.replace(';', ' ');
				stm.executeUpdate(line);
				line = Program.buildSQL(in);
			}
			stm.execute("CREATE SCHEMA IF NOT EXISTS chat");
			in.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("Error: cannot access database");
			System.exit(-1);
		}
	}

	private static void	fillDataBase(DataSource ds) throws SQLException {
		Connection	con;
		Statement	stm;
		Program		instance;

		con = ds.getConnection();
		stm = con.createStatement();
		instance = new Program();
		instance.executeQueryFile(stm, Program.DB_SCHEMA);
		instance.executeQueryFile(stm, Program.DB_DATA);
		con.close();
	}

	public static void	main(String args[]) {
		DataSource					ds;
		Message						msg, msg2;
		MessagesRepositoryJdbcImpl	messagesRepository;
		User						author;
		Chatroom					room;

		author = new User(1L, "user", "user", null, null);
		room = new Chatroom(1L, "room", author, null);
		msg = new Message(-1, author, room, "ciao come stai", LocalDateTime.now().toString().replace("T", " "));
		author = new User(2L, "user", "user", null, null);
		room = new Chatroom(4L, "room", author, null);
		msg2 = new Message(-1, author, room, "tutto bene", LocalDateTime.now().toString().replace("T", " "));
		try {
			ds = Program.createDataSource();
			Program.fillDataBase(ds);
			messagesRepository = new MessagesRepositoryJdbcImpl(ds);
			messagesRepository.save(msg);
			System.out.println("message id:" + msg.getID());
			messagesRepository.save(msg2);
			System.out.println("message id:" + msg2.getID());
			messagesRepository.closeCon();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}
