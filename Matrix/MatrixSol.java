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

    /**
    *Problem : Rotate Image
    *Link: https://leetcode.com/problems/rotate-image/
    *Constraint:
        1.Can matrix be empty
        2.Rotate 90 degreess - clockwise
        3.Is matrix a square matrix
    *Idea:
        1.Take the transpose of matrix- Matrix[i][j] = Matrix[j][i] - row will become column
        2.Then swap the nodes arounnd the center column
        Time Complexity :O(n^2) : space Complexity :O(1)
    */
    public void RotateImage(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = i; j < n; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n/2; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 -j];
                matrix[i][matrix.length - 1 -j] = temp;
            }
        }
    }

    /**
    *Problem : Pint a given matrix in spiral format
    *Link: https://leetcode.com/problems/spiral-matrix/
    *Constraint:
        1. Can matrix be empty
        2. Does the matrix is square matrix
    *Idea:
        1. Travse the matrix first right,down,left and up and do this recurrsively
        until you have processed all rows and column. Use two pointer rowBeign,colBegin
        and rowEnd,ColEnd to increment and decreemnt pointers as you traverse array
        Time Complexity : O(size of matrix) : since you are traversing one line
        at a time either row or column
    */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0) return result;

        int rowBeign = 0;
        int colBegin = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;

        while(rowBegin <= rowEnd && colBegin <= colEnd) {
            //traverse right
            for(int i = colBegin; i<= colEnd; i++) {
                result.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            //traverse down
            for(int i = rowBegin; i <= rowEnd; i++) {
                result.add(matrix[rowEnd][i]);
            }
            colEnd--;


        //traverse left
            if(rowBegin <= rowEnd) {
                for(int i = colEnd; i >= colBegin; i--) {
                    result.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            if(colBegin <= colEnd) {
                for(int i = rowEnd; i >= rowBegin; i--) {
                    result.add(matrix[i][colBegin]);
                }
            }
            colBegin++;

        }
        return result;
    }

    /**
    *Problem : Set Matrix Zero
    *Link: https://leetcode.com/problems/set-matrix-zeroes/
    *Constraint:
        1.Can matrix be empty - No
        2.Can be a case where matrix don't have zero element
        3. Size of matrix - is it square
    *Idea:
        1.Use first Row and first column as marker to capture a given elemnt is zero and
        that row and col needs to set with all element zero
        2.if first row have zero elements then capture them too using flag elements
        *Time Complexity : O(n^2) : space complexity: O(1)
    */
    public void setZeros(int[][] matrix) {
        boolean firstRow = false, firstCol = false;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) firstRow = true;
                    if(j == 0) firstCol = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix.length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(firstRow) {
            for(int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        if(firstCol) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
