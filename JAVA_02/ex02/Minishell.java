import	java.io.*;
import	java.util.*;

class	Minishell {
	private static int	KILOBYTE = 1024;
	private	String		path;
	private File		file;

	private	File	getFileTmp(String cmd) {
		File	tmp;

		if (cmd.toCharArray()[0] == '/') {
			tmp = new File(cmd);
		} else {
			tmp = new File(this.file.getAbsolutePath() + "/" + cmd);
		}
		return (tmp);
	}

	private String	shrinkPath(String oldPath) {
		String			newPath;

		newPath = oldPath;
		while (newPath.contains("/./")) {
			newPath = newPath.replaceAll("/\\./", "/");
		}
		while (newPath.contains("/..")) {
			newPath = newPath.replaceAll("/\\w+/\\.\\.", "");
			System.out.println(newPath);
		}
		newPath = newPath.replaceAll("/\\.", "");
		if (newPath.equals("")) {
			return ("/nfs");
		}
		return (newPath);
	}

	public	Minishell(String path) throws FileNotExistException {
		if (path.toCharArray()[0] != '/') {
			throw new FileNotExistException("Path not valid");
		}
		this.path = path;
		this.file = new File(path);
		if (!this.file.exists() || !this.file.isDirectory()) {
			throw new FileNotExistException("Path not valid");
		}
	}

	public void	list(String[] cmd) throws InvalidCommandException {
		String	list[];
		File	tmp;
		
		if (cmd.length != 1) {
			throw new InvalidCommandException("too many parameters");
		}
		list = this.file.list();
		for (String path : list) {
			tmp = new File(this.file.getAbsolutePath() + "/" + path);
			System.out.println(tmp.getName() + " " + (tmp.length() / KILOBYTE) + " KB");
		}
	}

	public void	changeDir(String[] cmd) throws InvalidCommandException, InvalidDirectoryException {
		File	tmp;

		if (cmd.length != 2) {
			throw new InvalidCommandException("too many parameters");
		}
		tmp = getFileTmp(cmd[1]);
		if (!tmp.exists() || !tmp.isDirectory()) {
			throw new InvalidDirectoryException("directory not found");
		}
		tmp = new File(this.shrinkPath(tmp.getAbsolutePath()));
		this.file = tmp;
		this.path = this.file.getAbsolutePath();
		System.out.println(tmp.getAbsolutePath());
	}

	public void	moveFile(String[] cmd) throws InvalidCommandException, InvalidFileException, InvalidDirectoryException {
		File	tmpFile;
		File	tmp;

		if (cmd.length != 3) {
			throw new InvalidCommandException("wrong number of parameters");
		}
		tmpFile = getFileTmp(cmd[1]);
		if (!tmpFile.exists() || !tmpFile.isFile()) {
			throw new InvalidFileException("file not found");
		}
		tmp = getFileTmp(cmd[2]);
		if (tmp.isDirectory()) {
			tmp = new File(tmp.getAbsolutePath() + "/" + tmpFile.getName());
		} else if (!tmp.getParentFile().isDirectory()) {
			throw new InvalidDirectoryException("directory not found");	
		}
		tmpFile.renameTo(tmp);
	}
}
