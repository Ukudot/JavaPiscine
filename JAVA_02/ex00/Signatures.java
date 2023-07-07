import	java.io.*;
import	java.util.*;

class	Signatures {
	private Map<int[], char[]>	signatures;
	private	boolean				loaded;
	private	int					sigSize;
	
	public	Signatures() {
		this.map = new HashMap<int[], char[]>();
		this.loaded = false;
		this.sigSize = 0;
	}

	public void	loadSignatures(String filename) {
		CSV				csv;
		char			type[];
		int				sig[];
		List<byte[]>	line;

		csv = new CSV(filename);
		line = new List<byte[]>();
		try {
			csv.open();
			while (true) {
				line = readLineCSV();
				if (line == null)
					break ;
				type = (char[]) line.get(0);
				sig = convertHexToRaw(line.get(0));
				this.map.put(sig, type);
				this.sigSize = this.sigSize < sig.length ? sig.length : this.sigSize;
			}
			csv.close();
		} catch {FileNotFoundException e} {
			System.out.println("File not found");
			return ;
		}
		this.loaded = true;
	}

	public char[]	processFile(String filename) throws ProcessException {
		FileInputStream	file;
		int				read[];
		int				size;

		if (!this.loaded) {
			throw new ProcessException("signature file not loaded");
		}
		try {
			file = new FileInputStream(filename);
			read = new int[this.sigSize];
			file.read((byte []) read);
			file.close();
		} catch (FileNotFoundException e) {
			throw new ProcessException("file not found");
		}
		for (int[] key : this.map.keySet()) {
			size = key.length;
			for (int i = 0; i < size; i++) {
				if (read[i] != key[i]) {
					break ;
				}
				if (i == size) {
					System.out.println("PROCESSED");
					return (this.map.get(key));
				}
			}
		}
		System.out.println("UNDEFINED");
		return (null);
	}
}
