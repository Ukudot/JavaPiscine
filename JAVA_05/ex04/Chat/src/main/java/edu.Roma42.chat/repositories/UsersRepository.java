package	edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.User;
import	java.util.List;

public interface	UsersRepository {
	List<User>	findAll(int page, int size);
}
