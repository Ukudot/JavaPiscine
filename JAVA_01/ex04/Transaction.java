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
		if (sender.getBalance() < ta) {
			System.out.println("Error: insufficient credit, cannot complete the operation; the transfer amount is setted to 0 by default");
			this.ta = ta;
		}
		else {
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
