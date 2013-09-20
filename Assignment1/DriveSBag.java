/**
 * @status       WAE: works as expected
 * @due          
 *
 * @author       BuckYoung 
 * @date         May 26, 2013
 *         
 */
public class DriveSBag{
    public static void main(String[] args){
        BagInterface<String> bag = new SBag<String>();
        
        System.out.println(bag.add(null));
        System.out.println("add Cal to bag? " + bag.add("Cal"));
        System.out.println("add John to bag? " + bag.add("John"));
        System.out.println("add Harry to bag? " + bag.add("Harry"));
        System.out.println("add Cal to bag? " + bag.add("Cal"));
        System.out.println("add Mary to bag? " + bag.add("Mary"));
        System.out.println("add Jill to bag? " + bag.add("Jill"));
        System.out.println("add Milton to bag? " + bag.add("Milton"));
        System.out.println("add Lars to bag? " + bag.add("Lars"));
        System.out.println("add Cal to bag? " + bag.add("Cal"));
        System.out.println("add Milton to bag? " + bag.add("Milton"));
        System.out.println("add Cal to bag? " + bag.add("Cal"));
        
        System.out.println("size of bag is " + bag.getCurrentSize());
        System.out.println("bag = " + bag + "\n");
        
        
       
        System.out.println("Items in bag are:");
        for(String s : (SBag<String>)bag){
            System.out.println(s);
        }
        
        
        System.out.println("\nremove an item: " + bag.remove());
        System.out.println("bag = " + bag + "\n");
        
        System.out.println("remove Mary");
        bag.remove("Mary");
        System.out.println("bag = " + bag + "\n");
        
        
        System.out.println("frequency of Cal is " + bag.getFrequencyOf("Cal"));
        
        System.out.println("does bag contain George? " + bag.contains("George"));
        System.out.println("does bag contain John? " + bag.contains("John"));
        
        System.out.println("\nConvert bag to an array:");
        Object[] names = bag.toArray();
        
        for(Object s: names){
            System.out.println(s);
        }
        
    }
}