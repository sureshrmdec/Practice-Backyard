import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    /*
    *Question :check for balanced parantehesis in an expression
    *Approach : use stack to insert all opening parantehesis and for a closing
    * parantehesis check if top of the stack you find corresponding match,
    * if not then return false
    *Time complexity : O(n) to traverse the string expression and O(n) space
    * complexity for stack
    */
    public boolean isValidExpression(String input) {
        Stack<Character> stack = new Stack<Character>();
        char[] charArray = input.toCharArray();
        for(char c : charArray) {
            if (c == '{' || c == '(' || c == '[') stack.push(Character.valueOf(c));
            if (c == '}' && stack.peek() == '{') stack.pop();
            if (c == ')' && stack.peek() == '(') stack.pop();
            if (c == ']' && stack.peek() == '[') stack.pop();
        }
        return stack.isEmpty();
    }

    public void intNextGreaterElement(int[] input) {
        int size = input.length;

        for(int i = 0; i < size; i++) {
            int next = -1;
            for(int j = i+1; j < size; j++) {
                if(input[j] > input[i]) {
                    next = input[j];
                    break;
                }
            }
            System.out.println(input[i]+"--->"+ next);
        }
    }

    public void intNextGreaterElementStack(ArrayList<Integer> input) {
        int size = input.length;
        int next;
        int element;
        Stack<Integer> stack = new Stack<Integer>();
        Map<Integer, Integer> valueMap = new HashMap<>();
        stack.push(Integer.valueOf(input[0]));
        for(int i = 1; i < size; i++) {
            next = input[i];
            if(!stack.isEmpty()) {
                element = stack.pop();
                while(element < next) {
                    valueMap.put(element, next);
                    if(stack.isEmpty()) break;
                    element = stack.pop();
                }
                if (element > next) {
                    stack.push(element);
                }
            }
            stack.push(next);
            while (!stack.isEmpty()) {
                element = stack.pop();
                valueMap.put(element, -1);
            }
        }
        for(int i  = 0; i < size; i++) {
            System.out.println(valueMap.get(i));
        }
    }
    public static void main(String[] args) {
        StackSol objStackSol = new StackSol();
        int[] in = {4, 5, 2, 25};
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(in)) ;
        objStackSol.intNextGreaterElementStack(input);
    }
}
