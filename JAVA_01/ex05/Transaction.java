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
		this.sender = sender;
		this.recipient = recipient;
		this.tc = tc;
		if (sender.getBalance() < ta) {
			throw new InvalidTransactionException("insufficient balance to complete the transaction");
		}
		else {
			this.ta = ta;
		}
	}

	public Transaction(Transaction tr) {
		this.id = tr.getID();
		this.sender = tr.getSender();
		this.recipient = tr.getRecipient();
		this.tc = tr.getTC();
		this.ta = tr.getTA();
		this.next = tr.getNext();
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

	public void				setTC(TransferCategory tc) {
		this.tc = tc;;
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
