
/**
 * @status COMPLETE
 * @due Friday 5/31/13
 *
 * @author BuckYoung
 * @date May 25, 2013
 *
 * @university University of Pittsburgh
 * @course CS445
 * @assignment Assignment 1
 * @description "IBag from an existing data structure"
 *
 */
import java.util.*;

public class SBag<Item> implements BagInterface<Item>, Iterable<Item> {

    private Stack<Item> bag;    // the bag is stored here
    private Stack<Item> aux;    // auxiliary storage

    //finished
    public SBag() {
        bag = new Stack<>();
        aux = new Stack<>();
    }

    /**
     *
     * @param newEntry the entry to be added to AUX
     * @return True if the entry is added. False if the entry is null.
     */
    @Override
    public boolean add(Item newEntry) {
        boolean result = false;
        if (newEntry != null) {
            aux.push(newEntry);
            result = true;
        }
        return result;
    }

    /**
     *
     * @return False: The SBag is, ultimately, based on an ArrayList and should
     * never be full.
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     *
     * @return An array of the generic type.
     */
    @Override
    public Item[] toArray() {
        Object[] array = new Object[this.getCurrentSize()];
        int count = 0;
        this.allAuxToBag();
        while (!bag.isEmpty()) {
            Item item = bag.pop();
            array[count++] = item; //adds and iterates for the next loop
            aux.push(item);
        }
        return (Item[]) array;
    }

    /**
     *
     * @return True if bag contains 0 elements. False otherwise.
     */
    @Override
    public boolean isEmpty() {
        return aux.isEmpty();
    }

    /**
     *
     * @return Type "int" of number of elements in the bag.
     */
    @Override
    public int getCurrentSize() {
        int result = 0;
        while(!aux.isEmpty()){
            bag.push(aux.pop());
            result++;
        }
        this.allBagToAux();
        return result;
    }

    /**
     *
     * @param anEntry The entry to be checked.
     * @return The number of elements in the bag matching the entry.
     */
    @Override
    public int getFrequencyOf(Item anEntry) {
        int freq = 0;
        while (!aux.isEmpty()) {
            Item item = aux.pop();
            if (item.equals(anEntry)) {
                freq++;
            }
            bag.push(item);
        }
        this.allBagToAux();
        return freq;
    }

    /**
     *
     * @param anEntry The entry to be checked.
     * @return true if element is in bag and false otherwise.
     */
    public boolean contains(Item anEntry) {
        boolean result = false;
        if (getFrequencyOf(anEntry) > 0) { //yay code reuse!
            result = true;
        }
        return result;
    }

    /**
     * Creates a new bag.
     */
    public void clear() {
        bag = new Stack<>();
        aux = new Stack<>(); 
    }

    /**
     *
     * @return The removed item (successful), or null (unsuccessful).
     */
    public Item remove() {
        Item result = null;
        if (this.getCurrentSize() > 0) {
            this.allAuxToBag();
            result = bag.pop();
        }
        this.allBagToAux();
        return result;
    }

    /**
     * Removes the first instance of a specified entry.
     *
     * @param anEntry the entry to remove
     * @return True if found and removed, false if nothing was removed.
     */
    public boolean remove(Item anEntry) {
        boolean found = false;
        if (this.contains(anEntry)) {

            while (!aux.isEmpty()) {
                Item item = aux.pop();
                if (item.equals(anEntry) && !found) {
                    //let it pop off and dont save it
                    found = true;
                } else {
                    bag.push(item);
                }
            }
            this.allBagToAux(); //add all saved items back into the aux

        }
        return found; //true or false
    }

    /**
     * Creates a useful String representation.
     *
     * @return the contents of the bag as a String.
     */
    public String toString() {
        String result;
        this.allAuxToBag();
        result = bag.toString();
        this.allBagToAux();
        return result;
    }

    /**
     * Returns an iterator for the SBag.
     *
     * @return a new SBag iterator
     */
    @Override
    public Iterator<Item> iterator() {
        return new IteratorForSBag(this);
    }


    //Removes all items in the aux an pushes them to the main bag
    private void allAuxToBag() {
        while (!aux.isEmpty()) {
            Item item = aux.pop();
            if (item != null){
                bag.push(item);
            }
        }
    }
    //all items in bag -> aux
    private void allBagToAux() {
        while (!bag.isEmpty()) {
            Item item = bag.pop();
            if (item != null) {
                aux.push(item);
            }
        }
    }

}