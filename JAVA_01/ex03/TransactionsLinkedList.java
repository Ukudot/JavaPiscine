class TransactionsLinkedList implements TransactionsList {
	private TransactionsNode	head;
	private	int					size;

	public	TransactionsLinkedList() {
		this.head = null;
		this.size = 0;
	}

	public void	addTransaction(Transaction trs) {
		Transaction	tmp = new TransactionsNode(trs);
		if (this.head != null) {
			this.head.setPrev(tmp);
			this.head.getPrev().setNext(this.head);
		}
		this.head = tmp;
		this.size++;
	}
}
