# https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75949/Python-recursive-with-memoization

class Solution():

    def maxProfit(self, prices):
        self.prices = prices
        self.mem = {}       
        return self.maxp(i=0, s='flat')

    def maxp(self,i, s):
        """Return the max profit you can get starting on day i with state s."""
        if i >= len(prices): return 0
        if (i, s) not in self.mem:
            if s == 'flat':
                self.mem[(i, s)] = max(
                    self.maxp(i+1, 'long'),          # buy
                    self.maxp(i+1, 'flat'))          # don't buy
            elif s == 'long':
                p = prices[i] - prices[i-1]
                self.mem[(i, s)] = p + max(
                    self.maxp(i+1, 'cool'),          # sell
                    self.maxp(i+1, 'long'))          # don't sell
            else: # 'cool'
                self.mem[(i, s)] = self.maxp(i+1, 'flat') # don't buy
        return self.mem[(i, s)]


if __name__ == "__main__":
    prices = [ 1, 2, 3, 0, 2]
    t = Solution()
    print(t.maxProfit(prices))