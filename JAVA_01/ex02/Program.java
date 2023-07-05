class	Program {
	static public void	main(String args[]) {
		UsersArrayList users = new UsersArrayList();
		User giovanni = new User("Giovanni", 1000);
		User marco = new User("Marco", 1000);
		User adistef = new User("Alessandro", 1000);
		User afraccal = new User("Alessandro", 1000);
		
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
	}
}
