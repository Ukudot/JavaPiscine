class UsersArrayList implements UsersList {
	private int		size;
	private int		noUsers;
	private User	users[];

	private	static User[]	resizeUsers(User users[], int oldSize, int newSize) {
		User	newUsers[] = new User[newSize];

		for (int i = 0; i < oldSize; i++) {
			newUsers[i] = users[i];
		}
		return (newUsers);
	}

	public					UsersArrayList() {
		this.size = 10;
		this.noUsers = 0;
		this.users = new User[this.size];
	}

	@Override
	public void				addUser(User user) {
		int	newSize;

		if (this.size == this.noUsers) {
			newSize = this.size + this.size / 2;
			this.users = resizeUsers(this.users, this.size, newSize);
			this.size = newSize;
		}
		this.users[this.noUsers] = user;
		this.noUsers++;
	}

	@Override
	public User				searchUserByID(int id) throws UserNotFoundException {
		for (int i = 0; i < this.noUsers; i++) {
			if (this.users[i].getID() == id) {
				return (this.users[i]);
			}
		}
		throw new UserNotFoundException("User not found");
	}

	@Override
	public User				searchUserByIndex(int ind) throws UserNotFoundException {
		if (ind >= this.noUsers) {
			throw new UserNotFoundException("User not found");
		}
		return (this.users[ind]);
	}

	@Override
	public int				getNoUsers() {
		return (this.noUsers);
	}

	public void				print() {
		for (int i = 0; i < this.noUsers; i++) {
			System.out.println(this.users[i]);
		}
	}
}
