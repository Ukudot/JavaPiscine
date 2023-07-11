class	Program {
	public static void	main(String args[]) {
		Dictionary	dict;

		if (args.length != 2) {
			System.out.println("Usage error: java Program <file_1> <file_2>");
			System.exit(-1);
		}
		try {
			dict = new Dictionary(args[0], args[1]);
			dict.loadFiles();
			dict.createDictionary();
			dict.saveDictionary("result.txt");
			System.out.printf("Similarity: %.3f\n", dict.calculateSimilarity());
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}
