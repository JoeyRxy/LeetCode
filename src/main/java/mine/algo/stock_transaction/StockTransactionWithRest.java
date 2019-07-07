package mine.algo.stock_transaction;

/**
 * StockTransactionWithRest
 * <p>
 * <a href=
 * "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking)">Genius
 * FSM with DP</a>
 */
public class StockTransactionWithRest {

    public int maxProfit(int[] prices) {
        int buy = 0, sell = 0, rest = 0, rest_prev = 0;
        int i = 1;
        while (i != prices.length) {
            sell = Math.max(sell + prices[i], buy);
            buy = Math.max(buy, rest_prev - prices[i]);
            rest_prev = rest;
            rest = sell;
            i++;
        }
        return sell;
    }

    public int FSMwithDP(int[] prices) {
        // three state : 1st: rested; 2nd : bought; 3rd : sold
        int len = prices.length;
        // TODO : how to explain the meaning of these DP arrays?
        int[] rested = new int[len];// rested[i] means
        int[] bought = new int[len];// bought[i] means
        int[] sold = new int[len];// sold[i] means
        // initialize state
        rested[0] = 0;
        bought[0] = -prices[0];
        sold[0] = Integer.MIN_VALUE;
        for (int i = 1; i != len; i++) {
            rested[i] = Math.max(rested[i - 1], sold[i - 1]);
            bought[i] = Math.max(rested[i - 1] - prices[i], bought[i - 1]);
            sold[i] = bought[i - 1] + prices[i];
        }
        return Math.max(sold[len - 1], rested[len - 1]);
    }

    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 0, 2 };
        StockTransactionWithRest t = new StockTransactionWithRest();
        System.out.println(t.FSMwithDP(prices));
    }
}