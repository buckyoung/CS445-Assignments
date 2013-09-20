/**
 * @status       COMPLETE         
 *
 * @author       BuckYoung 
 * @date         Jun 29, 2013
 *         
 */

class NoSuchPriorityException extends Exception {
    public NoSuchPriorityException(){
        super("Item added with larger priority than maximum.");
    }
}
