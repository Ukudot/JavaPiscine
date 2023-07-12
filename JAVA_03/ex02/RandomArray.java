import	java.util.*;

class	RandomArray	{
	private	List<Integer>	array;
	boolean					generated;
	int						size;

	public	RandomArray(int size) {
		this.size = size;
		this.array = new ArrayList<Integer>(size);
		this.generated = false;
	}

	public void	generateNumbers() {
		Random	rand;

		rand = new Random();
		for (int i = 0; i < size; i++) {
			this.array.add(i, (rand.nextInt(2001) - 1000));
		}
		this.generated = true;
	}

	public ArrayList<Integer>	getArray() throws RandomArrayException {
		if (!this.generated) {
			throw new RandomArrayException("Array not generated yet");
		}
		return ((ArrayList<Integer>) this.array);
	}

	public int	getElem(int ind) throws RandomArrayException {
		if (!this.generated) {
			throw new RandomArrayException("Array not generated yet");
		}
		if (ind >= this.size) {
			throw new RandomArrayException("Out of index");
		}
		return (this.array.get(ind));
	}
}
