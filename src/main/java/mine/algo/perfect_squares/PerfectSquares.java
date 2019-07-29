package mine.algo.perfect_squares;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * PerfectSquares
 */
public class PerfectSquares {
    private int[] dp, ans;
    private int n;

    public int numSquares(int n) {
        dp = new int[n + 1];
        ans = new int[n + 1];
        this.n = n;
        dp[0] = 0;
        int j, j2;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            j = 1;
            j2 = 1;
            while (j2 <= i) {
                if (dp[i] < dp[i - j2] + 1) {
                    ans[i] = i;
                } else {
                    ans[i] = i - j2;
                    dp[i] = dp[i - j2] + 1;
                }
                j++;
                j2 = j * j;
            }
        }
        return dp[n];
    }

    public void printAns() {
        System.out.print(n + " = ");
        int i = n;
        System.out.print(i - ans[i]);
        i = ans[i];
        for (; i > 0; i = ans[i]) {
            System.out.print(" + " + (i - ans[i]));
        }
        System.out.println();
    }

    public int BFS(int n) {
        // pre
        int[] f = new int[n + 1];
        int k = 0, i = 0, thresh;
        while (true) {
            thresh = (k + 1) * (k + 1);
            for (; i < thresh; i++) {
                f[i] = k;
                if (i == n)
                    break;
            }
            k++;
            if (i == n)
                break;
        }
        f[n] = k;
        // begin
        // 关于重复的数字，就不必再入列了，因为陷进去的那个一定有更短的路径
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        q.add(n);
        map.put(n, 1);
        int x;
        while (true) {
            n = q.remove();
            for (int j = f[n]; j != 0; j--) {
                x = n - j * j;
                if (x == 0) {
                    return map.get(n);
                }
                if (!map.containsKey(x)) {
                    q.add(x);
                    map.put(x, map.get(n) + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        PerfectSquares t = new PerfectSquares();
        System.out.println(t.BFS(10));
    }
}