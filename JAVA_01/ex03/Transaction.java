import java.util.UUID;

class	Transaction {
	private UUID				id;
	private User				recipient;
	private User				sender;
	private TransferCategory	tc;
	private int					ta;
	private Transaction			next;

	public Transaction(User sender, User recipient, TransferCategory tc, int ta) {
		this.id = UUID.randomUUID();
		this.recipient = recipient;
		this.sender = sender;
		this.tc = tc;
		if ((tc == TransferCategory.CREDIT && ta < 0) || (tc == TransferCategory.DEBIT && ta > 0)) {
			throw new InvalidTransactionException("Invalid amount for this type of transaction");
		} else if (sender.getBalance() < ta) {
			throw new InvalidTransactionException("Insufficient credit");
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

	public Transaction		getNext() {
		return (this.next);
	}

	public void				setNext(Transaction trs) {
		this.next = trs;
	}

	@Override
	public String	toString() {
		return ("Transacion id " + this.id.toString() + ", type " + this.tc + ", amount " + this.ta
			+ "\n\tSender: " + this.sender.getName() + "\n\tRecipient: " + this.recipient.getName());
	}
}
