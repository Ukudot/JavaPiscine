import	java.util.Scanner;

class	Program {
	static final int	DEFAULT_VALUE = -1;

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
		int	max_ind;

		max_ind = 0;
		for (int ind = 1; ind < length && array[ind][0] != -1; ind++) {
			if (array[max_ind][1] < array[ind][1]) {
					max_ind = ind;
			}
		}
		return (max_ind);
	}

	private static void	putInHisto(int histo[][], char elem, int occ, int row) {
		static int	max_occ;
		int i;
		int	n_ite;

		if (row == 0) {
			max_occ = occ;
			if (occ < 10) {
				max_occ = 10;
			}
		}
		i = 0;
		n_ite = (float) occ / max_occ * 10;
		histo[row][i] = elem;
		for (i = 1; i < n_ite + 1 ; i++) {
			histo[row][i] = '#';
		}
		histo[row][i] = occ;
	}

	private static void	printHisto(int histo[][]) {
		System.out.println("");
		for (int col = 10; col >= 0; col--)
		{
			for (int row = 0; row < 10; row++)
			{
				if (histo[row][col] == -1)
					break;
				else if (histo[row][col + 1] == -1)
					//print number
				else
					//print char
			}
			System.out.println("");
		}
	}

	public static void	main(String args[]) {
		Scanner	scanner = new Scanner(System.in);
		String	str;
		char	copy[];
		int		occs[][];
		int		histo[][];
		int		length;
		int		index;
		
		str = scanner.nextLine();
		length = str.length();
		occs = new int [length][2];
		copy = str.toCharArray();
		fill2DArray(occs, length, 2, DEFAULT_VALUE);
		for (char c : copy) {
			putInArray(occs, c, length);
		}
		histo = new int[10][12];
		fill2DArray(histo, 10, 12, -1);
		for (int i = 0; i < 10; i++) {
			index = findGreater(occs, length);
			putInHisto(occs, occs[index][0], occs[index][1], i);
			occs[index][1] = -1;
		}
		for (int i = 0; i < length; i++) {
			if (occs[i][0] == -1) {
				break ;
			}
			System.out.println((char) occs[i][0] + ": " + occs[i][1]);
		}
	}
}
