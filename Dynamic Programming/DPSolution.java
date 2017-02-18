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

    public static void main(String[] args) {
        DPSolution objDP = new DPSolution();
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
                            objDP.cutRod(arr, size));

    }
}
