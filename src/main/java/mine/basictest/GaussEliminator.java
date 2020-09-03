package mine.basictest;

import java.util.Arrays;

/**
 * GaussElimination
 */
public class GaussEliminator {

    public static double[] solve(Matrix a, double[] b) {
        // 组成增广矩阵
        int m = a.mat.length;
        int n = a.mat[0].length;
        if (m != b.length)
            throw new IllegalArgumentException("the matrix's size doesn't agree with vector's length");
        double[][] _new = new double[m][n + 1];
        for (int i = 0; i < _new.length; i++) {
            for (int j = 0; j < n; j++) {
                _new[i][j] = a.mat[i][j];
            }
            _new[i][n] = b[i];
        }
        // 化简增广矩阵
        helper(_new);
        // 从中提取解
        boolean flag = false;
        for (int i = m - 1; i > -1; i--) {
            if (_new[i][n] != 0) {
                for (int j = n - 1; j != 0; j--)
                    if (_new[i][j] != 0)
                        flag = true;

                if (flag == true) {// 有解
                    if (n > i + 1) {
                        // TODO:
                        System.out.println("There're multiple solutions!");
                        return null;
                    } else {
                        // System.out.println("There's one solution:");
                        double[] _t = new double[m];
                        for (int j = 0; j < _t.length; j++) {
                            _t[j] = _new[j][n];
                        }
                        return _t;
                    }
                } else {
                    System.out.println("There's no solution");
                    return null;
                }
            }
        }
        return null;
    }

    public static Matrix inverse(Matrix a) {
        double[][] mat = a.mat;
        if (mat.length != mat[0].length)
            throw new IllegalArgumentException("Must be an n-by-n matrix.");
        // TODO
        return null;
    }

    /**
     * 化简为最简阶梯型
     * <p>
     * 注意：会修改数组内容，提前备份源数据
     */
    public static void helper(double[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        if (m == 1)
            return;
        // 自上而下；自左而右
        int stage = -1;// IMPORTANT记录当前台阶
        // assert a[0][0] != 0;
        int row;
        double[] tmp;
        double _t;
        for (int col = 0; col < n; col++) {
            row = stage + 1;
            while (row < m && mat[row][col] == 0)
                row++;
            // 如果到了最后一行
            if (row == m)
                continue;
            // 归一化：将第row行归一化，从第col列开始（前面的列都是0）
            _t = mat[row][col];
            mat[row][col] = 1;
            for (int j = col + 1; j < n; j++)
                mat[row][j] /= _t;

            // 将第col列所有行化为0
            for (int _row = row + 1; _row < m; _row++) {
                if (mat[_row][col] == 0)
                    continue;
                double v = -mat[_row][col];
                for (int j = col + 1; j < n; j++)
                    mat[_row][j] += mat[row][j] * v;

                mat[_row][col] = 0;
            }
            // 将不为零的交换到下一级台阶
            ++stage;
            tmp = mat[stage];
            mat[stage] = mat[row];
            mat[row] = tmp;
        }
        // 自下而上；自右而左
        int col = n - 1;
        while (stage != 0) {
            while (mat[stage][col - 1] != 0)
                col--;
            for (row = 0; row < stage; row++) {
                double v = mat[row][col];
                mat[row][col] = 0;
                for (int j = col + 1; j < n; j++) {
                    mat[row][j] -= (mat[stage][j] * v);
                }
            }
            stage--;
        }
    }
}