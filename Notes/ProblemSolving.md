Problem : Rotate a Linked List (http://www.geeksforgeeks.org/rotate-a-linked-list/)

Constraint :
    1. How long Linked List will be ?
    2. what if linked List is smaller than the given size
    3. Is it 1 indexed or zero indexed
    4. What if linked List is Empty

Ideas :
    1. Loop through list till you reach pivot element for rotation and divide
    the list in two half and connect them.
    Time Complexity : O(n) and O(1) space Complexity


Code :
public void rotateLinkedList(ListNode head, int target) {
    if(target < 1) return;

    ListNode currentNode = head;
    ListNode secondList;
    int counter = 1;

    //Loop till rotate pivot element
    while(currentNode != null  &&  counter < target) {
        currentNode = currentNode.next;
        counter++;
    }
    // Check and divide the list in two set
    if(currentNode == null) return;
    secondList = currentNode.next;
    currentNode.next = null;
    currentNode  = secondList;

    //Find the last node and point to first list
    ListNode previousNode = null;
    while(currentNode != null) {
        previousNode = currentNode;
        currentNode = currentNode.next;
    }

    previousNode.next = head;
    printList(secondList);
}

Problem : Move Zeros https://leetcode.com/problems/move-zeroes/
Example :
    Input:[0, 1, 0, 3, 12]
    Output: [1, 3, 12, 0, 0]

Constrinat :
    1. What the size of array
    2. Can it contain negative numbers
    3. Can array be empty
    4. Is it always the case that it contain Zeros
    5. No copy of array
    6. With minimum operations

Idea:
    1. Enter the non zero elements to the top of array and then fill rest
    with zero

Time Complexity : O(n)
Space Complexity: O(1)

Code:
    public int[] moveZeros(int[] input) {
        if(input.length == 0) return input;

        int counter = 0;
        int size = input.length;
        for(int i = 0; i < size; i++) {
            if(input[i] != 0) {
                input[counter] = input[i];
                counter++;
            }
        }
        while(counter < size) {
            input[counter] = 0;
            counter++;
        }
        return input;
    }

Test :
1. Edge Case : if array is empty ,return array itself
2. Trivial test case : work properly
3. Non Trivial Case : Works


Problem:https://leetcode.com/problems/first-unique-character-in-a-string/
    first unique character in string

Constrinat:
    1. What if unique character don't exit : return -1;
    2. What if string is empty : return -1;

Idea :
    1. Loop over all elements and put character count in hashMap
    2. Loop again and find the first character with only one count
    Time Complexity: O(n); Space Complexity : O(n)

    1. We can have int frequency array to store number of occurence in string
    2. Scan the string and form the frequency array
    3. Go through string again and check if any one of them is 1

Code:
    public char firstUniqueCharacter(String input) {
        Map<character, Integer> map = new hashMap<>();

        int size = input.length();
        for(int i = 0; i < size; i++) {
            char temp = input.charAt(i);
            if(map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);
            } else {
                map.put(temp, 1);
            }
        }

        for(int i = 0; i < size; i++) {
            if(map.get(input[i]) == 1) return input.charAt(i);
        }
        return -1;
    }


Problem : https://leetcode.com/problems/min-stack/
Create a Min stack with push(), pop(), top() and getMin() in constant Time

Constraint :
    1. Are we allowed to have additional data structure

Idea:
    With every stack node have an extra label to store the minimum at this level
    and update with both push and pop()

    Create and additional stack data structure that will keep min node till this
    point

Code:

public class MinStack {
Node head;
/** initialize your data structure here. */
public MinStack() {

}

public void push(int x) {
 if(head == null) {
    Node newHead = new Node(x, x);
    head = newHead;
 }
    Node node = new Node(x, Math.min(x, head.min));
    node.next = head;
    head = node;
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

    public Node(int data, int min) {
        this.data = data;
        this.min = min;
        this.next = null;
    }
}
}
