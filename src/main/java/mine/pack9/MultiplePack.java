package mine.pack9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MultiplePack 使用简单的2^kv[i]还不够好
 * 
 * 利用单调优先队列优化
 */
public class MultiplePack {

    private int[] v;
    private int[] p;
    private int[] s;
    private int N;
    private int V;

    public MultiplePack(int[] v, int[] p, int[] s, int V) {
        this.v = v;
        this.p = p;
        this.s = s;
        this.N = v.length;
        this.V = V;
    }

    /**
     * 使用2^kv[i]的方法
     */
    public int naive() {
        ArrayList<Integer> v2 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        // pre processing
        int pi, vi, si, k;
        for (int i = 0; i < N; i++) {
            vi = v[i];
            pi = p[i];
            si = s[i] + 1;// 见背包九讲
            k = 1;
            while ((k << 1) < si && vi <= V) {
                v2.add(vi);
                p2.add(pi);
                vi <<= 1;
                pi <<= 1;
                k <<= 1;
            }
            k = si - k;
            v2.add(k * v[i]);
            p2.add(k * p[i]);
        }

        int[] dp = new int[V + 1];
        int size = v2.size();
        int min;
        for (int i = 0; i < size; i++) {
            min = v2.get(i);
            for (int j = V; j >= min; j--)
                dp[j] = Math.max(dp[j], dp[j - min] + p2.get(i));
        }

        return dp[V];
    }

    public static void main(String[] args) {

        int N, V;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        V = scanner.nextInt();

        int v[] = new int[N];// 体积
        int p[] = new int[N];// 价格
        int s[] = new int[N];// 第i种物品的数量

        for (int i = 0; i < N; i++) {
            v[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
            s[i] = scanner.nextInt();
        }

        MultiplePack t = new MultiplePack(v, p, s, V);

        long start = System.currentTimeMillis();
        System.out.println(t.naive());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        scanner.close();
    }
}
/*
 * test date: 100 100 56 96 76 87 17 65 1 87 78 34 82 85 36 98 66 87 92 91 34 68
 * 68 52 45 71 94 27 88 15 35 82 56 92 79 91 64 90 85 27 80 67 68 79 38 46 88 96
 * 73 86 29 78 53 87 72 69 49 80 61 32 21 94 10 56 65 91 64 67 78 66 91 64 19 75
 * 61 59 95 35 22 70 6 51 93 56 18 73 41 17 89 34 56 51 7 6 97 49 51 59 95 65 85
 * 51 5 69 94 84 87 18 59 83 87 41 80 95 4 60 35 18 73 62 60 52 81 12 60 88 10
 * 80 16 66 90 99 51 60 1 79 97 73 62 79 59 55 77 90 89 62 36 37 99 82 86 56 90
 * 49 76 73 83 97 65 36 74 39 60 92 23 67 69 31 56 81 44 49 100 38 63 79 45 80
 * 63 5 63 89 53 36 63 84 23 72 10 38 81 71 66 52 18 59 79 31 30 77 77 34 73 19
 * 8 66 66 20 73 44 16 64 28 51 56 6 22 81 97 72 82 90 44 75 3 71 91 15 95 99 18
 * 38 83 2 20 88 55 34 99 88 45 58 56 50 78 62 93 55 36 98 80 76 72 98 29 25 90
 * 58 37 95 84 66 76 55 92 70 40 23 54 88 41 59 90 1 75 79 85 88 96 6 58 69 13
 * 54 47 21 96 73 62 65 61 72 60 85 49 85 68 74 77 55 82 58
 * 
 * hope res: 8524
 */