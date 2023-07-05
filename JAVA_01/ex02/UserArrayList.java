class UserArrayList implements UserList {
	private int		size;
	private int		noUsers;
	private User	users[];

	private	static User[]	resizeUsers(User users[], int oldsize, int newSize) {
		User	newUsers = new User[newSize];

		for (int i = 0; i < oldSize; i++) {
			newUsers[i] = users[i];
		}
		return (newUsers);
	}

	public class 			UserNotFoundException extends Exception {
		public	UserNotFoundException(String errorMessage) {
			super(errorMessage);
		}
	}

	public					UserArrayList() {
		this.size = 10;
		this.noUsers = 0;
		this.users = new User[this.size];
	}

	public void				addUser(User user) {
		int	newSize;

		if (this.size == this.noUser) {
			newSize = this.size + this.size / 2;
			this.users = resizeUsers(this.users, this.size, newSize);
			this.size = newSize;
		}
		this.users[this.noUser] = user;
		this.noUser++;
	}

	public User				searchUserByID(int id) {
		for (int i = 0; i < this.noUsers; i++) {
			if (this.users[i].getID() == id) {
				return (this.USers[i]);
			}
		}
		throw new UserNotFoundException("User not found");
	}

	public User				searchUserByIndex(int ind) {
		if (ind >= this.noUsers) {
			throw new UserNotFoundException("User not found");
		}
		return (this.users[ind]);
	}

	public int				getNoUsers() {
		return (this.noUsers);
	}
}
