/**
 * @status       n/a
 * @due          
 *
 * @author       BuckYoung 
 * @date         May 25, 2013
 *         
 */
import java.util.*;

public class Stack<T> extends ArrayList<T> implements StackInterface<T> {
    
    public Stack(){
        super();
    }
    
    public void push(T item){
        super.add(0,item);
    }
    
    
    public T pop(){
        if(isEmpty()) return null;  //indicates pop() is unsuccessful
        
        return super.remove(0);
        
    }
    
}