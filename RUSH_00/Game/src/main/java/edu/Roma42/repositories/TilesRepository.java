package	edu.Roma42.repositories;
import	edu.Roma42.models.Tile;

interface	TilesRepository	{
	public Tile	findByType(String type);
}
