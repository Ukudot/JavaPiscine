class	ThreadsPool	{
	private	List<Integer>	randArray;
	private List<Thread>	threadsArray;
	private	List<Integer>	sums;
	private int				threadsCount;

	public	ThreadsPool(int	threadsCount, List<Integer> randArray) {
		this.threadsCount = threadsCount;
		this.randArray = randArray;
		this.threadsArray = new ArrayList(this.threadsCount);

	}


}
