
public interface MaxHeapInterface {

	/**
	 * This method adds to the heap with a series of integers, making all the 
	 * swaps needed to make the array a heap
	 */
	public void add(int entry);
	/**
	 * This removes the max number in the array representing the heap
	 */
	public int removeMax();
	/**
	 * This returns the max number in the array representing the max heap
	 */
	public int getMax();

	/**
	 * Checks if the array representing the heap is empty
	 */
	public boolean isEmpty();
	/**
	 * returns size of the heap
	 */
	public int getSize();
	/**
	 * Erases the whole heap
	 */
	public void clean();
	
}
