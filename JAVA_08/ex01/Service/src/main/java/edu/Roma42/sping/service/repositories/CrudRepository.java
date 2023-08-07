package	edu.Roma42.spring.service.repositories;
import	java.util.List;

public interface	CrudRepository<T> {
	public T findById(Long id);
	List<T>	findAll();
	void	save(T entity);
	void	update(T entity);
	void	delete(Long id);
}
