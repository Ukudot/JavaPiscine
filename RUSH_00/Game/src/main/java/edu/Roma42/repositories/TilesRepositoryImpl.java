package	edu.Roma42.repositories;
import	edu.Roma42.models.Tile;
import	java.util.Map;
import	java.util.HashMap;
import	java.io.BufferedReader;
import	java.io.InputStreamReader;
import	java.awt.Color;

public class	TilesRepositoryImpl implements TilesRepository {
	public static final String ENEMY = "enemy";
	public static final String PLAYER = "player";
	public static final String WALL = "wall";
	public static final String GOAL = "goal";
	public static final String EMPTY = "empty";
	public static final String CHAR = ".char";
	public static final String COLOR = ".color";

	private	Map<String,	Tile>	set;

	private Map<String, String> readFile(String filename) {
		BufferedReader		in;
		Map<String, String>	tmpSet;
		String				line;
		String				splitted[];


		tmpSet = new HashMap<String, String>();
		try {
			in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename)));
			line = in.readLine();
			while (line != null) {
				splitted = line.split("=");
				if (splitted.length == 1) {
					tmpSet.put(splitted[0].trim(), " ");
				} else {
					tmpSet.put(splitted[0].trim(), splitted[1].trim());
				}
				line = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error during load of " + filename + ": " + e.getMessage());
			System.exit(-1);
		}
		return (tmpSet);
	}

	public	TilesRepositoryImpl() {}

	public	TilesRepositoryImpl(String filename) {
		TilesRepositoryImpl	instance;
		Map<String, String>	tmpSet;
		Color				color;

		instance = new TilesRepositoryImpl();
		tmpSet = instance.readFile(filename);
		this.set = new HashMap<String, Tile>();
		try {
			color = (Color) Class.forName("java.awt.Color").getField(tmpSet.get(ENEMY + COLOR)).get(null);
			this.set.put(ENEMY, new Tile(color, tmpSet.get(ENEMY + CHAR).charAt(0)));
			color = (Color) Class.forName("java.awt.Color").getField(tmpSet.get(PLAYER + COLOR)).get(null);
			this.set.put(PLAYER, new Tile(color, tmpSet.get(PLAYER + CHAR).charAt(0)));
			color = (Color) Class.forName("java.awt.Color").getField(tmpSet.get(WALL + COLOR)).get(null);
			this.set.put(WALL, new Tile(color, tmpSet.get(WALL + CHAR).charAt(0)));
			color = (Color) Class.forName("java.awt.Color").getField(tmpSet.get(EMPTY + COLOR)).get(null);
			this.set.put(EMPTY, new Tile(color, tmpSet.get(EMPTY + CHAR).charAt(0)));
			color = (Color) Class.forName("java.awt.Color").getField(tmpSet.get(GOAL + COLOR)).get(null);
			this.set.put(GOAL, new Tile(color, tmpSet.get(GOAL + CHAR).charAt(0)));
		} catch (Exception e) {
			System.err.println("Error during load of " + filename + ": " + e.getMessage());
			System.exit(-1);
		}
	}

	@Override
	public Tile	findByType(String type) {
		Tile	tile;

		tile = this.set.get(type);
		return (tile);
	}
}
