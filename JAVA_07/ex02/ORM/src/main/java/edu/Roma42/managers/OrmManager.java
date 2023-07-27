package	edu.Roma42.managers;

public interface	OrmManager {
	public void	save(Object entity);
	public void	update(Object entity);
	public <T> T findById(Long id, Class<T> aClass);
}
