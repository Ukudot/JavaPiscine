package	edu.Roma42.repositories;
import	edu.Roma42.models.Tile;
import	edu.Roma42.services.MapServiceImpl;
import	java.awt.Color;
import	org.junit.jupiter.api.Test;
import static	org.junit.jupiter.api.Assertions.assertEquals;

public class	TilesRepositoryImplTest {
	final Tile	EXPECTED_FIND_BY_TYPE = new Tile(Color.RED, 'X');

	@Test
	public void	testFindByType() {
		TilesRepository ts;
		Tile			found;

		ts = new TilesRepositoryImpl("application-production.properties");
		found = ts.findByType("enemy");
		MapServiceImpl msi = new MapServiceImpl(ts, 2, 11, 8);
		msi.display(true, false);
		assertEquals(EXPECTED_FIND_BY_TYPE, found);
	}
}
