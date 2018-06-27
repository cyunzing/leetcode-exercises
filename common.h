#ifndef COMMON
#define COMMON

#include <map>
#include <vector>
#include <set>
#include <unordered_set>
#include <unordered_map>
#include <stack>
#include <queue>
#include <algorithm>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

struct Interval {
    int start;
    int end;
    Interval() : start(0), end(0) {}
    Interval(int s, int e) : start(s), end(e) {}
};

#endif // COMMON

