package mine.leetcode.nqueens;

import java.util.ArrayList;
import java.util.List;

/**
 * First
 */
public class First {

    private List<List<String>> res;
    private byte[][] tmp;
    private int n;

    private boolean isValid(int row, int col) {
        // cur row
        for (int i = 0; i < row; i++) {
            if (tmp[i][col] == 'Q')
                return false;
        }
        // cur col
        for (int j = 0; j < n; j++) {
            if (tmp[row][j] == 'Q')
                return false;
        }
        int k = 1;
        while (k <= row && k <= col) {
            if (tmp[row - k][col - k] == 'Q')
                return false;
            k++;
        }
        k = 1;
        while (k <= row && col + k < n) {
            if (tmp[row - k][col + k] == 'Q')
                return false;
            k++;
        }
        return true;
    }

    private List<String> getAns() {
        List<String> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ans.add(new String(tmp[i]));
        }
        return ans;
    }

    private void dfs(int row) {
        if (row == n) {
            res.add(getAns());
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                tmp[row][col] = 'Q';
                dfs(row + 1);
                tmp[row][col] = '.';
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        tmp = new byte[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = '.';
            }
        }
        this.n = n;
        dfs(0);
        return res;
    }

    public static void main(String[] args) {
        int n = 8;
        List<List<String>> solution = new First().solveNQueens(n);
        for (List<String> list : solution) {
            System.out.println("==============");
            for (String ans : list) {
                System.out.println(ans);
            }
        }
    }
}