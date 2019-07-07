package mine.algo.stock_transaction;

/**
 * StackTransaction
 */
public class StockTransaction {
    private int[] rev_max;

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0)
            return 0;
        rev_max = new int[len];
        int max = Integer.MIN_VALUE;
        for (int i = len - 1; i != -1; i--) {
            if (prices[i] > max)
                max = prices[i];
            rev_max[i] = max;
        }
        int max_profit = 0, tmp;
        for (int i = 0; i != len; i++) {
            tmp = rev_max[i] - prices[i];
            if (tmp > max_profit)
                max_profit = tmp;
        }
        return max_profit;
    }

    public static void main(String[] args) {
        int[] a1 = { 7, 1, 5, 3, 6, 4 };
        int[] a3 = { 1, 2 };
        StockTransaction foo = new StockTransaction();
        System.out.println(foo.maxProfit(a1));
        System.out.println(foo.maxProfit(a3));
    }
}