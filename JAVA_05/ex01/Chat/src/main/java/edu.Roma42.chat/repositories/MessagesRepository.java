package	edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.Message;
import	java.util.Optional<T>;

interface	MessageRepository() {
	Optional<Message>	findById(long id);
}
