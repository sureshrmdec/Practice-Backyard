
/*
*Problem a LRU Cache with O(1) time complexity for get and put operation
* Link : https://leetcode.com/problems/lru-cache/?tab=Description
*Observation : You have to find datastructure that will shift elements to top with
* every access and remove the least used tail element - doubly linked List
* Pseudo Code :
    1. Create a hashMap where key will be the integer index and value be the doubly
    linked List Node
    2. create count and capacity for given LRU Cache
    3. Add new element at top of double linked List
    4. when you set/get any element move that element to top using helper function
    5. when count is above capacity remove the tail entry
*/

public class LRUCache {
    private int count;
    private int capacity;
    private HashSet<Integer, LRUCacheNode> map;

    public LRUCache(int capacity) {
        count = 0;
        this.capacity = capacity;
        map = new HashSet<>(capacity);
    }

    public int get(int key) {

    }

    public void put(int key, int value) {

    }

    /**
    *Always add node at the top of linked List
    */
    public void addNode(LRUCacheNode node) {
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }

    static class LRUCacheNode {
        int key;
        int val;
        LRUCacheNode prev;
        LRUCacheNode next;

        public LRUCacheNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }
}
