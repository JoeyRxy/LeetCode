/**
 * 利用单调队列的终极优化版
 */
#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;

const int N = 20010;

int n, m;
int f[N], g[N], q[N];

int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        int c, w, s;
        cin >> c >> w >> s;
        memcpy(g, f, sizeof f); //siziof??

        for (int j = 0; j < c; j++)
        {
            int hh = 0, tt = -1;
            for (int k = 0; k <= m; k += c)
            {
                f[k] = g[k];
                if (hh <= tt && k - s * c > q[hh])
                    hh++;
                if (hh <= tt)
                    f[k] = max(f[k], g[q[hh]] + (k - q[hh]) / c * w);
                while (hh <= tt && g[q[tt]] - (q[tt] - j) / c * w <= g[k] - (k - j) / c * w)
                    tt--;
            }
        }
    }

    cout << f[m] << endl;
    system("pause");
    return 0;
}
