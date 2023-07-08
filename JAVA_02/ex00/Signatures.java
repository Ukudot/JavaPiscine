import	java.io.*;
import	java.util.*;

class	Signatures {
	private Map<int[], int[]>	signatures;
	private	boolean				loaded;
	private	int					sigSize;
	static private final char	cUndefined[] = "undefined".toCharArray();
	
	static private int[]	convertHexToRaw(int hex[]) {
		List<Integer>	rawList;
		int				rawArray[];
		int				raw;
		int				size;

		if (hex == null) {
			return (null);
		}
		size = hex.length;
		rawList = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			if (hex[i] != ' ') {
				raw = (hex[i] <= '9' ? hex[i] - '0' : hex[i] - 55) * 16;
				i++;
				raw += (hex[i] <= '9' ? hex[i] - '0' : hex[i] - 55);
				rawList.add(raw);
			}
		}
		size = rawList.size();
		rawArray = new int[size];
		for (int i = 0; i < size; i++) {
			rawArray[i] = rawList.get(i);
		}
		return (rawArray);
	}

	public	Signatures() {
		this.signatures = new HashMap<int[], int[]>();
		this.loaded = false;
		this.sigSize = 0;
	}

	public void	loadSignatures(String filename) {
		CSV			csv;
		int			type[];
		int			sig[];
		List<int[]>	line;

		csv = new CSV(filename);
		line = new ArrayList<int[]>();
		try {
			csv.open();
			while (true) {
				line = csv.readLine();
				if (line == null) {
					break ;
				}
				type = line.get(0);
				sig = convertHexToRaw(line.get(1));
				this.signatures.put(sig, type);
				this.sigSize = this.sigSize < sig.length ? sig.length : this.sigSize;
			}
			csv.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return ;
		}  catch (IOException e) {
			System.out.println("input output exception");
			return ;
		}
		this.loaded = true;
	}

	public int[]	processFile(String filename) throws ProcessException {
		FileInputStream	file;
		int				read[];
		int				undefined[];
		int				size;
		int				i;

		if (!this.loaded) {
			throw new ProcessException("signature file not loaded");
		}
		try {
			file = new FileInputStream(filename);
			read = new int[this.sigSize];
			for (i = 0; i < this.sigSize; i++) {
				read[i] = file.read();
			}
			file.close();
		} catch (FileNotFoundException e) {
			throw new ProcessException("file not found");
		}  catch (IOException e) {
			throw new ProcessException("Input output exception");
		}
		for (int  key[]: this.signatures.keySet()) {
			size = key.length;
			for (i = 0; i < size; i++) {
				if (read[i] != key[i]) {
					break ;
				}
			}
			if (i == size) {
				System.out.println("PROCESSED");
				return (this.signatures.get(key));
			}
		}
		System.out.println("UNDEFINED");
		
		undefined = new int[cUndefined.length];
		for (i = 0; i < cUndefined.length; i++) {
			undefined[i] = cUndefined[i];
		}
		return (undefined);
	}

	public boolean	isLoaded() {
		return (this.loaded);
	}

	public void		printMap() {
		int	type[];

		if (!this.loaded) {
			return ;
		}
		for (int	key[] : this.signatures.keySet()) {
			for (int i = 0; i < key.length; i++) {
				System.out.printf(key[i] + " ");
			}
			System.out.print(": ");
			type = this.signatures.get(key);
			for (int i = 0; i < type.length; i++) {
				System.out.print((char) type[i]);
			}
			System.out.println();
		}
	}
}
