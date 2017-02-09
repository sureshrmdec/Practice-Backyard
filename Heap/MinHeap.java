import java.util.Comparator;
/*
This program define operation with BinaryHeap
such as getMin(),extraxtMin(),decreaseKey(),insert(),delete()
*/
@SuppressWarnings("unchecked")
public class MinHeap  {
    /*
    public methods:
    void insert(x) -> Insert X
    int deleteMin() -> return and remove the smallest element
    int findMin() -> return the smallest element
    boolean isEmpty() -> return true if empty;else false
    void makeEmpty() -> remove all items

    Errors:
    Throw overflow if cacpacity excedeed

    @author Vipin Tiwari
    */
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;
    private Object[] data;
    protected Comparator comparator;

    public MinHeap(Comparator c) {
        this(DEFAULT_CAPACITY,c);
    }

    public MinHeap(int size, Comparator c) {
        currentSize = 0;
        comparator = c;
        data = new Object[size + 1];
    }

    /**
    *Insert into priority queue,maintaing the heap order
    *@param x the item to insert
    *@exception Overflow if array is full
    */
    public void insert(int x)  {
        if( !isFull() ) {
            data[currentSize+1] = x;
            percolateUp(currentSize+1);
            ++currentSize;
        }
    }

    public Object findMin() {
        return (isEmpty() ? -1 : data[1]);
    }

    /**
    *Remove the smallest item from the priority queue
    *@return the smallest item or null if empty
    */
    public Object deleteMin() {
        if(isEmpty()) return -1;
        Object minItem = data[1];
        data[1] = data[currentSize];
        percolateDown(1);
        --currentSize;
        return minItem;
    }

    /**
    *Check if priority queue is logically empty
    *@return true if empty;false otherwise
    */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
    *Check if priority queue is logical full
    @return true if currentSize matches array MaxSize; false otherwise
    */
    public boolean isFull() {
        return currentSize == data.length-1;
    }

    /**
    * Make priority queue logically empty
    */
    public void makeEmpty() {
        currentSize = 0;
    }

    /**
    *internal method to percolateUp in the heap
    *@param startNode the index of node at which percolate begins
    */
    private void percolateUp(int startNode) {
        int hole;
        Object temp = data[startNode];
        for(hole = startNode; hole > 1 && comparator.compare(temp, data[hole/2]) <0;
                hole /= 2) {
            data[hole] = data[hole/2];
        }
        data[hole] = temp;
    }

    private void percolateDown(int startNode) {
        int child;
        Object temp = data[startNode];
        int hole;
        for( hole = startNode; hole*2 <= currentSize; hole = child) {
            child = hole*2;
            if(child != currentSize && comparator.compare(data[ child+1 ], data[child]) < 0) {
                child++;
            }
            if(comparator.compare(data[child], temp) < 0) {
                data[hole] = data[child];
            } else {
                break;
            }
        }
        data[hole] = temp;
    }

    public static void main(String[] args) {
        int numItems  = 10000;
        MinHeap h = new MinHeap(numItems, new IntComparator());
        for(int i = 10; i < 20; i++) {
            h.insert(i);
        }
        System.out.println(h.findMin());
    }

}

class IntComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        int priority1 = ((Integer)o1).intValue();
        int priority2 = ((Integer)02).intValue();
        return compare(priority1, priority2);
    }

    public int compare(int val1, int val2) {
        if(val1 < val2) return -1;
        if(val2 > val1) return 1;
        return 0;
    }
}
