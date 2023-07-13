import	java.util.*;
import	java.io.*;

class	MyFile {
	private BufferedReader		in; 
	private List<String[]>		content;
	private String				filename;
	private	int					number;
	
	private static String	myReplace(String line, String oldset, String newset) {
		char	array[];

		array = oldset.toCharArray();
		for (char c : array) {
			line = line.replace(c, ' ');
		}
		return (line);
	}

	public	MyFile(String filename) throws InvalidFileException {
		String	line;
		File	file;
		String	words[];

		this.content = new Vector<String[]>();
		this.filename = filename;
		file = new File(this.filename);
		try {
			this.number = 1;
			this.in = new BufferedReader(new FileReader(this.filename));
			line = this.in.readLine();
			while (line != null) {
				words = line.split("\\s+");
				if (Integer.parseInt(words[0]) != this.number) {
					throw new InvalidFileException("Invalid file format");
				}
				this.number++;
				this.content.add(words);
				line = this.in.readLine();
			}
			this.in.close();
		} catch (FileNotFoundException e) {
			throw new InvalidFileException("File not found");
		} catch (IOException e) {
			throw new InvalidFileException("Input output exception");
		}
	}

	public List<String[]>	getContent() {
		return (this.content);
	}

	public String[]	popElem() {
		String[]	ret;

		ret = this.content.get(0);
		this.content.remove(0);
		return (ret);
	}

	public void	add(String elem) throws InvalidFileException {
		String	words[];

		words = new String[2];
		words[0] = Integer.toString(this.number);
		words[1] = elem;
		this.number++;
		this.content.add(words);
		return ;
	}

	public int	size() {
		return (this.content.size());
	}
}
