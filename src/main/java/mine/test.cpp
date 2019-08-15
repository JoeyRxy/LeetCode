#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;

int main()
{
    int N = 10;
    int *a = new int[N + 1];
    int *b = new int[N + 1];
    for (int i = 0; i <= N; i++)
    {
        a[i] = rand() * 100;
        b[i] = rand() * rand();
    }
    cout << a[N] << endl;
    delete[] b;
    delete[] a;
    system("pause");
    return 0;
}