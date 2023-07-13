import	java.util.*;

class	ThreadsPool {
	private MyFile					urls;
	private	int						threadsCount;
	private List<DownloadThread>	threadsArray;

	public	ThreadsPool(int threadsCount, MyFile urls) throws ThreadsPoolException{
		if (threadsCount <= 0) {
			throw new ThreadsPoolException("Invalid number of threads");
		}
		this.threadsCount = threadsCount;
		this.urls = urls;
		this.threadsArray = new Vector<DownloadThread>();
		for (int i = 0; i < this.threadsCount; i++) {
			this.threadsArray.add(new DownloadThread("Thread-" + (i + 1), this.urls));
		}
	}

	public void	start() {
		for (DownloadThread dt : this.threadsArray) {
			dt.start();
		}
	}

	public void	join() throws InterruptedException {
		for (DownloadThread dt : this.threadsArray) {
			dt.join();
		}	
	}

	public void	addElem(String url) throws InvalidFileException {
		synchronized (this.urls) {
			this.urls.add(url);
			this.urls.notify();
		}
	}
}
