interface	UsersList {
	public void	addUser(User user);
	public User searchUserByID(int id) throws UserNotFoundException ;
	public User	searchUserByIndex(int ind) throws UserNotFoundException ;
	public int	getNoUsers();

	static public class 			UserNotFoundException extends RuntimeException {
		public	UserNotFoundException(String errorMessage) {
			super(errorMessage);
		}
	}
}
