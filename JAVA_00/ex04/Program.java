import	java.util.Scanner;

class	Program {
	static final short	DEFAULT_VALUE = -1;

	private static void	putInArray(short occ[][], char c, int length)
	{
		for (int i = 0; i < length && occ[i][0] != -1; i++) {
			if (occ[i][0] == (short) c)
				occ[i][1]++;
		}
		occ[i][0] = (short) c;
		occ[i][1] = 1;
	}
	public static void	main(String args[]) {
		Scanner	scanner = new Scanner(System.in);
		String	str;
		char	copy[];
		short	occ[][];
		int		length;
		
		str = scanner.nextLine();
		length = str.length();
		occ = new short[length][2];
		copy = str.toCharArray();
		for (int i = 0; i < length; i++) {
			occ[i][0] = DEFAULT_VALUE;
			occ[i][1] = DEFAULT_VALUE;
		}
		for (char c : copy) {
			putInArray(occ, c, length);
		}
	}
}
