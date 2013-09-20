

import java.util.ArrayList;

public interface ThreadedBSTreeInterface<T extends Comparable<? super T>>{
  //Return nodes in tree
  public int numberOfNodes();
    
  //add an item to this ThreadedBST
  public void add(T item); //must be implemented recursively 
  
  //Return the root
  public int root();
  
  //Determine whether or not something equivalent to item is in this ThreadedBST. Return its index if in the tree otherwise return -1.
  public int contains(T item); //must be implemented recursively
  
  //Determine a maximum item in this ThreadedBST
  public T max(); //must be implemented recursively
  
  //Determine a minimum item in this ThreadedBST
  public T min(); //must be implemented recursively
  
  //Determine the parent of the item in this ThreadedBST; the parent of the root data is null.
  public T parent(T item);
    
  //Determine the ancestors of item in this ThreadedBST. The ancestors of the root data or data not in the tree is an empty list.
  public ArrayList<T> ancestors(T item);
  
  //Determine the predecessor of item in this ThreadedBST, otherwise, null.
  public T predecessor(T item);
  
  //Determine the successor of item in this ThreadedBST, otherwise, null.
  public T successor(T item);
 
  
  //Determine the number of items equivalent to item
  public int countOccurrences(T item);
  
  //Collect all items equivalent to item in an ArrayList
  public ArrayList<T> collectAllEqualTo(T item);
}