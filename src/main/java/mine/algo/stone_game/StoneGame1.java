package mine.algo.stone_game;

/**
 * StoneGame1
 */
public class StoneGame1 {

    private int[] piles;

    public boolean stoneGame(int[] piles) {
        this.piles = piles;
    }

    private int a, b;

    /**
     * 
     * @param i
     * @param j
     */
    private void recursiveA(int i, int j) {
        if (i == j)
            a += piles[i];
        // 1. 选择i
        a += piles[i];
        recursiveB(i + 1, j);
        // 2. 选择j
        a -= piles[i];
        a += piles[j];
        recursiveB(i, j - 1);
        // 3. 判断哪一个选择更好，并算成最终结果
    }

    private void recursiveB(int i, int j) {
        if (i == j)
            b += piles[i];
        b += piles[i];
        recursiveA(i + 1, j);
        b += piles[j];
        recursiveA(i, j - 1);
    }

    private int[][] dp;

    private void DP() {
        for (int i = 0; i < dp.length; i++) {

        }
    }

    public static void main(String[] args) {
        int[] piles = { 4, 7, 5, 1 };
        StoneGame1 game = new StoneGame1();
        boolean aWin = game.stoneGame(piles);
        System.out.println(aWin);
    }
}