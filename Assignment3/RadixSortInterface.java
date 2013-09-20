/**
 * @status       COMPLETE         
 *
 * @author       BuckYoung 
 * @date         Jun 29, 2013
 *         
 */

public interface RadixSortInterface{
    /*
     * Distribute each key into the appropriate Queue based on the digit at position place.
     **/
    public void distribute(int place) throws NoSuchPriorityException;
   
    /*
     * For each Queue, collect each of the items in order into a list.
     * */
    public void collect();

    /*
     * Display the list of keys, as a list.
     **/
    public void display();

    /*
     * Display the final sorted array one item per line.
     **/
    public void fdisplay();
}
