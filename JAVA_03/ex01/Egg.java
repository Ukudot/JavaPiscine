class	Egg implements Runnable {
	private	int		count;
	private Object	lock;

	public	Egg(int count, Object lock) {
		this.count = count;
		this.lock = lock;
	}
	public void	run() {
		for (int i = 0; i < this.count; i++) {
			synchronized (this.lock) {
				try {
					System.out.println("Egg");
					this.lock.notify();
					this.lock.wait();
				} catch (InterruptedException e) {
					System.out.println("Thread interrupted");
					System.exit(-1);
				}
			}
		}
	}
}
