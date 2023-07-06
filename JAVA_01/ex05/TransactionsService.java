import	java.util.UUID;

class	TransactionsService {
	UsersList	users;

	public	TransactionsService() {
		this.users = new UsersArrayList();
	}

	public void	addUser(User usr) {
		this.users.addUser(usr);
	}

	public int	getUserBalance(int id) {
		return (this.users.searchUserByID(id).getBalance());
	}

	public void	createTransaction(int senderID, int recipientID, int amount) {
		User		sender;
		User		recipient;
		Transaction	tr;
		
		if (amount < 0) {
			throw new InvalidOperationException("Cannot pass a negative amount");
		}
		sender = this.users.searchUserByID(senderID);
		recipient = this.users.searchUserByID(recipientID);
		tr = new Transaction(sender, recipient, TransferCategory.DEBIT, amount);
		sender.getTransactions().addTransaction(tr);
		tr = new Transaction(tr);
		tr.setTC(TransferCategory.CREDIT);
		recipient.getTransactions().addTransaction(tr);
		sender.setBalance(sender.getBalance() - amount);
		recipient.setBalance(recipient.getBalance() + amount);
	}

	public Transaction[]	getUserTransactions(int userID) {
		User	user;

		user = this.users.searchUserByID(userID);
		return (user.getTransactions().toArray());
	}

	public void	removeUserTransaction(int userID, UUID transactionID) {
		User	user;

		user = this.users.searchUserByID(userID);
		user.getTransactions().removeTransaction(transactionID);
	}

	public Transaction[]	checkValidity() {
		TransactionsList	tmp;
		Transaction			transactionsArray[];
		int					noUsers;

		tmp = new TransactionsLinkedList();
		noUsers = this.users.getNoUsers();
		for (int i = 0; i < noUsers; i++) {
			transactionsArray = this.users.searchUserByIndex(i).getTransactions().toArray();
			for (Transaction tr : transactionsArray) {
				if (!tr.getRecipient().getTransactions().findTransaction(tr.getID())) {
					tmp.addTransaction(tr);
				}
				if (!tr.getSender().getTransactions().findTransaction(tr.getID())) {
					tmp.addTransaction(tr);
				}
			}
		}
		return (tmp.toArray());
	}
}
