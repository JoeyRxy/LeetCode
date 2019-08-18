#include <iostream>
int main(int argc, char const *argv[])
{
    int n = 7;
    int *f = new int[n];
    int k = 0;
    while (true)
    {
        for (int i = k * k; i < (k + 1) * (k + 1); i++)
        {
            f[i] = k;
            if (i == n)
                break;
        }
    }

    return 0;
}
