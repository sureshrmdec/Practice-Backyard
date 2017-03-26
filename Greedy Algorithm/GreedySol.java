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


    /**
    *Problem: Minimum number of arrows to burst the ballons
    *Constraint:
        1. Can the matrix be null or empty,
        2. What will be the maximum ballon counts

    *Idea:
        1. Use greedy apporach to solve this problem, take first ballon and it's end point
        as high limit and if new ballon start is less than high limit then we can burst this
        ballon using previous arrow since it come under that radar.
        If not then increase the arrow count and set new high to new ballon end point
    *Time Complexity : O(nLogn) + o(n)
    *Space Complexity : O(1)
    Test Case:
    Null - 0; [] - 0; [[10,16],[2,8],[1,6],[7,12]] - 2
    */
    public int minArrowsBurstBallon(int[][] list) {
        if(list == null || list.length < 1) return 0;
        Arrays.sort(list, new Comparator<int[]> {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] -o2[2];
                return o1[0] -o[2];
            }
        });
        int maxArrows = 1;
        int arrowLimit = list[0][1];
        for(int i = 1; i < list.length; i++) {
            int[] ballon = list[i];
            if(ballon[0] <= arrowLimit) {
                arrowLimit = Math.min(arrowLimit, ballon[1]);
            }
            else {
                maxArrows++;
                arrowLimit = ballon[1];
            }
        }
        return maxArrows;
    }
}

    /**
    *Problem:Non overlapping intervals
    *Link: https://leetcode.com/problems/non-overlapping-intervals/
    *Constraint:
        1. Can interval list be null or empty
        2. What can be the maximum size of interval list
    *Idea:
        1.Sort the list interval by end times using custom Comparator
        2.Then loop through all the elemnts and see if star of given interval is greater
        or equal than last interval end time then you have a distinct and new interval
        update the couter and end pointer to given interal end time
    *Time Complexity : O(nlogn) + O(n)
    *Space Complexity : O(1)
    *Test Case:
    Null - 0; [] : 0 ;[[1,11],[1, 100], [2, 12],[11, 22]]
    */
    public int nonOverlappingIntervals(Interval[] intervals) {
    if (intervals.length == 0)  return 0;

        Arrays.sort(intervals, new myComparator());
        int end = intervals[0].end;
        int count = 1;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;
            }
        }
        return intervals.length - count;
    }

    class myComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
        return a.end - b.end;
    }
