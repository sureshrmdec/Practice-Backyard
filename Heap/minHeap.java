/*
This program define operation with BinaryHeap
such as getMin(),extraxtMin(),decreaseKey(),insert(),delete()
*/
public class minHeap {
    /*
    public methods:
    void insert(x) -> Insert X
    Comparable deleteMin() -> return and remove the smallest element
    Comparable findMin() -> return the smallest element
    boolean isEmpty() -> return true if empty;else false
    void makeEmpty() -> remove all items

    Errors:
    Throw overflow if cacpacity excedeed

    @author Vipin Tiwari
    */
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;
    private Comparable[] array;

    public minHeap() {
        this(DEFAULT_CAPACITY);
    }

    public minHeap(int size) {
        currentSize = 0;
        array = new Comparable[size + 1];
    }

    /**
    *Insert into priority queue,maintaing the heap order
    *@param x the item to insert
    *@exception Overflow if array is full
    */
    public void insert(Comparable x) throws Overflow {
        if(isFull()) {
            throw new Overflow();

            //percolate up
            int hole = ++currentSize;
            for(; hole > 1 && x.CompareTo(array[hole/2]) < 0; hole/=2) {
                array[hole] = array[hole/2];
                array[hole] = x;
            }
        }
    }

    public static void main(String[] args) {
        minHeap objHeap = new minHeap();
    }
}
