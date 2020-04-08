package mine.leetcode;

import java.util.Arrays;

/**
 * RotateMatrix
 */
public class RotateMatrix {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0 || n == 1)
            return;
        int m = n >> 1;
        int t;
        for (int i = 0; i < m; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                t = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = t;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println(Arrays.deepToString(matrix));
        new RotateMatrix().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}