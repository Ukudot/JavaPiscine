class	Program {
	
	private static String	checkInput(String input) {
		String	inputs[];
		int		num;

		inputs = input.split("=");
		if (inputs.length != 2) {
			System.out.println("Usage error: java Program --count=<number of print>");
			return (null);
		}
		return (inputs[1]);
	}

	public static void	main(String args[]) {
		Thread	thread1, thread2;
		Object	lock;
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

		lock = new Object();
		thread1 = new Thread(new Egg(count, lock));
		thread2 = new Thread(new Hen(count, lock));
		thread2.start();
		thread1.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println("Error: thread has been interrupted");
			System.exit(-1);
		}
	}
}
