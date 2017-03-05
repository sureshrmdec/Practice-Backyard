import java.util.ArrayList;
import java.util.List;

public class MatrixSol {

    /**
    *Problem : Print the elements of matrix in diagonal order
    *Link:http://www.ideserve.co.in/learn/print-matrix-diagonally
    */
    public void printMatrixDiagonally(int[][] input) {
        int rowCount = input.length;
        int colCount = input[0].length;
        int row, col;
        List<Integer> result = new ArrayList<Integer>();

        for(int i = 0; i < rowCount; i++) {
            for(row = i, col = 0; row >=0 && col < colCount; row--, col++) {
                result.add(input[row][col]);
            }
        }

        for(int i = 1; i < colCount; i++) {
            for(row = rowCount -1, col = i; row >=0 && col < colCount; row--, col++) {
                result.add(input[row][col]);
            }
        }
        for(Integer e : result) {
            System.out.println(e);
        }
    }

    /**
    *Problem : search a row and column sorted matrix
    *Link: https://leetcode.com/problems/search-a-2d-matrix-ii/
    *Time Complexity :O (m + n)
    */

    public boolean searchMatrix(int[][] matrix, int target) {

    if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
    int rowLimit = matrix.length - 1;
    int col = matrix[0].length - 1;

    int row = 0;
    while(row <= rowLimit && col >= 0) {
        if(matrix[row][col] == target) return true;
        else if (matrix[row][col] > target) col--;
        else if(matrix[row][col] < target) row++;
    }
    return false;
    }

    public static void main(String[] args) {
        MatrixSol objMatrix = new MatrixSol();
        int[][] input = {{1,2,3},
                         {4,5, 6},
                          {7,8,9}};
        objMatrix.printMatrixDiagonally(input);
    }
}
