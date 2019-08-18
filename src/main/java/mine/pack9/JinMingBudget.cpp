#include <iostream>
#include <algorithm>
#include <cstring>
#include <vector>

/*

有依赖的背包问题：
某一件物品的放入背包前必须要将其所有的依赖先放入背包。

https://www.acwing.com/problem/content/description/10/
*/

using namespace std;

int main()
{
    int money, n; //总钱数和物品数
    cin >> money >> n;
    int *v = new int[n + 1]; //价格
    int *p = new int[n + 1]; //重要程度:1-5
    int *q = new int[n + 1]; //主件还是附件

    for (int i = 1; i <= n; i++)
        cin >> v[i] >> p[i] >> q[i];

    //在总钱数money一定的情况下，使每个所拿物品的价格和重要程度乘积之和最大
    //pre
    for (int i = 0; i <= n; i++)
        p[i] *= v[i]; //变成 “重要程度和价格的乘积”

    return 0;
}