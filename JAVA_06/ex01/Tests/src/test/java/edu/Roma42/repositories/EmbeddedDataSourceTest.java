package	edu.Roma42.repositories;
import	org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import	org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import	org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import static	org.junit.jupiter.api.Assertions.assertNotNull;
import static	org.junit.jupiter.api.Assertions.assertTrue;
import	org.junit.jupiter.api.BeforeEach;
import	org.junit.jupiter.api.RepeatedTest;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.SQLException;

public class	EmbeddedDataSourceTest {
	DataSource	db;

	@BeforeEach
	public void	init() {
		this.db = new EmbeddedDatabaseBuilder()
			.generateUniqueName(true)
			.setType(EmbeddedDatabaseType.HSQL)
			.setScriptEncoding("UTF-8")
			.ignoreFailedDrops(true)
			.addScripts("schema.sql", "data.sql")
			.build();
	}

	@RepeatedTest(5)
	void checkConnection() throws SQLException {
		Connection	con;

		con = this.db.getConnection();
		assertNotNull(con);
		assertTrue(con.isValid(0));
	}
}
