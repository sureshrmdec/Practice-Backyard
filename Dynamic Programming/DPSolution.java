import java.util.List;

public class DPSolution {

    /**
    *Problem : Longest palindrome subsequence
    *Observation : Problem satisfies both optimal substructure and overlapping
    *   subproblem to guide DP solution
    *Pseudo Code :
    *   if string is only one character return that
    *   Intialize lookup array for DP memorization
    *   Define rule of optimal substructure
    *   Time Complexity :
    */


    /*
    *Problem :http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
    *Observation: problem have both overlapping substructure and optimal substructure
    * DP equation = max_val = Math.max(max_val, price[j] + val[i-j-1])
    *Time complexity : O(n^2)
    */
    public int cutRod(int price[], int n) {
        int val[] = new int[n+1];
        val[0] = 0;

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i<=n; i++){
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
              max_val = Math.max(max_val,
                                 price[j] + val[i-j-1]);
          val[i] = max_val;
        }
      }

      return val[n];
    }

    /*
    * Problem: Maximum sum increasing subsequence
    *http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
    * DP Equation = T[i] = Math.max(T[i], T[j] + arr[i]) , i-< outerloop, j-<innerloop
    * Time Complexity : O(n^2) ;Space Complexity :O(n)
    */
    public int maxSumSubarray(int[] input) {
        int size = input.length;
        int[] result = new int[size];

        for(int i = 0; i < size; i++) {
            result[i] = input[i];
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < i; j++) {
                if(input[j] < input[i]) {
                    result[i] = Math.max(result[i], result[j] + input[i]);
                }
            }
        }

        int max = result[0];
        for(int i = 1; i < size; i++) {
            if(result[i] > max) {
                max = result[i];
            }
        }
        return max;
    }

    /*
    * Problem: Maximum increasing subsequence
    *http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
    * DP Equation = T[i] = Math.max(T[i], T[j] + 1) , i-< outerloop, j-<innerloop
    * Time Complexity : O(n^2) ;Space Complexity :O(n)
    */
    public int longestIncreasingSubsequence(int[] input) {
        int size = input.length;
        int[] result = new int[size];

        for(int i = 0; i < size; i++) {
            result[i] = 1;
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < i; j++) {
                if(input[j] < input[i]) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }
        }

        int max = result[0];
        for(int i = 1; i < size; i++) {
            if(result[i] > max) {
                max = result[i];
            }
        }
        return max;
    }
    /*Problem:http://www.ideserve.co.in/learn/nth-fibonacci-number
    *Observation : Problem satisfies both optimal substructure and overlapping subproblem
    *Time Complexity : O(n) and space Complexity : O(n)
    */
    public int nthFibonaci(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;

        for(int i = 2; i <=n; i++) {
            result[i] = result[i-1] + result[i-2];
        }
        return result[n];
    }

    /*Problem:http://www.ideserve.co.in/learn/maximum-subarray-sum (Contagious)
    *Observation : Problem satisfies both optimal substructure and overlapping subproblem
    *Time Complexity: O(n) ; Space Complexity :O(n)
    */
    public int maxContigiousSubarray(int[] input) {
        int size = input.length;
        int[] result = new int[size];

        for(int i = 0; i < size; i++) {
            result[i] = input[i];
        }
        for(int i = 1; i < size; i++) {
            result[i] = Math.max(result[i], result[i-1] + result[i]);
        }
        int maxSum = result[0];
        for(int i = 1; i < size; i++) {
            if(result[i] > maxSum) {
                maxSum = result[i];
            }
        }
        return maxSum;
    }

    public int rob(int[] input) {
        int size = input.length;
        if(size == 0) return 0;
        int[] result = new int[size];
        for(int i = 0; i < size; i++) {
            result[i] = input[i];
        }

        result[1] = Math.max(result[1], result[0]);
        for(int i = 2; i < size; i++) {
            result[i] = Math.max(result[i] + result[i-2], result[i-1]);
        }
        return result[size -1];
    }

    /**
    *Problem :Mininum jump to reach to end of array
    *Link :https://leetcode.com/problems/jump-game-ii/
    *Time Complexity : O(n^2) and space complexity : O(n)
    */
    public int minJumpDP(int[] input) {
        int size = input.length;
        int[] jump = new int[size];
        int[] jumpPath = new int[size];

        jump[0] = 0;
        for(int i = 1; i < size; i++) {
            jump[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i < size; i++) {
            for(int j = 0; j < i; j++) {
                if(input[j] + j >= i) {
                    if(jump[j] + 1 < jump[i]) {
                        jump[i] = jump[j]  + 1;
                        jumpPath[i] = j;
                    }
                }
            }
        }
        return jump[size - 1];
    }

    /**
    *Problem : check if string matches with list of words in segments
    *Link :https://leetcode.com/problems/word-break/?tab=Description
    *Time Complexity : O(n^2) and space Complexity : O(n)
    */
    public boolean wordBreak(String s, List<String> wordDict) {
     boolean[] result = new boolean[s.length() + 1];
     result[0] = true;;

     for(int i = 1; i <= s.length(); i++) {
         for(int j = 0; j < i; j++) {
             if(result[j] && wordDict.contains(s.substring(j,i))) {
                 result[i] = true;
                 break;
             }
         }
     }
     return result[s.length()];
    }

    /**
    *Problem : Minimum Path sum in m * n grid
    *Link :https://leetcode.com/problems/minimum-path-sum/
    *Time Complexity : O(n ^2) ; space Complexity : O(n^2)
    */
    public int minPathSum(int[][] grid) {
        int m = grid.length;// row
       int n = grid[0].length; // column
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (i == 0 && j != 0) {
    				grid[i][j] = grid[i][j] + grid[i][j - 1];
    			} else if (i != 0 && j == 0) {
    				grid[i][j] = grid[i][j] + grid[i - 1][j];
    			} else if (i == 0 && j == 0) {
    				grid[i][j] = grid[i][j];
    			} else {
    				grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j])
    						+ grid[i][j];
    			}
    		}
    	}
	return grid[m - 1][n - 1];
    }

    /**
    *Problem : subset sum problem using DP
    *Link :https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubsetSum.java
    *Time Complexity: O(array.length*total); space Complexity: O(array.length *total)
    */
    public boolean subSetSum(int[] input, int total) {
        boolean[][] result = new boolean[input.length + 1][total + 1];

        for(int i = 0; i <= input.length; i++) {
            result[i][0] = true;
        }

        for(int i = 1; i <= input.length; i++) {
            for(int j = 1; j <= total; j++) {
                if(j - input[i - 1] > 0) {
                    result[i][j] = result[i -1][j] || result[i - 1][j - input[i -1]];
                } else {
                    result[i][j] = result[i - 1][j];
                }
            }
        }
        return result[input.length][total];
    }

    public static void main(String[] args) {
        DPSolution objDP = new DPSolution();
        int arr[] = new int[]{2,3,1,1,4};
        System.out.println(objDP.subSetSum(arr, 9));


    }
}
