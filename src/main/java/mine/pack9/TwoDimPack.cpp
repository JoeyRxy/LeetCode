#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;

int N, V, M; //物品数量，背包体积，背包最大重量

int main()
{
    cin >> N >> V >> M;
    int *v = new int[N]; //体积
    int *m = new int[N]; //重量
    int *w = new int[N]; //价值

    for (int i = 0; i < N; i++)
    {
        cin >> v[i] >> m[i] >> w[i];
    }

    /**
     * dp[i][v][m] = max(dp[i - 1][v][m], dp[i - 1][v - v[i]][m - m[i]] + w[i]);
     * 
     * 本来用三维数组，但是鉴于对第i个物品的处理只需要考虑对第i - 1个物品判断，所以二维数组就够了;
     */
    int **dp = new int *[V + 1];
    for (int i = 0; i <= V; i++)
        dp[i] = new int[M + 1];

    //init dp
    for (int i = 0; i <= V; i++)
        for (int j = 0; j <= M; j++)
            dp[i][j] = 0;

    for (int i = 0; i < N; i++)
        for (int j = V; j >= v[i]; j--)
            for (int k = M; k >= m[i]; k--)
                dp[j][k] = max(dp[j][k], dp[j - v[i]][k - m[i]] + w[i]);

    cout << dp[V][M] << endl;

    //后续处理
    for (int i = 0; i <= V; i++)
        delete[] dp[i];
    delete[] dp;

    system("pause");
    return 0;
}