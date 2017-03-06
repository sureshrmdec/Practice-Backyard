import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
Common Tricks to solve Array Problems:
    1. Solve using example for small test cases and see if you can generalize
    2. Sort the array use two pointer
*/
public class ArraySolution {

    private static final int MAX = 10000;

    /**
    *Problem : Two sum problem
    *Link :https://leetcode.com/problems/two-sum/
    *Time Complexity : O(nlogn) for sorting
    */
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

    /**
    *Problem : Two sum problem
    *Link :https://leetcode.com/problems/two-sum/
    *Time Complexity : O(n) ; space Complexity : O(n)
    */
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

    /**
    *Problem : Two sum problem -HashMap implementation
    *Link :https://leetcode.com/problems/two-sum/
    *Time Complexity : O(n) ; space Complexity : O(n)
    */
    public int[] twoSum(int[] input, int match) {
       Map<Integer, Integer> map = new HashMap<>();
       int[] result = new int[2];

       for(int i = 0; i < input.length; i++) {
           if(map.containsKey(input[i])) {
               int index = map.get(input[i]);
               result[0] = index;
               result[1] = i;
               break;
           }else {
               map.put(match - input[i], i);
           }
       }
       return result;
   }

   /**
   *Problem : Find the majority element which occurs more than (n/2) times
   *Link: https://leetcode.com/problems/majority-element/
   *Time Complexity : O(nlogn) ; space complexity : O(1)
   */
    public int majorityElement1(int[] input) {
        Arrays.sort(input);
        if(input.length ==1) return input[0];
        return input[input.length/2];
    }

    /**
    *Problem : Find number in array which occured odd number of times
    *Time Complexity : O(n) ; space complexity :O(n) for HashMap
    */
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

    /**
    *Problem : Find number in array which occured odd number of times
    *Time Complexity : O(nLogn) ; O(1) space Complexity
    */
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
    *Problem : find the missing number in range of 1...n
    *Link: https://leetcode.com/problems/missing-number/
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

    /*
    *Problem : find the missing number in range of 1...n
    *Link: https://leetcode.com/problems/missing-number/
    Time complexity : O(n) for scanning the input but execution time will be the
    fastest because of bit manupulation.
    */
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


      /**
      *Problem : search an element in rotated sorted array
      *Link:https://leetcode.com/problems/search-in-rotated-sorted-array/
      *Time Complexity : O(nlogn) and space Complexity : O(n)
      */
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

      /**
      *Problem : search an element in rotated sorted array
      *Link:https://leetcode.com/problems/search-in-rotated-sorted-array/
      *Time Complexity : O(nlogn) ; O(1) space Complexity
      */
      public int search(int[] A, int target) {
         if (A.length == 0) return -1;
         int lo = 0;
         int hi = A.length - 1;
         while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) return mid;

