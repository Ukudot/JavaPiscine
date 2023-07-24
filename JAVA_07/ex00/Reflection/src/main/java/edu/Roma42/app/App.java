package edu.Roma42.app;
import	edu.Roma42.classes.Student;
import	edu.Roma42.classes.University;
import	java.util.Scanner;

public class App 
{
	private final static String	separator = "-----------------------";

	private static Class	selectClass() throws ClassNotFoundException {
		Scanner	scanner;
		String	line;
		
		scanner = new Scanner(System.in);
		System.out.println("-> ");
		line = scanner.nextLine();
		System.out.println(separator);
		scanner.close();
		return (Class.forName(line));
	}

	private static void	printFields() {
		Scanner scanner;
		Method	methods[];

		scanner = new Scanner(System.in);
	}

	public static void main(String[] args)
	{
		Class	selected;
		System.out.println("Classes:\nStudent\nUniveristy");
		System.out.println(separator);
		try {
			selected = selectClass();
			System.out.println("fields: ");
			printFields();
			System.out.println("methods: ");
			printMethods();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
