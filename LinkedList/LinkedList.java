import java.util.ArrayList;
import java.util.Stack;

public class LinkedList {
    ListNode head; //head of the LinkedList

    /* static class
    */
    public static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void printList(ListNode node) {
        ListNode temp = node;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
    /*
    Push Operation to support stack functionality
    Time Complexity : O(1) as it does constant amount of work
    */
    public void push (int data) {
        ListNode newHead = new ListNode(data);
            newHead.next = head;
            head = newHead;
    }

    /**
    * Pop the top element from the stack
    @return top element of stack if present,-1 otherwise
    */
    public int pop() {
        if (head == null) return -1;
        int data = head.data;
        head = head.next;
        return data;
    }

    /**
    *Peek the top element from the stack
    @return top element of stack if present ,-1 otherwise
    */
    public int peek() {
        return head.data;
    }

    /**
    *makeEmpty will clear all stack entries
    */
    public void makeEmpty() {
        head = null;
    }


    /*
    Inserting an element at given position
    Time Complexity : O(n) in worst case
    */
    public void insertAtPosition(int position, int data) {
        ListNode insertNode = new ListNode(data);
        ListNode currentNode = head;
        int counter =0;
        while (counter < position) {
            currentNode = currentNode.next;
            counter++;
        }
        ListNode previousNode = currentNode;
        insertNode.next = previousNode.next;
        previousNode.next = insertNode;
    }

    /*
    Inserting an element in the end
    Time Complexity :: O(n) in worst case
    */
    public void insertAtlast(int data) {

        if (head == null) {
            head = new ListNode(data);
            return;
        }
        ListNode endNode = new ListNode(data);
        ListNode currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        ListNode previousNode = currentNode;
        previousNode.next = endNode;
    }

    public void deleteNode(int key) {
        ListNode currentNode = head, previousNode = null;
        if (currentNode == null) System.out.println ("The list is empty");
        if (currentNode != null && currentNode.data == key) {
            head = currentNode.next;
            return;
        }

        while (currentNode != null && currentNode.data != key) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode == null) return;
        previousNode.next = currentNode.next;
    }
    /*
    Time Complexity :  O(n), Iterative Length for LinkedList
    */
    public int listLength() {
        ListNode currentNode = head;
        if (currentNode == null) return 0;
        if (currentNode.next == null) return 1;

        int counter =0;
        while(currentNode != null) {
            counter++;
            currentNode =currentNode.next;
        }
        return counter;
    }

    /*
    List Length recurresively
    Time Complexity : O(n) and space Complexity O(n)
    */

    public int listLengthRec() {
        ListNode currentNode = head;
        return listLengthRecurrsive(currentNode);
    }

    public int listLengthRecurrsive(ListNode node) {
        if (node == null) return 0;
        return 1 + listLengthRecurrsive(node.next);
    }

    public void swapNodes(int data1, int data2) {
        // Don't swap if both data 1 and data2 are same
        if (data1 == data2) return;

        ListNode previousNodeX = null, currentNodeX = head;
        while(currentNodeX != null && currentNodeX.data  != data1) {
            previousNodeX = currentNodeX;
            currentNodeX = currentNodeX.next;
        }

        ListNode previousNodeY = null,currentNodeY = head;
        while(currentNodeY !=null && currentNodeY.data != data2) {
            previousNodeY = currentNodeY;
            currentNodeY = currentNodeY.next;
        }

        // If any of the two data element is not present in the LinkedList
        if (currentNodeX == null || currentNodeY == null) return;

        if(previousNodeX != null) {
            previousNodeX.next = currentNodeY;
        }else {
            head = currentNodeY;
        }

        if(previousNodeY != null) {
            previousNodeY.next =currentNodeX;
        }else {
            head = currentNodeX;
        }

        ListNode temp = currentNodeX.next;
        currentNodeX.next = currentNodeY.next;
        currentNodeY.next = temp;
    }

    /*
    Reverse a LinkedList
    Time Complexity : O(n)
    Logic : at each node keep swaping the nodes
    */

    public ListNode reverseList(ListNode node) {
        ListNode previousNode = null, nextNode = null, currentNode = node;
        if (currentNode == null || currentNode.next == null) return node;
        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return previousNode;
    }

