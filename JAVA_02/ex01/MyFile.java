import	java.util.*
import	java.io.*

class	MyFile {
	private static final	BUFFER_SIZE = 10000000;
	private BufferedReader	in; 
	private List<String[]>	file;
	private String			filename;
	private boolean			loaded;
	
	public	MyFile(String filename) {
		this.file = new Vector<String[]>;
		this.filename = filename;
	}

	public void	loadFile() {
		String	line;

		try {
			this.in = new BufferedReader(new FileReader(this.filename), BUFFER_SIZE);
			line = this.in.readLine();
			while (line != null) {
				tjos.file.add(line);
			}
		} catch (FileNotFoundException e) {
			throw new LoadFileException("File not found");
		} catch (IOExcetpion e) {
			throw new LoadFileException("Input output exception");
		}
	}

	public void	open() throws FileNotFoundException {
		this.in = new BufferedReader(new FileReader(this.filename));
	}

	public void	close() throws IOException {
		this.in.close()
	}

	

}
