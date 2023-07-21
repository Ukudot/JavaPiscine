package	edu.Roma42.repositories;
import	edu.Roma42.models.Product;
import	org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import	org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import	org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import static	org.junit.jupiter.api.Assertions.assertNotNull;
import static	org.junit.jupiter.api.Assertions.assertTrue;
import static	org.junit.jupiter.api.Assertions.assertEquals;
import static	org.junit.jupiter.api.Assertions.assertThrows;
import	org.junit.jupiter.api.BeforeEach;
import	org.junit.jupiter.api.AfterEach;
import	org.junit.jupiter.api.Test;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.SQLException;
import	java.util.Arrays;
import	java.util.List;
import	java.util.ArrayList;

public class	ProductsRepositoryJdbcImplTest {
	final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<Product>(Arrays.asList(
		new Product(new Long(1), "LAG", new Float(99.99)),
		new Product(new Long(2), "Panda", new Float(99.99)),
		new Product(new Long(3), "RoboNapoli", new Float(99.99)),
		new Product(new Long(4), "BiGDanno", new Float(99.99)),
		new Product(new Long(5), "Cybero", new Float(99.99)),
		new Product(new Long(6), "Scorpion", new Float(99.99)),
		new Product(new Long(7), "Devastator", new Float(99.99)),
		new Product(new Long(8), "Dark Franco", new Float(99.99)),
		new Product(new Long(9), "Arancino", new Float(99.99)),
		new Product(new Long(10), "Ultimate Weapon", new Float(99.99))));
	final Product	EXPECTED_FIND_BY_ID_PRODUCT_1 = new Product(new Long(1), "LAG", new Float(99.99));
	final Product	EXPECTED_FIND_BY_ID_PRODUCT_2 = new Product(new Long(3), "RoboNapoli", new Float(99.99));
	final Product	EXPECTED_FIND_BY_ID_PRODUCT_3 = new Product(new Long(6), "Scorpion", new Float(99.99));
	final Product	EXPECTED_UPDATED_PRODUCT = new Product(new Long(1), "L.A.G. v2.0", new Float(199.99));
	final Product	EXPECTED_SAVED_PRODUCT = new Product(new Long(13), "Gino", new Float(0.99));
	private DataSource					db;
	private ProductsRepositoryJdbcImpl	prji;

	@BeforeEach
	public void	init() throws SQLException {
		this.db = new EmbeddedDatabaseBuilder()
			.generateUniqueName(true)
			.setType(EmbeddedDatabaseType.HSQL)
			.setScriptEncoding("UTF-8")
			.ignoreFailedDrops(true)
			.addScripts("schema.sql", "data.sql")
			.build();
		this.prji = new ProductsRepositoryJdbcImpl(db);
	}

	@AfterEach
	public void	end() throws SQLException {
		this.prji.closeCon();
	}

	@Test
	void checkFindAll() throws SQLException {
		assertEquals(EXPECTED_FIND_ALL_PRODUCTS, this.prji.findAll());
	}

	@Test
	void checkFindById() throws SQLException {
		assertEquals(EXPECTED_FIND_BY_ID_PRODUCT_1, this.prji.findById(new Long(1)).get());
		assertEquals(EXPECTED_FIND_BY_ID_PRODUCT_2, this.prji.findById(new Long(3)).get());
		assertEquals(EXPECTED_FIND_BY_ID_PRODUCT_3, this.prji.findById(new Long(6)).get());
		assertThrows(ProductException.class, () -> {
			this.prji.findById(new Long(14));
		});
	}

	@Test
	void checkUpdate() throws SQLException {
		this.prji.update(EXPECTED_UPDATED_PRODUCT);
		assertEquals(EXPECTED_UPDATED_PRODUCT, this.prji.findById(EXPECTED_UPDATED_PRODUCT.getID()).get());
	}

	@Test
	void checkSave() throws SQLException {
		this.prji.save(EXPECTED_SAVED_PRODUCT);
		assertEquals(EXPECTED_SAVED_PRODUCT, this.prji.findById(EXPECTED_SAVED_PRODUCT.getID()).get());
	}

	@Test
	void checkdelete() throws SQLException {
		this.prji.delete(EXPECTED_FIND_BY_ID_PRODUCT_1.getID());
		this.prji.delete(EXPECTED_FIND_BY_ID_PRODUCT_2.getID());
		assertThrows(ProductException.class, () -> {
			this.prji.findById(EXPECTED_FIND_BY_ID_PRODUCT_1.getID());
		});
		assertThrows(ProductException.class, () -> {
			this.prji.findById(EXPECTED_FIND_BY_ID_PRODUCT_2.getID());
		});
		assertEquals(EXPECTED_FIND_BY_ID_PRODUCT_3, this.prji.findById(new Long(6)).get());
	}
}
