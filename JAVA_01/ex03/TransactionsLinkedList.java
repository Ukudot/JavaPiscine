import java.util.UUID;

class TransactionsLinkedList implements TransactionsList {
	private Transaction	head;
	private	int			size;

	public	TransactionsLinkedList() {
		this.head = null;
		this.size = 0;
	}

	@Override
	public void	addTransaction(Transaction trs) {
		if (this.head != null) {
			trs.setNext(this.head);
		}
		this.head = trs;
		this.size++;
	}

	@Override
	public void removeTransaction(UUID id) {
		Transaction	tmp, prev;

		tmp = this.head;
		if (tmp.getID() == id) {
			this.head = tmp.getNext();
			return ;
		}
		while (tmp.getNext() != null) {
			if (tmp.getNext().getID() == id) {
				tmp.setNext(tmp.getNext().getNext());
				return ;
			}
			tmp = tmp.getNext();
		}
		throw new TransactionNotFoundException("Transaction not found");
	}

	@Override
	public Transaction[]	toArray() {
		Transaction	array[];
		Transaction tmp;

		array = new Transaction[this.size];
		tmp = this.head;
		for (int i = 0; i < this.size; i++) {
			array[i] = tmp;
			tmp = tmp.getNext();
		}
		return (array);
	}
}