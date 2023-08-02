package	edu.Roma42.managers;
import	edu.Roma42.annotations.*;
import	edu.Roma42.exceptions.AnnotationNotFoundException;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.SQLException;
import	java.util.Map;
import	java.util.HashMap;
import	java.lang.annotation.Annotation;
import	java.lang.reflect.Field;
import	java.lang.reflect.Method;
import	java.lang.reflect.InvocationTargetException;
import	java.util.StringJoiner;


public class	OrmManager {
	private DataSource					ds;
	private Connection					con;
	private String						tableName;
	private	Map<String, String>			columns;
	private	Map<String, String>			columnsObject;
	private Map<String, String>	types = new HashMap<String, String>();

	private void	processAnnotation(Object entity) throws AnnotationNotFoundException {
		Class		classEntity;
		Annotation	tmpAnnotation;
		String		name;
		String		length;
		String		type;

		this.columns = new HashMap<String, String>();
		this.columnsObject = new HashMap<String, String>();
		classEntity = entity.getClass();
		tmpAnnotation = classEntity.getAnnotation(OrmEntity.class);
		if (tmpAnnotation == null) {
			throw new AnnotationNotFoundException("Error: not annotated class");
		}
		System.out.println(tmpAnnotation.annotationType().toString());
		try {
			this.tableName = tmpAnnotation.annotationType().getMethod("table").invoke(tmpAnnotation).toString();
			System.out.println(">> table: " + this.tableName);
			for (Field field : classEntity.getDeclaredFields()) {
				tmpAnnotation = field.getAnnotation(OrmColumnId.class);
				if (tmpAnnotation != null) {
					this.columns.put(field.getName(), "BIGSERIAL");
					continue ;
				}
				tmpAnnotation = field.getAnnotation(OrmColumn.class);
				if (tmpAnnotation == null) {
					continue ;
				}
				name = tmpAnnotation.annotationType().getMethod("name").invoke(tmpAnnotation).toString();
				length = tmpAnnotation.annotationType().getMethod("length").invoke(tmpAnnotation).toString();
				type = this.types.get(field.getType().getSimpleName());
				if (type.equals("VARCHAR")) {
					this.columns.put(name, type + "(" + length + ")");
					this.columnsObject.put(name, "BIGSERIAL");
				} else {
					this.columns.put(name, type);
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			return ;
		}
	}

	private void	createTable() {
		String			query;
		StringJoiner	sj;

		sj = new StringJoiner(", ", "CREATE TABLE IF NOT EXISTS " + this.tableName + "( ", ")");
		this.columns.forEach((k, v) -> sj.add(k + " " + v));
		query = sj.toString();
		System.out.println(query);
	}

	public	OrmManager() {
		this.types.put("Integer", "INT");
		this.types.put("Long", "BIGINT");
		this.types.put("String", "VARCHAR");
	}

	public	OrmManager(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
		this.types.put("Integer", "INT");
		this.types.put("Long", "BIGINT");
		this.types.put("String", "VARCHAR");
	}

	public void	save(Object entity) {
		try {
			processAnnotation(entity);
			createTable();

		} catch (AnnotationNotFoundException e) {
			System.err.println(e);
		}
	}

	public void	update(Object entity) {}
	public <T> T findById(Long id, Class<T> aClass) { return (null);}
}
