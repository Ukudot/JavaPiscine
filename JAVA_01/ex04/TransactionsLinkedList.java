import java.util.UUID;

class TransactionsLinkedList implements TransactionsList {
	private Transaction	head;
	private	int			size;

	public	TransactionsLinkedList() {
		this.head = null;
		this.size = 0;
	}

	public boolean	findTransaction(UUID id) {
		Transaction	tmp;

		tmp = this.head;
		while (tmp != null) {
			if (tmp.getID() == id) {
				return (true);
			}
			tmp = tmp.getNext();
		}
		return (false);
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
			this.size--;
			return ;
		}
		while (tmp.getNext() != null) {
			if (tmp.getNext().getID() == id) {
				tmp.setNext(tmp.getNext().getNext());
				this.size--;
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

		if (this.size == 0) {
			return (null);
		}
		array = new Transaction[this.size];
		tmp = this.head;
		for (int i = 0; i < this.size; i++) {
			array[i] = tmp;
			tmp = tmp.getNext();
		}
		return (array);
	}
}
