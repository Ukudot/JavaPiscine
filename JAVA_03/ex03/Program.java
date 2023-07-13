import	java.io.*;
import	java.util.Scanner;

class	Program {
	private static String	URLS_FILE = "urls.txt";

	private static int	takeInput(String input) throws BadInputException {
		String	inputs[];
		int		threadsCount;

		inputs = input.split("=");
		if (inputs.length != 2 || !inputs[0].equals("--threadsCount")) {
			throw new BadInputException("Usage error: java Program --threadsCount=<number of threads>");
		}
		threadsCount = 0;
		try {
			threadsCount = Integer.parseInt(inputs[1]);
		} catch (NumberFormatException e) {
			throw new BadInputException("Error: not a number");
		}
		if (threadsCount <= 0) {
			throw new BadInputException("Error: invalid number of threads");
		}
		return (threadsCount);
	}

	public static void	main(String args[]) {
		int			threadsCount;
		ThreadsPool	threadsPool;
		MyFile		urlsFile;
		String		line;
		Scanner		scanner = new Scanner(System.in);

		if (args.length != 1) {
			System.err.println("Usage error: java Program --threadsCount=<number of threads>");
			System.exit(-1);
		}
		threadsCount = 0;
		urlsFile = null;
		try {
			threadsCount = takeInput(args[0]);
			urlsFile = new MyFile(URLS_FILE);
		} catch (BadInputException e) {
			System.err.println(e);
			System.exit(-1);
		} catch (InvalidFileException e) {
			System.err.println(e);
			System.exit(-1);
		}
		threadsPool = new ThreadsPool(threadsCount, urlsFile);
		threadsPool.start();
		line = scanner.nextLine();
		while (true) {
			threadsPool.addElem(line);
			line = scanner.nextLine();
		}
	}
}
