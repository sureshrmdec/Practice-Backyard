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

    public void inOrder() {
        inOrderR(root);
    }

    public void preOrder() {
        preOrderR(root);
    }

    public void postOrder() {
        postOrderR(root);
    }

    public treeNode searchKey(treeNode node, int key) {
        if(root == null  || root.data = key) {
            return root;
        }
        if(root.data > key) {
            searchKey(root.left, key);
        }
        if(root.data < key) {
            searchKey(root.right,key);
        }
    }

    public static void main(String[] args) {
        Trees tree = new Trees();
        tree.root = new treeNode(5);
        tree.root.left = new treeNode(2);
        tree.root.right = new treeNode();
        tree.root.left.left = new treeNode(1);
        tree.root.left.right = new treeNode(3);
        tree.root.right.left = new treeNode(6);
        tree.root.right.right = new treeNode(7);
        tree.inOrder();
        System.out.println(" ");
        tree.preOrder();
        System.out.println(" ");
        tree.postOrder();
    }


}
