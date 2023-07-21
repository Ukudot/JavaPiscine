package	edu.Roma42.models;
import	java.util.Objects;

public class	Product {
	Long	id;
	String	name;
	Float	price;

	public	Product() {
		this.id = new Long(null);
		this.name = null;
		this.price = new Float(null);
	}

	public	Product(Long id, String name, Float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long	getID() {
		return (this.id);
	}

	public String	getName() {
		return (this.name);
	}
	
	public Float	getPrice() {
		return (this.price);
	}

	public void	setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void	setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String	toString() {
		return ("Product: " + this.name + " (" + this.id + "), price: " + this.price + "$");
	}

	@Override
	public boolean	equals(Object obj) {
		Product	tmpProduct;
		if (this == obj) {
			return (true);
		} else if (!(obj instanceof Product)) {
			return (false);
		}
		tmpProduct = (Product) obj;
		if (this.id.equals(tmpProduct.getID()) && this.name.equals(tmpProduct.getName())
				&& this.price.equals(tmpProduct.getPrice())) {
			return (true);
		}
		return (false);
	}

	@Override
	public int	hashCode() {
		return (Objects.hash(this.id, this.name, this.price));
	}
}
