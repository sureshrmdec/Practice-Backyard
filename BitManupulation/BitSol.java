public class BitSol {

    /**
    Problem : Calculate number of 1's bit in number
    Link: https://leetcode.com/problems/number-of-1-bits/?tab=Description
    Observation : check each bit is 1 or not then right shift the mask pointer
    */
    public int HammingWeight(int n) {
        int bitCount = 0;
        int mask = 1;
        for(int i =0; i < 32; i++) {
            if((n & mask) != 0) bitCount++;
            mask <<= 1;
        }
        return bitCount;
    }

    public int HammingWeight2(int n) {
        int bitCount = 0;
        while(n > 0) {
            n = n & (n - 1);
            bitCount++;
        }
        return bitCount;
    }

    /**
    *Problem : Hamming distance between two numbers
    *Link :https://leetcode.com/problems/hamming-distance/?
    *Time Complexity : O(n); space Complexity : O(1)
    */
    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int counter = 0;
        while(res > 0)  {
            counter++;
            res = res & (res -1);
        }
        return counter;
    }

    public static void main(String[] args) {
        BitSol objBitSol = new BitSol();
        System.out.println(objBitSol.HammingWeight(7));
    }
}
