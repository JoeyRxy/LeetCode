#include <iostream>
#include <algorithm>
#include <cstring>
#include <vector>

using namespace std;

/**
 * 转化成0-1背包问题；
 * 每件物品根据重量可以生成多个物品：
 * v[i],2v[i],...,2^kv[i]
 * 
 * test data:
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 5
 * output:
 * 10
 */
int main()
{
    int N, V; //物品个数和背包体积
    cin >> N >> V;

    int *v = new int[N];
    int *p = new int[N];
    for (int i = 0; i < N; i++)
        cin >> v[i] >> p[i];

    vector<int> v2;
    vector<int> p2;

    //预处理：对于每个物品，生成(2^kv[i],2^kp[i])
    int Vm, Pm;
    for (int i = 0; i < N; i++)
    {
        Vm = v[i];
        Pm = p[i];
        while (Vm <= V)
        {
            v2.push_back(Vm);
            p2.push_back(Pm);
            Vm <<= 1;
            Pm <<= 1;
        }
    }

    int size = v2.size();
    int *dp = new int[V + 1];
    for (int i = 0; i <= /*以前填的是size，比V大，使用了超过所分配的内存的空间。不过为什么不在这里报错而在delete的时候报错？*/ V; i++)
        dp[i] = 0;

    for (int i = 0; i < size; i++)
        for (int j = V; j >= v2[i]; j--)
            dp[j] = max(dp[j], dp[j - v2[i]] + p2[i]);

    cout << dp[V] << endl;

    delete[] v;
    delete[] p;
    delete[] dp; //FIXME:为什么出现了错误：Breakpoint trap：答案：见52行
    system("pause");
    return 0;
}