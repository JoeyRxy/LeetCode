#include <bits/stdc++.h>
using namespace std;
int n,f[1<<20][21],i,j,k;
int weight[21][21];
int main()
{
    ios::sync_with_stdio(false);//加快cin的读入速度，但是scanf将会不能用。
    memset(f,0x3f,sizeof(f));//初始化最大值
    cin>>n;
    for (i=0; i<n; i++)
        for (j=0; j<n; j++)
            cin>>weight[i][j];
    f[1][0]=0;//第一个点是不需要任何费用的
    for (i=1; i<(1<<n); i++)//i代表着是一个方案集合，其中每一个位置1和0，代表着这个点经过还是没有经过
        for (j=0; j<n; j++)//枚举当前到了哪一个点
            if ((i>>j & 1))//如果i集合中第j位是1，也就是到达过这个点
                for (k=0; k<n; k++)//枚举到达j的点k
                    if ((i^(1<<j)) >> k & 1)//重点，判断k和j的条件，具体在上面解说
                        f[i][j]=min(f[i][j],f[i^(1<<j)][k]+weight[k][j]);//选择最小值，也就是判断，k点到j点最优，还是以前的方案最优
    cout<<f[(1<<n)-1][n-1];//输出最后的最优值
    return 0;
}

作者：秦淮岸灯火阑珊
链接：https://www.acwing.com/solution/acwing/content/789/
来源：AcWing
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。