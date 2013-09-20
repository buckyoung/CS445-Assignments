
import Applications.AThreadedBSTree;

public class TestClone{
    //public static void main(String[] args){
        AThreadedBSTree<String> bst = new AThreadedBSTree<String>();
        AThreadedBSTree<String> other = null;
        
        bst.add("Bill");
        bst.add("Al");
        bst.add("Ben");
        bst.add("Carl");
                       
        bst.printTree();
        System.out.println();
        
        other = (AThreadedBSTree<String>)(bst.clone());   //make a clone of bst
        
        other.printTree();
        System.out.println();
        
        System.out.println("add Ada to bst");  //change bst by adding Ada and Abe
        bst.add("Ada");
        bst.add("Abe");
        System.out.println("Displaying bst");
        bst.printTree();                       //see what bst looks like
        System.out.println();

        System.out.println("add Don to other"); //change the clone by adding Don and Bruce
        other.add("Don");
        other.add("Bruce");      
        System.out.println("Displaying other");
        other.printTree();                      //see what the clone other looks like
        System.out.println();
    }
}