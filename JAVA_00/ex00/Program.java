class	Program
{
	public static void	main(String args[])
	{
		int	num;
		int	sum;

		num = 479598;
		sum = 0;
		for (int i = 0; i < 6; i++)
		{
			sum += num % 10;
			num /= 10;
		}
		System.out.println("Result: " + sum);
	}
}
