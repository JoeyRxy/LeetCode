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
    private Node node;

    public int numSquares(int n) {
        dp = new int[n + 1];
        ans = new int[n + 1];
        this.n = n;
        dp[0] = 0;
        int j, j2;
        for (int i = 1; i <= n; i++) {
            j = 1;
            j2 = 1;
            int min = Integer.MAX_VALUE, idx = 0;
            while (j2 <= i) {
                if (min > dp[i - j2]) {
                    min = dp[i - j2];
                    idx = i - j2;
                }
                j++;
                j2 = j * j;
            }
            dp[i] = min + 1;
            ans[i] = idx;
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

        // int k = 0, i = 0, thresh;
        // while (true) {
        // thresh = (k + 1) * (k + 1);
        // for (; i < thresh; i++) {
        // f[i] = k;
        // if (i == n)
        // break;
        // }
        // k++;
        // if (i == n)
        // break;
        // }
        // f[n] = k;

        int i = 0, i2 = 1, k = 0;
        while (k != n) {
            f[k++] = i;
            if (i2 == k) {
                i++;
                i2 = (i + 1) * (i + 1);
            }
        }
        f[k] = i;
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

    /**
     * Node
     */
    public class Node {

        Node parentNode;
        int val;
        int depth;

        public Node(Node parentNode, int val, int depth) {
            this.parentNode = parentNode;
            this.val = val;
            this.depth = depth;
        }

    }

    public int BFSwithNode(int n) {
        this.n = n;
        int[] f = new int[n + 1];
        int i = 0, i2 = 1, k = 0;
        while (k != n) {
            f[k++] = i;
            if (i2 == k) {
                i++;
                i2 = (i + 1) * (i + 1);
            }
        }
        f[k] = i;

        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.add(new Node(null, n, 1));
        visited[n] = true;
        int x;
        while (true) {
            node = q.remove();
            n = node.val;
            for (int j = f[n]; j > 0; j--) {
                x = n - j * j;
                if (x == 0) {
                    return node.depth;
                }
                if (!visited[x]) {
                    q.add(new Node(node, x, node.depth + 1));
                    visited[x] = true;
                }
            }
        }
    }

    public void printAnsNode() {
        int tmp = node.val;
        System.out.print(n + " = " + tmp);
        node = node.parentNode;
        while (node != null) {
            System.out.print(" + " + (node.val - tmp));
            tmp = node.val;
            node = node.parentNode;
        }
    }

    public static void main(String[] args) {
        PerfectSquares t = new PerfectSquares();
        long start = System.currentTimeMillis();
        System.out.println(t.numSquares(12345678));
        long end = System.currentTimeMillis();
        System.out.println("duration : " + (end - start));
        t.printAns();
        System.out.println();
        start = System.currentTimeMillis();
        System.out.println(t.BFSwithNode(12345678));
        end = System.currentTimeMillis();
        System.out.println("duration : " + (end - start));
        t.printAnsNode();
        System.out.println();

    }
}