package mine.leetcode.new_21_game;

/**
 * Solution
 */
@SuppressWarnings("unused")
public class Game {
    // =============== API ==================//
    public double new21Game(int N, int K, int W) {
        this.N = N;
        this.K = K;
        this.p0 = 1. / W;
        this.W = W;
        return DP();
    }

    private double sum = 0;
    private double p0;
    private int K;
    private int N;
    private int W;

    // =============== 1 : DFS ================//
    // time limited exceeded
    /**
     * 起初这个DFS的方法也是走了不少弯路……总是打算使dfs直接给出结果，不行的！ 你得遵循着真正计算的过程！ 对(N=21,K=17,W=10)来说，
     */
    public void dfs(int cur_count, double cur_p) {
        if (cur_count >= K) {
            if (cur_count <= N)
                sum += cur_p;
            return;
        }
        for (int i = 1; i != W + 1; i++) {
            dfs(cur_count + i, cur_p * p0);
        }
    }

    // ATTENTION : DP : 从递归表达式入手，不要想着直接dp的返回值就是结果！
    private double[] dp;

    /*
     * 如何想到这个dp方法？ dp[i]的意思是什么？ dp[i]应该模仿第一种DFS的方法。 所谓的带备忘的从上到下的方法
     * 遍历DFS所在的树的时候，从顶部开始； 一开始的想法总是从dp[N]开始，比如dp[21]=1/W*(Sum dp[j] from j = 21-W to
     * 20) 但是就拿(N=21,K=17,W=10)这个例子来说吧：(不妨令(N,K,W)这个三元组来表示问题的规模) 首先：dp[i]=0 forall
     * i<17，因为对于小于K=17的N来说，要想积攒够17，必然已经超过了N 其次：如何计算dp[17]？想一想，如果它使用dp[17]=1/W*(Sum
     * dp[j] from j = 17-W=7 to 16) 那么就只能得出dp[17]=0;
     * 这么想是不行的，想要以dp[i]来表示N=i时(i,K,W)这个子问题的直接答案是不行的，而且没有模仿DFS的流程。
     * 如果非要这么表示的话，我想这样或许可以：
     * dp[i][j]表示N=i,K=j是的问题答案，那么递推关系应该是：dp[i][j]=1/W*(sum(dp[i-k][j-k],where k in
     * [1,W])) 这样的话怎么初始化呢？TODO <p> 回归问题！！！！
     * 
     */
    public double DP() {
        dp = new double[K + W];
        for (int i = K; i != N + 1; i++)
            dp[i] = 1;
        double sum;
        for (int i = K - 1; i != -1; i--) {
            sum = 0;
            for (int j = 0; j != W; j++)
                sum += dp[i + j + 1];
            dp[i] = sum * p0;
        }
        return dp[0];
    }

    public double DP_improved() {
        double[] dp = new double[K + W + 1];
        for (int i = K; i != N + 1; i++)
            dp[i] = 1;
        double Sum = 0;// init:sum from K to K + W
        for (int i = K; i != K + W + 1; i++)
            Sum += dp[i];
        for (int i = K - 1; i != -1; i--) {
            dp[i] = Sum * p0;
            Sum = Sum - dp[i + W] + dp[i];
        }
        return dp[0];
    }

    /**
     * dp[i][j]表示N=i,K=j是的问题答案，那么递推关系应该是：dp[i][j]=1/W*(sum(dp[i-k][j-k],where k in
     * [1,W])) 这样的话怎么初始化呢？TODO
     * <p>
     * 不行！！！！！！！！！
     */
    private double DP2() {
        double[][] dp = new double[N + 1][K];
        for (int i = 0; i != N + 1; i++) {
            for (int j = 0; j != K; j++) {

            }
        }
        return dp[N][K];
    }

    public static void main(String[] args) {
        Game t = new Game();
        double start = System.currentTimeMillis();
        t.new21Game(9807, 9717, 9057);
        double end = System.currentTimeMillis();

        start = System.currentTimeMillis();
        System.out.println(t.DP());
        end = System.currentTimeMillis();
        System.out.print("Duration : " + (end - start) + " ms\n");

        start = System.currentTimeMillis();
        System.out.println(t.DP_improved());
        end = System.currentTimeMillis();
        System.out.print("Duration : " + (end - start) + " ms\n");
    }
}