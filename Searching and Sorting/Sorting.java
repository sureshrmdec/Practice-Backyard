import java.util.ArrayList;

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

    public static void main(String[] args){
        Sorting objSort = new Sorting();
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(20);
        input.add(10);
        input.add(50);
        input.add(35);
        objSort.mergeSort(input);
        Searching objSearch = new Searching();
        objSearch.printArraylist(input);
    }
}
