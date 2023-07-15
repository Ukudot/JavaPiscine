package	app;
import	logic.*;

public class	App {

	private static final String	FILE = "./target/resources/it.bmp";

	private static Image	takeInput(String args[]) throws InvalidInputException {
		Image	image = null;

		if (args[0].length() != 1 || args[1].length() != 1) {
			throw new InvalidInputException("Invalid input");
		}
		image = new Image(args[0].charAt(0), args[1].charAt(0), FILE);
		return (image);
	}

	public static void main(String args[]) {
		Image	image = null;

		if (args.length != 2) {
			System.err.println("Usage error: java App <white_char> <black_char>");
			System.exit(-1);
		}
		try {
			image = takeInput(args);
		} catch (InvalidInputException e) {
			System.err.println(e);
			System.exit(-1);
		}
		image.print();
	}
}
