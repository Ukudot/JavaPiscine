class	Program {
	static public void	main(String args[]) {
		TransactionsService	ts = new TransactionsService();
		Transaction			trs[];
		User				giovanni = new User("Giovanni", 1000);
		User				marco = new User("Marco", 2000);
		User				adistef = new User("Adistef", 3000);
		User				afraccal = new User("Afraccal", 4000);

		ts.addUser(giovanni);
		ts.addUser(marco);
		ts.addUser(adistef);
		ts.addUser(afraccal);
		System.out.println("Before: ");
		System.out.println("Marco's balance: " + ts.getUserBalance(marco.getID()));
		System.out.println("Giovanni's balance: " + ts.getUserBalance(giovanni.getID()));
		System.out.println("Adistef's balance: " + ts.getUserBalance(adistef.getID()));
		System.out.println("Afraccal's balance: " + ts.getUserBalance(afraccal.getID()));
		ts.createTransaction(marco.getID(), giovanni.getID(), 1000);
		ts.createTransaction(adistef.getID(), afraccal.getID(), 1000);
		ts.createTransaction(afraccal.getID(), marco.getID(), 2000);
		System.out.println("------------------------------------------------");
		System.out.println("After: ");
		System.out.println("Marco's balance: " + ts.getUserBalance(marco.getID()));
		System.out.println("Giovanni's balance: " + ts.getUserBalance(giovanni.getID()));
		System.out.println("Adistef's balance: " + ts.getUserBalance(adistef.getID()));
		System.out.println("Afraccal's balance: " + ts.getUserBalance(afraccal.getID()));
		System.out.println("------------------------------------------------");
		trs = ts.getUserTransactions(marco.getID());
		System.out.println("Marco's transactions: ");
		for (Transaction tr : trs) {
			System.out.println(tr);
		}
		System.out.println("------------------------------------------------");
		System.out.println("check validity: ");
		trs = ts.checkValidity();
		if (trs == null) {
			System.out.println("Nothing to report");
		} else {
			for (Transaction tr : trs) {
				System.out.println(tr);
			}
		}
		System.out.println("------------------------------------------------");
		trs = ts.getUserTransactions(marco.getID());
		ts.removeUserTransaction(marco.getID(), trs[1].getID());
		trs = ts.getUserTransactions(marco.getID());
		System.out.println("Marco's transactions after remove: ");
		for (Transaction tr : trs) {
			System.out.println(tr);
		}
		System.out.println("------------------------------------------------");
		System.out.println("check validity: ");
		trs = ts.checkValidity();
		for (Transaction tr : trs) {
			System.out.println(tr);
		}
		System.out.println("------------------------------------------------");
	}
}
