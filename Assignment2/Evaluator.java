
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @status Mostly Complete -- TODO: handle exceptions
 *
 * @author BuckYoung
 * @date Jun 15, 2013
 *
 */
public class Evaluator {

    private static TokenFinder tokenFinder;
    private static Stack<Double> valueStack = new Stack<>();
    private static Stack<Character> operatorStack = new Stack<>();
    //
    private static String assignThis;
    private static Hashtable<String, Double> hashTable = new Hashtable<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));

            String line;

            //
            System.out.println("\nEvaluate Assignment Statements:");
            int assignmentCount = 0;
            while ((line = reader.readLine()) != null) { //while we still have lines in the file
                assignmentCount++;
                System.out.println(" "+assignmentCount + ". " + line);

                tokenFinder = new TokenFinder(line);

                assignThis = (String) tokenFinder.nextToken(); //Save the ultimate assignee for later
                tokenFinder.nextToken(); //Let the equal sign disappear

                //Time to evaluate the expression by using INFIX and 2 Stacks!
                double result;
                try {
                    result = evaluateInfix();
                    //Update hashtable.
                    hashTable.put(assignThis, result);
                } catch (ExpressionException ex) {
                    Logger.getLogger(Evaluator.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


            System.out.println("\nVariables and their final values:");

            Enumeration<String> keys = hashTable.keys();
            Enumeration<Double> elements = hashTable.elements();
            while (keys.hasMoreElements()) {
                System.out.println(keys.nextElement() + " = " + elements.nextElement());
            }
            System.out.println();
            reader.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("ERROR: Include the file name as a single command line argument!");
        } catch (FileNotFoundException ex){
            System.out.println("ERROR: File not found!");
        }
    }

    /*
     * determines which stack to add the token to!
     */
    private static void addToStack(Object token) throws ExpressionException {


        if (token.getClass() == Double.class) { //This is a number
            //push value onto valueStack
            valueStack.push((Double) token);

        } else if (token.getClass() == String.class) { //This is essentially a number
            //get value of variable
            Double value = hashTable.get((String) token);
            //push value onto valueStack
            valueStack.push(value);

        } else if (token.getClass() == Character.class) { //This is an operator
            //Always push ^
            //Always push (
            try {
                switch ((Character) token) {
                    case '^':
                        operatorStack.push((Character) token);
                        break;
                    case '(':
                        operatorStack.push((Character) token);
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!operatorStack.isEmpty() && precedence((Character) token, operatorStack.peek())) {

                            Character topOperator = operatorStack.pop();
                            double operandTwo = valueStack.pop();
                            double operandOne = valueStack.pop();
                            valueStack.push(evaluate(operandOne, topOperator, operandTwo));
                        }
                        operatorStack.push((Character) token);
                        break;
                    case ')':
                        Character topOperator = operatorStack.pop();
                        while (topOperator != '(') {

                            double operandTwo = valueStack.pop();
                            double operandOne = valueStack.pop();
                            valueStack.push(evaluate(operandOne, topOperator, operandTwo));
                            topOperator = operatorStack.pop();

                        }
                        break;
                    default:
                        break;

                }
            } catch (NullPointerException ex) {
                throw new ExpressionException();
            }

        }

    }

    private static boolean precedence(Character first, Character second) {
        //assume (
        int firstVal = 0;
        int secondVal = 0;

        //check for + or -
        if (first == '+' || first == '-') {
            firstVal = 1;
        }
        if (second == '+' || second == '-') {
            secondVal = 1;
        }

        //check for * or /
        if (first == '*' || first == '/') {
            firstVal = 2;
        }
        if (second == '*' || second == '/') {
            secondVal = 2;
        }

        //check for ^
        if (first == '^') {
            firstVal = 3;
        }
        if (second == '^') {
            secondVal = 3;
        }

        return firstVal <= secondVal;
    }

    private static double evaluate(double one, Character operator, double two) {
        double result = -1;
        switch (operator) {
            case '+':
                result = one + two;
                break;
            case '-':
                result = one - two;
                break;
            case '*':
                result = one * two;
                break;
            case '/':
                result = one / two;
                break;
            case '^':
                result = Math.pow(one, two);
                break;
        }
        return result;
    }

    private static double evaluateInfix() throws ExpressionException {
        try {
            Object token;

            while ((token = tokenFinder.nextToken()) != null) { //while we still have tokens to find             
                addToStack(token); //send the token off to find a home on one of the two stacks
            }

            //FINALLY, while the operatorStack is not empty, do evaluations
            while (!operatorStack.isEmpty()) {

                Character topOperator = operatorStack.pop();
                double operandTwo = valueStack.pop();
                double operandOne = valueStack.pop();
                valueStack.push(evaluate(operandOne, topOperator, operandTwo));

            }
            return valueStack.pop();
        } catch (NullPointerException ex) {
            throw new ExpressionException();
        }
    }
}
