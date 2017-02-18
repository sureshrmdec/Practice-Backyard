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


    public static void main(String[] args) {
        DPSolution objDP = new DPSolution();
        int arr[] = new int[]{ 10, 22, 9, 33, 21, 50, 41, 60};
        int n = arr.length;
        System.out.println("increasing "+
               " subsequence is "+
               objDP.longestIncreasingSubsequence( arr) );

    }
}
