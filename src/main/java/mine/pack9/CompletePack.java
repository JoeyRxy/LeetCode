package mine.pack9;

/**
 * CompletePack
 * 
 * https://www.acwing.com/problem/content/6/
 * 
 * test data: 4 5 1 2 3 2 4 1 3 4 3 4 5 2 expected output:10
 */
public class CompletePack {

    // public static void main(String[] args) {
    // int N, V;
    // var scanner = new Scanner(System.in);
    // N = scanner.nextInt();
    // V = scanner.nextInt();
    // int v[] = new int[N];
    // int p[] = new int[N];
    // for (int i = 0; i < N; i++) {
    // v[i] = scanner.nextInt();
    // p[i] = scanner.nextInt();
    // }

    // ArrayList<Integer> v2, p2;
    // v2 = new ArrayList<>();
    // p2 = new ArrayList<>();

    // int Vt, Pt;
    // for (int i = 0; i < N; i++) {
    // Vt = v[i];
    // Pt = p[i];
    // while (Vt < V) {
    // v2.add(Vt);
    // p2.add(Pt);
    // Vt <<= 1;
    // Pt <<= 1;
    // }
    // }

    // int size = v2.size();
    // // Begin cal
    // int dp[] = new int[V + 1];
    // int idx;
    // for (int i = 0; i < size; i++) {
    // idx = v2.get(i);
    // for (int j = V; j >= idx; j--)
    // dp[j] = Math.max(dp[j], dp[j - idx] + p2.get(i));
    // }
    // System.out.println(dp[V]);
    // scanner.close();
    // }

    /**
     * FAV完全背包再认识
     * 设：f[i, j]表示容量为j，前0...i个物品
     * <p>
     * 原始方程： f[i, j] = max{f[i - 1, j - k * v[i]] + k * p[i] | 0<= k * v[i] <= j};
     * <p>
     * 牛的来了：dp中还可以用dp，因为本身就有重叠子问题！
     * <p>
     * f[i, j - v[i]] = max{f[i - 1, j - v[i] - k' * v[i]] + k' * p[i] | k = 0 ....}
     * <p>
     * 因此：f[i, j] = max{f[i - 1, j], max{f[i - 1, j - v[i] - k * v[i]] + p[i] + k *
     * p[i] | k = 0 ...}}
     * <p>
     * = max{f[i - 1, j], f[i, j - v[i]] + p[i]}
     * <p>
     * 进一步的，可以优化空间。
     * 
     * @param V 背包容量
     * @param v v[i]表示第i个物品的体积
     * @param p p[i]表示第i个物品的价格
     * @return
     */
    public int dp(int V, int[] v, int[] p) {
        int len = v.length;
        if (len != p.length)
            throw new IllegalArgumentException();
        int[] f = new int[V + 1];
        for (int i = 1; i < len; i++) {
            for (int j = v[i]; j <= V; j++) {
                f[j] = Math.max(f[j], f[j - v[i]] + p[i]);
            }
        }
        return f[V];
    }

    public static void main(String[] args) {
        int V = 17;
        int[] p = { 2, 3, 5, 8 };
        int[] v = { 3, 4, 6, 9 };

    }
}