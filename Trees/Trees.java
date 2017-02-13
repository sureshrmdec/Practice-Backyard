import java.util.LinkedList;
import java.util.Queue;

public class Trees {
    treeNode root;

    public Trees(int key) {
        root = new treeNode(key);
    }

    public Trees() {
        root = null;
    }

    public static class treeNode {
        int data;
        treeNode left, right;

        public treeNode(int data) {
            this.data = data;
            this.left =  this.right = null;
        }
    }

    public void inOrderR(treeNode node) {
        if (node == null) return;
            inOrderR(node.left);
            System.out.print(node.data+ " ");
            inOrderR(node.right);
    }

    public void preOrderR(treeNode node) {
        if(node == null) return;
        System.out.print(node.data + " ");
        preOrderR(node.left);
        preOrderR(node.right);
    }

    public void postOrderR(treeNode node) {
        if(node == null) return;
        postOrderR(node.left);
        postOrderR(node.right);
        System.out.print(node.data + " ");
    }

    private void printLevelOrderTraversal(treeNode node) {
        Queue<treeNode> queue = new LinkedList<treeNode>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            treeNode tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
            if(tempNode.left != null) queue.offer(tempNode.left);
            if(tempNode.right != null) queue.offer(tempNode.right);
        }
    }
    public void inOrder() {
        inOrderR(root);
    }

    public void preOrder() {
        preOrderR(root);
    }

    public void postOrder() {
        postOrderR(root);
    }

    public void printLevelOrderTraversal() {
        printLevelOrderTraversal(root);
    }

    public treeNode searchKey(treeNode node, int key) {
        if(node == null  || node.data == key) {
            return root;
        }
        if(node.data > key) {
            searchKey(root.left, key);
        }
        if(node.data < key ) {
            return searchKey(node.right,key);
        }
        return null;
    }

    public void diameter() {
        System.out.println(diameter(root));
    }
    public int diameter(treeNode root) {
        if (root == null) return 0;

        //get height of left and right subtree
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return max(leftHeight + rightHeight + 1, max(diameter(root.left), diameter(root.right)));
    }

    public int max(int a, int b) {
        return a>= b ? a : b;
    }
    public int height(treeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return (1 + max(leftHeight, rightHeight));
    }

    public int size(treeNode root) {
        if(root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }

    public static void main(String[] args) {
        Trees tree = new Trees();
        tree.root = new treeNode(5);
        tree.root.left = new treeNode(2);
        tree.root.right = new treeNode(8);
        tree.root.left.left = new treeNode(1);
        tree.root.left.right = new treeNode(3);
        tree.root.right.left = new treeNode(6);
        tree.root.right.right = new treeNode(7);
        System.out.println(tree.height(tree.root));
    }


}
