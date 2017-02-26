public class LRUCache {
    private Map<Integer, Node> cache = new HashMap<>();
    private Node head = null, tail = null;
    public int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;

    }

    public int get(int key) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            remove(node);
            setHead(node);
        } else {
            Node newNode = new Node(key,value);
            if(cache.size() >= capacity) {
                cache.remove(tail.key);
                remove(tail);
            }
            cache.put(key, newNode);
            setHead(newNode);
        }
    }

    public void remove(Node node) {
        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }else {
            tail = node.prev;
        }
    }

    public void setHead(Node node) {
        node.next = head;
        if(head != null) {
            head.prev = node;
        }
        head = node;
        node.prev = null;
        if(tail == null) {
            tail = head;
        }
    }
    public static void main(String[] args) {

    }

    private static class Node {
        private int key;
        private Node prev;
        private Node next;
        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
