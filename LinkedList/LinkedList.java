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

    public void printList() {
        ListNode temp = head;
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

    public void reverseList() {
        ListNode previousNode = null, nextNode = null, currentNode = head;
        if (currentNode == null || currentNode.next == null) return;
        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        head = previousNode;
    }

    public ListNode mergeSortedList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }

        if(head2 == null) {
            return head1;
        }

        Node currentNode = null;
        if(head1.data < head2.data) {
            current = head1;
            current.next = mergeSortedList(head1.next,head2);
        }else {
            current = head2;
            current.next = mergeSortedList(head1, head2.next);
        }
        return current;
    }

    public static void main(String[] args) {

        LinkedList llist = new LinkedList();

        llist.push(5);
        llist.push(10);
        llist.push(15);
        llist.push(20);
        llist.printList();
        System.out.println(" ");
        llist.reverseList();
        llist.printList();
    }


}
