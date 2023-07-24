package edu.Roma42.app;
import	edu.Roma42.classes.Student;
import	edu.Roma42.classes.University;
import	java.util.Scanner;
import	java.util.StringJoiner;
import	java.lang.reflect.Field;
import	java.lang.reflect.Method;
import	java.lang.reflect.Constructor;
import	java.lang.reflect.Parameter;
import	java.lang.reflect.InvocationTargetException;

public class App 
{
	private final static String	separator = "-----------------------";
	private final static String	pack = "edu.Roma42.classes.";

	private static Class	selectClass(Scanner scanner) throws ClassNotFoundException {
		String	line;
		
		System.out.print("-> ");
		line = scanner.nextLine();
		return (Class.forName(pack + line));
	}

	private static void	printFields(Class selected) {
		Field	fields[];

		fields = selected.getDeclaredFields();
		for (Field field : fields) {
			System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
		}
	}

	private static void	printMethods(Class selected) {
		Method			methods[];
		StringJoiner	params;

		methods = selected.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals("toString")) {
				continue ;
			}
			params = new StringJoiner(", ", "(", ")");
			for (Class type : method.getParameterTypes()) {
				params.add(type.getSimpleName());
			}
			System.out.println("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + params.toString());
		}
	}

	private static Object	createObject(Class selected, Scanner scanner) throws NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
		Constructor	constructors[];
		Object		arglist[];
		Parameter	params[];
		String		line;
		Method		methods[];
		Object		created = null;

		System.out.println("Let's create an object.");
		constructors = selected.getConstructors();
		for (Constructor con : constructors) {
			if (con.getParameterCount() == 0) {
				continue ;
			} else {
				arglist = new Object[con.getParameterCount()];
				params = con.getParameters();
				for (int i = 0; i < params.length; i++) {
					System.out.println(params[i].getName() + ":");
					System.out.print("-> ");
					line = scanner.nextLine();
					arglist[i] = params[i].getType().getConstructor(String.class).newInstance(line);
				}
				created = con.newInstance(arglist);
				System.out.println("Object created: " + created);
				break ;
			}
		}
		return (created);
	}

	public static void main(String[] args)
	{
		Class	selected;
		Scanner	scanner;

		System.out.println("Classes:\nStudent\nUniveristy");
		System.out.println(separator);
		try {
			scanner = new Scanner(System.in);
			selected = selectClass(scanner);
			System.out.println(separator);
			System.out.println("fields: ");
			printFields(selected);
			System.out.println("methods: ");
			printMethods(selected);
			System.out.println(separator);
			createObject(selected, scanner);
			scanner.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}
