#include <iostream>
#include <map>

using namespace std;
int main()
{
	int nums[] = {3, 4, 4, 5, 1, 2, 3, 6};

	map<int, int> m;
	for (auto &&x : nums)
	{
		++m[x];
	}

	int k = m.cbegin()->first;

	cout << k << endl;
}