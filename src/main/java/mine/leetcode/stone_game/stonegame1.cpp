#include <vector>
#include <iostream>
using namespace std;
class StoneGame1
{
private:
    /**
     * p1 : first one's max stones;
     * p2 : second one's max stones;
     */
    bool helper(vector<int> &piles, int start, int end, int &p1, int &p2)
    {
        if (start == end)
            return p1 > p2 ? true : false;

        int sum1, sum2;
        if ((start + end) / 2 == 0) //?
        {
            sum1 = p2 + piles[start];
            sum2 = p2 + piles[end];
            return helper(piles, start + 1, end, p1, sum1) ||
                   helper(piles, start, end - 1, p1, sum2);
        }
        else
        {
            sum1 = p1 + piles[start];
            sum2 = p1 + piles[end];
            return helper(piles, start + 1, end, sum1, p2) ||
                   helper(piles, start, end - 1, sum2, p2);
        }
    }

public:
    bool stoneGame(vector<int> &piles)
    {
        int p1, p2;
        p1 = p2 = 0;
        return helper(piles, 0, piles.size() - 1, p1, p2);
    }
};

int main(int argc, char const *argv[])
{
    StoneGame1 t;
    vector<int> piles = {3, 2, 10, 4};
    bool e = t.stoneGame(piles);
    cout << e << endl;
    return 0;
}
