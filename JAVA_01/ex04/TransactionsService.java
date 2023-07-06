class	TransactionsService {
	UsersList	users;

	public	TransactionsService() {
		this.users = new UsersArrayList();
	}

	public void	addUser(User usr) {
		this.users.addUser(usr);
	}

	public int	getUserBalance(int id) {
		return (this.users.searchUserByID(id).getBalance());
	}
}
