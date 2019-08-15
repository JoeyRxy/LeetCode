#include <iostream>
using namespace std;
int N;
bool *marked;
int *edgeTo; //当前节点所指向的下一个节点
int main()
{
	cout << "Enter N for permutation of 1...N:\n";
	cin >> N;
	marked = new bool[N + 1];
	edgeTo = new int[N + 1];
	for (int i = 0; i < N + 1; ++i)
	{
		marked[i] = false;
		edgeTo[i] = 0;
	}
	void dfs(int, int);
	dfs(0, N);
	system("pause");
}
void dfs(int v, int n)
{
	marked[v] = true;
	if (n == 0)
	{
		for (int i = 0; i != v; i = edgeTo[i])
			printf("%d", edgeTo[i]);
		putchar('\n');
		return;
	}
	for (int i = 1; i <= N; ++i)
		if (!marked[i])
		{
			edgeTo[v] = i;
			dfs(i, n - 1);
			marked[i] = false;
		}
}