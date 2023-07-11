import	java.util.*;
import	java.io.*;

class	MyFile {
	private static final long	MAX_FILE_SIZE = 1024 * 1024 * 10;
	private BufferedReader		in; 
	private List<String[]>		file;
	private String				filename;
	private boolean				loaded;
	
	private static String	myReplace(String line, String oldset, String newset) {
		char	array[];

		array = oldset.toCharArray();
		for (char c : array) {
			line = line.replace(c, ' ');
		}
		return (line);
	}

	public	MyFile(String filename) {
		this.file = new Vector<String[]>();
		this.filename = filename;
		this.loaded = false;
	}

	public void	loadFile() throws LoadFileException {
		String	line;
		File	file;

		file = new File(this.filename);
		if (file.length() > MAX_FILE_SIZE) {
			throw new LoadFileException("File too large");
		}
		try {
			this.in = new BufferedReader(new FileReader(this.filename));
			line = this.in.readLine();
			while (line != null) {
				this.file.add(myReplace(line, ",.;:?!()[]{}\"\'<>", " ").toLowerCase().split("\\s+"));
				line = this.in.readLine();
			}
			this.in.close();
		} catch (FileNotFoundException e) {
			throw new LoadFileException("File not found");
		} catch (IOException e) {
			throw new LoadFileException("Input output exception");
		}
		this.loaded = true;
	}

	public boolean	isLoaded() {
		return (this.loaded);
	}

	public Vector<String[]>	getFile() throws LoadFileException {
		if (!this.loaded) {
			throw new LoadFileException("File is not loaded yet");
		}
		return ((Vector<String[]>) this.file);
	}

	public void	printFile() throws LoadFileException {
		int	size;

		if (!this.loaded) {
			throw new LoadFileException("File is not loaded yet");
		}
		for (String[] line : this.file) {
			for (String word : line) {
				if (word.equals("\n")) {
					continue ;
				}
				System.out.println(word.trim());
			}
		}
	}
}
