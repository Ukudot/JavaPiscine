class	Program {
	
	private static String	checkInput(String input) {
		String	inputs[];
		int		num;

		inputs = input.split("=");
		if (inputs.length != 2 || !inputs[0].equals("--count")) {
			System.out.println("Usage error: java Program --count=<number of print>");
			System.exit(-1);
		}
		return (inputs[1]);
	}

	public static void	main(String args[]) {
		Thread	thread1, thread2;
		Hen		hen;
		Egg		egg;
		int		count;

		if (args.length != 1) {
			System.out.println("Usage error: java Program --count=<number of print>");
			System.exit(-1);
		}
		count = 0;
		try {
			count = Integer.parseInt(checkInput(args[0]));
		} catch (NumberFormatException e) {
			System.out.println("Error: not a number");
			System.exit(-1);
		}

		thread1 = new Thread(new Hen(count));
		thread2 = new Thread(new Egg(count));
		thread2.start();
		thread1.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println("Error: thread has been interrupted");
			System.exit(-1);
		}
		for (int i = 0; i < count; i++) {
			System.out.println("Human");
		}
	}
}
