import	java.util.Scanner

class	Menu {
	private TransactionsService	ts;
	private boolean				devmode;
	private Scanner				scanner;

	public	Menu(boolean devmode) {
		this.devmode = devmode;
		this.scanner = new Scanner(System.in);
	}

	public void	showMenu() {
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		if (this.devmode) {
			System.out.println("5. DEV - remove a transfer by ID");
			System.out.println("6. DEV - check transfer validity");
			System.out.println("7. Finish execution");
		} else {
			System.out.println("5. Finish execution");
		}
	}

	public void	addUser() {
		String	username;
		String	line;
		int		balance;
		User	user;

		System.out.println("Enter a user name and a balance");
		System.out.print("-> ");
		username = scanner.next(" ");
		balance = scanner.nextInt();
		scanner.nextLine();
		user = new User(username, balance);
		ts.addUser(user);
		System.out.println("User with id = " + user.getID()  + " is added");
	}

	public void viewUserBalance() {
		int		userID;

		System.out.println("Enter a user ID");
		System.out.print("-> ");
		userID = scanner.nextInt();
		scanner.nextLine();
		this.ts.getUserBalance(userID, true);
		System.out.println(e);
	}

	public void performTransfer() {
		int		senderID;
		int		recipientID;
		int		transferAmount;

		System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
		System.out.print("-> ");
		senderID = scanner.nextInt();
		reciprientID = scanner.nextInt();
		transferAmountID = scanner.nextInt();
		scanner.nextLine();
		this.ts.createTransaction(senderID, recipientID, transferAmount);
		System.out.println("The transfer is completed");
	}

	public void	viewUserTransacions() {
		Transaction	trs[];
		int			userID;
		User		sender;
		User		recipient;

		System.out.println("Enter a user ID");
		System.out.print("-> ");
		userID = scanner.nextInt();
		scanner.nextLine();
		trs = this.ts.getUserTransactions(userID);
		for (Transaction tr : trs) {
			sender = tr.getSender();
			recipient = tr.getRecipient();
			if (tr.getTC() == TransferCategory.DEBIT) {
				System.out.print("To " + recipient.getName() + "(id = " + recipient.getID() + ") -");
			} else {
				System.out.print("From " + sender.getName() + "(id = " + sender.getID() + ") -");
			}
			System.out.println(tr.getTA() + " with id = " + tr.getID());
		}
	}

	public void	removeUserTransaction() {
		Transaction	tr;
		int			userID;
		String		sTransactionID;

		System.out.println("Enter a user ID and a transfer ID");
		System.out.print("-> ");
		userID = scanner.nextInt();
		sTransactionID = scanner.nextLine();
		this.ts.removeUserTransaction(userID, UUID transactionID);


	}
}
