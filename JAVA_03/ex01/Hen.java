class	Hen implements Runnable {
	private int		count;
	private Object	lock;

	public	Hen(int count, Object lock) {
		this.count = count;
		this.lock = lock;
	}

	public void	run() {
		for (int i = 0; i < this.count; i++) {
			synchronized (this.lock) {
				try {
					this.lock.notify();
					this.lock.wait();
					System.out.println("Hen");
				} catch (InterruptedException e) {
					System.out.println("Thread interrupted");
					System.exit(-1);
				}
			}
		}
		synchronized (this.lock) {
				this.lock.notify();
		}
	}
}
