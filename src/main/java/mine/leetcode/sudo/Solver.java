package mine.leetcode.sudo;

/**
 * Solve
 */
public class Solver {

    private char[][] board;

    private boolean isValid(int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == c && k != j)
                return false;
            if (board[k][j] == c && k != i)
                return false;
        }
        int s = (i / 3) * 3;
        int t = (j / 3) * 3;
        for (int k = s; k < s + 3; k++) {
            for (int l = t; l < t + 3; l++) {
                if (k != i && l != j && board[k][l] == c)
                    return false;
            }
        }
        return true;
    }

    public Solver() {
        solved = false;
    }

    private boolean solved;

    private void dfs(int row) throws InterruptedException {
        for (int i = row; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(i, j, c)) {
                            board[i][j] = c;
                            // Thread.sleep(20);
                            // printBoard();
                            dfs(i);
                            if (solved)
                                return;
                            board[i][j] = '.';
                            // Thread.sleep(20);
                            // printBoard();
                        }
                    }
                    return;
                }
            }
        }
        solved = true;
    }

    public void printBoard() {
        System.out.println("___________________");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
    }

    public void solveSudoku(char[][] board) throws InterruptedException {
        this.board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        solved = false;
        dfs(0);
    }

    public static void main(String[] args) throws InterruptedException {
        char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        Solver solver = new Solver();
        solver.solveSudoku(board);
        solver.printBoard();
    }
}