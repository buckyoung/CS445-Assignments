
/**
 * @status Complete.
 * @due Friday 5/31
 *
 * @author BuckYoung
 * @date May 25, 2013
 *
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForSBag<Item> implements Iterator<Item> {

    private BagInterface<Item> baggy;
    private int current;
    private boolean wasNextCalled;
    
    public IteratorForSBag(BagInterface<Item> sbag) {
        baggy = sbag;
        current = 0;
        wasNextCalled = false;
    }

    public boolean hasNext() {
        return current < baggy.getCurrentSize();
    }

    public Item next() {
        if (hasNext()) {
           wasNextCalled = true;
           Item item = baggy.toArray()[current++]; //iteratecurrent
           return item;
        } else {
            throw new NoSuchElementException("Illegal call to next()");
        }
    }

    public void remove() {
        if (wasNextCalled) {
            wasNextCalled = false;
            baggy.remove(baggy.toArray()[--current]);
        } else {
            throw new IllegalStateException("Illegal call to remove()");
        }
    }
}