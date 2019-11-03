package mine.leetcode.stone_game;

import org.jetbrains.annotations.NotNull;

/**
 * StoneGame1
 * 
 * @see
 */
public class StoneGame1 {

    private int[] piles;

    public boolean stoneGame(@NotNull int[] piles) {
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

    public boolean DP(int[] piles) {
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
        System.out.println("A : " + a + " ,B : " + b);
        return a > b;
    }

    public static void main(String[] args) {
        int[] piles = { 4, 7, 1 };
        StoneGame1 game = new StoneGame1();
        boolean aWin = game.stoneGame(piles);
        System.out.println(aWin);
        boolean res = game.DP(piles);
        System.out.println("is A win? " + res);
    }
}