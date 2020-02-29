package mine.knowledge.dynamic_programming.intro_to_algo;

import mine.knowledge.Matrix;

/**
 * MatrixMultiply
 */
public class MatrixMultiplyParens {

    private Matrix[] matrices;
    /** 记录在[l, r]的最小乘法次数 */
    private long[][] dp;
    /** 记录在[l, r]之间的分割点位置 */
    private int[][] record;

    /**
     * 对于一列Matrix : <code>A_l,..., A_k,..., A_r</code>，有
     * <p>
     * <code>dp[l, r] = min{ dp[l, k] + dp[k + 1, r] + matrices[l].size()[0] * matrices[k].size()[1] * matrices[r].size()[1] | k in {l,...,r - 1} }</code>
     * <p>
     * 在<code>A_k</code>后分割，记录<code>record[l, r] = k</code>
     * 
     * @param p
     */
    public MatrixMultiplyParens(Matrix[] matrices) {
        this.matrices = matrices;
        int len = matrices.length;
        dp = new long[len][];
        record = new int[len][];
        for (int i = 0; i < len; i++) {
            dp[i] = new long[len];
            record[i] = new int[len];
        }
        // =========== //
        int r;
        long min, tmp;
        int rec = -1;
        for (int i = 2; i <= len; i++) {
            for (int l = 0; l <= len - i; l++) {
                r = l + i - 1;
                min = Long.MAX_VALUE;
                for (int k = l; k < r; k++) {
                    tmp = dp[l][k] + dp[k + 1][r]
                            + matrices[l].size()[0] * matrices[k].size()[1] * matrices[r].size()[1];
                    if (tmp < min) {
                        min = tmp;
                        rec = k;
                    }
                }
                dp[l][r] = min;
                record[l][r] = rec;
            }
        }
    }

    public long dfs(int l, int r) {
        if (l == r)
            return 0;
        long min = Long.MAX_VALUE;
        long res;
        for (int k = l; k < r; k++) {
            res = dfs(l, k) + dfs(k + 1, r) + matrices[l].size()[0] * matrices[k].size()[1] * matrices[r].size()[1];
            if (res < min) {
                min = res;
            }
        }
        return min;
    }

    public long dpAns() {
        return dp[0][matrices.length - 1];
    }

    public String parenRes() {
        return parenResHelper(0, matrices.length - 1);
    }

    private String parenResHelper(int l, int r) {
        if (l == r)
            return " A_" + l + " ";
        int k = record[l][r];
        return "(" + parenResHelper(l, k) + parenResHelper(k + 1, r) + ")";
    }

    public Matrix multiRes() {
        return multiResHelper(0, matrices.length - 1);
    }

    private Matrix multiResHelper(int l, int r) {
        if (l == r)
            return matrices[l];
        int k = record[l][r];
        return multiResHelper(l, k).multiply(multiResHelper(k + 1, r));
    }

}