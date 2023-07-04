import	java.util.Scanner;

class	Program {
	static final int	DEFAULT_VALUE = -1;
	static int			maxOcc;

	private static void	putInArray(int array[][], char c, int length) {
		int	i;

		for (i = 0; i < length && array[i][0] != -1; i++) {
			if (array[i][0] == (int) c) {
				array[i][1]++;
				return ;
			}
		}
		array[i][0] = (int) c;
		array[i][1] = 1;
	}

	private static void	fill2DArray(int array[][], int row, int col, int value) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				array[i][j] = value;
			}
		}
	}

	private static int	findGreater(int array[][], int length) {
		int	maxInd;

		maxInd = 0;
		for (int ind = 1; ind < length && array[ind][0] != -1; ind++) {
			if (array[maxInd][1] < array[ind][1]) {
					maxInd = ind;
			}
			else if (array[maxInd][1] == array[ind][1]) {
				if (array[maxInd][0] > array[ind][0]) {
					maxInd = ind;
				}
			}
		}
		if (array[maxInd][1] == -1) {
			return (-1);
		}
		return (maxInd);
	}

	private static void	putInHisto(int histo[][], int elem, int occ, int row) {
		int i;
		int	nIte;

		if (row == 0) {
			maxOcc = occ;
			if (occ < 10) {
				maxOcc = 10;
			}
		}
		i = 0;
		nIte = (int) ((float) occ / maxOcc * 10);
		histo[row][i] = elem;
		for (i = 1; i < nIte + 1 ; i++) {
			histo[row][i] = '#';
		}
		histo[row][i] = occ;
	}

	private static void	printPadded(int num, int padding)
	{
		if (padding == 0) {
			return ;
		}
		else if (num  == 0) {
			printPadded(num, padding - 1);
			System.out.print(" ");
		}
		else {
			printPadded(num / 10, padding - 1);
			System.out.print(num % 10);
		}
	}

	private static void	printPadded(char c, int padding)
	{
		for (int i = 1; i < padding; i++) {
			System.out.print(" ");
		}
		System.out.print(c);
	}

	private static void	printHisto(int histo[][]) {
		System.out.println("");
		for (int col = 11; col >= 0; col--)
		{
			for (int row = 0; row < 10; row++)
			{
				if (histo[row][col] == -1)
					break;
				else if (histo[row][col + 1] == -1)
					printPadded(histo[row][col], 3);
				else
					printPadded((char) histo[row][col], 3);
			}
			System.out.println("");
		}
	}

	private static void	plotHisto(int array[][], int length) {
		int	histo[][];
		int	index;

		histo = new int[10][13];
		fill2DArray(histo, 10, 13, -1);
		for (int i = 0; i < 10; i++) {
			index = findGreater(array, length);
			if (index == -1) {
				break ;
			}
			putInHisto(histo, array[index][0], array[index][1], i);
			array[index][1] = -1;
		}
		printHisto(histo);
	}

	public static void	main(String args[]) {
		Scanner	scanner = new Scanner(System.in);
		String	str;
		char	copy[];
		int		occs[][];
		int		length;
		
		str = scanner.nextLine();
		length = str.length();
		occs = new int [length][2];
		copy = str.toCharArray();
		fill2DArray(occs, length, 2, DEFAULT_VALUE);
		for (char c : copy) {
			putInArray(occs, c, length);
		}
		plotHisto(occs, length);
	}
}
