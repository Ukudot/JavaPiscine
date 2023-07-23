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
import	com.diogonunes.jcolor.Attribute;
import	com.diogonunes.jcolor.Ansi;


public class	MapServiceImpl implements MapService {
	private TilesRepository		ts;
	private MyMap				map;
	private	int					countEnemy;
	private static int			mat[][];
	private final static int	directions[][] = {{1, 1}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

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

	private static void	printHeader(boolean dev) {
		System.out.println("Game's instruction:");
		System.out.println("1 to move up");
		System.out.println("2 to move down");
		System.out.println("3 to move left");
		System.out.println("4 to move right");
		if (dev) {
			System.out.println("8 to move enemy");
		}
		System.out.println("9 to give up");
		System.out.println();
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
	}

	private boolean	playerCanMove() {
		int		playerPos[];
		int		counter;
		Tile	tiles[][];
		Tile	enemy;
		Tile	wall;
		int		x;
		int		y;

		playerPos = this.map.getPlayerPos();
		tiles = this.map.getMap();
		enemy = this.ts.findByType("enemy");
		wall = this.ts.findByType("wall");
		counter = 0;
		for (int i = 0; i < 4; i++) {
			y = playerPos[0] + directions[i][0];
			x = playerPos[1] + directions[i][1];
			if (x < 0 || y < 0 || x >= this.mat.length || y >= this.mat.length) {
				counter++;
			} else if (tiles[y][x].equals(enemy) || tiles[y][x].equals(wall)) {
				counter++;
			}
		}
		if (counter == 4) {
			return (false);
		}
		return (true);
	}

	private boolean	playerWin(int move) {
		int		playerPos[];
		Tile	tiles[][];
		Tile	goal;
		int		x;
		int		y;

		playerPos = this.map.getPlayerPos();
		x = playerPos[1] + directions[move][1];
		y = playerPos[0] + directions[move][0];
		if (x < 0 || y < 0 || x >= mat.length || y >= mat.length) {
			return (false);
		}
		if (move == 0) {
			return (false);
		}
		tiles = this.map.getMap();
		goal = this.ts.findByType("goal");
		if (tiles[y][x].equals(goal)) {
			return (true);
		}
		return (false);
	}

	private boolean	makeMove(int move, int pos[]) {
		Tile	tiles[][];
		Tile	empty;
		Tile	player;
		int		x;
		int		y;

		x = pos[1] + directions[move][1];
		y = pos[0] + directions[move][0];
		if (x < 0 || y < 0 || x >= mat.length || y >= mat.length) {
			return (false);
		}
		if (move == 0) {
			return (false);
		}
		empty = this.ts.findByType("empty");
		player = this.ts.findByType("player");
		tiles = this.map.getMap();
		if (tiles[y][x].equals(empty)) {
			tiles[y][x] = player;
			tiles[pos[0]][pos[1]] = empty;
			this.map.setMap(tiles);
			pos[0] = y;
			pos[1] = x;
			this.map.setPlayerPos(pos);
			return (true);
		}
		return (false);
	}

	public boolean	update(int move, Scanner scanner) {
		int		playerPos[];
		Tile	tiles[][];
		Tile	enemy;
		Tile	wall;

		playerPos = this.map.getPlayerPos();
		tiles = this.map.getMap();
		enemy = this.ts.findByType("enemy");
		wall = this.ts.findByType("wall");
		if (!this.playerCanMove()) {
			System.out.println("You lose");
			return (false);
		} else if (this.playerWin(move)) {
			System.out.println("You win");
			return (false);
		} while (!this.makeMove(move, playerPos)) {
			System.out.println("\033[2A\033[2K\rImpossible move, please enter a new movement command");	
			System.out.print("\033[2K-> ");	
			try {
				move = scanner.nextInt();
				if (!scanner.nextLine().equals("") || move < 1 || move > 4) {
					move = 0;
				}
			} catch (Exception e) {
				scanner.nextLine();
				move = 0;
			}
		}
		/*
		 * Enemy movement
		 */
		return (true);
	}

	public boolean	updateDev(int move, Scanner scanner) {return true;}
	public void	display(boolean first, boolean dev) {
		Tile	tiles[][];
		Color	color;

		if (!first) {
			System.out.print("\033[" + (mat[0].length + 2) + "A\r");
		} else {
			printHeader(dev);
		}
		tiles = this.map.getMap();
		for (Tile row[] : tiles) {
			for (Tile tile : row) {
				color = tile.getBgColor();
				System.out.print(Ansi.colorize(new Character(tile.getType()).toString(),
							Attribute.BACK_COLOR(color.getRed(), color.getGreen(), color.getBlue())));
			}
			System.out.println();
		}
	}
}
