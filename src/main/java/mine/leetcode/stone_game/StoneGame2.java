package mine.leetcode.stone_game;

import java.util.Arrays;

/**
 * StoneGame2
 * <p>
 * Alex&nbsp;and Lee continue their&nbsp;games with piles of stones.&nbsp; There
 * are a number of&nbsp;piles&nbsp;<strong>arranged in a row</strong>, and each
 * pile has a positive integer number of
 * stones&nbsp;<code>piles[i]</code>.&nbsp; The objective of the game is to end
 * with the most&nbsp;stones.&nbsp;
 * </p>
 * <p>
 * Alex and Lee take turns, with Alex starting first.&nbsp; Initially,
 * <code>M = 1</code>.
 * </p>
 * <p>
 * On each player's turn, that player&nbsp;can take <strong>all the
 * stones</strong> in the <strong>first</strong> <code>X</code> remaining piles,
 * where <code>1 &lt;= X &lt;= 2M</code>.&nbsp; Then, we
 * set&nbsp;<code>M = max(M, X)</code>.
 * </p>
 * <p>
 * The game continues until all the stones have been taken.
 * </p>
 * <p>
 * Assuming Alex and Lee play optimally, return the maximum number of stones
 * Alex can get.
 * </p>
 * IMPORTANT
 */
public class StoneGame2 {
    private int[] s;// s[i] = sum of piles[i,..., n]
    private int n;
    private int[][] memo;

    public StoneGame2(int[] piles) {
        this.n = piles.length - 1;
        s = new int[n + 1];
        s[n] = piles[n];
        memo = new int[n + 1][(n << 1) + 3];
        for (int i = n - 1; i > -1; i--) {
            s[i] = piles[i] + s[i + 1];
        }
    }

    public int ans() {
        return recurMemo(0, 1);
    }

    private int recurMemo(int i, int M) {
        int M2 = (M << 1);
        if (M2 >= n - i + 1)
            return s[i];
        if (memo[i][M] > 0)
            return memo[i][M];
        int max = 0, t, si = s[i], x;
        for (x = 1; x < M; x++) {
            // if (i + x > n)
            // break;
            memo[i + x][M] = recurMemo(i + x, M);
            t = si - memo[i + x][M];
            if (t > max)
                max = t;
        }
        for (; x <= M2; x++) {
            // if (i + x > n)
            // break;
            memo[i + x][x] = recurMemo(i + x, x);
            t = si - memo[i + x][x];
            if (t > max)
                max = t;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] piles = { 1, 2, 3, 4, 5, 100 };
        StoneGame2 game = new StoneGame2(piles);
        int ans = game.ans();
        System.out.println(ans);
    }
}