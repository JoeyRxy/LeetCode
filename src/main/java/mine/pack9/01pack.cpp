#include <iostream>
#include <algorithm>

using namespace std;

const int n = 10, m = 40; //物品数量，背包容量
int v[] = {2, 3, 9, 6, 5, 14, 10, 19, 17, 1};
int w[] = {5, 7, 14, 9, 8, 20, 16, 27, 24, 2};
int f[n][m + 1] = {0};
int ans[n][m + 1] = {0};

int getAns(int);
int dp1dim(int, int, int *, int *);

/**
 * 
 * 0-1 pack:
 * f[i][j] : 面对前i个物体和可以容纳j重量的背包，可以获得的最大价值。
 * f[i][j] = max(f[i - 1][j], v[i] + f[i - 1][j - w[i]]);
 */
int main()
{
    for (int j = w[0]; j <= m; j++)
    {
        f[0][j] = v[0];
    }

    for (int i = 1; i < n; i++)
        for (int j = 0; j <= m; j++)
        {
            f[i][j] = f[i - 1][j];
            ans[i][j] = j;
            if (j >= w[i])
            {
                int tmp = f[i - 1][j - w[i]] + v[i];
                if (f[i][j] < tmp)
                {
                    f[i][j] = tmp;
                    ans[i][j] = j - w[i];
                }
            }
        }

    for (int j = 0; j <= m; j++)
        cout << j << " : " << f[n - 1][j] << "$$";

    cout << endl
         << f[n - 1][m] << endl;

    //输出结果
    int i = n - 1, prev = m;
    int j = ans[i][prev], t;
    while (i != 0)
    {
        prev = j;
        j = ans[i][prev];
        if (prev > j)
        {
            t = prev - j;
            cout << t << " : " << getAns(t) << endl;
        }
        i--;
    }

    cout << endl
         << endl;

    int res = dp1dim(n, m, v, w);
    cout << res << endl;
    return 0;
}

int getAns(int t)
{
    int i;
    for (i = 0; i < n; i++)
        if (w[i] == t)
            break;

    return v[i];
}

/**
 * 观察上文的f[][]的遍历方式，发现每次更新都需要之前的一行，而不是全部，
 * 所以我们只要以为数组就可以了。
 * 但是要保证在更新的时候，右值（dp[j],dp[j-w[i]]）是之前的值，所以j的遍历要从大到小遍历
 */
int dp1dim(int n, int m, int *v, int *w)
{
    int dp[m + 1] = {0};
    for (int i = 0; i < n; i++)
        for (int j = m; j >= w[i]; j--)
            dp[j] = max(dp[j], dp[j - w[i]] + v[i]);

    return dp[m];
}