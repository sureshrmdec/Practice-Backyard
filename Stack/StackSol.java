import java.lang.StringBuilder;
import java.util.Stack;

public class StackSol {

    /*
    *Question : Reverse a string using Stack
    *Approach : Loop through each element and put in stack then pop and build
    * reverse String - Time complexity : 0(n) + space complexity 0(n)
    */
    public String reverseString(String input) {
        Stack<Character> stack = new Stack<Character>();
        int size = input.length();
        for(int i = 0; i < size; i++) {
            stack.push(input.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        StackSol objStackSol = new StackSol();
        System.out.println(objStackSol.reverseString("GeeksQuiz"));
    }
}
