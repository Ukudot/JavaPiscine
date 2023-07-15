package	app;
import	logic.*;
import	java.awt.Color;
import	com.beust.jcommander.JCommander;
import	com.beust.jcommander.Parameter;
import	com.beust.jcommander.Parameters;
import	com.beust.jcommander.ParameterException;
import	com.beust.jcommander.IParameterValidator;

@Parameters(separators = "=")
public class	App {
	private static final String	FILE = "./target/resources/it.bmp";
	@Parameter(
		names = "--white",
		description = "color used instead of white",
		required = true,
		validateWith = ColorValidator.class
	)
	private String				white;

	@Parameter(
		names = "--black",
		description = "color used instead of white",
		required = true,
		validateWith = ColorValidator.class
	)
	private String				black;

	static public class	ColorValidator implements IParameterValidator {
		@Override
		public void	validate(String name, String value) throws ParameterException {
			Color	color;

			try {
				color = (Color) Class.forName("java.awt.Color").getField(value).get(null);
			} catch (Exception e) {
				throw new ParameterException("color " + value + " not found");
			}
		}
	}

	public static void main(String args[]) {
		App	app;

		if (args.length != 2) {
			System.err.println("Usage error: java App <white_char> <black_char>");
			System.exit(-1);
		}
		app = new App();
		try {
			JCommander.newBuilder()
				.addObject(app)
				.build()
				.parse(args);
				app.run();
		} catch (ParameterException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}

	public void	run() {
		Image	image;

		image = null;
		try {
			image = new Image(this.white, this.black, FILE);
			image.print();
		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
