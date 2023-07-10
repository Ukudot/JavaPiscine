class	Program {
	public static void	main(String args[]) {
		Dictionary	dict;

		try {
			dict = new Dictionary(args[0], args[1]);
			dict.loadFiles();
			dict.createDictionary();
			dict.saveDictionary("result.txt");
			System.out.printf("Similarity: %.3f", dict.calculateSimilarity());
		} catch (Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
	}
}
