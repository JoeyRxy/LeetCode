package mine.suanfajingsaijinjiezhinan.sec0;

import java.util.Scanner;

public class ShotestHamilton {
    private static int[][] memo;
    private static int[][] g;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        g = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                g[i][j] = scanner.nextInt();
        scanner.close();
        memo = new int[N][1 << (N - 1)];
        int S = (1 << (N - 1)) - 2;
        dfs(0, S);
        System.out.println(memo[0][S]);
    }

    /**
     * 
     * @param v cur vertex
     * @param S unvis set right now
     */
    static void dfs(int v, int S) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < memo.length; i++) {
            int t = 1 << i;
            if ((t & S) != 0) {
                int _S = S & (~t);
                if (memo[i][_S] == 0)
                    dfs(i, _S);
                min = Math.min(min, g[v][i] + memo[i][_S]);
            }
        }
        memo[v][S] = min;
    }

}