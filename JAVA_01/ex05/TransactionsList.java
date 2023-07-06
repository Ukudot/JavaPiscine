import java.util.UUID;

interface	TransactionsList {
	public void				addTransaction(Transaction trs);
	public void				removeTransaction(UUID id);
	public Transaction[]	toArray();
}
