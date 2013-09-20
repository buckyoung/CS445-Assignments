import Applications.AThreadedBSTree;
import java.util.ArrayList;

public class AThreadedBSTreeDriver{
   public static void main(String[] args){
        AThreadedBSTree<String> bst = new AThreadedBSTree<String>();
        AThreadedBSTree<String> myclone = null;
        
        bst.add("Bill");  //same entries as animation
        bst.add("Mel");
        bst.add("Ron");
        bst.add("Ben");
        bst.add("Pete");
        bst.add("Amy");
        bst.add("Bess");
        bst.add("Pam");
        
        bst.printTree();
        
        System.out.println("Parent of Bill is: "+ bst.parent("Bill"));
        System.out.println("Parent of Ben is: "+ bst.parent("Ben"));
        System.out.println("Parent of Bess is: "+ bst.parent("Bess"));
        System.out.println("Parent of Amy is: "+ bst.parent("Amy"));
        System.out.println("Parent of Mel is: "+ bst.parent("Mel"));
        System.out.println("Parent of Ron is: "+ bst.parent("Ron"));
        System.out.println("Parent of Pete is: "+ bst.parent("Pete"));
        System.out.println("Parent of Pam is: "+ bst.parent("Pam"));
        System.out.println("Parent of Larry is: "+ bst.parent("Larry"));
        System.out.println();
        
        System.out.println("Predecessor of Bill is: "+ bst.predecessor("Bill"));
        System.out.println("Predecessor of Ben is: "+ bst.predecessor("Ben"));
        System.out.println("Predecessor of Bess is: "+ bst.predecessor("Bess"));
        System.out.println("Predecessor of Amy is: "+ bst.predecessor("Amy"));
        System.out.println("Predecessor of Mel is: "+ bst.predecessor("Mel"));
        System.out.println("Predecessor of Ron is: "+ bst.predecessor("Ron"));
        System.out.println("Predecessor of Pete is: "+ bst.predecessor("Pete"));
        System.out.println("Predecessor of Pam is: "+ bst.predecessor("Pam"));
        System.out.println("Predecessor of Larry is: "+ bst.predecessor("Larry"));
        System.out.println();
        
        System.out.println("Successor of Bill is: "+ bst.successor("Bill"));
        System.out.println("Successor of Ben is: "+ bst.successor("Ben"));
        System.out.println("Successor of Bess is: "+ bst.successor("Bess"));
        System.out.println("Successor of Amy is: "+ bst.successor("Amy"));
        System.out.println("Successor of Mel is: "+ bst.successor("Mel"));
        System.out.println("Successor of Ron is: "+ bst.successor("Ron"));
        System.out.println("Successor of Pete is: "+ bst.successor("Pete"));
        System.out.println("Successor of Pam is: "+ bst.successor("Pam"));
        System.out.println("Successor of Larry is: "+ bst.successor("Larry"));
        System.out.println();
        
        System.out.println("Ancestors of Amy: "+ bst.ancestors("Amy"));
        System.out.println("Ancestors of Pam: "+ bst.ancestors("Pam"));
        System.out.println("Ancestors of Bill: "+ bst.ancestors("Bill"));
        System.out.println("Ancestors of Larry: "+ bst.ancestors("Larry"));
        
      
        System.out.println("\nDoes Bess belong? " + bst.contains("Bess"));
        System.out.println("Does George belong? " + bst.contains("George"));
        
        System.out.println("\nWho is the minimum? " + bst.min());
        System.out.println("Who is the maximum? " + bst.max());
        
        System.out.println("\nHow many occurrences of Mel? " + bst.countOccurrences("Mel"));
        System.out.println("Collect all occurrences of Mel? " + bst.collectAllEqualTo("Mel"));
        
        //Add some duplicates
        bst.add("Mel");
        bst.add("Ron");
        bst.add("Amy");
        bst.add("Mel");
        
        System.out.println("\nHow many occurrences of Mel? " + bst.countOccurrences("Mel"));
        System.out.println("Collect all occurrences of Mel? " + bst.collectAllEqualTo("Mel") + "\n");
        
        bst.printTree(); 
        System.out.println();
        
        
        //Add some items to exceed initial array-space size
        bst.add("Aly");
        bst.add("Mandy");
        bst.add("Paul");
        bst.add("George");
        bst.add("Bette");
        
        System.out.println();
        
        bst.printTree(); 
        System.out.println();
        
    }
}