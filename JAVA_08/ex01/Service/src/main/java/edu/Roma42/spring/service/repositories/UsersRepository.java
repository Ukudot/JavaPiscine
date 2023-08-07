package	edu.Roma42.spring.service.repositories;
import	edu.Roma42.spring.service.models.User;
import	java.util.Optional;

public interface	UsersRepository extends CrudRepository<User> {
	Optional<User>	findByEmail(String email);	
}