    public ListNode mergeSortedList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }

        if(head2 == null) {
            return head1;
        }

        ListNode currentNode = null;
        if(head1.data < head2.data) {
            currentNode = head1;
            currentNode.next = mergeSortedList(head1.next,head2);
        } else {
            currentNode = head2;
            currentNode.next = mergeSortedList(head1, head2.next);
        }
        return currentNode;
    }
    public void mergeSort(ListNode node) {

        if (node == null || node.next == null) return;

        ListNode mid = getMidNode(head);
        ListNode firstList = head;
        ListNode secondList = mid.next;
        mid.next = null;
        mergeSort(firstList);
        mergeSort(secondList);
        head = mergeSortedList(firstList,secondList);
    }

    public void mergeSort() {
        mergeSort(head);
    }

    ListNode getMidNode(ListNode head) {
        ListNode slow =head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
    *Problem :Reverse a Linked List in groups of given size
    *Approach :
    *   Divide the list into two part with pivot as give size key
    *   Reverese each list
    *   Join first list with secondList
    *Time Complexity : O(n)
    *Space Complexity : O(n)
    */
    public void ReverseGroup(ListNode node, int k) {
        if(node == null || node.next == null) return;
        ListNode firstList = node;
        ListNode currentNode = node;
        ListNode secondList;
        int counter =0;
        while(counter < k && currentNode.next != null) {
            currentNode = currentNode.next;
            counter++;
        }
        secondList = currentNode.next;
        currentNode.next = null;
        firstList = reverseList(firstList);
        secondList = reverseList(secondList);
        currentNode = firstList;
        while (currentNode.next !=null) {
            currentNode = currentNode.next;
        }
        currentNode.next = secondList;
        printList(firstList);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        ListNode currentNodeL1 = l1;
        ListNode currentNodeL2 = l2;

        while(currentNodeL1 != null) {
            s1.push(currentNodeL1.data);
            currentNodeL1 = currentNodeL1.next;
        }

        while(currentNodeL2 != null) {
            s2.push(currentNodeL2.data);
            currentNodeL2 = currentNodeL2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {

            if(!s1.empty()) sum += s1.pop();
            if(!s2.empty()) sum += s2.pop();
            list.data = sum % 10;
            ListNode head = new ListNode(sum/10);
            head.next = list;
            list = head;
            sum = sum/10;
        }

        return list.data  == 0 ? list.next  : list;
    }
    /*
    *Problem :http://www.geeksforgeeks.org/rotate-a-linked-list/
    *Idea : Divide the list in two sets and rearrange the pointers
    *Time Complexity : O(n); Space Complexity : O(1)
    *@param : ListNode head, int target
    *@return void,Change the List in place
    */
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

    /*
    *Problem :detect a loop in linked ListNode
    *http://www.ideserve.co.in/learn/detect-a-loop-in-a-linked-list
    *Flyod loop detection Algorithm
    *Time Complexity: O(n); space Complexity :O(1)
    */
    public ListNode isListContainLoop(ListNode head) {
        ListNode slowNode = head, fastNode = head;
        if(head == null || head.next == null) return null;
        while(slowNode != fastNode) {
            if(slowNode == null || fastNode == null || fastNode.next == null) return null;
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        slowNode = head;
        while(slowNode != fastNode) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        return slowNode;
    }
    /*
    *https://leetcode.com/problems/merge-two-sorted-lists/?tab=Description
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode currentNode1 = l1, currentNode2 = l2;
        if(currentNode1 == null && currentNode2 == null) return null;
        if(currentNode1 == null) return l2;
        if(currentNode2 == null) return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode dummyPointer = dummyHead;
        while(currentNode1 != null && currentNode2 != null) {
            if(currentNode1.val < currentNode2.val) {
                dummyPointer.next = currentNode1;
                currentNode1 = currentNode1.next;
                dummyPointer = dummyPointer.next;
            } else {
                dummyPointer.next = currentNode2;
                currentNode2 = currentNode2.next;
                dummyPointer = dummyPointer.next;
            }
        }
        while(currentNode1 != null) {
            dummyPointer.next = currentNode1;
            currentNode1 = currentNode1.next;
            dummyPointer = dummyPointer.next;
        }
        while(currentNode2 != null) {
            dummyPointer.next = currentNode2;
            currentNode2 = currentNode2.next;
            dummyPointer = dummyPointer.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {

        LinkedList llist = new LinkedList();
        llist.head = new ListNode(10);
        llist.head.next = new ListNode(20);
        llist.head.next.next = new ListNode(30);
        llist.head.next.next.next = new ListNode(40);
        llist.head.next.next.next.next = new ListNode(50);
        llist.head.next.next.next.next.next = new ListNode(60);
        llist.rotateLinkedList(llist.head, 4);
    }


}
