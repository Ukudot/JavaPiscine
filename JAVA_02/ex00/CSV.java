import	java.util.*;
import	java.io.*;

class	CSV {
	private String			filename;
	private FileInputStream	file;

	public	CSV(String filename) {
		this.filename = filename;
	}

	public void	open() throws FileNotFoundException {
		this.file = new FileInputStream(filename);
	}

	public List<int[]>	readLine() throws IOException {
		List<int[]>		line;
		List<Integer>	bytesList;
		int				bytes[];
		int				size;
		int				read;

		if (this.file.available() == 0) {
			return (null);
		}
		read = 0;
		line = new ArrayList<int[]>();
		while (read != '\n' && this.file.available() != 0) {
			bytesList = new ArrayList<Integer>();
			read = file.read();
			while (read != ',' && read != '\n' && this.file.available() != 0) {
				bytesList.add(read);
				read = file.read();
			}
			size = bytesList.size();
			bytes = new int[size];
			for (int i = 0; i < size; i++) {
				bytes[i] = bytesList.get(i);
			}
			line.add(bytes);
		}
		return (line);
	}

	public void	close() throws IOException {
		this.file.close();
	}
}
