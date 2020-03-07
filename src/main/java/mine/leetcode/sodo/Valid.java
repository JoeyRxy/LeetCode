package mine.leetcode.sodo;

/**
 * Valid
 */
public class Valid {
    private char[][] board;

    private boolean isValid(int i, int j) {
        char c = board[i][j];
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == c || board[k][j] == c)
                return false;
        }
        int s = i / 3;
        int t = j / 3;
        s *= 3;
        t *= 3;
        for (int k = s; k < s + 3; k++) {
            for (int l = t; l < t + 3; l++) {
                if (k != i && l != j && board[i][j] == c)
                    return false;
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!isValid(i, j))
                        return false;
                }
            }
        }
        return true;
    }
}