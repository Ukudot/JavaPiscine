package	edu.Roma42.services;
import	edu.Roma42.models.MyMap;
import	edu.Roma42.models.Tile;
import	edu.Roma42.repositories.TilesRepository;
import	java.util.Scanner;
import	java.util.List;
import	java.util.ArrayList;
import	java.awt.Color;
import	java.util.Random;
import	java.util.Arrays;

public class	MapServiceImpl implements MapService {
	private TilesRepository	ts;
	private MyMap			map;
	private	int				countEnemy;
	private static int		mat[][];

	private static void	dfs(int sx, int sy, int size, boolean valid[][]) {
		if (sx < 0 || sy < 0 || sx >= size || sy >= size || valid[sy][sx]) {
			return ;
		}
		valid[sy][sx] = true;
		dfs(sx, sy + 1, size, valid);
		dfs(sx, sy - 1, size, valid);
		dfs(sx + 1, sy, size, valid);
		dfs(sx - 1, sy, size, valid);
	}

	private static boolean	pathFinder(int start[], int end[], int size) {
		boolean	valid[][];

		valid = new boolean[size][size];
		for (boolean row[] : valid) {
			Arrays.fill(row, false);
		}
		dfs(start[1], start[0], size, valid);
		if (valid[end[0]][end[1]]) {
			return (true);
		}
		return (false);
	}

	private static List<int[]>	generatePositions(int size) {
		List<int[]>	positions;
		int			pos[];

		positions = new ArrayList<int[]>();
		pos = new int[2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				pos[0] = i;
				pos[1] = j;
				positions.add(Arrays.copyOf(pos, pos.length));
			}
		}
		return (positions);
	}

	private static Tile[][]	generateBlankTiles(TilesRepository ts, int size) {
		Tile	tiles[][];
		Tile	blank;

		tiles = new Tile[size][size];
		blank = ts.findByType("empty");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tiles[i][j] = blank;
			}
		}
		return (tiles);
	}

	private static boolean	randomMat(List<int[]> poolPositions, int obstacleNumber, int start[], int end[]) {
		Random	rand = new Random();
		int		idx;
		int		pos[];

		while (obstacleNumber > 0 && poolPositions.size() > 0) {
			idx = rand.nextInt(poolPositions.size());
			pos = poolPositions.get(idx);
			poolPositions.remove(idx);
			mat[pos[0]][pos[1]] = 1;
			if (pathFinder(start, end, mat[0].length)) {
				if (randomMat(poolPositions, obstacleNumber - 1, start, end)) {
					return (true);
				}
			mat[pos[0]][pos[1]] = 0;
			}
		}
		if (obstacleNumber == 0) {
			return (true);
		}
		return (false);
	}

	private static void		fillTiles(TilesRepository ts, int ce, int cw, Tile tiles[][]) {
		Random	rand;
		Tile	enemy;
		Tile	wall;
		int		size;
		int		n;

		enemy = ts.findByType("enemy");
		wall = ts.findByType("wall");
		rand = new Random();
		size = mat[0].length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (mat[i][j] == 1) {
					if (ce == 0 || cw == 0) {
						tiles[i][j] = ce == 0 ? wall : enemy;
						continue;
					}
					n = rand.nextInt(2);
					if (n == 0) {
						tiles[i][j] = enemy;
						ce--;
					} else {
						tiles[i][j] = wall;
						cw--;
					}
				}
			}
		}
	}

	private	static MyMap	generateMap(TilesRepository ts, int ce, int cw, int size) {
		List<int[]>	poolPositions;
		Tile		tiles[][];
		MyMap		map;
		Random		rand;
		int			idx;
		int			pos[];

		map = new MyMap();
		map.setSize(size);
		rand = new Random();
		poolPositions = generatePositions(size);
		tiles = generateBlankTiles(ts, size);
		idx = rand.nextInt(poolPositions.size());
		pos = poolPositions.get(idx);
		poolPositions.remove(idx);
		map.setPlayerPos(pos);
		tiles[pos[0]][pos[1]] = ts.findByType("player");
		idx = rand.nextInt(poolPositions.size());
		pos = poolPositions.get(idx);
		poolPositions.remove(idx);
		map.setTargetPos(pos);
		tiles[pos[0]][pos[1]] = ts.findByType("goal");
		if (!randomMat(poolPositions, ce + cw, map.getPlayerPos(), map.getTargetPos())) {
			System.err.println("Error: cannot find a valid map with passed values");
			System.exit(-1);
		}
		fillTiles(ts, ce, cw, tiles);
		map.setMap(tiles);
		return (map);
	}

	public	MapServiceImpl(TilesRepository ts, int countEnemy, int countWalls, int size) {
		mat = new int[size][size];
		for (int [] row : mat) {
			Arrays.fill(row, 0);
		}
		this.ts = ts;
		this.countEnemy = countEnemy;
		this.map = generateMap(ts, countEnemy, countWalls, size);
		System.out.println(this.map);
	}


	public boolean	update(int move) {return true;}
	public boolean	updateDev(int move, Scanner scanner) {return true;}
	public void	display() {}
}
