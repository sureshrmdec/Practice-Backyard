import java.lang.Math;

public class BST {
    Node root;

    public BST() {
        this.root = null;
    }

    public void insert(int key) {
        insert(root,key);
    }
    public Node insert(Node root, int key) {
        if(root == null) {
            root = new Node(key);
            return root;
        }

        if(key < root.key) {
            root.left = insert(root.left, key);
        }else if(key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }
    public void inOrderTraversal(Node root) {
        System.out.println("Starting of inOrderTraversal");
        if(root != null) {
            inOrderTraversal(root.left);
            System.out.print(" " + root.key);
            inOrderTraversal(root.right);
        }
    }
    public static class Node {
        int key;
        Node left,right;
        public Node(int data) {
            this.key = data;
            this.left = this.right = null;
        }

        public void insert(int data) {

        }
    }

    /**
    *Problem : Search a BST
    * Approach : use recurrsion to search key in both the subtree
    *time Complexity :O(log n) ,Space Complexity O(log n)
    */
    public Node searchWrapper(int key) {
        return search(root,key);
    }

    public Node search(Node node, int key) {
        if(node == null || node.key == key) return node;
        if(node.key < key) {
            return search(node.right, key);
        }

        return search(node.left, key);
    }

    /**
    *Problem : Find the minimum value of BST
    *Observation : use the property of BST that left subtree will be less than
    *  root and  root less than right subtree
    *Pseudo Code:
    *   MinBST :
    *       if root is null return
    *       min(root,leftSubtree, rightSubtree)
    *TimeComplexity : O (n) when tree is skewed
    */
    public int findMinElement(Node node) {
        if(node.left == null) return node.key;
        if(node.right == null) return node.key;
        return Math.min(Math.min(findMinElement(node.left), findMinElement(node.right)), node.key);
    }

    /**
    Problem : Validate a BST
    Link: https://leetcode.com/problems/validate-binary-search-tree/
    */
    public boolean isValidBST(TreeNode root) {
       return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

   public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
       if (root == null) return true;
       if (root.val >= maxVal || root.val <= minVal) return false;
       return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
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
        //objBST.findMinElement(objBST.root);
    }

    private class Node<T> {
        private T data;
        private Node<T> left, right;

        public Node(T data, Node<T> l, Node<T> r) {
            left = l; right = r;
            this.data = data;
        }
    }
}
