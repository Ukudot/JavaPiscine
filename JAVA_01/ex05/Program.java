import	java.util.Scanner;

class	Program {
	static final int	MAX_COMMAND_DEV = 7;
	static final int	MAX_COMMAND_PROD = 5;


	static private void selectCommand(int select, int noCommand, Menu menu) {
		if (noCommand == MAX_COMMAND_PROD && select == MAX_COMMAND_PROD) {
			select = MAX_COMMAND_DEV;
		}
		switch (select) {
			case 1:
				menu.addUser();
				break ;
			case 2:
				menu.viewUserBalance();
				break ;
			case 3:
				menu.performTransfer();
				break ;
			case 4:
				menu.viewUserTransactions();
				break ;
			case 5:
				menu.removeUserTransaction();
				break ;
			case 6:
				menu.checkTransferValidity();
				break ;
			default:
		}
	}

	static public void	main(String args[]) {
		Scanner scanner;
		String	endline;
		Menu	menu;
		int		select;
		int		noCommand;
		boolean	passSelection;

		scanner = new Scanner(System.in);
		if (args.length != 0 && args[0].equals("--profile=dev")) {
			menu = new Menu(true, scanner);
			noCommand = MAX_COMMAND_DEV;
		} else {
			menu = new Menu(false, scanner);
			noCommand = MAX_COMMAND_PROD;
		}
		select = 0;
		while (select != noCommand) {
			menu.showMenu();
			System.out.print("-> ");
			passSelection = false;
			try {
				select = scanner.nextInt();
				passSelection = true;
				endline = scanner.nextLine();
				if (endline.length() != 0 || select < 1 || select > noCommand) {
					throw new NonExistingCommandException("Command not valid");
				}
				selectCommand(select, noCommand, menu);
				System.out.println("---------------------------------------------------------");
			} catch (Exception e) {
				System.out.println(e);
				if (!passSelection) {
					scanner.nextLine();
				}
				System.out.println("---------------------------------------------------------");
			}
		}
		System.out.println("Bye bye <3");
	}
}
