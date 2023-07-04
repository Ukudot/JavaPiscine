import	java.util.Scanner;

class	Program {
	static final int	SEPTEMBER_DAYS = 30;
	static final int	MAX_STUDENT = 10;
	static final int	MAX_LESSONS = 10;
	static final int	MAX_LESSON_PD = 5;
	static final String	LINE_SEPARETOR = ".";
	static final int	DAYS_MAP[] = {1, -1, -1, -1, 2, 5, -1, 4, 6, -1, 3, -1, -1, -1, 0};
	static final String	DAYS[] = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};

	private static int	hash(char day[]) {
		int	ind;

		ind = (int) day[1] * (int) day[2];
		ind /= 10;
		ind %= 100;
		ind %= 16;
		return (DAYS_MAP[ind]);
	}

	private static void	addToCalendar(int calendar[][], String hour, String day) {
		int	hr_index = (int)hour.toCharArray()[0] - '1';
		int	day_index = hash(day.toCharArray());

		for (; day_index < 30; day_index += 7){
			calendar[day_index][hr_index] = 1;
		}
	}

	private static int	findStudent(String students[], String student) {
		for (int i = 0; i < MAX_STUDENT && students[i] != null; i++) {
			if (student.equals(students[i])) {
				return (i);
			}
		}
		return (-1);
	}

	private static void	sign(int calendar[][], int std_index, Scanner scanner) {
		int		hour = scanner.nextInt() - 1;
		int		day = scanner.nextInt() - 1;
		String	here = scanner.nextLine();
		int		presence = 0;

		presence = 1;
		if (here.equals(" NOT_HERE")) {
			presence = 2;
		}
		presence = presence << (std_index * 2 + 1);
		calendar[day][hour] |= presence;
	}

	private static void printPadded(String str, int padded){
		int len = str.length();

		for (int i = 0; i < padded - len; i++){
			System.out.print(" ");
		}
		System.out.print(str);
	}

	private static void printHeader(int calendar[][]) {
		for (int i = 0; i < SEPTEMBER_DAYS; i++) {
			for (int j = 0; j < MAX_LESSON_PD; j++) {
				if (calendar[i][j] % 2 == 1) {
					System.out.print((j + 1) + ":00 " + DAYS[i % 7] + " ");
					if ((i + 1) / 10 != 0) {
						System.out.print((i + 1));
					}
					else {
						System.out.print(" " + (i + 1));
					}
					System.out.print("|");
				}
			}
		}
		System.out.println();
	}

	private static void	printDay(int calendar_day, int ind_std) {
		if (calendar_day % 2 == 1) {
			switch (calendar_day >> (ind_std * 2 + 1) & 3) {
				case 1:
					printPadded("1", 10);
					break ;
				case 2:
					printPadded("-1", 10);
					break ;
				default:
					printPadded("", 10);
			}
		System.out.print("|");
		}
	}

	private static void printMonth(int calendar[][], String student, int ind_std) {
		printPadded(student, 10);
		for (int i = 0; i < SEPTEMBER_DAYS; i++) {
			for (int j = 0; j < MAX_LESSON_PD; j++) {
				printDay(calendar[i][j], ind_std);
			}
		}
		System.out.println();
	}

	private static void print(int calendar[][], String students[]) {
		printPadded("", 10);
		printHeader(calendar);
		for (int ind_std = 0; ind_std < MAX_STUDENT && students[ind_std] != null; ind_std++) {
			printMonth(calendar, students[ind_std], ind_std);
		}
	}

	public static void	main(String args[]) {
		Scanner	scanner = new Scanner(System.in);
		String	students[] = new String[MAX_STUDENT];
		int		calendar[][] = new int[SEPTEMBER_DAYS][MAX_LESSON_PD];
		String	input_read = "";

		for (int i = 0; i < MAX_STUDENT; i++) {
			input_read = scanner.nextLine();
			if (input_read.equals(LINE_SEPARETOR))
				break ;
			students[i] = input_read;
		}
		if (!input_read.equals(LINE_SEPARETOR)) {
			scanner.nextLine();
		}
		for (int i = 0; i < MAX_LESSONS; i++) {
			input_read = scanner.next();
			if (input_read.equals(LINE_SEPARETOR)) {
				break ;
			}
			addToCalendar(calendar, input_read, scanner.nextLine());
		}
		scanner.nextLine();
		while (true) {
			input_read = scanner.next();
			if (input_read.equals(LINE_SEPARETOR)) {
				break ;
			}
			sign(calendar, findStudent(students, input_read), scanner);
		}
		scanner.nextLine();
		print(calendar, students);
	}
}
