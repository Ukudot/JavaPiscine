class	Program {
	public static void	main(String args[]) {
		int	num;
		int	sum;

		num = 479598;
		sum = 0;
		sum += num % 10;
		num /= 10;
		sum += num % 10;
		num /= 10;
		sum += num % 10;
		num /= 10;
		sum += num % 10;
		num /= 10;
		sum += num % 10;
		num /= 10;
		sum += num % 10;
		num /= 10;
		System.out.println("Result: " + sum);
	}
}
