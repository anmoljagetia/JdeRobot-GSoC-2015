#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <iostream>
#include <stack>
#include <utility>



using namespace std;

void inputFromAFileThatCanALsoUsoDefaultIO() {
	#ifndef READ_FROM_FILE_IF_AVAILABLE_ELSE_READ_FROM_CONSOLE
	freopen("in.txt","r",stdin);
	#endif
}

int a[1000][1000] = {0};
int pathLength = -1;
stack<pair<int, int> > path;
stack<pair<int, int> > stk;

// The maximum size is assumed to be 1000 * 1000
// Although this algorithm can be easily extended to use upto INT_MAX * INT_MAX

int visitedArray[1000][1000];
int cnt = 0;

void calculatePath(int i, int j, int l) {
	//cout << a[i][j] << endl;
	if (visitedArray[i][j] == 1) return;
	visitedArray[i][j] = 1;
	if (i < 0 || i >= 100) return;
	if (j < 0 || j >= 100) return;
	if (a[i][j] != 46) return;

	l = l + 1;
	stk.push(make_pair(i, j));
	if (l > pathLength) {
		pathLength = l;
		while (!path.empty()) path.pop();
		path = stk;
	}
	calculatePath(i, j + 1, l);
	calculatePath(i, j - 1, l);
	calculatePath(i + 1, j, l);
	calculatePath(i - 1, j, l);

	stk.pop();
	return;
}

int main(int argc, char const *argv[])
{
	inputFromAFileThatCanALsoUsoDefaultIO();

	string s;
	int i = 0;
	
	while(!cin.eof()) {
		cin >> s;
		cnt++;
		for (int j = 0; j < s.length(); ++j) {
			a[i][j] = s[j];
		}
		i++;
	}

	for (int x = 0; x < i; ++x) {
		for (int y = 0; y < s.length(); ++y) {
			memset(visitedArray, 0, sizeof visitedArray);
			calculatePath(x, y, 0);
		}
	}

	int t = pathLength;

	while (!path.empty()) {
		int k = path.top().first;
		int l = path.top().second;
		path.pop();
		a[k][l] = '0' + t--;
	}

	cout << pathLength << endl;

	if (pathLength != -1) {
		for (int x = 0; x < cnt; ++x) {
			for (int y = 0; y < s.length(); ++y) {
				printf("%c", a[x][y]);
			}
			cout << endl;
		}
	}
	return 0;
}