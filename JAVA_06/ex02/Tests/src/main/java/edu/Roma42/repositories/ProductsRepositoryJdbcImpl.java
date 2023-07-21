package	edu.Roma42.repositories;
import	edu.Roma42.models.Product;
import	java.util.List;
import	java.util.ArrayList;
import	java.util.Optional;
import	javax.sql.DataSource;
import	java.sql.Connection;
import	java.sql.Statement;
import	java.sql.ResultSet;
import	java.sql.SQLException;

public class	ProductsRepositoriesJdbcImpl extends ProductsRepository {
	DataSource	db;
	Connection	con;

	public class	ProductNotFoundException extends RuntimeException {
		public	ProductNotFoundException(String errorMessage) {
			super(errorMessage);
		}
	}

	public ProductsRepositoriesJdbcImpl(DataSource db) throws SQLException {
		this.db = db;
		this.con = db.getConnection();
	}

	@Override
	List<Product> findAll() {
		List<Product>	products;
		Statement		stm;
		ResultSet		rs;

		products = new ArrayList<Product>();
		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM products");
			while (rs.next()) {
				products.add(new Product(new Long(rs.getLong("identifier")), rs.getString("name"), new Float(rs.getFloat("price"))));
			}
			stm.close();
		} catch (SQLException) {
			System.err.println(e);
			return (null);
		}
		return (products);
	}

	@Override
	Optional<Product>	findById(Long id) {
		Product			product;
		Statement		stm;
		ResultSet		rs;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM products WHERE id=" + id.longValue());
			if (!rs.next()) {
				throw new ProductNotFoundException("Product with " + id.longValue() + " not found");
			}
			product = new Product(new Long(rs.getLong("identifier")), rs.getString("name"), new Float(rs.getFloat("price")));
			stm.close();
		} catch (SQLException) {
			System.err.println(e);
			return (Optional.empty());
		}
		return (Optional.of(product));
	}

	@Override
	void	update(Product product) {
		Product			product;
		Statement		stm;
		ResultSet		rs;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM products WHERE id=" + id.longValue());
			if (!rs.next()) {
				throw new ProductNotFoundException("Product with " + id.longValue() + " not found");
			}
			product = new Product(new Long(rs.getLong("identifier")), rs.getString("name"), new Float(rs.getFloat("price")));
			stm.close();
		} catch (SQLException) {
			System.err.println(e);
			return (Optional.empty());
		}
		
	}
	public void	closeCon() throws SQLException {
		this.con.close();
	}
}
