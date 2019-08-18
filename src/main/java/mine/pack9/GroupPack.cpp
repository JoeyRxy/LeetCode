#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;

int main()
{
    int N, V;
    cin >> N >> V;
    int *s = new int[N];    //每组的数量
    int **v = new int *[N]; //体积
    int **w = new int *[N]; //价值
    int tmp;
    for (int i = 0; i < N; i++)
    {
        cin >> s[i];
        tmp = s[i];
        v[i] = new int[tmp];
        w[i] = new int[tmp];

        for (int j = 0; j < tmp; j++)
            cin >> v[i][j] >> w[i][j];
    }

    /**
     * dp[i][j]面对第i组百宝容量为j的时候，所能获得的最大价值；
     * 
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - v[i][k]] + w[i][k]),k = 0,...,s[i] - 1
     * 由于每一组的v[i][]内没有大小关系，我认为不能使用一维数组代替吧？
     * 实际上也可以
     */
    int **dp = new int *[N];
    for (int i = 0; i < N; i++)
    {
        dp[i] = new int[V + 1]();
    }

    int Max;
    tmp = s[0];
    for (int j = 1; j <= V; j++)
    {
        Max = 0;
        for (int k = 0; k < tmp; k++)
            if (Max < w[0][k] && v[0][k] <= j)
                Max = w[0][k];
        dp[0][j] = Max;
    }

    int *dpt, *vt, *wt;

    for (int i = 1; i < N; i++)
    {
        tmp = s[i];
        dpt = dp[i - 1];
        vt = v[i];
        wt = w[i];
        for (int j = 1; j <= V; j++)
        {
            Max = dpt[j];
            for (int k = 0; k < tmp; k++)
                if (j >= vt[k] && dpt[j - vt[k]] + wt[k] > Max)
                    Max = dpt[j - vt[k]] + wt[k];
            dp[i][j] = Max;
        }
    }

    cout << dp[N - 1][V] << endl;

    system("pause");
    return 0;
}