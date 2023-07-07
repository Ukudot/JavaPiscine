import	java.util.*;
import	java.io.*;

class	CSV {
	private String			filename;
	private FileInputStream	file;

	public	CSV(String filename) {
		this.filename = filename;
	}

	public void	openCSV() throws FileNotFoundException {
		this.file = new FileInputStream(filename);
	}

	public List<byte[]>	readLineCSV() {
		List<byte[]>	line;
		List<Byte>		bytesList;
		byte			bytes[];
		int				size;
		int				read;

		if (this.file.available() == 0) {
			return (null);
		}
		read = 0;
		line = new ArrayList<byte[]>();
		while (read != '\n' && this.file.available() != 0) {
			bytesList = new ArrayList<Byte>();
			while (read != ',' && read != '\n' && this.file.available() != 0) {
				read = file.read();
				bytesList.add((byte) read);
			}
			size = bytesList.size();
			bytes = new byte[size];
			for (int i = 0; i < size; i++) {
				bytes[i] = bytesList.get(i);
			}
			line.add(bytes);
		}
		return (line);
	}

	public void	closeCSV() {
		this.file.close();
	}
}
