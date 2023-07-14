import	com.beust.jcommander.Parameter;
import	com.beust.jcommander.Parameters;
import	com.beust.jcommander.JCommander;
import	com.beust.jcommander.IParameterValidator;
import	com.beust.jcommander.ParameterException;

@Parameters(separators = "=")
class	App {
	@Parameter(names = "-white", validateWith = SingleChar.class)
	private String	white;
	@Parameter(names = "-black", validateWith = SingleChar.class)
	private String	black;

	private static final String	FILE = "../resources/it.bmp";

	public static void main(String args[]) {
		App	app = new App();

		if (args.length != 2) {
			System.err.println("Usage error: java App <white_char> <black_char>");
			System.exit(-1);
		}
		try {
			JCommander.newBuilder()
				.addObject(app)
				.build()
				.parse(args);
			app.run();
		} catch (ParameterException e) {
			System.err.println(e);
		}
	}

	public void	run() {
		Image	image = null;

		try {
			image = new Image(this.white.charAt(0), this.black.charAt(0), FILE);
		} catch (InvalidInputException e) {
			System.err.println(e);
			System.exit(-1);
		}
		image.print();
	}

	public static class SingleChar implements IParameterValidator {
		public	SingleChar() {}
		public void validate(String name, String value) throws ParameterException {
			if (value == null || value.length() != 1) {
				throw new ParameterException("Invalid input");
			}
		}
	}
}
