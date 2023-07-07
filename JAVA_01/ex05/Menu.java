import	java.util.Scanner;
import	java.util.UUID;

class	Menu {
	private TransactionsService	ts;
	private Scanner				scanner;
	private boolean				devmode;

	public	Menu(boolean devmode, Scanner scanner) {
		this.devmode = devmode;
		this.scanner = scanner;
		this.ts = new TransactionsService();
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
		username = scanner.next();
		balance = scanner.nextInt();
		line = scanner.nextLine();
		if (!line.equals("")) {
			throw new NonExistingCommandException("Wrong input");
		}
		user = new User(username, balance);
		ts.addUser(user);
		System.out.println("User with id = " + user.getID()  + " is added");
	}

	public void viewUserBalance() {
		int		userID;
		String	line;

		System.out.println("Enter a user ID");
		System.out.print("-> ");
		userID = scanner.nextInt();
		line = scanner.nextLine();
		if (!line.equals("")) {
			throw new NonExistingCommandException("Wrong input");
		}
		this.ts.getUserBalance(userID, true);
	}

	public void performTransfer() {
		int		senderID;
		int		recipientID;
		int		transferAmount;
		String	line;

		System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
		System.out.print("-> ");
		senderID = scanner.nextInt();
		recipientID = scanner.nextInt();
		transferAmount = scanner.nextInt();
		line = scanner.nextLine();
		if (!line.equals("")) {
			throw new NonExistingCommandException("Wrong input");
		}
		this.ts.createTransaction(senderID, recipientID, transferAmount);
		System.out.println("The transfer is completed");
	}

	public void	viewUserTransactions() {
		Transaction	trs[];
		int			userID;
		User		sender;
		User		recipient;
		String		line;

		System.out.println("Enter a user ID");
		System.out.print("-> ");
		userID = scanner.nextInt();
		line = scanner.nextLine();
		if (!line.equals("")) {
			throw new NonExistingCommandException("Wrong input");
		}
		trs = this.ts.getUserTransactions(userID);
		if (trs == null) {
			System.out.println("this user hasn't any transaction");
			return ;
		}
		for (Transaction tr : trs) {
			sender = tr.getSender();
			recipient = tr.getRecipient();
			if (tr.getTC() == TransferCategory.DEBIT) {
				System.out.print("To " + recipient.getName() + "(id = " + recipient.getID() + ") -");
				System.out.println((-tr.getTA()) + " with id = " + tr.getID());
			} else {
				System.out.print("From " + sender.getName() + "(id = " + sender.getID() + ") -");
				System.out.println(tr.getTA() + " with id = " + tr.getID());
			}
		}
	}

	public void	removeUserTransaction() {
		Transaction	trs[];
		int			userID;
		String		sTransactionID;
		UUID		transactionID;

		System.out.println("Enter a user ID and a transfer ID");
		System.out.print("-> ");
		userID = scanner.nextInt();
		sTransactionID = scanner.nextLine();
		trs = this.ts.getUserTransactions(userID);
		if (trs == null) {
			throw new TransactionNotFoundException("transaction not found");
		}
		for (Transaction tr : trs) {
			transactionID = tr.getID();
			if (sTransactionID.equals(" " + transactionID.toString())) {
				if (tr.getTC() == TransferCategory.DEBIT) {
					System.out.print("Transfer to " + tr.getRecipient().getName() + "(id = " + tr.getRecipient().getID() + ") ");
					System.out.println((-tr.getTA()) + " removed");
				} else {
					System.out.print("Transfer from " + tr.getSender().getName() + "(id = " + tr.getSender().getID() + ") ");
					System.out.println(tr.getTA() + " removed");
				}
				this.ts.removeUserTransaction(userID, transactionID);
				return ;
			}
		}
		throw new TransactionNotFoundException("transaction not found");
	}

	public void	checkTransferValidity() {
		Transaction	trs[];
		User		sender;
		User		recipient;

		System.out.println("Check results:");
		trs = this.ts.checkValidity();
		if (trs == null) {
			System.out.println("there aren't unpaired transactions");
			return ;
		}
		for (Transaction tr : trs) {
			sender = tr.getSender();
			recipient = tr.getRecipient();
			if (recipient.getTransactions().findTransaction(tr.getID())) {
				System.out.print(recipient.getName() + "(id = " + recipient.getID() + ") ");
				System.out.print("has an unacknowledged transfer id = " + tr.getID());
				System.out.print(" from " + sender.getName() + "(id = " + sender.getID() + ") for ");
				if (tr.getTC() == TransferCategory.DEBIT) {
					System.out.println((-tr.getTA()));
				} else {
					System.out.println(tr.getTA());
				}
			} else {
				System.out.print(sender.getName() + "(id = " + sender.getID() + ") ");
				System.out.print("has an unacknowledged transfer id = " + tr.getID());
				System.out.print(" to " + recipient.getName() + "(id = " + recipient.getID() + ") for ");
				if (tr.getTC() == TransferCategory.DEBIT) {
					System.out.println((-tr.getTA()));
				} else {
					System.out.println(tr.getTA());
				}
			}
		}
	}
}
