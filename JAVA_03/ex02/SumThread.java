import	java.util.*;

class	SumThread implements Runnable {
	private	int				index;
	private	int				start;
	private int 			end;
	private	RandomArray		randArray;
	private	List<Integer>	sums;

	public	SumThread(int index, int start, int end, RandomArray randArray, List<Integer> sums) {
		this.index = index;
		this.start = start;
		this.end = end;
		this.randArray = randArray;
		this.sums = sums;
	}

	@Override
	public void run() {
		int	sum;

		sum = 0;
		for (int i = start; i <= end; i++) {
			sum += this.randArray.getElem(i);
		}
		System.out.println("Thread " + (this.index + 1) + ": from " + this.start + " to " + this.end + " sum is " + sum);
		this.sums.set(index, sum);
	}
}
