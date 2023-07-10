import	java.io.*;

class	Minishell {
	private static int	KILOBYTE = 1024;
	private	String		filename;
	private File		file;

	public	Minishell(String filename) throws FileNotExistException {
		this.filename = filename;
		this.file = new File(filename);
		if (this.file.exists() && !this.file.isDirectory()) {
			throw new FileNotExistException("Path not valid");
		}
	}

	public void	list() {
		String	list[];
		File	tmp;
		
		System.out.println(this.file.getName() + " " + this.file.length() + " KB");
		list = this.file.list();
		for (String file : list) {
			tmp = new File(this.file.getAbsolutePath() + "/" + file);
			System.out.println(tmp.getName() + " " + tmp.length() + " KB");
		}
	}
}
