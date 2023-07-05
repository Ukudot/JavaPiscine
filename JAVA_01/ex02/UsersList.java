interface	UsersList {
	public void	addUser(User user);
	public User searchUserByID(int id);
	public User	searchUserByIndex(int ind);
	public int	getNoUsers();
}
