
import java.util.ArrayList;


/**
 * @status Complete -- expected output
 *
 * @author BuckYoung
 * @date Jun 15, 2013
 *
 */
public class DriveTokenFinder {

    public static void main(String[] args) {
        try{
            
        TokenFinder finder = new TokenFinder(args[0]);
        ArrayList<Object> result = new ArrayList<>();
        
        Object token = finder.nextToken();
        
        while (token != null) {
            result.add(token);
            token = finder.nextToken();
        }
        
        System.out.print("Tokens: ");
        System.out.println(result.toString());
        
        
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("ERROR: Include the expression as a single command line argument in single-quotations!");
        }
        
    }
}
