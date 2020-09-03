package mine.leetcode.stone_game;

/**
 * StoneGame1
 * <p>
 * Alex and Lee play a game with piles of stones.&nbsp; There are an even number
 * of&nbsp;piles <strong>arranged in a row</strong>, and each pile has a
 * positive integer number of stones <code>piles[i]</code>.
 * </p>
 * <p>
 * The objective of the game is to end with the most&nbsp;stones.&nbsp; The
 * total number of stones is odd, so there are no ties.
 * </p>
 * <p>
 * Alex and Lee take turns, with Alex starting first.&nbsp; Each turn, a
 * player&nbsp;takes the entire pile of stones from either the beginning or the
 * end of the row.&nbsp; This continues until there are no more piles left, at
 * which point the person with the most stones wins.
 * </p>
 * <p>
 * Assuming Alex and Lee play optimally, return <code>True</code>&nbsp;if and
 * only if Alex wins the game.
 * </p>
 * 
 */
public class StoneGame1 {

    private int[] piles;

    public boolean stoneGame(int[] piles) {
        this.piles = piles;
        int A = first(0, piles.length - 1);
        System.out.println(A);
        int sum = 0;
        for (int x : piles)
            sum += x;

        int B = sum - A;
        System.out.println(B);
        return A > B;
    }

    // private int a, b;

    // /**
    // *
    // * @param i
    // * @param j
    // */
    // private void recursiveA(int i, int j) {
    // if (i == j)
    // a += piles[i];
    // // 1. 选择i
    // a += piles[i];
    // recursiveB(i + 1, j);
    // // 2. 选择j
    // a -= piles[i];
    // a += piles[j];
    // recursiveB(i, j - 1);
    // // 3. 判断哪一个选择更好，并算成最终结果
    // }

    // private void recursiveB(int i, int j) {
    // if (i == j)
    // b += piles[i];
    // b += piles[i];
    // recursiveA(i + 1, j);
    // b += piles[j];
    // recursiveA(i, j - 1);
    // }

    /**
     * 先手（first）面对[i...j]能获得的最大数量
     * 
     * @param i
     * @param j
     * @return
     */
    private int first(int i, int j) {
        if (i == j)
            return piles[i];

        int left = second(i + 1, j, true) + piles[i];
        int right = second(i, j - 1, false) + piles[j];
        return Math.max(left, right);
    }

    /**
     * 后手（second）面对[i...j]能获得的最大数量 后手需要知道先手选择的是i还是j。
     * 
     * @param i
     * @param j
     * @param isLeft 先手是选择了左边的吗? 这是两个函数之间进行通信的标志位.
     * @return
     */
    private int second(int i, int j, boolean chooseLeft) {
        // ATTENTION:使用isLeft变量进行两个函数之间的通讯
        if (i == j)
            return 0;
        if (chooseLeft)
            return first(i + 1, j);
        else
            return first(i, j - 1);
    }

    public boolean dp(int[] piles) {
        int n = piles.length;

        int[][] dpA, dpB;
        dpA = new int[n][n];
        dpB = new int[n][n];

        for (int i = 0; i < n; i++) {
            dpA[i][i] = piles[i];
            dpB[i][i] = 0;
        }

        int left, right;
        for (int i = 1; i < n; i++) {// 以第[0,i]为起点，向右下角填充。
            int x = 0, y = i;
            while (y < n) {
                left = dpB[x + 1][y] + piles[x];
                right = dpB[x][y - 1] + piles[y];
                if (left > right) {
                    dpA[x][y] = left;
                    dpB[x][y] = dpA[x + 1][y];
                } else {
                    dpA[x][y] = right;
                    dpB[x][y] = dpA[x][y - 1];
                }

                x++;
                y++;
            }
        }
        int a = dpA[0][n - 1];
        int b = dpB[0][n - 1];
        // System.out.println("A : " + a + " ,B : " + b);
        return a > b;
    }

    // f[i, j] 记为先手能从[i ,j]中获得的最大值
    // f[i, j] = max(piles[i] + s[i + 1, j] - f[i + 1, j], piles[j] + s[i, j - 1] -
    // f[i, j - 1])
    private int[][] s;// s[i][j] = sum of piles[i,...j]
    private int[][] f;

    public boolean ans(int[] piles) {
        int n = piles.length;
        s = new int[n][n];
        f = new int[n][n];
        for (int i = 0; i < n; i++) {
            s[i][i] = piles[i];
            f[i][i] = piles[i];
        }
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                s[i][j] = piles[j] + s[i][j - 1];
        //
        for (int col = 1; col < n; col++) {
            int i = 0, j = col;
            while (j < n) {
                f[i][j] = Math.max(piles[i] + s[i + 1][j] - f[i + 1][j], piles[j] + s[i][j - 1] - f[i][j - 1]);
                i++;
                j++;
            }
        }
        return (f[0][n - 1] << 1) > s[0][n - 1];
    }

    public static void main(String[] args) {
        int[] piles = { 3, 7, 1 };
        StoneGame1 game = new StoneGame1();
        boolean ans = game.ans(piles);
        System.out.println(ans);
    }
}