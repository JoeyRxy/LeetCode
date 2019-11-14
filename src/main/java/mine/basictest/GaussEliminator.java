package mine.basictest;

/**
 * GaussElimination
 */
public class GaussEliminator {

    public GaussEliminator(Matrix a) {

    }

    public GaussEliminator(Matrix a, double[] b) {
    }

    /**
     * 化简为最简阶梯型
     * <p>
     * 注意：会修改数组内容，可提前备份源数据
     */
    private void helper(double[][] mat) {
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

    // private <T> void swap(T a, T b) {
    // T _t = a;
    // a = b;
    // b = _t;
    // }

    public static void main(String[] args) {

    }
}