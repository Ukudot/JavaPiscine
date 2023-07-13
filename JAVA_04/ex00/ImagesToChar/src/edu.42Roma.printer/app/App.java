class	App {
	public static void main(String args[]) {
		ImageConverter	imageConverter;

		try {
			imageConvertert = takeInput(args);
		} catch (InvalidInputException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
