#include <iostream>
#include <vector>
#include <bits/stdc++.h>

using namespace std;

/**
 * 在第i天可以选择买，卖，等；
 * 设计两个函数分别表示在第i天买/卖/等这三种选择所能获得的最大利益。
 * 
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/119402/Recurse-and-memoize-solution
*/
class Solution
{
public:
    vector<int> buyMemo, sellMemo;

    int recurseBuyNext(int day, const vector<int> &prices)
    {
        if (day >= prices.size())
            return 0;

        int &memoIter = buyMemo[day];
        if (memoIter != INT_MIN)
            return memoIter;

        // lets try buying stock today
        int possA = recurseSellNext(day + 1, prices) - prices[day];

        // lets trying buying stock in the future
        int possB = recurseBuyNext(day + 1, prices);
        return memoIter = max(possA, possB);
    }

    int recurseSellNext(int day, const vector<int> &prices)
    {
        if (day >= prices.size())
            return 0;

        int &memoIter = sellMemo[day];
        if (memoIter != INT_MIN)
            return memoIter;

        // lets try selling today
        int possA = recurseBuyNext(day + 2, prices) + prices[day];

        // lets try selling in the future
        int possB = recurseSellNext(day + 1, prices);

        return memoIter = max(possA, possB);
    }

    int maxProfit(vector<int> &prices)
    {
        buyMemo = vector<int>(prices.size(), INT_MIN);
        sellMemo = buyMemo; //it's not the same address as `buyMemo`

        //Clearly I don't own any stock, so my first move is to buy some.
        return recurseBuyNext(0, prices);
    }
};

int main()
{
    vector<int> prices({1, 2, 3, 0, 2});
    Solution t;
    cout << t.maxProfit(prices) << endl;
    return 0;
}