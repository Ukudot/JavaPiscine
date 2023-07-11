import	java.util.Scanner;

class	Program {
	private static String	getPath(String args) {
		String	splitted[];

		splitted = args.split("=");
		if (splitted[0].equals("--current-folder"))
		{
			return (splitted[1]);
		}
		return (null);
	}

	public static void	main(String args[]) {
		String		initialPath;
		Minishell	minish;
		Scanner		scanner;
		String		line;
		String		cmds[];

		if (args.length != 1) {
			System.out.println("Usage error: java Program --current-folder=<folder absolute path>");
			System.exit(-1);
		}
		initialPath = getPath(args[0]);
		if (initialPath == null) {
			System.out.println("Usage error: java Program --current-folder=<folder absolute path>");
			System.exit(-1);
		}
		scanner = new Scanner(System.in);
		minish = null;
		try {
			minish = new Minishell(initialPath);
		} catch (FileNotExistException e) {
			System.out.println(e);
			System.exit(-1);
		}
		System.out.print("-> ");
		line = scanner.nextLine();
		cmds = line.split("\\s+");
		while (!cmds[0].equals("exit")) {
			try {
				if (cmds[0].equals("ls")) {
					minish.list(cmds);
				} else if (cmds[0].equals("cd")) {
					minish.changeDir(cmds);
				} else if (cmds[0].equals("mv")) {
					minish.moveFile(cmds);
				} else {
					throw new InvalidCommandException("Command not found");
				}
			} catch (InvalidCommandException e) {
				System.out.println(e);
			} catch (InvalidDirectoryException e) {
				System.out.println(e);
			} catch (InvalidFileException e) {
				System.out.println(e);
			}
		System.out.print("-> ");
		line = scanner.nextLine();
		cmds = line.split("\\s+");
		}
	}
}
