import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.lang.StringBuilder;

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

    public void intNextGreaterElementStack(int[] input) {
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

    /**
    *Problem : Reverese a stack using revereseStackRecurrsion
    *Approach : key observation is at each step of recurrsion pop out value and
    * combine them while calling back the recurrsion
    * Time Complexity : O(n) -> calling recurrsion stack for all values of stack
    * Space Complexity : 0(n) -> stack Space
    */

     public String revereseStackRecurrsion(Stack input) {

    }

    /**
    *Problem : Sort a stack using recurrsion, No looping construct are allowed
    *Obeservation : Thing in terms of recurrsion how recurssion stack will hold
    *values and how it will recompute
    *Pseudo Code :
    * sortStack(stack S)
        if stack is not empty
        temp = s.pop();
        sortStack(s)
        sortedInsert(s, temp);
    * sortedInsert(Stack s, element)
        if stack is empty or element is smaller than top
            s.push()
        int temp = s.pop();
        sortedInsert(stack s, element);
        push (s, temp)
    * Time Complexity : O(n) , space Complexity : O(n)
    */
    public void sortStackRecurrsion(Stack<Integer> s) {
            if (s.isEmpty()) return;
            Integer temp = s.pop();
            sortStackRecurrsion(s);
            sortedInsert(s, temp);
            System.out.println(s.toString());
    }

    public void sortedInsert(Stack<Integer> s, Integer element) {
        if(s.isEmpty() || s.peek() < element) {
            s.push(element);
            return;
        }
        Integer temp = s.pop();
        sortedInsert(s, element);
        s.push(temp);
    }

    public String infixToPrefix(String input) {
        char[] charArray = input.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        int inputSize = charArray.length;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < inputSize; i++) {
            if(charArray[i] == ' ' || charArray[i] == ',' ) continue;
            if(isOperator(charArray[i])) {
                while(!stack.isEmpty() && stack.peek() != '(' && HasHigherPrecedence(stack.peek(), charArray[i])) {
                    sb.append(stack.pop());
                }
                s.push(charArray[i]);
                sb.append(char);
            } else if (isOperand(charArray[i])) {
                sb.append(charArray[i]);
            } else if (charArray[i] == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(s.pop());
                }
            }
        }

        while(!stack.isEmpty()) {
            sb.append(s.pop());
        }

        return sb.toString();
    }

    boolean isOperator(char C) {
        if(C == '+'|| C == '-' || C == '/' || C == '*' || C == '^' ||  C == '$') return true;
        return false;
    }

    boolean isOperand(char C) {
        if(C >= '0' && C <= '9') return true;
        if(C >= 'a' && C <= 'z') return true;
        if(C >= 'A' && C <= 'Z') return true;
        return false;
    }
    int GetOperatorWeight(char op)  {

        if(op == '+' || op == '-') return 1;
        if(op =='*' || op == '/') return 2;
        if(op == '$') return 3;
        return -1;
    }

    if isRightAssociative(char op) {
        if(op == '$') return true;
        return false;
    }
    boolean HasHigherPrecedence(char op1, char op2) {
        int op1Weight = GetOperatorWeight(op1);
        int op2Weight = GetOperatorWeight(op2);
        if (op1Weight == op2Weight) {
            if(isRightAssociative(op1)) return false;
            else return true;
        }
        return op1Weight > op2Weight ? true : false;
    }

    public static void main(String[] args) {
        StackSol objStackSol = new StackSol();
    }

    /**
    *problem : evaluate the postfix Notation - reverse polish Notation
    *Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
    *Time Complexity : O(n) ; space Complexity :O(n)
    */
    public int evalRPN(String[] tokens) {
       int a,b;
		Stack<Integer> S = new Stack<Integer>();
		for (String s : tokens) {
			if(s.equals("+")) {
				S.add(S.pop()+S.pop());
			}
			else if(s.equals("/")) {
				b = S.pop();
				a = S.pop();
				S.add(a / b);
			}
			else if(s.equals("*")) {
				S.add(S.pop() * S.pop());
			}
			else if(s.equals("-")) {
				b = S.pop();
				a = S.pop();
				S.add(a - b);
			}
			else {
				S.add(Integer.parseInt(s));
			}
		}
		return S.pop();
    }

    /**
    *Problem : find the next greater element for a subset of array in larger ArrayList
    *Link :https://leetcode.com/problems/next-greater-element-i/
    *Time Complexity :  O(n) ; space Complexity :O(n)
    */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Stack<Integer> stack = new Stack<>();

        for(int n : nums) {
            while(!stack.isEmpty() && stack.peek() < n ) {
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }
        for(int i = 0; i < findNums.length; i++) {
            findNums[i] = map.getOrDefault(findNums[i], -1);
        }
        return findNums;

    }
}
