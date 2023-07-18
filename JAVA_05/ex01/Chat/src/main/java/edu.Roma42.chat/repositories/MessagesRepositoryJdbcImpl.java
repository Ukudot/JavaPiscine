package	edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.Message;
import	java.util.Optional<T>;
import	javax.sql.DataSource;

class	MessageRepositoryJdbcImpl() implements MessageRepository {
	DataSource
	Optional<Message>	findById(long id);
}
