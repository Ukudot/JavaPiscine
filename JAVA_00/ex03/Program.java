import	java.util.Scanner;

class	Program {
	
	static final int	N_GRADES = 5;	

	private static long	pow(long base, long exp) {
		if (exp == 0) {
			return (1);
		}
		return (base * pow(base, exp - 1));
	}

	private static void	print(long store, int i) {
		long	min;

		for (int j = 1; j <= i; j++) {
			min = store % 10;
			System.out.print("Week " + j + " ");
			for (int k = 0; k < min; k++) {
				System.out.print("=");
			}
			System.out.println(">");
			store /= 10;
		}
	}

	public static void	main(String args[]) {
		Scanner	scanner = new Scanner(System.in);
		String	week;
		long	store;
		long	num;
		long	min;
		int		i;

		store = 0;
		for (i = 0; i <= 17; i++) {
			week = scanner.nextLine();
			if (week.equals("42")) {
				break ;
			}
			if (!week.equals("Week " + (i + 1))) {
				System.err.println("Invalid input");
				System.exit(-1);
			}
			min = 10;
			for (int j = 0; j < N_GRADES; j++) {
				num = scanner.nextInt();
				if (num > 9 || num < 1) {
					System.err.println("Invalid input");
					System.exit(-1);
				}
				if (num < min) {
					min = num;
				}
			}
			scanner.nextLine();
			store += (min * pow(10, i));
		}
		print(store, i);
	}
}
