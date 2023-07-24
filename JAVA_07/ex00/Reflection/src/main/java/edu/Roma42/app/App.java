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
	private final static String	langPack = "java.lang.";

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
		int				i = 0;

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

	private static Object	createObject(Class selected, Scanner scanner) throws NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
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

	public static Object	updateObject(Class selected, Object created, Scanner scanner) throws NoSuchFieldException, 
		   InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		String	line;
		Field	field;

		System.out.println("Enter name of the field for changing:");
		System.out.print("-> ");
		line = scanner.nextLine();
		field = selected.getDeclaredField(line);
		System.out.println("Enter " + field.getType().getSimpleName() + " value");
		System.out.print("-> ");
		line = scanner.nextLine();
		field.setAccessible(true);
		field.set(created, field.getType().getConstructor(String.class).newInstance(line));
		System.out.println("Object updated: " + created);
		return (created);
	}

	public static void	callMethod(Class selected, Object created, Scanner scanner) throws ClassNotFoundException,
		   NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String	line;
		Method	method;
		Class	params[];
		String	types[];
		Object	arglist[] = null;
		Object	returnValue;

		System.out.println("Enter name of the method for call:");
		System.out.print("-> ");
		line = scanner.nextLine();
		types = line.substring(line.indexOf("(") + 1, line.length() - 1).split(",");
		if (!types[0].trim().equals("")) {
			params = new Class[types.length];
			for (int i = 0; i < params.length; i++) {
				params[i] = Class.forName(langPack + types[i].trim());
			}
		} else {
			params = new Class[0];
		}
		method = selected.getDeclaredMethod(line.substring(0, line.indexOf("(")), params);
		arglist = new Object[params.length];
		for (int i = 0; i < params.length; i++) {
			System.out.println("Enter " + params[i].getSimpleName() + " value");
			System.out.print("-> ");
			line = scanner.nextLine();
			arglist[i] = params[i].getConstructor(String.class).newInstance(line);
		}
		if (arglist == null) {
			returnValue = method.invoke(created);
		} else {
			returnValue = method.invoke(created, arglist);
		}
		if (method.getReturnType().equals(Class.forName(langPack + "Void"))) {
			return ;
		}
		System.out.println("Method returned:");
		System.out.println(returnValue);
	}

	public static void	main(String[] args)
	{
		Class	selected;
		Scanner	scanner;
		Object	created;

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
			created = createObject(selected, scanner);
			System.out.println(separator);
			created = updateObject(selected, created, scanner);
			System.out.println(separator);
			callMethod(selected, created, scanner);
			scanner.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}
