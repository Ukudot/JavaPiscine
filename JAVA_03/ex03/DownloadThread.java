import	java.nio.file.*;
import	java.io.*;
import	java.net.*;

class	DownloadThread extends Thread {
	private MyFile	urls;

	public	DownloadThread(String name, MyFile urls) {
		this.setName(name);
		this.urls = urls;
	}

	@Override
	public void	run() {
		String		url[];
		String		file_name;
		int			number;
		InputStream	in;

		while (true) {
			synchronized (this.urls) {
				while (this.urls.size() == 0) {
					try {
						this.urls.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.exit(-1);
					}
				}
				url = this.urls.popElem();
			}
			file_name = url[1].substring(url[1].lastIndexOf('/') + 1);
			number = Integer.parseInt(url[0]);
			in = null;
			try {
				in = new URL(url[1]).openStream();
				System.out.println(this.getName() + " start download file number " + url[0]);
				Files.copy(in, Paths.get(file_name), StandardCopyOption.REPLACE_EXISTING);
				System.out.println(this.getName() + " finish download file number " + url[0]);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
