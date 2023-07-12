class	Hen implements Runnable {
	private int	count;

	public	Hen(int count) {
		this.count = count;
	}

	public void	run() {
		for (int i = 0; i < this.count; i++) {
			System.out.println("Hen");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted");
				System.exit(-1);
			}
		}
	}
}
