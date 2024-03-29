import java.util.UUID;

class	Transaction {
	static enum		TransferCategory {
		DEBIT,
		CREDIT
	}

	private UUID				id;
	private User				recipient;
	private User				sender;
	private TransferCategory	tc;
	private int					ta;

	public Transaction(User sender, User recipient, TransferCategory tc, int ta) {
		this.id = UUID.randomUUID();
		this.recipient = recipient;
		this.sender = sender;
		this.tc = tc;
		if ((tc == TransferCategory.CREDIT && ta < 0) || (tc == TransferCategory.DEBIT && ta > 0)) {
			System.out.println("Error: invalid amount for this type of transaction; the transfer amount is setted to 0 by default");
			this.ta = 0;
		} else if (sender.getBalance() < ta) {
			System.out.println("Error: insufficient credit, cannot complete the operation; the transfer amount is setted to 0 by default");
			this.ta = 0;
		} else {
			this.ta = ta;
		}
	}
	
	public UUID				getID() {
		return (this.id);
	}
	
	public User				getRecipient() {
		return (this.recipient);
	}
	
	public User				getSender() {
		return (this.sender);
	}
	
	public TransferCategory	getTC() {
		return (this.tc);
	}
	
	public int				getTA() {
		return (this.ta);
	}

	@Override
	public String	toString() {
		return ("Transacion id " + this.id.toString() + ", type " + this.tc + ", amount " + this.ta
			+ "\nSender:\n" + this.sender + "\nRecipient:\n" + this.recipient);
	}
}
