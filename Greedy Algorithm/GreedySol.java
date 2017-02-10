public class GreedySol {

    /**
    *Problem : Activity Selection with input sorted with finish time
    *Observation : Greedy approach look for next best job with minimum finish T
    *Pseudo Code:
    *   add first job to result
    *   loop through second job and see if start time is greater than last
    *   finish time -> use pointer for previous finish job
    *Time Complexity : O(n) ; Space Complexity : O(n) - worst case
    */
    public void activitySelectionSortedInput(int[] st, int[] ft) {
        int i, j;
        int size = st.length;
        i = 0;
        System.out.print(i + " ");
        for(j = 1; j < size; j++) {
            if (st[j] >= ft[i]) {
                System.out.print(j + " ");
                i = j;
            }
        }
    }

    public static void main(String[] args) {
        GreedySol objGreedySol = new GreedySol();
        int s[] =  {1, 3, 0, 5, 8, 5};
        int f[] =  {2, 4, 6, 7, 9, 9};
        objGreedySol.activitySelectionSortedInput(s, f);
    }
}

// class Job implement Comparator<Job> {
//     private startTime;
//     private finishTime;
//
//     public Job(int st, int ft) {
//         this.startTime = st;
//         this.finishTime = ft;
//     }
//     public int compare(Job j1, Job j2) {
//         return j1.finishTime - j2.finishTime;
//     }
}
