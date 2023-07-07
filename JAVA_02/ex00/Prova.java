import	java.io.*;
import java.util.*;

class	Prova {
	static public void	main(String args[]) {
		Map<char[], int[]>	map;
		List<Character>	type;
		List<Integer>	sig;
		FileInputStream	file;
		int				read;
		char			array[];
		int				arrayInt[];
		int				number;

		if (args.length == 0) {
			System.out.println("Usage Error: java Prova <filename>");
			System.exit(-1);
		}
		type = new ArrayList<Character>();
		sig = new ArrayList<Integer>();
		try {
			read = 0;
			file = new FileInputStream("prova");
			for (int i = 0; i < 10; i++) {
				read = file.read();
				System.out.printf(read + " ");
			}
			System.out.println();
			file.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
		try {
			read = 0;
			file = new FileInputStream(args[0]);
			while (read != ',') {
				read = file.read();
				if (read != ',') {
					type.add((char) read);
				}
			}
			while (read != '\n' || file.available() != 0) {
				read = file.read();
				if (read != ' ' && (read != '\n' || file.available() != 0)) {
					if (read <= '9' && read >= '0') {
						read -= '0';
					} else {
						read -= 55;
					}
					number = read * 16;
					read = file.read();
					if (read <= '9' && read >= '0') {
						read -= '0';
					} else {
						read -= 55;
					}
					number += read;
					sig.add(number);
					System.out.printf(number + " ");
				}
			}
			System.out.println();
			file.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
		array = new char[type.size()];
		map = new HashMap<char[], int[]>();
		for (int i = 0; i < type.size(); i++) {
			array[i] = type.get(i);
		}
		arrayInt = new int[sig.size()];
		for (int i = 0; i < sig.size(); i++) {
			arrayInt[i] = sig.get(i);
		}
		map.put(array, arrayInt);
		for (char c : array) {
			System.out.print(c);
		}
		System.out.println();
		for (int i : map.get(array)) {
			System.out.printf("%x ", i);
		}
		System.out.println();
	}
}
