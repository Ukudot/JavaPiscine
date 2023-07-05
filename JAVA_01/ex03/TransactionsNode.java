class	TransactionsNode {
	private	Transaction			data;
	private TransactionsNode	next;
	private TransactionsNode	prev;

	public TransactionsNode(Transaction data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	public Transaction	getData() {
		return (this.data);
	}

	public TransactionsNode	getNext() {
		return (this.next);
	}

	public TransactionsNode	getPrev() {
		return (this.prev);
	}

	public void	setData(Transaction data) {
		this.data = data;
	}

	public void	setNext(TransactionsNode next) {
		this.next = next;
	}

	public void	setPrev(TransactionsNode prev) {
		this.prev = prev;
	}

	@Override
	public String	toString() {
		return (this.data);
	}
}
