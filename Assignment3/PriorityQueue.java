
/**
 * @status COMPLETE
 *
 * @author BuckYoung
 * @date Jun 29, 2013
 *
 */
public class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

    private Queue[] store;
    private int numberOfPriorities; //Set in Constructor
    private int size; //Number of Elements 
    private int maxPriority; //highest priority with elements
    private Queue max; //points to store[maxPriority]

    //FINISHED:
    public PriorityQueue(int numOfPriorities) {

        this.numberOfPriorities = numOfPriorities;

        store = new Queue[numOfPriorities];

        while (numOfPriorities > 0) {
            store[--numOfPriorities] = new Queue(); //creates array of Queues from 0 to num-1
        }

        initVars();

    }

    //FINISHED:
    public String toString() {
        StringBuilder result = new StringBuilder("Priority Queue contains:\n");
        int count = numberOfPriorities;
        while (count > 0) {
            count--;
            if (count == maxPriority) {
                result.append("*");
            } else {
                result.append(" ");
            }
            result.append(count);
            result.append(store[count].toString());
        }
        return result.toString();
    }

    //FINISHED:
    public int getSize() {
        return size;
    }

    //FINISHED;
    public boolean isEmpty() {
        return (size == 0);
    }

    //FINISHED:
    public void add(T newEntry, int priority) throws NoSuchPriorityException {
        if (priority > (numberOfPriorities - 1)) { //index starts at 0, so subtract 1
            throw new NoSuchPriorityException();
        } else {
            //
            store[priority].enqueue(newEntry); //Add the entry to the proper Queue
            //
            size++; //update size
            if (priority > maxPriority) { //update max if needed
                maxPriority = priority;
                max = store[maxPriority];
            }
        }

    }
    
    //FINISHED:
    public T remove() {
        T result = null;

        if (size > 0) {
            result = (T) max.dequeue();
            size--;
            if (size > 0) { //if we still have items in the store
                if (max.isEmpty()) { //if the current max is empty as a result of the remove, we must find a new max
                    while (maxPriority > 0) {
                        if (!store[--maxPriority].isEmpty()) { //if we encounter a queue that is not empty, make it new max
                            //maxPriority is already set
                            max = store[maxPriority];
                            break;
                        } 
                    }
                } //else if the current max still has elements, leave it all alone
            } else { //else if we dont have any items in the store
                initVars();
            }
        }

        return result;
    }

    //FINISHED:
    public T peek() {
        return (T) max.getFront();
    }

    //FINISHED:
    public void clear() {
        int count = maxPriority;
        while (count >= 0) {
            store[count--].clear();
        }
        initVars();
    }

    //Private:
    private void initVars() {
        this.size = 0;
        this.maxPriority = -1;
        this.max = null;
    }

   /* public static void main(String[] args) throws NoSuchPriorityException {
        PriorityQueue<String> pq = new PriorityQueue<String>(6);
        System.out.println("adding 'Bill' priority 5");
        pq.add("Bill", 5);
        System.out.println("adding 'Jim' priority 4");
        pq.add("Jim", 4);
        System.out.println("adding 'George' priority 2");
        pq.add("George", 2);
        System.out.println("adding 'Ann' priority 0");
        pq.add("Ann", 0);
        System.out.println("adding 'Will' priority 5");
        pq.add("Will", 5);
        System.out.println("adding 'Carol' priority 2");
        pq.add("Carol", 2);
        System.out.println("adding 'Leo' priority 2");
        pq.add("Leo", 2);

        System.out.println();
        System.out.println(pq);
        System.out.println("size is " + pq.getSize() + "\n");


        System.out.println("removing: " + pq.remove() + "\n");
        System.out.println(pq);
        System.out.println("size is " + pq.getSize() + "\n");

        System.out.println("peeking: " + pq.peek());
        System.out.println("removing: " + pq.remove());
        System.out.println(pq);
        System.out.println("size is " + pq.getSize() + "\n");

        System.out.println("removing: " + pq.remove() + "\n");
        System.out.println(pq);
        System.out.println("size is " + pq.getSize() + "\n");

        System.out.println("adding 'Jill' priority 0");
        pq.add("Jill", 0);
        System.out.println(pq);
        System.out.println("size is " + pq.getSize() + "\n");

        System.out.println("removing: " + pq.remove() + "\n");
        System.out.println(pq);
        System.out.println("size is " + pq.getSize());

        System.out.println("removing: " + pq.remove() + "\n");
        System.out.println(pq);
        System.out.println("size is " + pq.getSize());

        System.out.println("clear");
        pq.clear();
        System.out.println(pq);
        System.out.println("size is " + pq.getSize());
    }
    */
}