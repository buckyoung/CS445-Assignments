/**
 * @status       n/a
 * @due          
 *
 * @author       BuckYoung 
 * @date         May 25, 2013
 *         
 */

public interface StackInterface<T>{
    public boolean isEmpty();
    public void push(T item);
    public T pop();
}