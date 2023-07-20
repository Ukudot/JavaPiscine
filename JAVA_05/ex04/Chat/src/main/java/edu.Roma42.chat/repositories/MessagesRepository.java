package	edu.Roma42.chat.repositories;
import	edu.Roma42.chat.models.Message;
import	edu.Roma42.chat.exceptions.NotSavedSubEntityException;
import	java.util.Optional;

public interface	MessagesRepository {
	public Optional<Message>	findById(long id);
	public void	save(Message message) throws NotSavedSubEntityException;
}