            if (A[lo] <= A[mid]) {
               if (target >= A[lo] && target < A[mid]) {
                  hi = mid - 1;
               } else {
                  lo = mid + 1;
               }
            } else {
               if (target > A[mid] && target <= A[hi]) {
                  lo = mid + 1;
               } else {
                  hi = mid - 1;
               }
            }
         }
         return A[lo] == target ? lo : -1;
      }

    /**
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
      /**
      *Problem : find Maximimum in the array which increases and then decreases
      *Observation : This problem is just another variant of binary search , only
      consideration is to put boundary for selecting the sub array for binary search
      *Time complexity : O(logn); space complexity : O(logn) stack call space
      */
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
   *Time Complexity : O(n) -scanning list once; space Complexity :O(1)
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
      long result = 0;
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
     return (int)result;
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
    *Link :https://leetcode.com/problems/reverse-string/
    *Time Complexity : O(n) and O(1) space Complexity
    */
    public String reverseStringInPlace(String input) {
        int size = input.length();
        if(size < 2) return input;
        int low = 0;
        int high = size -1;
        StringBuilder sb = new StringBuilder(input);
        while(low < high) {
            char temp = sb.charAt(high);
            sb.setCharAt(high, sb.charAt(low)); // setCharAt to set char in string
            sb.setCharAt(low, temp);
            low++;
            high--;
        }
        return sb.toString();
    }

    /**
    *Problem : Joseph Circle : Problem of last survivor
    *Observation : if the number of person are even then after each iteration half
    of the elements will be left and the same starting person will hold the gun
    Let's assume K is the gap in consecutive elements so output will be
    2*(N-2^k) + 1; - Perform operation in binary format
    *Another observation is to move the first bit to last position(circular shift)
    */
    public double JosephSurvivor(int n, int k) {
        return 2*(n - Math.pow(2, k)) + 1;
    }

    /**
    *Problem :Best Time to buy and sell stock
    *Link :https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/?tab=Solutions
    *Time Complexity : O(n) and space Complexity :O(1)
    *Observation : if there is long chain of profit ,it will always come from two
    consecutive pair so if it's all the sum of consecutive pair ,and if negative return 0;
    */
    public int maxProfit(int[] prices) {
        int result = 0;
        int size = prices.length;
        for(int i = 1; i < size; i++) {
            result += Math.max(prices[i] - prices[i-1], 0);
        }
        return result;
    }

    public int sqrtNumber(int n) {
        if(n == 0 || n == 1) return n;
        int i = 1, result = 1;
        while(result <= n) {
            i++;
            result = i*i;
        }
        return i - 1;
    }

    public int sqrtNumberBinarySearch(int n) {
        if(n == 0 || n == 1) return n;
        int low = 1;
        int high = n;
        int ans = 0;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(mid * mid == n) return mid;

            if (mid * mid  < n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return ans;
    }
    /**
   //  *Problem : Merge overlapping intervals
   //  *Link: https://leetcode.com/problems/merge-intervals/
   //  *Time Complexity : o(nlogn) to sort the interval based on start time
   //  */
   //  public List<Interval> merge(List<Interval> intervals) {
   //      if(intervals.size() < 2) return intervals;
   //      intervals.sort((l1, l2) -> Integer.compare(l1.start, l2.start));
   //      List<Interval> result = new LinkedList<>();
   //      int start = intervals.get(0).start;
   //      int end = intervals.get(0).end;
    //
   //      for(Interval e : intervals) {
   //          if(e.start <= end) {
   //              end = Math.max(end, e.end);
   //          }else {
   //              result.add(new Interval(start, end));
   //              start = e.start;
   //              end = e.end;
   //          }
   //      }
   //      result.add(new Interval(start, end));
   //      return result;
   //  }

    /**
    *Problem : Check if two string is valid anagram
    *Link:
    *Time Complexity : O(n) and space Complexity : O(n)
    */
    public boolean isAnagram(String s, String t) {
       if(s == null || t == null || s.length() != t.length()) return false;
       int[] charCount = new int[26];
       for(int i = 0; i < s.length(); i++) {
           int c = s.charAt(i) -'a';
           if(charCount[c] != 0) charCount[c] += 1;
           else charCount[c] = 1;
       }

       for(int i = 0; i < t.length(); i++) {
           int c = t.charAt(i) -'a';
           if(charCount[c] != 0) charCount[c] -= 1;
           else charCount[c] = 1;
       }

       for(int i = 0; i <charCount.length; i++) {
           if(charCount[i] != 0) return false;
       }
       return true;
   }
   /**
   *Problem : find the element set in list that form anagram together
   *Link: https://leetcode.com/problems/anagrams/
   *Time Complexity : O(nlong) for sorting + O(n) space and time for hashmap and looping
   */
   public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String s : strs) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String key = String.valueOf(ch);
            if(map.containsKey(key)) map.get(key).add(s);
            else {
                map.put(key, new ArrayList<>());
                map.get(key).add(s);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
    *Problem : Convert an string to Integer
    *Problem Link :https://leetcode.com/problems/string-to-integer-atoi/
    *Solution Link: https://github.com/vtiwari227/Coding-Practice/blob/master/LeetCode/atoi.java
    *Observation : Understand the question which is intenionally vague in nature and consider all
    edge cases
    *Time Complexity : O(n) ; space Complexity : O(1)
    */
    public int atoi(String input) {
        if(input.length() == 0 || input == null) return 0;
        int index = 0, sign = 1;
        long sum = 0;
        input = input.trim();
        if(input.charAt(0) == '+') {
            sign = 1;
            index++;
        } else {
            sign = -1;
            index++;
        }
        for(int i = index; i < input.length(); i++) {
            if( !Character.isDigit(input.charAt(i))) return (int) sum * sign;
            sum = sum * 10 + input.charAt(i) -'0';
            if(sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if(sign == -1 && (-1) * sum < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) sum * sign;
    }

    /**
    *Problem : Maximum sum rectangle with 1's in matrix
    *Link: https://leetcode.com/problems/maximal-rectangle/
    *Observation: Each row form "find highest  area in historgram" problem that we loop over
    * all rows and acculmate values across all rows and return the max area
    *Time Complexity : O(col* row); space Complexity :O(col)
    */
    public int MaximumRectangularSubmatrixOf1s(int[][] input) {
        int[] temp = new int[input[0].length];
        int maxArea = 0;
        int area = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < temp.length; j++) {
                if(input[i][j] == 0) temp[j] = 0;
                else temp[j] += input[i][j];
            }
            area  = maxHistrogram(temp);
            maxArea = area > maxArea ? area : maxArea;
        }
        return maxArea;
    }

    /**
    *Problem : Find the maximum area in histrogram
    *Link :https://github.com/mission-peace/interview/blob/master/src/com/interview/stackqueue/MaximumHistogram.java
    *Video link https://youtu.be/ZmnqCZp9bBs
    */
    public int maxHistrogram(int[] input) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int area = 0;
        int i;
        for(i = 0; i <input.length;) {
            if(stack.isEmpty() || input[stack.peek()] <= input[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                //if stack is empty means everything till i has to be greater or
                // equal to input[top] so get area by input[top]*i;
                if(stack.isEmpty()) {
                    area = input[top]*i;
                }
                //if stack is not empty then everything from i-1 to stack.peek() +1
                //has to be greater or equal to input[top]
                else {
                    area = input[top]*(i - stack.peek() -1);
                }
                if(area > maxArea) maxArea = area;
            }
        }

        while(!stack.isEmpty()){
            int top = stack.pop();
            //if stack is empty means everything till i has to be
            //greater or equal to input[top] so get area by
            //input[top] * i;
            if(stack.isEmpty()){
                area = input[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else{
                area = input[top] * (i - stack.peek() - 1);
            }
        if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }


   /**
   *Problem : find the area of overlapping rectangle while removing common area
   *Link: https://leetcode.com/problems/rectangle-area/
   *Time complexity : O(1) ; space complexity : O(1)
   *Logic : find area of both the rectangle and substract the common one
   */
   public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
      int areaA = (C - A) * (D - B);
      int areaB = (G - E) * (H - F);

      int left = Math.max(A, E);
      int right = Math.min(G, C);
      int bottom = Math.max(F, B);
      int top = Math.min(D,H);

      int overlap = 0;
      if(right > left && top > bottom) {
         overlap = (right - left) * (top - bottom);
      }
      return areaA + areaB - overlap;
   }

    /**
    *Problem : convert fraction into string with lowest form
    *Link:https://leetcode.com/problems/fraction-to-recurring-decimal/
    */
    public String fractionToDecimal(int num, int den) {
       if (num == 0) return "0";
       StringBuilder sb = new StringBuilder();

       //check for '+'/'-'
       sb.append((num > 0 ^ den > 0) ? "-" : "");

       long n = Math.abs((long) num);
       long d = Math.abs((long) den);
       //check for main value
       sb.append(n/d);
       n %= d;

       if(n == 0) return sb.toString();

       //fractional part
       sb.append(".");
       HashMap<Long, Integer> map = new HashMap<Long, Integer>();
       map.put(n, sb.length());
       while(n != 0) {
           n = n * 10;
           sb.append(n/d);
           n %= d;
           if(map.containsKey(n)) {
               int index = map.get(n);
               sb.insert(index, "(");
               sb.append(")");
               break;
           }
           else map.put(n, sb.length());
       }
       return sb.toString();
    }

   /**
   */
   public List<String> findMissingRanges(int[] a, int lo, int hi) {
      List<String> res = new ArrayList<String>();

      // the next number we need to find
      int next = lo;

      for (int i = 0; i < a.length; i++) {
         // not within the range yet
         if (a[i] < next) continue;

         // continue to find the next one
         if (a[i] == next) {
            next++;
            continue;
         }

         // get the missing range string format
         res.add(getRange(next, a[i] - 1));

         // now we need to find the next number
         next = a[i] + 1;
      }

      // do a final check
      if (next <= hi) res.add(getRange(next, hi));

      return res;
      }

   String getRange(int n1, int n2) {
      return (n1 == n2) ? String.valueOf(n1) : String.format("%d->%d", n1, n2);
   }

   /**
   *Problem : Given positive integer ,return excel sheet column number
   *Link :https://leetcode.com/problems/excel-sheet-column-title/
   */
   public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while(n > 0) {
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /=26;
        }
        return result.toString();
    }

    /**
    *Problem : find the peak element in array which is greater than it's neighbour
    *Link: https://leetcode.com/problems/find-peak-element/
    *time complexity : O(logn) for binary search
    */
    public int findPeakElement(int[] nums) {
      if(nums == null || nums.length < 1) return -1;
      int low = 0;
      int high = nums.length -1;

      while(low < high) {
          int mid = low + (high - low)/2;
          if(nums[mid] < nums[mid + 1]) low = mid + 1;
          else high = mid;
      }
      return low;
   }

   /***
   *Problem : rearrange the array to form the highest number
   *Link:https://leetcode.com/problems/largest-number/
   *Time Complexity : O(nlogn); space : O(n)
   */
   public String largestNumber(int[] nums) {
        if(nums == null || nums.length < 1) return null;

        int size = nums.length;
        String[] input = new String[size];

        for(int i = 0; i < size; i++) {
            input[i] = Integer.toString(nums[i]);
        }

        Comparator digitComp = new Comparator<String>() {

          @Override
          public int compare(String o1, String o2) {
              return (o2 + o1).compareTo(o1 + o2);
          }
        };
        Arrays.sort(input, digitComp);

        if(input[0].charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) sb.append(input[i]);

        return sb.toString();
   }

    /**
    *Problem : find a given number is happy
    *Link: https://leetcode.com/problems/happy-number/
    *Time Complexity : O(digitLength); spaceComplexity : O(n)
    */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        int squareSum;
        while(set.add(n)) {
            squareSum = 0;
            while(n > 0) {
                int remainder = n % 10;
                squareSum += remainder * remainder;
                n /= 10;
            }

            if(squareSum == 1) return true;
            else n = squareSum;
        }
        return false;
    }

   /**
   *Problem : Add Digits and return until it has only one digit left
   *Link: https://leetcode.com/problems/add-digits/?
   *time Compleoxity :O (1) ; space Compleoxity : O(1)
   */
   public int addDigits(int num) {
        return num = num % 9 == 0 ? 9 : num % 9;
   }

   /**
   *Problem : Find intersection of two array
   *Link : https://leetcode.com/problems/intersection-of-two-arrays-ii/
   *Time Complexity : O(n); space Complexity : O(n)
   */

   public int[] intersect(int[] nums1, int[] nums2) {
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums1.length; i++)
        {
            if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
            else map.put(nums1[i], 1);
        }

        for(int i = 0; i < nums2.length; i++)
        {
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
            {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i])-1);
            }
        }

       int[] r = new int[result.size()];
       for(int i = 0; i < result.size(); i++)
       {
           r[i] = result.get(i);
       }

       return r;
    }

    public static void main(String[] args) {
        ArraySolution objArraySol = new ArraySolution();
        int input1[][] = {{1,1,1,0},
                         {1,1,1,1},
                         {0,1,1,0},
                         {0,1,1,1},
                         {1,0,0,1},
                         {1,1,1,1}};
         int input[] = {2,2,2,6,1,5,4,2,2,2,2};
        System.out.println(objArraySol.MaximumRectangularSubmatrixOf1s(input1));
    }
}
