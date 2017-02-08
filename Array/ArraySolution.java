import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class ArraySolution {

    private static final int MAX = 10000;

    public int pairSumX(int[] input, int match) {
        Arrays.sort(input);
        int leftIndex = 0;;
        int rightIndex = input.length-1;
        while(leftIndex < rightIndex) {
            if(input[leftIndex] + input[rightIndex] == match) {
                return 1;
            } else if (input[leftIndex] + input[rightIndex] > match) {
                rightIndex --;
            } else {
                leftIndex++;
            }
        }
        return 0;
    }

    public void pairSumx2(int[] input, int match) {


        boolean[] binMap  = new boolean[MAX];

        for (int i = 0; i < input.length; i++) {
            int temp = match - input[i];
            if (temp > 0 && binMap[temp]) {
                System.out.println("Pair with given sum is "+ input[i] +", "+ temp);
            }
            binMap[input[i]] = true;
        }
    }

    public int majorityElement1(int[] input) {
        Arrays.sort(input);
        if(input.length ==1) return input[0];
        return input[input.length/2];
    }

    public int oddNumberOccurence1(int[] input) {
        HashMap<Integer, Integer> occurenceCount = new HashMap<Integer, Integer>();
        for(int value : input) {
            if(occurenceCount.containsKey(value)) {
                occurenceCount.put(value, occurenceCount.get(value)+1);
            }else {
                occurenceCount.put(value, 1);
            }
        }
        Set<Integer> key = occurenceCount.keySet();
        for (Integer k : key) {
            if (occurenceCount.get(k)%2 !=0) {
                return k;
            }
        }
        return -1;
    }

    public int oddNumberOccurence2(int[] input) {
        Arrays.sort(input);
        int size = input.length;
        int count =1;
        int previous = input[0];
        for(int i = 1; i < size; i++) {
            if (input[i] == previous) {
                count++;
                previous = input[i];
            } else {
                if (count%2 ==1) {
                    return previous;
                }else {
                    count =1;
                }
            }
        }
        return -1;
    }

    /*
    Time complexity : O(n) but sum can be overflow so can substract from fixed
    number and perform the operation on that list
    */
    public int missingElement1(int[] input) {
        int size = input.length;
        int currentArraySum =0;
        for (int i = 0; i < size; i++) {
            currentArraySum = currentArraySum + input[i];
        }
        int neededSum = ((size+1)*(size+2))/2;
        return neededSum - currentArraySum;
    }

    public int missingElement2(int[] input) {
        int size = input.length;
        int x1 =input[0];
        int x2 = 1;
        for (int i =0; i < size; i++) {
            x1 = x1^input[i];
        }

        for (int i =0; i < size +1; i++) {
            x2 = x2^i;
        }
        return (x1^x2);
    }

    public int searchRotatedArray(int[] input, int match) {
        int pivotIndex = -1;
        int size = input.length;
        for(int i =0; i < size-1; i++) {
            if(input[i+1] < input[i]) {
                pivotIndex = i;
            }
        }
        int result = -1;
        int[] firstArray = Arrays.copyOfRange(input,0,pivotIndex+1);
        int[] secondArray = Arrays.copyOfRange(input,pivotIndex+1,size);

        if(match >= firstArray[0] && match <= firstArray[firstArray.length-1]) {
            result =  Arrays.binarySearch(firstArray, match);
        }
        result =  Arrays.binarySearch(secondArray, match);
        return (result + pivotIndex + 1);
    }
    public static void main(String[] args) {
        ArraySolution objArraySol = new ArraySolution();
        int[] input = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int[] input2 = {30, 40, 50, 10, 20};
        System.out.println(objArraySol.searchRotatedArray(input2, 10));



    }
}
