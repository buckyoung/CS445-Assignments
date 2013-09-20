
/**
 * @status COMPLETE
 *
 * @author BuckYoung
 * @date Jun 29, 2013
 *
 */
import java.util.*;

public class RadixSort implements RadixSortInterface {

    private PriorityQueue<Integer> bin;
    private ArrayList<Integer> list;

    public RadixSort(int[] ar) {   //ar[] is the array of integers to be sorted
        
        list = new ArrayList<>(ar.length);
        for(int num : ar){
            list.add(num);
        }
        
        bin = new PriorityQueue<>(10);
    }

    //place -> 0, 1, 2, 3 ...
    public static int getDigit(int number, int place) {
        for (int i = 0; i < place; i++) {
            number /= 10;
        }
        return (number % 10);
    }

    /*
     * Distribute each key into the appropriate Queue based on the digit at position place.
     **/
    public void distribute(int place) throws NoSuchPriorityException {
        while(!list.isEmpty()){
            int num = list.remove(0);
            bin.add(num, RadixSort.getDigit(num, place));
            
        }
        
    }

    /*
     * For each Queue, collect each of the items in order into a list.
     * */
    public void collect() {
        while(!bin.isEmpty()){
            list.add(bin.remove());
        }
    }

    /*
     * Display the list of keys, as a list.
     **/
    public void display() {
        System.out.println(list.toString());
    }

    /*
     * Display the final sorted array one item per line.
     * --(In reverse order)
     **/
    public void fdisplay() {
        while(!list.isEmpty()){
            System.out.println(list.remove(list.size()-1));
        }
    }

    public static void main(String[] args) throws NoSuchPriorityException {
        //Use a Scanner to read integers (keys) from the Stdin into list (see private variables).
        System.out.println("Input quantity of list, then each item of the list: ");
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        
        ArrayList<Integer> al = new ArrayList<>();
        while (count>0){
            al.add(in.nextInt());
            count--;
        }
        int[] intArray = new int[al.size()];
        count = 0;
        Integer max = -1;
        for(int num : al){
            if (num>max){
                max = num;
            }
            intArray[count++] = num;
        }
        //Define a PriorityQueue with priorities for each of the digits
        RadixSort radixSort = new RadixSort(intArray);
        
        //Write code to repeatedly 
        //     1) distribute the keys into the PriorityQueue (i.e., add the key based on the priority
        //        of the current digit being scanned).
        //     2) Collect the keys from highest to lowest priority placing them back into list.
        //
        count = max.toString().length();
        int place = 0;
        System.out.println("\nSorted:");
        while (count > 0){
            try{
            radixSort.distribute(place++);
            radixSort.collect();
            count--;
            } catch(NoSuchPriorityException ex){
                System.err.println("Exception: No Such Priority" + --place);
            }
        }
       
        // until the list is sorted,
        // Display the sorted keys in increasing order.
        radixSort.fdisplay();
        
    }
}