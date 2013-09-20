


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

//Buck Young -- bcy3
//Status: COMPLETED
//July 24, 2013


public class AThreadedBSTree<T extends Comparable<? super T>> implements ThreadedBSTreeInterface<T>, Cloneable {

    private T[] store;
    private int[][] links;                 //a link is an array index 0 through store.length
    private int[] parents;
    private int length;
    private int root;
    private static final int NULL = -1;   //analogous to the null reference

    //######FINISHED
    public AThreadedBSTree() {
        //make the initial array-space of size 12
        this(12);
    }

    //######FINISHED  
    private AThreadedBSTree(int capacity) {

        store = (T[]) new Comparable[capacity];
        links = new int[capacity][2];
        parents = new int[capacity];
        length = 0;
        root = NULL;

        for (int i = 0; i < capacity; i++) {
            links[i][0] = NULL;
            links[i][1] = NULL;
            parents[i] = NULL;
        }

    } //private constructor

    //######FINISHED
    //Return nodes in tree
    public int numberOfNodes() {
        return length;
    }

    //######FINISHED
    //Return the root
    public int root() {
        return root;
    }

    //######FINISHED
    public void add(T item) {//must be implemented recursively! //call private add()
        //if item < node's data, step left
        //if item >= node's data, step right
        //place item where link to node is -1 (NULL), note NULL is not Java's built-in null value but a constant defined above

        ensureCapacity();

        store[length] = item;

        if (root == NULL) {
            root++;    //if root is -1, set it to 0
        } else {
            add(root, length, NULL); //current root, //current data
        }


        length++;
        return;

    }

    //######FINISHED
    private void add(int currentRoot, int currentData, int child) {

        int comparison = store[currentData].compareTo(store[currentRoot]); //x<y=-1 //x>y=1 //x==y=0

        if (comparison < 0) { //current data < current root
            //Step left //links[x][0]
            if (links[currentRoot][0] == NULL) { //if there is no left child yet
                links[currentRoot][0] = currentData;
                parents[currentData] = currentRoot;
            } else { //if there is a left child already
                add(links[currentRoot][0], currentData, child); //recursively call add on leftchild
            }

        } else {
            //if >=, step right //links[1][x]
            if (links[currentRoot][1] == NULL) { //if there is no right child yet
                links[currentRoot][1] = currentData;
                parents[currentData] = currentRoot;
            } else { //if there is a right child already
                add(links[currentRoot][1], currentData, child); //recursively call add on that rightchild
            }

        }

        return;
    }

    //######FINISHED
    public Object clone() {
        AThreadedBSTree<T> theCopy = new AThreadedBSTree(length);

        theCopy.store = Arrays.copyOf(store, length);

        theCopy.parents = Arrays.copyOf(parents, length);
        theCopy.length = length;
        theCopy.root = root;

            //copy over links
            int[][] copyLinks = new int[length][2];
            for (int i = 0; i < length; i++) {
                copyLinks[i][0] = links[i][0];
                copyLinks[i][1] = links[i][1];
            }
            theCopy.links = copyLinks;
        
        return theCopy;
    }

    //######FINISHED
    public int contains(T item) { //call private contains() //must be implemented recursively 
        return contains(item, root);
    }

    //######FINISHED
    private int contains(T item, int currentRoot) {
        if (currentRoot == length) { //BASE CASE //we did not find it and we are about to step out of bounds
            return NULL;
        } else { //we are still in bounds
            //check for a match
            if (store[currentRoot].equals(item)) { //if it is a match
                return currentRoot;//return the node! (index)
            } else { //if it is not a match
                return contains(item, ++currentRoot);//recursively call contains!
            }
        }
    }

    //######FINISHED
    public T parent(T item) {
        T result = null;

        for (int i = root + 1; i < length; i++) {
            if (store[i].equals(item)) { //if the item in store array is the item we are looking for
                result = store[parents[i]]; //result is the parent of the item
                break;
            }
        }
        return result;
    }

    //######FINISHED
    public ArrayList<T> ancestors(T item) {
        ArrayList<T> result = new ArrayList<>(length - 1); //at most every other node will be an ancestor, therefore length-1

        int itemIndex = contains(item);

        if (itemIndex == NULL || parents[itemIndex] == NULL) { //if the item is not in the tree or if the item has no ancestors
            //return an empty set (do nothing)
        } else { //the item is in the tree and it has ancestors
            //add the first ancestor
            //continue doing this until we get to the root
            do {
                itemIndex = parents[itemIndex];
                result.add(store[itemIndex]);
            } while (itemIndex != 0);

        }

        return result;
    }

    //######FINISHED
    public T max() { //call private max()  //must be implemented recursively
        return max(root);  //step right until you can't step right anymore in this ArrayBSTree
    }

    //######FINISHED
    private T max(int currentRoot) { //all the way right
        T result = null;

        //links[currentRoot][1] //right child of currentRoot

        if (links[currentRoot][1] == NULL) { //if there is no right child
            result = store[currentRoot];//return this node as the max
        } else {
            result = max(links[currentRoot][1]); //find max of right child
        }


        return result;
    }

