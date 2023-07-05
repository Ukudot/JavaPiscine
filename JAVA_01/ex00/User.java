class	User {
	private	String	identifier;
	private String	name;
	private int		balance;

	public User(int balance) {
		if (balance < 0)
			
		this.balance	= balance;
	}

	public User(String name, int balance) {
		this.name		= name;
		this.balance	= balance;
	}

	public String	getID(void) {
		return (this.identifier);
	}

	public String	getName(void) {
		return (this.name);
	}

	public int		getBalance(void) {
		return (this.balance);
	}

	public void		setID(String identifier) {
		this.identifier = identidier;
	}

	public void		setName(String name) {
		this.name = name;
	}

	public void		setBalance(int balance) {
		this.balance = balance;
	}
}
