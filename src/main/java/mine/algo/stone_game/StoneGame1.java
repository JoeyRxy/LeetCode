package mine.algo.stone_game;

/**
 * StoneGame1
 */
public class StoneGame1 {

    public StoneGame1() {
    }

    private int[] piles;

    public boolean stoneGame(int[] piles) {
        this.piles = piles;
    }

    public int recursive(int i, int j) {
        if (i == j)
            return piles[i];
        return Math.max(piles[i] - recursive(i + 1, j), piles[j]);
    }

    public static void main(String[] args) {

    }
}