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
    public static void main(String[] args) {
        MathSolution objMath = new MathSolution();
        System.out.println(objMath.fibonaciDP(8));
    }
}
