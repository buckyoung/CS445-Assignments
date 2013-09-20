
import java.util.Scanner;

public class PhDCandidate implements Comparable<PhDCandidate> {

    private static double threshold;   //a passing score is threshold or higher
    private static int numberOfExams;  //number of exams requiring a passing score
    private static int candidates;     //number of candidates taking the preliminary exams
    private String name; //candidate's name
    private Exam[] preliminaryExams;  //an array of exam scores for this candidate

    //STATICmethods  //#########FINISHED
    public static void setThreshold(double t) { //sets the threshold (the passing score) for each exam for this preliminary exam
        threshold = t;
    }

    public static void setNumberOfExams(int n) { //sets the number of exams to be passed be all candidates
        numberOfExams = n;
    }

    public static void setCandidates(int c) {  //sets the number of candidates taking the preliminary exams this time
        candidates = c;
    }

    public static double getThreshold() { //gets the threshold
        return threshold;
    }

    public static int getNumberOfExams() { //gets the number of exams
        return numberOfExams;
    }

    public static int getCandidates() { //gets the number of candidates taking the preliminary exams
        return candidates;
    }

    //CONSTRUCTOR
    public PhDCandidate(Scanner in) { //reads data for each exam and places the exam (see private inner-class) in the preliminaryExam[] array
        //First line in is name
       name = in.nextLine();
       
        //Next two lines are an exam and the score //while > number of exams
        preliminaryExams = new Exam[numberOfExams];
        for (int i = 0; i < numberOfExams; i++) {
            preliminaryExams[i] = new Exam(in.nextLine(), Double.parseDouble(in.nextLine()));
        }
      

    }

    //INSTANCEmethods
    public boolean equals(PhDCandidate other) { //does this candidate name equal other candidate name
        return this.getName().equals(other.getName());
    }

    public String getName() { //get this candidate's name
        return name;
    }

    public int numberOfExamsAboveOrEqualThreshold() {  //compute the number of exams with a score greater than or equal to the threshold
        int count = 0;
        for (int i = 0; i < preliminaryExams.length;i++){
            if (preliminaryExams[i].score >= threshold)
               count++;
        }
        return count;
    }

    public String toString() { //a textual representation of this PhDCandidate (name, followed by the exams taken and scores received) 
        //for example:
        // Reesa Leopold
        // Abstract Algebra I       : 82.00
        StringBuilder result = new StringBuilder();
        result.append(name+"\n");
        for (int i=0; i<preliminaryExams.length;i++){
            result.append(preliminaryExams[i].course + " \t : "+preliminaryExams[i].score+"\n");
        }
        return result.toString();
    }

    public int compareTo(PhDCandidate other) { //this candidate is greater than the other candidate if and only if this candidate passes more exams than the other candidate.
        if (this.numberOfExamsAboveOrEqualThreshold() > other.numberOfExamsAboveOrEqualThreshold()){
            return 1;
        } else if (this.numberOfExamsAboveOrEqualThreshold() < other.numberOfExamsAboveOrEqualThreshold()) {
            return -1;
        } else {
            return 0;
        }
    }

    private class Exam {

        private String course;
        private double score;

        public Exam(String course, double score) {
            this.course = course;
            this.score = score;
        }

        public String toString() {
            return String.format("%-25s: %1.2f", course, score);
        }
    }
}