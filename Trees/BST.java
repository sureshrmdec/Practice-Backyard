public class BST {
    ListNode root;

    public BST() {
        this.root = null;
    }

    public void insert(int key) {
        insert(root,key);
    }
    public ListNode insert(ListNode node, int key) {
        if(node == null) {
            root = new ListNode(key);
            return root;
        }

        if(key < node.key) {
            root.left = insert(root.left, key);
        }else if(key > node.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }
    public void inOrderTraversal(ListNode node) {
        if(node == null) return;
            inOrderTraversal(node.left);
            System.out.print(" "+ node.key);
            inOrderTraversal(node.right);

    }
    public static class ListNode {
        int key;
        ListNode left,right;
        public ListNode(int data) {
            this.key = data;
            this.left = this.right = null;
        }

        public void insert(int data) {

        }
    }
    public static void main(String[] args) {
        BST objBST = new BST();
        objBST.insert(50);
        objBST.insert(30);
        objBST.insert(20);
        objBST.insert(40);
        objBST.insert(70);
        objBST.insert(60);
        objBST.insert(80);
        objBST.inOrderTraversal();

    }
}
