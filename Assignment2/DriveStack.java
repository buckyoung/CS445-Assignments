
/**
 * @status COMPLETE: expected output
 *
 * @author BuckYoung
 * @date Jun 15, 2013
 *
 */
public class DriveStack {

    public static void main(String[] args) {
        StackInterface<String> stack = new Stack<String>();
        System.out.println("stack = " + stack);
        System.out.println("is stack empty? " + stack.isEmpty());
        System.out.println("try to pop an empty stack - " + stack.pop());
        System.out.println("try to peek an empty stack - " + stack.peek());

        stack.push("Al");
        System.out.println("push: Al");
        stack.push("Bob");
        System.out.println("push: Bob");
        stack.push("Annie");
        System.out.println("push: Annie");
        System.out.println("stack = " + stack);

        System.out.println("pop: " + stack.pop());
        System.out.println("stack = " + stack);

        stack.push("Cal");
        System.out.println("push: Cal");
        stack.push("Dina");
        System.out.println("push: Dina");

        System.out.println("stack = " + stack);
        System.out.println("peek: " + stack.peek());
        System.out.println("stack = " + stack);
        System.out.println("is stack empty? " + stack.isEmpty());

        while (!stack.isEmpty()) {
            System.out.println("pop: " + stack.pop());
        }

        System.out.println("stack = " + stack);

        stack.push("Cal");
        System.out.println("push: Cal");
        stack.push("Dina");
        System.out.println("push: Dina");
        System.out.println("stack = " + stack);

        stack.clear();
        System.out.println("clear stack");
        System.out.println("stack = " + stack);

    }
}