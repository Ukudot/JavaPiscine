package	edu.Roma42.repositories;
import	edu.Roma42.models.Tile;

public interface	TilesRepository	{
	public Tile	findByType(String type);
}
