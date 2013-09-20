
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @status INCOMPLETE
 *
 * @author BuckYoung
 * @date Jul 24, 2013
 *
 */
public class PhDCandidateDriver {

    private static final String file = "./src/prelimfall2013.txt";

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new FileReader(file));

        //sets the threshold to the first line of data in the data file (see prelimfall2013.txt)
        PhDCandidate.setThreshold(Double.parseDouble(in.nextLine()));

        //set the number of exams to second line of data in the data file
        PhDCandidate.setNumberOfExams(Integer.parseInt(in.nextLine()));

        //sets the number ofcandidates to the third line of data in the data file
        PhDCandidate.setCandidates(Integer.parseInt(in.nextLine()));


        /*
         4) Initialize an empty bst = AThreadedBSTree<PhDCandidate>
         while there are lines left in the file
         read a PhDCandidate
         add candidate to bst (Binary Search Tree)
         */
        AThreadedBSTree<PhDCandidate> bst = new AThreadedBSTree<>();
        while (in.hasNext()) {
            PhDCandidate c = new PhDCandidate(in);
            bst.add(c);
        }
        
        //Display Exam Statistics:
        System.out.println("Exam Statistics:");
        // a) The minimum number of exams passed
        System.out.println("Minimum number of exams passed: = " + bst.min().numberOfExamsAboveOrEqualThreshold());
        // b) The maximum number of exams passed
        System.out.println("Maximum number of exams passed: = " + bst.max().numberOfExamsAboveOrEqualThreshold());
        System.out.println();
        
        // c) for each candidate display their name and the number of exams passed
        PhDCandidate temp = bst.max();
        for (int i = 0; i < PhDCandidate.getCandidates();i++){
            System.out.println((i+1)+". "+temp.getName()+" passed "+temp.numberOfExamsAboveOrEqualThreshold()+" exams");
            temp = bst.predecessor(temp);
        }
        System.out.println();
        
        // d) Collect into an ArrayList all candidates who passed the preliminary exams and display their exam scores
        System.out.println("Passing candidate's performance summary");
        System.out.println("---------------------------------------");
        temp = bst.min();
        ArrayList<PhDCandidate> passingList = new ArrayList<>();
        for (int i = 0; i < PhDCandidate.getCandidates();i++){
            if (temp.numberOfExamsAboveOrEqualThreshold()>=PhDCandidate.getNumberOfExams()){
                //we have a passing candidate
                passingList.add(temp);
                System.out.println(temp);
                System.out.println();
            }
            temp = bst.successor(temp);
        }
        // e) Collect into an ArrayList all candidates who passed the fewest exams (worst performers) and display their scores.
        System.out.println("Worst performers summary");
        System.out.println("------------------------");
        ArrayList<PhDCandidate> worstPerformers = new ArrayList<>();
        PhDCandidate worstPhD = bst.min();
        temp = bst.min();
        for (int i = 0; i < PhDCandidate.getCandidates();i++){
            if (temp.numberOfExamsAboveOrEqualThreshold()==worstPhD.numberOfExamsAboveOrEqualThreshold()){
                //we have a passing candidate
                worstPerformers.add(temp);
                System.out.println(temp);
                System.out.println();
            }
            temp = bst.successor(temp);
        }
    }
}
