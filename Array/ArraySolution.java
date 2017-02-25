import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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

   /**
   *problem : Check if a string is valid palindrome
   Link : https://leetcode.com/problems/valid-palindrome/
   *Time Complexity :
   */
   public boolean isPalindrome(String s) {

        if(s.length() == 0) return true;
        char[] charArray = s.toLowerCase().toCharArray();
        int low = 0;
        int high = charArray.length - 1;

        while (low <= high) {
            if(!Character.isLetterOrDigit(charArray[low])) {
                low++;
            }else if(!Character.isLetterOrDigit(charArray[high])) {
                high--;
            }
            else {
                if (charArray[low] != charArray[high]) return false;
                low++;
                high--;
            }
        }
        return true;
    }

    /**
    Problem : Minimum number of gates required so no planes keeps standing
    *Problem Link:https://www.careercup.com/question?id=5679778427305984
    *Time Complexity : O(n) as the inner loop is bounded by 1440 minuts of day
    *Space Complexity : O(n) - due to use of hashMap
    */
    public int maxGates(int[] arrivalTime, int[] deptTime) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxValue = 0;
        for(int i = 0; i < arrivalTime.length; i++) {
            int a = arrivalTime[i];
            int d = deptTime[i];

            // Store each second how many plane needs gate
            for(int k = a; i < d; k++) {
                int count = map.containsKey(k) ? map.get(k) : 0;
                map.put(k, count + 1);
            }
        }

        //Calculate the maxValue;
        for(Integer n : map.values()) {
            if(n > maxValue) maxValue = n;
        }
        return maxValue;
    }

    /**
    *Problem : Given a string sort it's letter by frequency
    *Observation : We can keep an array of character and keep increasing it count
    */
    // public String frequencyArrangement(String input) {
    //     char[] charArray = input.toCharArray();
    //     Map<character, Integer> map = new HashMap<>();
    //
    //     for(int i = 0; i < charArray.size; i++) {
    //         char temp = charArray[i];
    //         if(!Character.isLetter(temp)) continue;
    //         if(map.containsKey(temp)) map.put(temp, map.get(temp) + 1);
    //         map.put(temp, 1);
    //     }
    //
    //     List<Entry<Character, Integer>> charCount =
    //             new ArrayList<Entry<Character, Integer>>(map.entrySet());
    //     Collections.sort(charCount, new frequencyComparator(Entry<Character, Integer>));
    // }
    //
    // class frequencyComparator implements Comparator {
    //     public int Compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
    //         Float diff = (o2.getValue() - o1.getValue());
    //         return diff.intValue();
    //     }
    // }

    /**
    *Problem : find the first unique element in array
    *Observation : create a map containg the count of each element in array and
    * scan again to see if any of the element count is 1.
    *Time complexity : O(n) and space complexity : O(n)
    */

    public int firstUniqueElement(int[] input) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if(input.length == 0) return -1;
        for(int i = 0; i < input.length; i++) {
            if(map.containsKey(input[i])) map.put(input[i], map.get(input[i]) + 1);
            else map.put(input[i], 1);
        }

        for(int i = 0; i < input.length; i++) {
            if (map.get(input[i]) == 1) return input[i];
        }
        return -1;
    }

    /**
    *Problem :reverse an Integer
    *Link: https://leetcode.com/problems/reverse-integer/
    Time Complexity : O(n); space Complexity : O(1)
    */
    public int reverseInteger(int n) {
        if(n == 0) return 0;
        boolean flag = false;
        int result = 0;
        if(n < 0) {
            n = 0 - n;
            flag = true;
        }

        while(n > 0) {
            int digit = n % 10;
            result = result * 10 + digit;
            n = n / 10;
        }
        if(flag) {
            result =  0 - result;
        }
        if( result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        return result;
    }

    /**
    *Problem : find the min in array that decreases and then increases
    *Link :https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    *Time Complexity : O(nlogn) : Classical Binary Search
    */
    public int findMinRotatedArray(int[] input) {
        int size = input.length;
        int low = 0;
        int high = size - 1;

        if(size == 0) return -1;
        if(input[low] < input[high]) return input[0];
        while(low < high) {
            if (input[low]< input[high]) {
                return input[low];
            }
            int mid = low + (high - low)/2;
            if(input[low] <= input[mid]) low = mid + 1;
            else high = mid;
        }
        return input[low];
    }

    /**
    *Problem: Replace a string in place
    *Link :
    */
    public String reverseStringInPlace(String input) {
        int size = input.length();
        if(size < 2) return input;
        int low = 0;
        int high = size -1;
        StringBuilder sb = new StringBuilder(input);
        while(low < high) {
            char temp = sb.charAt(high);
            sb.setCharAt(high, sb.charAt(low));
            sb.setCharAt(low, temp);
            low++;
            high--;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        ArraySolution objArraySol = new ArraySolution();
        int[] input = {4,5,6,7,1,2,3};
        System.out.println(objArraySol.reverseStringInPlace("VIPIN"));
    }
}
