import	java.util.*;
import	java.io.*;

class	Program {
	static final String	FILE_OUT = "result.txt";

	static public void	main(String args[]) {
		List<char[]>		toWrite;
		FileOutputStream	file;
		Signatures			signatures;
		Scanner				scanner;
		String				line;
		
		if (args.length != 1) {
			System.out.println("Usage error: java Program <signatures_file>");
			System.exit(-1);
		}
		signatures = new Signatures();
		scanner = new Scanner(System.in);
		line = "";
		toWrite = new List<char[]>();
		signatures.loadSignatures(args[0]);
		try {
			while (!line.equals("42")) {
				System.out.print("-> ");
				line = scanner.nextLine();
				toWrite.add(processFile(line));
			}
		} catch (ProcessException e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}
