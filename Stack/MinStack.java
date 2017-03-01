/**
*Problem : implement min stack
*Link : https://leetcode.com/problems/min-stack/
*Time Complexity: O(1) for push, top, pop() and space complexity : O(n)
*/

public class MinStack {
    long min;
    Stack<Long> stack;

    public MinStack(){
        stack=new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L);
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change
            if (x<min) min=x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long pop=stack.pop();

        if (pop<0)  min=min-pop;//If negative, increase the min value

    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);
        }else{
           return (int)(min);
        }
    }

    public int getMin() {
        return (int)min;
    }
}

    /**
    *below approach uses a way where you just have extra attriubte holding minimum value
    at each node
    */
    class MinStack {
    Node head;
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
     if(head == null) {
        Node newHead = new Node(x, x, null);
        head = newHead;
     }
        head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        if(head == null) return;
        Node temp = head;
        head = head.next;
        temp.next = null;
    }

    public int top() {
        if(head == null) return -1;
        return head.data;
    }

    public int getMin() {
        if(head == null) return -1;
        return head.min;
    }

    static class Node {
        int data;
        int min;
        Node next;

        public Node(int data, int min, Node next) {
            this.data = data;
            this.min = min;
            this.next = next;
        }
    }
}
