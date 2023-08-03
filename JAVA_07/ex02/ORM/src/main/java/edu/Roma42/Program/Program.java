package	edu.Roma42.Program;
import	edu.Roma42.models.User;
import	edu.Roma42.managers.OrmManager;

public class	Program	{
	public static void	main(String args[]) {
		User		user;
		OrmManager	ormManager;

		user = new User(new Long(1), "Giovanni", "Panico", 24);
		ormManager = new OrmManager();
		ormManager.save(user);
		ormManager.update(user);
	}
}
