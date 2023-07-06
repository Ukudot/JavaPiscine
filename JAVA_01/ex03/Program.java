class	Program {
	static public void	main(String args[]) {
		UsersArrayList users = new UsersArrayList();
		User giovanni = new User("Giovanni", 1000);
		User marco = new User("Marco", 1000);
		User adistef = new User("Alessandro", 1000);
		User afraccal = new User("Alessandro", 1000);
		Transaction t1 = new Transaction(giovanni, marco, TransferCategory.DEBIT, 500);
		Transaction t2 = new Transaction(giovanni, marco, TransferCategory.CREDIT, 500);
		Transaction t3 = new Transaction(marco, giovanni, TransferCategory.DEBIT, 500);
		Transaction t4 = new Transaction(marco, giovanni, TransferCategory.CREDIT, 500);
		Transaction t5 = new Transaction(giovanni, marco, TransferCategory.DEBIT, 500);
		
		users.addUser(giovanni);
		users.addUser(marco);
		users.addUser(adistef);
		users.addUser(afraccal);
		users.print();
		try {
			System.out.println(users.searchUserByID(1));
			System.out.println(users.searchUserByIndex(1));
			System.out.println(users.searchUserByID(5));
		}
		catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(users.getNoUsers());
		users.searchUserByID(1).transactions.addTransaction(t1);
		users.searchUserByID(1).transactions.addTransaction(t4);
		users.searchUserByID(1).transactions.addTransaction(t5);
		users.searchUserByID(2).transactions.addTransaction(t3);
		users.searchUserByID(2).transactions.addTransaction(t2);
		Transaction array[] = users.searchUserByID(1).transactions.toArray();
		System.out.println("Giovanni's Transactions: ");
		for (Transaction tr : array) {
			System.out.println(tr);
		}

		array = users.searchUserByID(2).transactions.toArray();
		System.out.println("Marco's Transactions: ");
		for (Transaction tr : array) {
			System.out.println(tr);
		}
		array = users.searchUserByID(1).transactions.toArray();
		try {
			users.searchUserByID(1).transactions.removeTransaction(array[1].getID());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		array = users.searchUserByID(1).transactions.toArray();
		System.out.println("Giovanni's after removing Transactions: ");
		for (Transaction tr : array) {
			System.out.println(tr);
		}

		array = users.searchUserByID(2).transactions.toArray();
		try {
			users.searchUserByID(1).transactions.removeTransaction(array[1].getID());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		array = users.searchUserByID(1).transactions.toArray();
		System.out.println("Giovanni's after removing invalid Transactions: ");
		for (Transaction tr : array) {
			System.out.println(tr);
		}
	}
}
