import	java.util.*;
import	java.io.*;

class	Program {
	static private final String	FILE_OUT = "result.txt";

	static public void	main(String args[]) {
		List<int[]>			toWrite;
		FileOutputStream	file;
		Signatures			signatures;
		Scanner				scanner;
		String				line;
		byte				type[];
		
		if (args.length != 1) {
			System.out.println("Usage error: java Program <signatures_file>");
			System.exit(-1);
		}
		signatures = new Signatures();
		scanner = new Scanner(System.in);
		toWrite = new ArrayList<int[]>();
		signatures.loadSignatures(args[0]);
		if (!signatures.isLoaded()) {
			System.exit(-1);
		}
		signatures.printMap();
		try {
			System.out.print("-> ");
			line = scanner.nextLine();
			while (!line.equals("42")) {
				toWrite.add(signatures.processFile(line));
				System.out.print("-> ");
				line = scanner.nextLine();
			}
		} catch (ProcessException e) {
			System.out.println(e);
			System.exit(-1);
		}
		try {
			file = new FileOutputStream(FILE_OUT);
			for (int[] proc : toWrite) {
				type = new byte[proc.length];
				for (int i = 0; i < proc.length; i++) {
					type[i] = (byte) proc[i];
				}
				file.write(type);
				file.write(10);
			}
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);	
		} catch (IOException e) {
			System.out.println("Input output exception");
			System.exit(-1);	
		}
	}
}
