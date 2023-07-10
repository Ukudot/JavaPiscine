import	java.util.*;
import	java.io.*;
import	java.lang.Math;

class	Dictionary {
	private MyFile					fileA;
	private MyFile					fileB;
	private	Map<String, Integer>	dictionary;
	private boolean					loaded;
	private	BufferedWriter			out;

	private	void	putInDictionary(Vector<String[]> content) {
		for (String[] line : content) {
			for (String word : line) {
				if (this.dictionary.containsKey(word)) {
					this.dictionary.put(word, this.dictionary.get(word) + 1);
				} else {
					this.dictionary.put(word, 1);
				}
			}
		}
	}

	private double	scalarProd(Vector<Integer> vectorA, Vector<Integer> vectorB) {
		int		size;
		double	num;

		size = vectorA.size();
		num = 0;
		for (int i = 0; i < size; i++) {
			num += vectorA.get(i) * vectorB.get(i);
		}
		return (num);
	}

	private double	calculateMod(Vector<Integer> vector) {
		int		size;
		double	mod;

		size = vector.size();
		mod = 0;
		for (int i = 0; i < size; i++) {
			mod += vector.get(i) * vector.get(i);
		}
		mod = Math.sqrt(mod);
		return (mod);
	}

	private Vector<Integer>	createOccVector(Vector<String[]> content) {
		List<Integer>	vector;
		int 			occ;

		vector = new Vector<Integer>();
		for (String key : this.dictionary.keySet()) {
			occ = 0;
			for (String[] line : content) {
				for (String word: line) {
					if (key.equals(word)) {
						occ++;
					}
				}
			}
			vector.add(occ);
		}
		return ((Vector<Integer>) vector);
	}

	public	Dictionary(String filenameA, String filenameB) {
		this.fileA = new MyFile(filenameA);
		this.fileB = new MyFile(filenameB);
		this.dictionary = new HashMap<String, Integer>();
		this.loaded = false;
	}

	public void	loadFiles() throws LoadFileException {
		this.fileA.loadFile();
		this.fileB.loadFile();
		this.loaded = true;
	}

	public void	createDictionary() throws LoadFileException {
		if (!this.loaded) {
			throw new LoadFileException("Files not loaded");
		}
		this.putInDictionary(this.fileA.getFile());
		this.putInDictionary(this.fileB.getFile());
	}

	public void	saveDictionary(String filename) throws LoadFileException, WriteDictionaryException {
		String	value;

		if (!this.loaded) {
			throw new LoadFileException("Files not loaded");
		}
		try {
			this.out = new BufferedWriter(new FileWriter(filename));
			for (String key : this.dictionary.keySet()) {
				this.out.write(key, 0, key.length());
				this.out.write(": ", 0, 2);
				value = this.dictionary.get(key).toString();
				this.out.write(value, 0, value.length());
				this.out.newLine();
			}
			this.out.close();
		} catch (IOException e) {
			throw new WriteDictionaryException("Output error");
		}
	}

	public double	calculateSimilarity() throws LoadFileException {
		double			num;
		double			den;
		List<Integer>	vectorA;
		List<Integer>	vectorB;

		if (!this.loaded) {
			throw new LoadFileException("Files not loaded");
		}
		vectorA = createOccVector(this.fileA.getFile());
		vectorB = createOccVector(this.fileB.getFile());
		num = scalarProd((Vector<Integer>) vectorA, (Vector<Integer>) vectorB);
		den = calculateMod((Vector<Integer>) vectorA) * calculateMod((Vector<Integer>) vectorB);
		return (num / den);
	}

	public boolean	isLoaded() {
		return (this.loaded);
	}
}
