package edu.Roma42.chat.app;
import	edu.Roma42.chat.models.*;
import	edu.Roma42.chat.repositories.*;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.util.Scanner;

class	Program {
	private static final String	DB_URL = "jdbc:postgresql://localhost:5432/";
	private static final String	DB_USR = "gpanico";
	private static final String	DB_PWD = "1234";
	private static final String	DB_SCHEMA "target/main/resources/schema.sql";
	private static final String	DB_DATA = "target/main/resources/data.sql";

	private static DataSource	createDataSource() {

	}

	private static void	fillDataBase() {

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
