
/**
 * @status COMPLETE
 *
 * @author BuckYoung
 * @date Jun 29, 2013
 *
 */
public interface PriorityQueueInterface<T extends Comparable<? super T>> {

    /**
     * Task: Adds a new entry to the priority queue with the given priority.
     *
     * @param newEntry an object
     * @param priority an int representing newEntry's priority.
     */
    public void add(T newEntry, int priority) throws NoSuchPriorityException;

    /**
     * Task: Removes and returns the item with the highest priority.
     *
     * @return either the object with the highest priority or, if the priority
     * queue is empty before the operation, null
     */
    public T remove();

    /**
     * Task: Retrieves the item with the highest priority.
     *
     * @return either the object with the highest priority or, if the priority
     * queue is empty, null
     */
    public T peek();

    /**
     * Task: Detects whether the priority queue is empty.
     *
     * @return true if the priority queue is empty, or false otherwise
     */
    public boolean isEmpty();

    /**
     * Task: Gets the size of the priority queue.
     *
     * @return the number of entries currently in the priority queue
     */
    public int getSize();

    /**
     * Task: Removes all entries from the priority queue
     */
    public void clear();
    
    
} // end PriorityQueueInterface

