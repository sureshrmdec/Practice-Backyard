import java.util.ArrayList;
import java.util.List;

public class Searching {
    public int linearSearch(ArrayList<Integer> input, int match) {
        if(input.size() == 0) return -1;
        for(int i =0; i<input.size(); i++) {
            if(input.get(i) == match) return i;
        }
        return -1;
    }
    public int binarySearch(ArrayList<Integer> input, int match) {
        int low =0, high = input.size()-1;
        return binarySearch(input,match,low,high);
    }
    public int binarySearch(ArrayList<Integer> input, int match, int low, int high) {
        if (high >= low) {
            int mid = low +(high-low)/2;
            if (input.get(mid) == match) return mid;
            if (input.get(mid) > match) {
                binarySearch(input,match,low,mid-1);
            } else {
                binarySearch(input,match,mid+1,high);
            }
        }
        return -1;
    }

    public void printArraylist(ArrayList<Integer> input) {
        for(int i =0; i<input.size(); i++) {
            System.out.print(input.get(i) + " ");
        }
    }
    public static void main(String[] args) {
        Searching objSearch = new Searching();
        ArrayList<Integer> input = new ArrayList<>();
        input.add(10);
        input.add(20);
        input.add(30);
        input.add(40);
        objSearch.printArraylist(input);
        System.out.println("The value is found at index " +objSearch.binarySearch(input,30));
    }
}
