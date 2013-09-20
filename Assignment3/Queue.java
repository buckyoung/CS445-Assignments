
/**
 * @status COMPLETE
 *
 * @author BuckYoung
 * @date Jun 29, 2013
 *
 */
import java.util.*;

public class Queue<E> {

    private Node<E> firstNode; // references node for front of queue
    private Node<E> lastNode;  // references node for back of queue

    public Queue() {
        firstNode = null;
        lastNode = null;
    } // end default constructor

    public String toString() {
        if (isEmpty()) {
            return "[]\n";
        }

        Node p = firstNode;
        String result = "[";

        while (p != null) {
            if (p.next == null) {
                result += p.data;
            } else {
                result += p.data + ",";
            }

            p = p.next;
        }

        return result + "]\n";
    }

    public void enqueue(E newEntry) {
        Node<E> newNode = new Node<E>(newEntry, null);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.setNextNode(newNode);
        }

        lastNode = newNode;
    } // end enqueue

    public E dequeue() {
        E front = null;

        if (!isEmpty()) {
            front = firstNode.getData();
            firstNode = firstNode.getNextNode();

            if (firstNode == null) {
                lastNode = null;
            }
        } // end if

        return front;
    } // end dequeue

    public E getFront() {
        E front = null;

        if (!isEmpty()) {
            front = firstNode.getData();
        }

        return front;
    } // end getFront

    public boolean isEmpty() {
        return firstNode == null;
    } // end isEmpty

    public void clear() {
        firstNode = null;
        lastNode = null;
    } // end clear

    private class Node<E> {

        private E data;  // data portion
        private Node next;  // next to next node

        private Node(E dataPortion) {
            data = dataPortion;
            next = null;
        } // end constructor

        private Node(E dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private E getData() {
            return data;
        } // end getData

        private void setData(E newData) {
            data = newData;
        } // end setData

        private Node<E> getNextNode() {
            return next;
        } // end getNextNode

        private void setNextNode(Node<E> nextNode) {
            next = nextNode;
        } // end setNextNode
    } // end Node
} // end LinkedQueue