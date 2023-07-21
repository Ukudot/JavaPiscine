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

class	ProductException extends RuntimeException {
	public	ProductException(String errorMessage) {
		super(errorMessage);
	}
}

public class	ProductsRepositoryJdbcImpl implements ProductsRepository {
	DataSource	db;
	Connection	con;

	public ProductsRepositoryJdbcImpl(DataSource db) throws SQLException {
		this.db = db;
		this.con = db.getConnection();
	}

	@Override
	public List<Product> findAll() {
		List<Product>	products;
		Statement		stm;
		ResultSet		rs;

		products = new ArrayList<Product>();
		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM product");
			while (rs.next()) {
				products.add(new Product(new Long(rs.getLong("identifier")), rs.getString("name"), new Float(rs.getFloat("price"))));
			}
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
			return (null);
		}
		return (products);
	}

	@Override
	public Optional<Product>	findById(Long id) {
		Product			product;
		Statement		stm;
		ResultSet		rs;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery("SELECT * FROM product WHERE identifier =" + id.longValue());
			if (!rs.next()) {
				throw new ProductException("Product with " + id.longValue() + " not found");
			}
			product = new Product(new Long(rs.getLong("identifier")), rs.getString("name"), new Float(rs.getFloat("price")));
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
			return (Optional.empty());
		}
		return (Optional.of(product));
	}

	@Override
	public void	update(Product product) {
		Statement		stm;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stm.executeUpdate("UPDATE product SET name = " + (product.getName() == null ? null : "'" + product.getName() + "'")
				   	+ ", price = " + product.getPrice().floatValue() + " WHERE identifier = " + product.getID().longValue());
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
			return ;
		}
	}

	@Override
	public void	save(Product product) {
		Statement		stm;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stm.executeUpdate("INSERT INTO product(identifier, name, price) VALUES(" + product.getID().floatValue()
					+ ", " + (product.getName() == null ? null : "'" + product.getName() + "'")
				   	+ ", " + product.getPrice().floatValue() + ")");
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
			return ;
		}
	}

	@Override
	public void	delete(Long id) {
		Statement		stm;
		ResultSet		rs;

		try {
			stm = this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stm.executeUpdate("DELETE FROM product WHERE identifier = " + id.longValue());
			stm.close();
		} catch (SQLException e) {
			System.err.println(e);
			return ;
		}
	}

	public void	closeCon() throws SQLException {
		this.con.close();
	}
}
