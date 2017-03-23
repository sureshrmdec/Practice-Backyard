import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
Common Tricks working with Tree Problems:
    1. Remember tree are recursive data structure
    2. Every node is a root in it's own capactiy with child nodes as part of that Tree
    3. Solve for base case and upto one level and see how it generalizes
    4. DFS(inorder travesal) start building from leftmost leaf nodes
    5. visualization is improtant in tree Problems
    6. Level order travesal is very powerfull, if current size of queue , you can
       you have all elements of that level so can modify according to Situation
    7. Solve example and see how you can generalize that.
    8. for recurrsion ,create a utility function and call that with update result data
       structure for each call
*/
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

    /**
    Problem : Check if a node exit in BST
    *Link :
    *Time Complexity : O(Logn) in average case
    */
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

    /**
    *Problem :find the diameter of given tree
    *Link :
    *Observation :  The key observation is left and right node to root is subtree
    and they will generate maximum diamener on calling function to left and right node
    */
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

    /**
    *Problem :find the height of the tree
    *Link :
    *Observation :  The key observation is left and right node to root is subtree
    and they will generate maximum height on calling function to left and right node
    and just add level root to find total height;
    */
    public int height(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return (1 + max(leftHeight, rightHeight));
    }

    /**
    *Problem :Count number of nodes in  Tree
    *Link :
    *Observation :  The key observation is left and right node to root is subtree
    and they will generate node count  on calling function to left and right node
    */
    public int size(TreeNode root) {
        if(root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }

    /**
    *Problem : To check if two tree are identicalTree
    *Link :
    *Observation : To have two tree identifical their root should be same and
    considering both left and  right part if tree check if they are same.
    */
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
    *Observation : The idea is to think the problem as around center both the
    trees are mirror images so now the problem is to check if two tree are mirror
    which can be solved recuurisvley checking root value are root on (left, right)
    and (right, left) pair
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
    *Observation : Understand the constraint : Path with sum at leaf node so left and
    right child will be null and root node at leaf level will hold the value
    */
    public boolean hasPathWithSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) return true;
        return (hasPathWithSum(root.left, sum -root.val)|| hasPathWithSum(root.right, sum - root.val));
    }

    /**
    *Problem: Invert a Binary TreeNode
    *Link :https://leetcode.com/problems/invert-binary-tree/
    *Time Complexity : O(n) using recurrision since in worst case all n nodes will be visited
    *Space Complexity : O(h) : height of tree
    */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
    *Problem: Invert a Binary TreeNode
    *Link :https://leetcode.com/problems/invert-binary-tree/
    *Time Complexity : O(n) in worst case queue hold all the elements
    *Space Complexity : O(n) for queue space
    */
    public TreeNode invertTreeBFS(TreeNode root) {
        if(root == null) return null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            TreeNode left, right;
            if(temp.left != null) left = temp.left;
            if(temp.right != null) right = temp.right;
            temp.left = right;
            temp.right = left;
            queue.offer(right);
            queue.offer(left);
        }
        return root;
    }

    /**
    *Problem: level order traversal using DFS
    *Link : https://discuss.leetcode.com/topic/7332/java-solution-using-dfs
    *Time Complexity : O(n) at worst all n nodes will be visited and O(h) stack call space
    */
    public List<List<Integer>> inorderTraversalDFS(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        inorderDFSUtil(result, root, 0);
        return result;
    }

    /**
    *Problem : find frequency sum of all subtrees in tree
    *Link: https://leetcode.com/problems/most-frequent-subtree-sum/
    */
    class findFrequentTreeSum {
    Map<Integer, Integer> sumToCount;
    int maxCount;

    public int[] findFrequentTreeSum(TreeNode root) {
        maxCount = 0;
        sumToCount = new HashMap<Integer, Integer>();

        postOrder(root);

        List<Integer> res = new ArrayList<>();
        for (int key : sumToCount.keySet()) {
            if (sumToCount.get(key) == maxCount) {
                res.add(key);
            }
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    private int postOrder(TreeNode root) {
        if (root == null) return 0;

        int left = postOrder(root.left);
        int right = postOrder(root.right);

        int sum = left + right + root.val;
        int count = sumToCount.getOrDefault(sum, 0) + 1;
        sumToCount.put(sum, count);

        maxCount = Math.max(maxCount, count);

        return sum;
    }
}
    public List<List<Integer>> inorderDFSUtil(List<List<Integer>> res, TreeNode root, int level) {
        if(root == null) return;
        if(level >= levelResult) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root);
        inorderDFSUtil(res, root.left, level + 1);
        inorderDFSUtil(res, root.right, level + 1);
    }

    /**
    *Problem : Find sum of all left leafs in the tree
    *Link: https://leetcode.com/problems/sum-of-left-leaves/
    * Time Complexity : O(n) - worst case you will traverse all the nodes; space
    * complexity : O(h) : height of tree
    */

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null ||(root.left == null && root.right ==null)) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if(temp.left != null && temp.left.left == null && temp.left.right == null) sum += temp.left.val;
            if(temp.left != null) queue.offer(temp.left);
            if(temp.right != null) queue.offer(temp.right);
        }
       return sum;
    }
    /**
    *Problem : WAP to do inorder traversal iteratively for an tree
    *Link :
    *Approach : Use stack to go as deep as possible and then explore right
    *candidates if no more left can be traversed
    */

    public List<Integer> inorderIterative (TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        TreeNode currentNode = root;

        while(currentNode != null || !stack.isEmpty()) {
            while(currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            int value = stack.pop().val;
            result.add(value);
            currentNode = currentNode.right;
        }
        return result;
    }


    /**
    *Problem : Find the kth smallest element in BST
    *Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    *Approach :
        1. Do Binary Search and reduce the search space ,if number of elements in left
        subtree is less than k than search for right subtree ,if not search in left search space
        2.Do recursive inroder traversal with helper utility , if left != null go left
        and decrement the counter and if at one point we find k = 0 then return result
        but if we reach null then do this for right side tree
    */
    public int kthSmallestBST(TreeNode root, int k) {
        int count = countNodes(root.left);
        if(K <= count){
            return kthSmallestBST(root.left, k);
        } else if (k > count + 1){
            return kthSmallestBST(root.right, k - 1 - count);
        }
        return root.val;
    }

    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
    *Problem : Most Frequent SubStree Sum
    *Link: https://leetcode.com/problems/most-frequent-subtree-sum/
    *Constraint:
        1.Can tree be empty
        2. What if all the subtree sum is unique,return all of them
    *Idea:
        1.Go recurresively and store at each tree node what sum we have and also
        maintain what the maxSum we got and update any changes to maxCount; this will
        help us reterive items from Map - get item with values as maxCount
        TimeComplexity :O(n) : worst case you will traverse all the tree node ; O(n) - map
    Test:
        5
        / \    => [2]
        2  -5
    */
    public List<Integer> frequentSumTree(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        postOrder(root, map, maxCount);
        List<Integer> result = new ArrayList<Integer>();
        for(Integer e : map.keySet()){
            if(map.get(e) == maxCount) result.add(e);
        }
        return result;
    }

    public int postOrder(TreeNode root, HashMap<Integer, Integer> map, int maxCount) {
        if(root == null) return 0;

        int left = postOrder(root.left, map, maxCount);
        int right = postOrder(root.right, map, maxCount);
        int sum = left + right + root.val;
        int mapValue = map.getOrDefault(sum, 0) + 1;
        maxCount = Math.max(mapValue, maxCount);
        return sum;
    }

    /**
    *Problem : Print the right side view of Tree
    *Link: https://leetcode.com/problems/binary-tree-right-side-view/
    *Constraint:
        1. Can tree be empty
    *Idea:
        1. Do level order traversal from right side and print the first node value in that
        process, if right node exit it will be printed or if right node is not there left node
        will be printed.
        TimeComplexity :O(n) : queue will process all tree node : space complexity :O(n)
    */
    public List<Integer> rightSideViewTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) return result;
        queue.offer(root);
        while(!queue.isEmpty()) {
            for(int i = 0; i < queue.size(); i++) {
                TreeNode temp = queue.poll();
                if(i == 0) result.add(temp.val);
                if(temp.right != null) queue.add(temp.right);
                if(temp.left != null) queue.add(temp.left);
            }
        }
        return result;
    }

    /**
    *Problem : Print the right side view of Tree - recurresively
    *Link: https://leetcode.com/problems/binary-tree-right-side-view/
    *Constraint:
        1. Tree can be empty
    *Idea:
        1.Backtracking : write a recurrsive function that first traverse the right hand
        side, and at any stage if current depth equals to result list size then add that
        to result set.
        Time Complexity :O(n) : spaceComplexity : O(n)
    */
    public List<Integer> rightSideViewReccursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        rightSideUtility(root, result, 0);
        return result;
    }

    public void rightSideUtility(TreeNode root, List<Integer> result, int depth) {
        if(root == null) return;
        if(depth == result.size()) result.add(root.val);
        rightSideUtility(root.right, result, depth + 1);
        rightSideUtility(root.left, result, depth + 1);
    }

    /**
    *Problem: Find bottom left tree Value
    *Link: https://leetcode.com/problems/find-bottom-left-tree-value/
    *Constraint:
        1. Can tree be null - return 0
    *Idea:
        1.Do a level order traversal right to left and then last element to get out
        from the queue will be the leftMost elemenet at maximum depth
        *Time Complexity : O(n) since all tree nodes will be traversed and o(n) queue space
    */
    public int BottomLeftValue(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offfer(root);

        while(!queue.isEmpty()) {
            root = queue.poll();
            if(root.right != null) queue.offer(root.right);
            if(root.left != null) queue.offer(root.left);
        }
        return root.val;
    }

    /**
    *Problem: Find bottom left tree Value
    *Link: https://leetcode.com/problems/find-bottom-left-tree-value/
    *Constraint:
        1. Can tree be null - return 0
    *Idea:
        1.Do a level order traversal right to left and then last element to get out
        from the queue will be the leftMost elemenet at maximum depth
        *Time Complexity : O(n) since all tree nodes will be traversed and o(n) queue space
    */
    public int BottomLeftValue(TreeNode root) {
        if(root == null) return 0;
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if(i == 0) result = temp.val;
                if(temp.left != null) queue.offer(temp.left);
                if(temp.right != null) queue.offer(temp.right);
            }
        }
        return result;
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
