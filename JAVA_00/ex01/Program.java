import	java.util.Scanner;

class	Program
{
	public static void	main(String args[])
	{
		Scanner	scanner = new Scanner(System.in);
		int		num;
		int		steps;
		boolean	prime;

		System.out.print("Your input: ");
		num = scanner.nextInt();
		System.out.println("");
		steps = 1;
		prime = true;
		if (num <= 1)
		{
			System.out.println(num + ": Invalid number");
			System.exit(-1);
		}
		if (num != 2 && num % 2 == 0)
			prime = false;
		else if (num == 2)
			prime = true;
		else
		{
			for (int i = 3; i < num / i; i += 2)
			{
				steps++;
				if (num % i == 0)
				{
					prime = false;
					break ;
				}
			}
		}
		System.out.println(prime + " " + steps);
	}
}
