package	edu.Roma42.models;
import	java.awt.Color;
import	java.util.Objects;

public class Tile {
	Color	bgColor;
	char	type;

	public	Tile(Color bgColor, char type) {
		this.bgColor = bgColor;
		this.type = type;
	}

	public Color	getBgColor() {
		return (this.bgColor);
	}

	public char	getType() {
		return (this.type);
	}

	public void	setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public void	setType(char type) {
		this.type = type;
	}

	@Override
	public String	toString() {
		return ("type: " + this.type + ", background color: " + this.bgColor);
	}

	@Override
	public boolean	equals(Object obj) {
		Tile	tmpTile;

		if (this == obj) {
			return (true);
		} else if (!(obj instanceof Tile)) {
			return (false);
		}
		tmpTile = (Tile) obj;
		if (this.bgColor.equals(tmpTile.getBgColor()) && this.type == tmpTile.getType()) {
			return (true);
		}
		return (false);
	}

	@Override
	public int	hashCode() {
		return (Objects.hash(this.bgColor, this.type));
	}
}
