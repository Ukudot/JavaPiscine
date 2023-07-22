package	edu.Roma42.models;
import	java.util.Objects;

public class	Map {
	int		playerPos[];
	int		targetPos[];
	int		size;
	Tile	map[][];

	public	Map(int playerPos[], int targetPos[], int size, Tile map[][]) {
		this.playerPos = playerPos;
		this.targetPos = targetPos;
		this.size = size;
		this.map = map;
	}

	public int[]	getPlayerPos() {
		return (this.playerPos);
	}

	public int[]	getTargetPos() {
		return (this.targetPos);
	}

	public int		getSize() {
		return (this.size);
	}

	public Tile[][]	getMap() {
		return (this.map);
	}

	public void	setPlayerPos(int playerPos[]) {
		this.playerPos = playerPos;
	}

	public void	setTargetPos(int targetPos[]) {
		this.targetPos = targetPos;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMap(Tile map[][]) {
		this.map = map;
	}

	@Override
	public String	toString() {
		StringBuilder	sb;

		sb = new StringBuilder();
		for (Tile row[] : this.map) {
			for (Tile tile : row) {
				sb.append(tile.getType());
			}
			sb.append('\n');
		}
		return (sb.toString());
	}

	@Override
	public boolean	equals(Object obj) {
		Map	tmpMap;
		if (this == obj) {
			return (true);
		} else if (!(obj instanceof Map)) {
			return (false);
		}
		tmpMap = (Map) obj;
		if (this.playerPos.equals(tmpMap.getPlayerPos()) && this.targetPos.equals(tmpMap.getTargetPos())
				&& this.size == tmpMap.getSize() && this.map.equals(tmpMap.getMap())) {
			return (true);
		}
		return (false);
	}

	@Override
	public int	hashCode() {
		return (Objects.hash(this.playerPos, this.targetPos, this.size, this.map));
	}
}
