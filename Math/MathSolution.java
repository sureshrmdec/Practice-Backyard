public class MathSolution {

    /*
    Example : 0,1,1,2,3,5,8,13
    Recurisve Solution ,Time Complexity : O(n)
    Space Complexity : O(n)
    */
    public int fibonaci(int n) {
        if (n<=1) return n;
        return fibonaci(n-1) + fibonaci(n-2);
    }

    public int fibonaciDP(int n) {
        int[] lookupTable = new int[100];

        if(lookupTable[n] == 0) {
            if (n <= 1) {
                lookupTable[n] = n;
            } else {
                lookupTable[n] = fibonaciDP(n-1) + fibonaciDP(n-2);
            }
        }
        return lookupTable[n];
    }

    /** generate Pascal triangle
    *Link: https://leetcode.com/problems/pascals-triangle/
    *Time Complexity : O(n^2); space Complexity : O(n)
    */

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows < 0) return result;

        for(int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for(int j = 0; j < i + 1; j++) {
                if(j == 0 || j == i)
                row.add(1);
                else {
                row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }

    /**
    *Problem : generate pascal trinagle for given rows
    *Link: https://leetcode.com/problems/pascals-triangle/
    *Time Complexity : O(n)
    */
    public List<List<Integer>> generate(int numRows){
	List<List<Integer>> allrows = new ArrayList<List<Integer>>();
	ArrayList<Integer> row = new ArrayList<Integer>();
	for(int i=0;i<numRows;i++)
	{
		row.add(0, 1);
		for(int j=1;j<row.size()-1;j++)
			row.set(j, row.get(j)+row.get(j+1));
		allrows.add(new ArrayList<Integer>(row));
	}
	return allrows;

    }
    public static void main(String[] args) {
        MathSolution objMath = new MathSolution();
        System.out.println(objMath.fibonaciDP(8));
    }
}
