import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
    /*
    Lowwer bound on comparison based Sorting : nlogn
    Consider problem as decision tree and each root to leaf path as one permutation
    of input array. n! <= 2^x ( numbers of leaf of decision tree height x)
    x- number of comparison needed to sort the array will lower bound to Log n

    Memory writes : Selection sortes have minimum memory writes but if you use
    cycle sort it will further less, Cycle short value will be written one time
    or zero based on the fact that item is already in correct position or written
    one time to it's correct position
    */

public class Sorting {

    public void mergeSort(ArrayList<Integer> input) {
        int size = input.size();
        if(size < 2) return;
        int mid = size/2;
        int leftSize = mid;
        int rightSize = size-mid;
        ArrayList<Integer> leftList = new ArrayList<>(leftSize);
        ArrayList<Integer> righList = new ArrayList<>(rightSize);

        for (int i = 0; i < mid; i++) {
            leftList.add(input.get(i));
        }

        for (int i = mid; i < size; i++) {
            righList.add(input.get(i));
        }
        mergeSort(leftList);
        mergeSort(righList);
        merge(leftList, righList, input);
    }
    public void merge(ArrayList<Integer> left ,ArrayList<Integer> right, ArrayList<Integer> input) {
        int leftSize = left.size();
        int rightSize = right.size();
        int i=0, j =0, k=0;
        while (i < leftSize && j < rightSize) {
            if(left.get(i) <= right.get(i)) {
                input.set(k,left.get(i));
                i++;
                k++;
            } else {
                input.set(k,right.get(j));
                j++;
                k++;
            }
            while (i < leftSize) {
                input.set(k,left.get(i));
                k++;
                i++;
            }

            while (j < rightSize) {
                input.set(k, right.get(j));
                j++;
                k++;
            }
        }
    }
    /*
    time Complexity : O(Log n) ,Worst case depend on how pivot element is selected
    if pivot element is selected as leftmost or rightmost ,worst case occur when
    the array is completely sorted in one way or other.

    USe random element as pivot or median of first ,middile and last element
    */
    public void quickSort(int[] input, int low, int high) {
        if(low < high) {
            /*
            Partition the array assuming last element as pivot element
            */
            int partitionIndex = partition(input, low ,high);
            quickSort(input, low, partitionIndex-1);
            quickSort(input, partitionIndex+1, high);
        }
    }

    private int partition(int[] input, int low , int high) {
        int pi = input[high];
        int belowPiPointer = low -1;
        for (int i =low; i <= high-1; i++) {
            if(input[i] <= pi) {
                belowPiPointer++;
                int temp = input[i];
                input[i] = input[belowPiPointer];
                input[belowPiPointer] = temp;
            }
        }
        int temp = input[belowPiPointer + 1];
        input[belowPiPointer + 1] = input[high];
        input[high] = temp;
        return belowPiPointer+1;
    }

    public void quickSort(int[] input) {
        int low =0;
        int high = input.length-1;
        quickSort(input, low, high);
    }

    public void printIntArray(int[] input) {
        int size = input.length;
        for(int i = 0; i< size; i++) {
            System.out.print(input[i] + " ");
        }
    }

    /**
    *Problem : Sort the array from the pivot -closet come first then farthest
    *Approach : Use java comparator to define comparison to sort the Integers
    @param Input Integer array and pivot element to compare
    @return void -print the  array in sorted order
    */
    public void sortArrayFromPivot(Integer[] input, Integer pivot) {
        PivotComparater objComparater = new PivotComparater(pivot);
        Arrays.sort(input, objComparater);
        for(Integer i : input) {
            System.out.println(i);
        }

    }
    /**
    * Problem : Sort the given array using count sort
    * Approach :Create a temp array containing frequency of each input then define
    * array where each element shows number of values below that index and last
    * print the value from input array to output array using above temp array
    */
    public void counterSort(int[] input) {
        int size = input.length;
        int[] countArray = new int[256];
        for (int i = 0; i < size; i++) {
            countArray[input[i]]++;
        }
        int countArraySize = countArray.length;
        for (int i = 1; i < countArraySize; i++) {
            countArray[i] = countArray[i] + countArray[i-1];
        }
        int[] output = new int[size];
        for(int i = 0; i < size; i++) {
            int indexCount = countArray[input[i]];
            if(indexCount >= 0) {
                output[indexCount-1] = input[i];
                countArray[input[i]]--;
            }
        }
        for (int i = 0; i < size; i++) {
            input[i] = output[i];
        }
    }

    public static void main(String[] args){
        Sorting objSort = new Sorting();
        int[] input =  {20, 10, 15, 3, 18, 4, 25};
        objSort.counterSort(input);
        objSort.printIntArray(input);


    }
}

    class PivotComparater implements Comparator<Integer> {
        private int pivot;

        public PivotComparater(int d) {
            super();
            this.pivot = d;
        }

        @Override
        public int compare(Integer a, Integer b) {
            return (Math.abs(a - pivot) - Math.abs(b - pivot));
        }
    }
