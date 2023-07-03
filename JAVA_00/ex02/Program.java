import	java.util.Scanner;

class	Program {
	public static boolean	isPrime(int num) {
		if (num <= 1) {
			return (false);
		}
		if (num != 2 && num % 2 == 0) {
			return (false);
		}
		else if (num == 2) {
			return (true);
		}
		else {
			for (int i = 3; i < num / i; i += 2) {
				if (num % i == 0) {
					return (false);
				}
			}
		}
		return (true);
	}

	public static int		sum(int num) {
		int	sum;

		sum = 0;
		while (num != 0) {
			sum += num % 10;
			num /= 10;
		}
		return (sum);
	}

	public static void		main(String args[]) {
		Scanner	scanner = new Scanner(System.in);
		int		num;
		int		res;

		num = 0;
		res = 0;
		while (num != 42) {
			System.out.print("Enter a number: ");
			num = scanner.nextInt();
			if (num < 0) {
				System.err.println("Invalid input");
				System.exit(-1);
			}
			if (isPrime(sum(num))) {
				res++;
			}
		}
		System.out.println("Count of coffee-request - " + res);
	}
}
