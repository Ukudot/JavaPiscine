class	Program {
	static public void	main(String args[]) {
		User		sen = new User("Giovanni", 1000);
		User		rec = new User("Marco", 1000);
		Transaction	trans = new Transaction(sen, rec, Transaction.TransferCategory.DEBIT, 500);

		System.out.println(trans);

		System.out.println("------------------------------------------");
		System.out.println("User tests");

		User giovanni = new User("Giovanni", -123);

		System.out.println(giovanni);
		giovanni.setName("gpanico");
		giovanni.setID(12);
		giovanni.setBalance(123);
		System.out.println(giovanni);
	}
}
