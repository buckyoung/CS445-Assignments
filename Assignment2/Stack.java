
import java.util.ArrayList;

/**
 * @status       COMPLETE         
 *
 * @author       BuckYoung 
 * @date         Jun 15, 2013
 *         
 */

public class Stack<T> extends ArrayList implements StackInterface<T> {
    
    public Stack(){
        super();
    }
    
    @Override
    public void push(T item) {
        super.add(0, item);
    }

    @Override
    public T pop() {
        T result = null;
        if(!super.isEmpty()){
            result = (T)super.remove(0);
        }
        return result;
    }
    
    public T peek(){
        T result = null;
        if(!super.isEmpty()){
            result = (T)super.get(0);
        }
        return result;
    }
    
}
