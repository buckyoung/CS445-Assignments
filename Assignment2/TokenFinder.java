
/**
 * @status COMPLETE
 *
 *
 * @author BuckYoung
 * @date Jun 15, 2013
 *
 */
public class TokenFinder {

    private StringBuffer statement;   //valid Java assignment statement
    private int index;                //index of the character that the TokenFinder is currently sitting at

    public TokenFinder(String s) {  //initializes the assignment statement and index
        statement = new StringBuffer(s);
        index = 0;
    }

    /*
     * Returns null if an unsupported character was encountered
     * 
     * Returns the token otherwise
     * 
     */
    public Object nextToken() {     //returns the next token in the assignment statement provided there is one, otherwise returns null.

        Object result = null;

        if (index != statement.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            //Lets find a target char, ignoring whitespace.
            char target = '?';

            //get the next character and ignore whitespace:
            while (index != statement.length()) {
                target = statement.charAt(index);
                index++;
                if (!Character.isWhitespace(target)) {
                    break;
                }
            }

            //Now we have a target, lets determine what it is:

            //if is a letter, then we must find the end of the variable     xval yval
            if (Character.isLetter(target)) {
                //append it!
                stringBuilder.append(target);
                //check to see if it is longer than one character
                while (index != statement.length()) { //stay in range
                    target = statement.charAt(index);
                    index++;
                    if (Character.isLetterOrDigit(target) || target == '_') { //append if found another letter, digit, or legal underscore
                        stringBuilder.append(target);
                    } else {
                        result = stringBuilder.toString(); //SET RESULT
                        index--;
                        break;
                    }
                }

            } //DO THE SAME FOR DIGITS!
            else if (Character.isDigit(target)) {
                //append it!
                stringBuilder.append(target);
                while (index != statement.length()) {
                    target = statement.charAt(index);
                    index++;
                    if (Character.isDigit(target) || target == '.') { //append if found another number or legal decimal place
                        stringBuilder.append(target);
                    } else {
                        index--;
                        break;
                    }
                }
                result = Double.parseDouble(stringBuilder.toString()); //SET RESULT
            } //if is an operator, a parens, or a semicolon : return it    + - / * ^ ( ) ; =
            else if (target == '^' || target == '*' || target == '/' || target == '+' || target == '-'
                    || target == '(' || target == ')' || target == ';' || target == '=') {
                result = target; //SET RESULT

            }
        }
        return result; //result == a string, a double, or a char (or NULL)
    }
}