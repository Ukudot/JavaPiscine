interface	TransactionList {
	public void				addTransaction(Transaction trs);
	public void				removeTransanction(UUID id);
	public Transaction[]	toArray();
}
