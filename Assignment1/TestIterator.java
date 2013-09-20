/**
 * @status       WAE: works as expected        
 *
 * @author       BuckYoung 
 * @date         May 26, 2013
 *         
 */
import java.util.*;

public class TestIterator{
    public static void main(String[] args){
        SBag<String> bag = new SBag<String>();
        
        bag.add("Joe"); bag.add("Jill"); bag.add("Jan");
        bag.add("Jan"); bag.add("Joe"); bag.add("Jan");
        bag.add("Cleo"); bag.add("Jasper"); bag.add("Mike");
        bag.add("Jill"); bag.add("Jan"); bag.add("Joe");
        bag.add("Jasper"); bag.add("Jan"); bag.add("Mike");
        
        System.out.println("names Bag = " + bag);
        
        //This example is given on pages 376-377. It tests to see if your iterator for SBag is iterating properly.
        Iterator<String> nameIterator = new IteratorForSBag<String>(bag);
        Iterator<String> countingIterator;
        String current, other;
        int nameCount;
        
        while(nameIterator.hasNext()){
            nameCount = 0;
            current = nameIterator.next();
            
            countingIterator = new IteratorForSBag<String>(bag);
            while(countingIterator.hasNext()){
                other = countingIterator.next();
                if(current.equals(other)){
                    nameCount++;         
                    if(nameCount > 1){
                        countingIterator.remove();
                        //System.out.println(bag);
                    }
                }
            }
            
            System.out.printf("%s occurs %d times\n", current, nameCount);
        }
        
        System.out.println("\n");
        
        //This code tests to see if the remove() method in the iterator class is working properly.
        SBag<Integer> numbers = new SBag<Integer>();
        
        for(int k=7, diff = 5; k<=208; k=k+diff, diff++)
            numbers.add(k);
        
        System.out.println("numbers Bag = " + numbers);
        
        Iterator<Integer> itr = new IteratorForSBag<Integer>(numbers);
        
        
        while(itr.hasNext()){
           if(itr.hasNext()) itr.next();
           if(itr.hasNext()) itr.next();
           if(itr.hasNext()) itr.next();
           if(itr.hasNext()) itr.next();
           itr.remove();
           System.out.println(numbers);
        }       
    }
}