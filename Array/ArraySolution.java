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

    /*
    *Problem : Reverse an array String
    *observation : Use two pointers to keep track of head and tail index
    *Pseudo Code:
    *   if string length is < 2 return string
    *   Run a loop through half size of array and swap the head and tail element
    *Time Complexity : O(n/2)
    */
    public void reverseArray(int[] input, int start, int end) {
        int temp;

        if(start >= end)  return;
            temp = input[start];
            input[start] = input[end];
            input[end] = temp;
            reverseArray(input, start+1, end-1);

    }

    public static void printArray(int[] input) {
        int size = input.length;
        for(int i = 0; i < size; i++) {
            System.out.print(input[i] + " ");
        }
    }

    /*
    *Problem :http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
    *Observation : Look for ranges such as it grow and found new small value then
    *found new maxDifference in that range.
    *Time Compleoxity : O(n); Space Complexity : O(1)
    */
    public int maxDifference(int[] input) {
        int minValue = input[0];
        int maxDifference = input[1] - input[0];

        for(int i = 1; i < input.length; i++) {
            if((input[i] - minValue) > maxDifference) {
                maxDifference = (input[i] - minValue);
            }
            if(input[i] < minValue) {
                minValue = input[i];
            }
        }
        return maxDifference;
    }

    /*
    *Problem :http://codereview.stackexchange.com/questions/65335/basic
        -string-compression-counting-repeated-characters
    */
    public String stringCompression(String input) {
        if (input.isEmpty()) return "";
        char[] charArray = input.toCharArray();
        int size = charArray.length;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char previous = charArray[0];
        for(int i = 1; i < size; i++) {
            if(charArray[i] == previous) {
                count++;
            }else {
                sb.append(previous).append(count);
                count = 1;
            }
            previous = charArray[i];

        }
        return sb.append(previous).append(count).toString();
    }

    public int findMaximumIncreasingDecreasingArray(int[] input, int low, int high) {
        if(low == high) return input[low];
        if(high == low + 1) return input[high] > input[low] ? input[high] : input[low];

        int mid = low + (high - low)/2;
        if(input[mid] > input[mid + 1] && input[mid] > input[mid -1]) return input[mid];
        if(input[mid] > input[mid + 1] && input[mid] < input[mid -1]) {
            return findMaximumIncreasingDecreasingArray(input, low, mid -1);
        } else {
            return findMaximumIncreasingDecreasingArray(input, mid +1, high);
        }
    }

    /**
    Problem : Check if the array contains duplciate element or not
    Link: https://leetcode.com/problems/contains-duplicate/?tab=Description
    Time Complexity : O (n); space Complexity : O(n)
    */
    public boolean containsDuplicate(int[] input) {
        int size = input.length;
       if(size  == 0) return false;
       Set<Integer> uniqueSet = new HashSet<>();
       for(int i = 0; i < size; i++) {
           if(uniqueSet.contains(input[i])) return true;
           uniqueSet.add(input[i]);
       }
       return false;
   }

   /**
   *Problem : find the longest common prefix in the string array
   * Link :https://leetcode.com/articles/longest-common-prefix/
   *Time Complexity : O(S) where S is sum of all character count in the string array
   */
   public String longestCommonPrefix(String[] input) {
       int size = input.length;
       if(size == 0) return "";

       String prefix = input[0];
       for(int i = 0; i < size; i++) {
           while(input[i].indexOf(prefix) != 0) {
               prefix = prefix.substring(0, prefix.length() -1);
               if(prefix == "") return "";
           }
        }
   return prefix;
   }

   public String longestCommonPrefixVerticalScan(String[] input) {
       int size = input.length;
       if(size == 0) return "";

       for(int i = 0; i < input[0].length(); i++) {
           char c = input[0].charAt(i);
           for(int j = 1; j < size; i++) {
               if(i == input[j].length() || input[j].charAt(i) != c) return input[0].substring(0, i);
           }
       }
       return input[0];
   }

   /**
   Problem : Move Zeros
   Link : https://leetcode.com/problems/move-zeroes/
   Time Complexity : O(n) and space complexity : O(1)
   */
   public void moveZeroes(int[] input) {
       if(input.length == 0) return;

       int counter = 0;
       int size = input.length;
       for(int i = 0; i < size; i++) {
           if(input[i] != 0) {
               input[counter] = input[i];
               counter++;
           }
       }
       while(counter < size) {
           input[counter] = 0;
           counter++;
       }
   }
    public static void main(String[] args) {
        ArraySolution objArraySol = new ArraySolution();
        int[] input = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
        //System.out.println(objArraySol.stringCompression("aaabbbccc"));
        System.out.println(objArraySol.findMaximumIncreasingDecreasingArray(input, 0, input.length - 1));


    }
}