    //######FINISHED
    public T min() { //call private min() //must be implemented recursively 
        return min(root); //step left until you can't step left anymore in thsi ArrayBSTree
    }

    //######FINISHED
    private T min(int currentRoot) { //all the way left
        T result = null;

        //links[currentRoot][0] //left child of currentRoot

        if (links[currentRoot][0] == NULL) { //if there is no left child
            result = store[currentRoot];//return this node as the max
        } else {
            result = min(links[currentRoot][0]); //find min of left child
        }


        return result;
    }

    //######FINISHED
    public T successor(T item) { //Find the item using InOrder Traversal, then get the next inorder item
        T result = null;

        Stack<Integer> stackA = new Stack<>();
        Stack<T> stackT = new Stack<>();
        boolean found = false;
        int p = root;

        do {
            if (p == NULL) { //if p is null
                //p = stackA.pop
                if (!stackA.isEmpty()) {
                    p = stackA.pop();
                } else {
                    //else the tree does not contain the item which was passed to this method
                    break;
                }

                //visit
                if (found) { //this is our next item!
                    result = store[p];
                    break;
                }

                if (store[p].equals(item)) { //visit p, if item is found then we want the NEXT inorder item
                    //we found our item
                    found = true;
                    //now we want the next item
                } else { //p is not the item we want
                    //continue
                }

                // p = right link of p
                p = links[p][1]; // p = right child of p

            } else { //if not null
                stackA.push(p);
                p = links[p][0]; // p = left child of p
                //goto top
            }
        } while (true);

        return result;
    }

    //######FINISHED
    public T predecessor(T item) { //do an inorder traversal and return the previous item 
        T result = null;

        Stack<Integer> stackA = new Stack<>();
        Stack<T> stackT = new Stack<>();
        int p = root;

        do {
            if (p == NULL) { //if p is null
                if (!stackA.isEmpty()) {
                    p = stackA.pop();
                } else {
                    //else the tree does not contain the item which was passed to this method
                    break;
                }
                if (store[p].equals(item)) { //visit p, if item is found then we want the PREVIOUS inorder item
                    //we found our item, we want to return the top of the stack!
                    if (!stackT.isEmpty()) {
                        result = stackT.pop();
                    }
                    break;
                } else { //p is not the item we want
                    stackT.push(store[p]); //push onto T stack
                    //continue
                }
                p = links[p][1]; // p = right child of p
            } else { //if not null
                stackA.push(p);
                p = links[p][0]; // p = left child of p
                //goto top
            }
        } while (true);

        return result;
    }

    //######FINISHED
    public ArrayList<T> collectAllEqualTo(T item) { //must be implemented recursively //call private version
        return collectAllEqualTo(item, root);
    }

    //######FINISHED
    private ArrayList<T> collectAllEqualTo(T item, int currentIndex) {
        ArrayList<T> result = new ArrayList<>(length);

        if (currentIndex == length) { //BASE CASE //we are about to fall off
            return result; //append nothing
        } else { //else we are well defined
            if (store[currentIndex].equals(item)) { //if we found the item
                result.add(store[currentIndex]); //add it to result
                result.addAll(collectAllEqualTo(item, ++currentIndex));
                return result;
            } else { //otherwise, dont
                return collectAllEqualTo(item, ++currentIndex);
            }
        }

    }

    //######FINISHED
    public int countOccurrences(T item) { //must be implemented recursively //call private version
        return countOccurrences(item, root);
    }

    //######FINISHED
    private int countOccurrences(T item, int index) {

        if (index == length) { //BASE CASE //We are about to step out of bounds
            return 0;
        } else { //still in bounds
            if (store[index].equals(item)) { //if we have a match
                return 1 + countOccurrences(item, ++index);
            } else {
                //no matter what, call recursive
                return countOccurrences(item, ++index);
            }
        }

    }

    //######FINISHED
    private void ensureCapacity() {
        if (length == store.length) {
            //Copy over store and parents
            store = Arrays.copyOf(store, 2 * length);
            parents = Arrays.copyOf(parents, 2 * length);

            //copy over links
            int[][] copyLinks = new int[2 * length][2];
            for (int i = 0; i < length; i++) {
                copyLinks[i][0] = links[i][0];
                copyLinks[i][1] = links[i][1];
            }
            links = copyLinks;
            
            //initialize new locations
            for (int i = length; i < 2*length; i++) {
                links[i][0] = NULL;
                links[i][1] = NULL;
                parents[i] = NULL;
            }
        }
    }

    //######FINISHED
    public void printTree() {
        System.out.println("\nroot = " + root);
        System.out.println("length = " + length);

        System.out.println("\nlinks:");
        for (int k = 0; k < length; k++) {
            System.out.printf("%2d|%3d|%3d%n", k, links[k][0], links[k][1]);
        }


        System.out.println("\nparents:");
        for (int k = 0; k < length; k++) {
            System.out.printf("%2d|%3d%n", k, parents[k]);
        }

        System.out.println("\ndata:");
        for (int k = 0; k < length; k++) {
            System.out.println(k + " " + store[k]);
        }
    }
}