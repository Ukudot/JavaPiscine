package	edu.Roma42.managers;
import	edu.Roma42.annotations.*;
import	edu.Roma42.exceptions.AnnotationNotFoundException;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.ResultSet;
import	java.sql.SQLException;
import	java.sql.SQLWarning;
import	java.util.Map;
import	java.util.HashMap;
import	java.lang.annotation.Annotation;
import	java.lang.reflect.Field;
import	java.lang.reflect.Method;
import	java.lang.reflect.InvocationTargetException;
import	java.util.StringJoiner;


public class	OrmManager {
	private final String				schema = "orm";
	private DataSource					ds;
	private Connection					con;
	private String						tableName;
	private	Map<String, String>			columns;
	private	Map<String, String>			columnsObject;
	private Map<String, String>	types = new HashMap<String, String>();

	public	OrmManager() {
		this.types.put("Integer", "INT");
		this.types.put("Long", "BIGINT");
		this.types.put("String", "VARCHAR");
	}

	public	OrmManager(DataSource ds) throws SQLException {
		Statement	stm;
		SQLWarning	warning;

		this.ds = ds;
		this.con = ds.getConnection();
		this.types.put("Integer", "INT");
		this.types.put("Long", "BIGINT");
		this.types.put("String", "VARCHAR");
		stm = this.con.createStatement();
		System.out.println(">> log: [input] DROP SCHEMA IF EXISTS " + this.schema + " CASCADE");
		stm.executeQuery("DROP SCHEMA IF EXISTS " + this.schema + " CASCADE");
		warning = stm.getWarnings();
		while (warning != null) {
			System.out.println(">> log: [warning] " + warning.getMessage());
			warning = warning.getNextWarning();
		}
		System.out.println(">> log: [input] CREATE SCHEMA IF NOT EXISTS " + this.schema);
		stm.executeQuery("CREATE SCHEMA IF NOT EXISTS " + this.schema);
		warning = stm.getWarnings();
		while (warning != null) {
			System.out.println(">> log: [warning] " + warning.getMessage());
			warning = warning.getNextWarning();
		}
		stm.close();
	}

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
		try {
			this.tableName = this.schema + "." + tmpAnnotation.annotationType().getMethod("table").invoke(tmpAnnotation).toString();
			for (Field field : classEntity.getDeclaredFields()) {
				tmpAnnotation = field.getAnnotation(OrmColumnId.class);
				if (tmpAnnotation != null) {
					this.columns.put(field.getName(), "BIGSERIAL");
					field.setAccessible(true);
					this.columnsObject.put(field.getName(), field.get(entity).toString());
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
				} else {
					this.columns.put(name, type);
				}
				field.setAccessible(true);
				this.columnsObject.put(name, field.get(entity).toString());
			}
		} catch (Exception e) {
			System.err.println(e);
			return ;
		}
	}

	private void	createTable() throws SQLException {
		String			query;
		StringJoiner	sj;
		Statement		stm;
		SQLWarning		warning;

		sj = new StringJoiner(", ", "CREATE TABLE IF NOT EXISTS " + this.tableName + "( ", ")");
		this.columns.forEach((k, v) -> sj.add(k + " " + v));
		query = sj.toString();
		System.out.println(">> log: [input] " + query);
		stm = this.con.createStatement();
		stm.executeUpdate(query);
		warning = stm.getWarnings();
		while (warning != null) {
			System.out.println(">> log: [warning] " + warning.getMessage());
			warning = warning.getNextWarning();
		}
		stm.close();
	}

	public void	save(Object entity) {
		StringJoiner	insert;
		StringJoiner	values;
		Statement		stm;
		SQLWarning		warning;

		try {
			processAnnotation(entity);
			createTable();
			insert = new StringJoiner(", ", "INSERT INTO " + this.tableName + "(", ")");	
			values = new StringJoiner(", ", "VALUES(", ")");
			this.columnsObject.forEach((k, v) -> {
				if (this.columns.get(k).equals("BIGSERIAL")) {
				}
				else if (this.columns.get(k).equals("INT") || this.columns.get(k).equals("BIGINT")) {
					insert.add(k);
					values.add(v);
				} else {
					insert.add(k);
					values.add(v == null ? v : "'" + v + "'");
				}
			});
			System.out.println(">> log: [input] " + insert + " " + values);
			stm = this.con.createStatement();
			stm.executeQuery(insert.toString() + " " + values.toString());
			warning = stm.getWarnings();
			while (warning != null) {
				System.out.println(">> log: [warning] " + warning.getMessage());
				warning = warning.getNextWarning();
			}
			stm.close();
		} catch (AnnotationNotFoundException e) {
			System.err.println(e);
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void	update(Object entity) {
		StringJoiner	update;
		Statement		stm;
		StringBuilder	id;
		StringBuilder	idValue;
		SQLWarning		warning;

		try {
			processAnnotation(entity);
			update = new StringJoiner(", ", "UPDATE " + this.tableName + " SET ", "");
			id = new StringBuilder();
			idValue = new StringBuilder();
			this.columnsObject.forEach((k, v) -> {
				if (this.columns.get(k).equals("BIGSERIAL")) {
					id.append(k);
					idValue.append(v);
				}
				else if (this.columns.get(k).equals("INT") || this.columns.get(k).equals("BIGINT")) {
					update.add(k + " = " + v);
				} else {
					update.add(k + " = " + (v == null ? null : "'" + v + "'"));
				}
			});
			System.out.println(">> log: [input] " + update + " WHERE " + id + " = " + idValue);
			stm = this.con.createStatement();
			stm.executeUpdate(update.toString() + " WHERE " + id.toString() + " = " + idValue.toString());
			warning = stm.getWarnings();
			while (warning != null) {
				System.out.println(">> log: [warning] " + warning.getMessage());
				warning = warning.getNextWarning();
			}
			stm.close();
		} catch (AnnotationNotFoundException e) {
			System.err.println(e); 
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public <T> T findById(Long id, Class<T> aClass) {

		return (null);
	}
}
