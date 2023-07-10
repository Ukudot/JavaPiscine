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

		if (args.length != 1) {
			System.out.println("Usage error: java Program --current-folder=<folder absolute path>");
			System.exit(-1);
		}
		initialPath = getPath(args[0]);
		if (initialPath == null) {
			System.out.println("Usage error: java Program --current-folder=<folder absolute path>");
			System.exit(-1);
		}
		try {
			System.out.println(initialPath);
			minish = new Minishell(initialPath);
			minish.list();
		} catch (FileNotExistException e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}
