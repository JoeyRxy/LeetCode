package mine.knowledge.dynamic_programming;

import java.util.List;

/**
 * Triangle
 * <p>
 * <div>
 * <p>
 * Given a triangle, find the minimum path sum from top to bottom. Each step you
 * may move to adjacent numbers on the row below.
 * </p>
 * 
 * <p>
 * For example, given the following triangle
 * </p>
 * 
 * <pre>
 * [
     [<strong>2</strong>],
    [<strong>3</strong>,4],
   [6,<strong>5</strong>,7],
  [4,<strong>1</strong>,8,3]
]
 * </pre>
 * 
 * <p>
 * The minimum path sum from top to bottom is <code>11</code> (i.e.,
 * <strong>2</strong> + <strong>3</strong> + <strong>5</strong> +
 * <strong>1</strong> = 11).
 * </p>
 * 
 * <p>
 * <strong>Note:</strong>
 * </p>
 * 
 * <p>
 * Bonus point if you are able to do this using only <em>O</em>(<em>n</em>)
 * extra space, where <em>n</em> is the total number of rows in the triangle.
 * </p>
 * </div>
 * <p>
 * 我的：
 * <p>
 * dp[i][j] = min{dp[i-1][j-1],dp[i-1][j]} + a[i];
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[2][n];// IMPORTANT 如何节省空间! `&`运算等效于%2效果
        dp[0][0] = triangle.get(0).get(0);
        int min;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                min = Integer.MAX_VALUE;
                if (j > 0)
                    min = Math.min(dp[i - 1 & 1][j - 1], min);
                if (j < i)
                    min = Math.min(dp[i - 1 & 1][j], min);
                dp[i & 1][j] = min + triangle.get(i).get(j);
            }
        }
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[n - 1 & 1][i]);
        }
        return min;
    }
}