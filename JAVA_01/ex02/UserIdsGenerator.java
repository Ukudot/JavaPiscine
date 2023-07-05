class UserIdsGenerator {
	static private int				id;
	static private UserIdsGenerator	instance  = null;

	private UserIdsGenerator() {
		this.id = 0;
	}

	static public UserIdsGenerator	getInstance() {
		if (instance == null) {
			instance = new UserIdsGenerator();
		}
		return (instance);
	}

	public int						generateId() {
		return (++id);
	}
}
