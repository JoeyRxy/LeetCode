package mine.algo.zero_one_matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Solution
 * <p>
 * <a href="https://leetcode.com/problems/01-matrix/">ZeroOneMatrix<a>
 * <p>
 * {@code DP} 很有想法！
 */
@SuppressWarnings("unused")
public class Matrix {

    public int[][] updateMatrix(int[][] matrix) {
        // return BFS(matrix);
        return DP(matrix);
    }

    // ============================NO.1==================//

    // ATTENTION : DP: 双向DP!妙！
    public int[][] DP(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0)
                    ans[i][j] = 0;
                else
                    ans[i][j] = Integer.MAX_VALUE - 10;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) {
                    // ans[i][j] = Math.min(ans[i][j], Math.min(ans[i][j - 1], ans[i - 1][j]) + 1);
                    // 行不通！
                    if (i != 0)
                        ans[i][j] = Math.min(ans[i][j], ans[i - 1][j] + 1);
                    if (j != 0)
                        ans[i][j] = Math.min(ans[i][j], ans[i][j - 1] + 1);
                }
            }
        }
        // print2D(matrix);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    // ans[i][j] = Math.min(ans[i][j], Math.min(ans[i][j + 1], ans[i + 1][j]) + 1);
                    if (i != n - 1)
                        ans[i][j] = Math.min(ans[i][j], ans[i + 1][j] + 1);
                    if (j != m - 1)
                        ans[i][j] = Math.min(ans[i][j], ans[i][j + 1] + 1);
                }
            }
        }
        return ans;
    }

    // =======================NO.2=========================//

    private class Node {

        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node() {
        }

        Node add(Node node) {
            Node res = new Node();
            res.x = this.x + node.x;
            res.y = this.y + node.y;
            return res;
        }
    }

    // ===============method of NO.2=======================//
    private int[][] BFS(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] ans = new int[n][m];
        Queue<Node> q;
        Node[] dir = { new Node(1, 0), new Node(0, 1), new Node(-1, 0), new Node(0, -1) };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    ans[i][j] = 0;
                    continue;
                }
                q = new LinkedList<>();
                q.add(new Node(i, j));
                boolean found = false;
                boolean[][] marked = new boolean[n][m];// init by `false`
                while (!q.isEmpty()) {
                    if (found)
                        break;
                    Node node = q.remove();
                    Node tmp;
                    for (int k = 0; k < 4; k++) {
                        tmp = node.add(dir[k]);
                        int x = tmp.x;
                        int y = tmp.y;
                        if (isLegal(tmp, n, m) && !marked[x][y]) {
                            if (matrix[tmp.x][tmp.y] == 0) {
                                ans[i][j] = dist(i, j, x, y);
                                found = true;
                                break;
                            }
                            marked[x][y] = true;
                            q.add(tmp);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean isLegal(Node tmp, int n, int m) {
        int x = tmp.x;
        int y = tmp.y;
        if (x >= 0 && x < n && y >= 0 && y < m)
            return true;
        return false;
    }

    private int dist(int i, int j, int x, int y) {
        return Math.abs(x - i) + Math.abs(y - j);
    }

    // =======================辅助===================//
    public static void print2D(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("=================\n");
    }

    // ===========================Test==================//
    public static void main(String[] args) {
        int[][] matrix = { { 0, 1, 1 }, { 1, 1, 1 }, { 1, 1, 0 } };
        print2D(matrix);
        System.out.println();
        int[][] ans = new Matrix().updateMatrix(matrix);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("=========================\n");
        int[][] ans2 = new Matrix().DP(matrix);
        for (int i = 0; i < ans2.length; i++) {
            for (int j = 0; j < ans2[0].length; j++) {
                System.out.print(ans2[i][j] + " ");
            }
            System.out.println();
        }
    }
}