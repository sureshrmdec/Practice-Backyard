import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Trees {
    TreeNode root;

    public Trees(int key) {
        root = new TreeNode(key);
    }

    public Trees() {
        root = null;
    }

    public static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left =  this.right = null;
        }
    }

    public void inOrderR(TreeNode node) {
        if (node == null) return;
            inOrderR(node.left);
            System.out.print(node.val+ " ");
            inOrderR(node.right);
    }

    public void preOrderR(TreeNode node) {
        if(node == null) return;
        System.out.print(node.val + " ");
        preOrderR(node.left);
        preOrderR(node.right);
    }

    public void postOrderR(TreeNode node) {
        if(node == null) return;
        postOrderR(node.left);
        postOrderR(node.right);
        System.out.print(node.val + " ");
    }

    private void printLevelOrderTraversal(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.val + " ");
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

    public TreeNode searchKey(TreeNode node, int key) {
        if(node == null  || node.val == key) {
            return root;
        }
        if(node.val > key) {
            searchKey(root.left, key);
        }
        if(node.val < key ) {
            return searchKey(node.right,key);
        }
        return null;
    }

    public void diameter() {
        System.out.println(diameter(root));
    }
    public int diameter(TreeNode root) {
        if (root == null) return 0;

        //get height of left and right subtree
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return max(leftHeight + rightHeight + 1, max(diameter(root.left), diameter(root.right)));
    }

    public int max(int a, int b) {
        return a>= b ? a : b;
    }
    public int height(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return (1 + max(leftHeight, rightHeight));
    }

    public int size(TreeNode root) {
        if(root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }

    public boolean identicalTree(TreeNode t1, TreeNode t2) {

        //if both are empty then return
        if(t1 == null && t2 == null) return true;
        if(t1 != null && t2 != null) {
            return (t1.val == t2.val && identicalTree(t1.left, t2.left) && identicalTree(t2.right, t2.right));
        }
        //if one is empty and other is not
        return false;
    }
    /**
    *Problem : Check if a tree is symmetric about it's center
    *Link :https://leetcode.com/problems/symmetric-tree/?tab=Description
    */
    public boolean isSymmetric(TreeNode root) {
      if (root == null) return true;
      return symmetricNode(root.left, root.right);
    }

    public boolean symmetricNode(TreeNode left, TreeNode right) {
      if(left == null || right == null) return left == right;
      if(left.val != right.val) return false;
      return (symmetricNode(left.left, right.right) && symmetricNode(left.right, right.left));
    }

    /**
    *Problem : Convert a inorder and postorder traversal to a binary tree
    *Link :https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    *Time Complexity : O(n)
    */
    public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
        if(inorder == null || inorder.length == 0 || postorder == null ||
                postorder.length == 0 || inorder.length != postorder.length) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreePostIn(inorder, 0, inorder.length - 1, postorder, 0, postorder.length -1, map);
    }

    public TreeNode buildTreePostIn(int[] inorder, int iLow, int iHigh, int[] postorder, int pLow, int pHigh,
            HashMap<Integer, Integer> map) {
        if(iLow > iHigh || pLow > pHigh) return null;

        TreeNode root = new TreeNode(postorder[pHigh]);
        int rootIndexInorder = map.get(postorder[pHigh]);
        int offset = rootIndexInorder - iLow;
        root.left = buildTreePostIn(inorder, iLow, iLow + offset -1, postorder, pLow, pLow + offset -1, map);
        root.right = buildTreePostIn(inorder, iLow + offset + 1, iHigh, postorder, pLow + offset + 1, pHigh, map);
        return root;
    }

    /**
    *Problem : Convert a inorder and preOrder traversal to a binary tree
    *Link :https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    *Time Complexity : O(n)
    */
    public TreeNode buildTreePreIn(int[] inorder, int[] preorder) {
        if(inorder == null || inorder.length == 0 || preorder == null ||
                preorder.length == 0 || inorder.length != preorder.length) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreePreIn(inorder, 0, inorder.length - 1, preorder, 0, preorder.length -1, map);
    }

    public TreeNode buildTreePreIn(int[] inorder, int iLow, int iHigh, int[] preorder, int pLow, int pHigh,
            HashMap<Integer, Integer> map) {
        if(iLow > iHigh || pLow > pHigh) return null;

        TreeNode root = new TreeNode(preorder[pLow]);
        int rootIndexInorder = map.get(preorder[pLow]);
        int offset = rootIndexInorder - iLow;
        root.left = buildTreePreIn(inorder, iLow, iLow + offset -1, preorder, pLow + 1, pLow + offset, map);
        root.right = buildTreePreIn(inorder, iLow + offset + 1, iHigh, preorder, pLow + offset + 1, pHigh, map);
        return root;
    }

    /**
    *Problem : Find if a root to leaf path exit with given sum
    *Link: https://leetcode.com/problems/path-sum/?tab=Description
    */
    public boolean hasPathWithSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) return true;
        return (hasPathWithSum(root.left, sum -root.val)|| hasPathWithSum(root.right, sum - root.val));
    }

    public static void main(String[] args) {
        Trees tree = new Trees();
        tree.root = new TreeNode(5);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(8);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(3);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
        System.out.println(tree.height(tree.root));
    }


}
