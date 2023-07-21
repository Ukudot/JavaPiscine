package	edu.Roma42.services;
import	edu.Roma42.repositories.UsersRepository;
import	edu.Roma42.exceptions.AlreadyAuthenticatedException;
import	edu.Roma42.models.User;


public class	UsersServiceImpl {
	private UsersRepository	usersRepo;
	
	public	UsersServiceImpl(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	public boolean	authenticate(String login, String password) {
		User	user;

		try {
			user = this.usersRepo.findByLogin(login);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			return (false);
		}
		if (user.getAuthentication()) {
			throw new AlreadyAuthenticatedException("This user is already online");
		} else if (!user.getPassword().equals(password)) {
			return (false);
		}
		user.setAuthentication(true);
		try {
			this.usersRepo.update(user);
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			return (false);
		}
		return (true);
	}
}
