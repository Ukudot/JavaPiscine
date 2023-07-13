class	Program {
	private static int	MAX_ELEMENTS = 2000000;

	private static String	checkFirstInput(String input) {
		String	inputs[];

		inputs = input.split("=");
		if (inputs.length != 2 || !inputs[0].equals("--arraySize")) {
			System.out.println("Usage error: java Program --arraySize=<number of element> --threadsCount=<number of threads>");
			System.exit(-1);
		}
		return (inputs[1]);
	}

	private static String	checkSecondInput(String input) {
		String	inputs[];

		inputs = input.split("=");
		if (inputs.length != 2 || !inputs[0].equals("--threadsCount")) {
			System.out.println("Usage error: java Program --arraySize=<number of element> --threadsCount=<number of threads>");
			System.exit(-1);
		}
		return (inputs[1]);
	}

	public static void	main(String args[]) {
		int			arraySize;
		int			threadsCount;
		RandomArray	randArray;
		int			sum;
		ThreadsPool	threadsPool;

		if (args.length != 2) {
			System.out.println("Usage error: java Program --arraySize=<number of element> --threadsCount=<number of threads>");
			System.exit(-1);
		}
		arraySize = 0;
		threadsCount = 0;
		try {
			arraySize = Integer.parseInt(checkFirstInput(args[0]));
			threadsCount = Integer.parseInt(checkSecondInput(args[1]));
		} catch (NumberFormatException e) {
			System.out.println("Error: not a number");
			System.exit(-1);
		}
		if (arraySize > MAX_ELEMENTS || arraySize <= 0) {
			System.out.println("Error: invalid number of elements");
			System.exit(-1);
		} else if (threadsCount > arraySize || threadsCount <= 0) {
			System.out.println("Error: invalid number of threads");
			System.exit(-1);
		}
		randArray = new RandomArray(arraySize);
		randArray.generateNumbers();
		sum = 0;
		for (int i : randArray.getArray()) {
			sum += i;
		}
		System.out.println("sum: " + sum);
		threadsPool = new ThreadsPool(threadsCount, randArray);
		threadsPool.prepareThreads();
		threadsPool.start();
		try {
			threadsPool.join();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted");
			System.exit(-1);
		}
		System.out.println("Sum by threads: " + threadsPool.getSum());
	}
}
