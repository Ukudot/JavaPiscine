import	java.util.*;

class	ThreadsPool	{
	private	RandomArray		randArray;
	private List<Thread>	threadsArray;
	private	List<Integer>	sums;
	private int				threadsCount;

	public	ThreadsPool(int	threadsCount, RandomArray randArray) {
		this.threadsCount = threadsCount;
		this.randArray = randArray;
		this.threadsArray = new ArrayList();
		this.sums = new ArrayList();
	}

	public void	prepareThreads() {
		int	size;
		int part;
		int start;
		int end;

		size = this.randArray.getArray().size();
		part = size / this.threadsCount;
		start = 0;
		end = 0;
		for (int i = 0; i < this.threadsCount; i++) {
			start = end;
			if (i == this.threadsCount - 1) {
				end = size;
			} else {
				end = part * (i + 1);
			}
			this.threadsArray.add(new Thread(new SumThread(i, start, end - 1, this.randArray, this.sums)));
			this.sums.add(0);
		}
	}

	public void	start() {
		for (Thread t : this.threadsArray) {
			t.start();
		}
	}

	public void	join() throws InterruptedException {
		for (Thread t : this.threadsArray) {
			t.join();
		}
	}
	
	public int	getSum() {
		int	sum;

		sum = 0;
		for (int i : this.sums) {
			sum += i;
		}
		return (sum);
	}
}
