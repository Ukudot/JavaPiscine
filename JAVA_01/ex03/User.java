class	User {
	private	int					identifier;
	private String				name;
	private int					balance;
	private TransactionsList	transactions;

	public User(int balance) {
		this.identifier = UserIdsGenerator.getInstance().generateId();
		this.transactions = new TransactionsLinkedList();
		if (balance < 0) {
			System.out.println("Error: balance cannot be negative, it will be set at 0 by default");
			this.balance = 0;
		}
		else {
			this.balance = balance;
		}
	}

	public User(String name, int balance) {
		this(balance);
		this.name = name;
	}

	public int		getID() {
		return (this.identifier);
	}

	public String	getName() {
		return (this.name);
	}

	public int		getBalance() {
		return (this.balance);
	}

	public void		setName(String name) {
		this.name = name;
	}

	public void		setBalance(int balance) {
		if (balance < 0) {
			System.out.println("Error: balance cannot be negative");
			return ;
		}
		this.balance = balance;
	}

	public TransactionsList	getTransactions() {
		return (this.transactions);
	}
	@Override
	public String toString() {
		return ("Name: " + this.name + "\nID: " + this.identifier + "\nBalance: " + this.balance);
	}
}
