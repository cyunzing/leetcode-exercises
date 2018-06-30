#ifndef LEETCODE100_H
#define LEETCODE100_H

#include "common.h"

//51. N-Queens
vector<vector<string>> solveNQueens(int n)
{

}

//53. Maximum Subarray
int maxSubArray(vector<int>& nums)
{
    int res = nums[0];
    int sum = 0;
    for (int i = 0; i < nums.size(); ++i) {
        sum += nums[i];
        res = max(res, sum);
        sum = max(sum, 0);
    }
    return res;
}

//54. Spiral Matrix
vector<int> spiralOrder(vector<vector<int>>& matrix)
{
    vector<int> res;
    int m = matrix.size();
    if (m == 0)
        return res;

    int n = matrix[0].size();
    int rb = 0, re = m - 1;
    int cb = 0, ce = n - 1;

    while (rb <= re && cb <= ce) {
        for (int j = cb; j <= ce; ++j) {
            res.push_back(matrix[rb][j]);
        }
        rb++;
        for (int j = rb; j <= re; ++j) {
            res.push_back(matrix[j][ce]);
        }
        ce--;

        if (rb <= re)
            for (int j = ce; j >= cb; --j)
                res.push_back(matrix[re][j]);
        re--;

        if (cb <= ce)
            for (int j = re; j >= rb; --j)
                res.push_back(matrix[j][cb]);
        cb++;
    }
    return res;
}

//55. Jump Game
bool canJump(vector<int>& nums)
{
    int n = nums.size();
#if 1
    int reach = 0;
    for (int i = 0; i < n && i <= reach; ++i) {
        reach = max(reach, nums[i] + i);
    }
    return i == n;
#elif
    if (n < 2)
        return true;

    int curMax = 0, nextMax = 0, i = 0;
    while (curMax - i + 1 > 0) {
        for (; i <= curMax; ++i) {
            nextMax = max(nums[i] + i, nextMax);
            if (nextMax >= n - 1)
                return true;
        }
        curMax = nextMax;
    }
    return false;
#endif
}

//56. Merge Intervals
bool intervalCmp(const Interval &l, const Interval &r)
{
    return l.start < r.start;
}

vector<Interval> merge(vector<Interval>& intervals)
{
    sort(intervals.begin(), intervals.end(), intervalCmp);

    vector<Interval> res;
    int n = intervals.size();
    for (int i = 1; i < n; ++i) {
        if (intervals[i].start <= intervals[i - 1].end) {
            Interval tmp;
            tmp.start = min(intervals[i - 1].start, intervals[i].start);
            tmp.end = max(intervals[i - 1].end, intervals[i].end);
            intervals[i] = tmp;
        } else {
            res.push_back(intervals[i - 1]);
        }
    }
    if (n > 0)
        res.push_back(intervals[n - 1]);
    return res;
}

//57. Insert Interval
vector<Interval> insert(vector<Interval>& intervals, Interval newInterval)
{
    intervals.push_back(newInterval);

    return merge(intervals);
}

//58. Length of Last Word
int lengthOfLastWord(string s)
{
    int result = 0;
    for (int i = s.size() - 1; i >= 0; --i) {
        if (s[i] != ' ')
            result++;
        else if (result > 0)
            break;
    }
    return result;
}


//59. Spiral Matrix II
vector<vector<int>> generateMatrix(int n)
{
    vector<vector<int>> res(n, vector<int>(n));
    int rb = 0, re = n - 1;
    int cb = 0, ce = n - 1;
    int num = 1;
    while (rb <= re && cb <= ce) {
        for (int i = cb; i <= ce; ++i) {
            res[rb][i] = num++;
        }
        rb++;

        for (int i = rb; i <= re; ++i) {
            res[i][ce] = num++;
        }
        ce--;

        if (rb <= re) {
            for (int i = ce; i >= cb; --i) {
                res[re][i] = num++;
            }
        }
        re--;

        if (cb <= ce) {
            for (int i = re; i >= rb; --i) {
                res[i][cb] = num++;
            }
        }
        cb++;
    }
    return res;
}

//60. Permutation Sequence
string getPermutation(int n, int k)
{
    int sum = 1;
    vector<int> factorial(n + 1);
    factorial[0] = 1;
    for (int i = 1; i <= n; ++i) {
        sum *= i;
        factorial[i] = sum;
    }

    vector<int> nums;
    for (int i = 1; i <= n; ++i) {
        nums.push_back(i);
    }

    k--;

    string res;
    for (int i = 1; i <= n ; ++i) {
        int index = k / factorial[n - i];
        res += char(nums[index] - '0');
        nums.erase(nums.begin() + index);
        k -= index * factorial[n - i];
    }
    return res;
}

//61. Rotate List
ListNode* rotateRight(ListNode* head, int k)
{
#if 1
    if (!head)
        return head;

    int len = 1;
    ListNode *newH, *tail;
    newH = tail = head;

    while (tail->next) {
        tail = tail->next;
        len++;
    }
    tail->next = head;

    if (k %= len) {
        for (int i = 0; i < len - k; ++i) {
            tail = tail->next;
        }
    }
    newH = tail->next;
    tail->next = NULL;
    return newH;
#else
    int n = 0;
    ListNode *tmp = head;
    ListNode *last = NULL;
    while (tmp) {
        n++;
        if (tmp->next == NULL)
            last = tmp;
        tmp = tmp->next;
    }

    if (n == 0)
        return NULL;

    ListNode *mid = NULL;

    k %= n;
    k = n - k;
    tmp = head;
    while (k && tmp) {
        if (k == 1)
            mid = tmp;
        tmp = tmp->next;
        k--;
    }
    mid->next = NULL;

    if (tmp) {
        last->next = head;
        head = tmp;
    }

    return head;
#endif
}

//62. Unique Paths
int uniquePaths(int m, int n)
{
#if 1
    vector<int> res(m, 1);
    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {
            res[j] += res[j - 1];
        }
    }
    return res[m - 1];
#else
    vector<vector<int>> res(m, vector<int>(n, 1));
    for (int i = 1; i < m; ++i) {
        for (int j = 1; j < n; ++j) {
            res[i][j] = res[i - 1][j] + res[i][j - 1];
        }
    }
    return res[m - 1][n - 1];
#endif
}

//63. Unique Paths II
int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid)
{
    int m = obstacleGrid.size();
    int n = obstacleGrid[0].size();
    vector<int> res(m, 0);
    res[0] = 1;
    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {
            if (obstacleGrid[i][j])
                res[j] = 0;
            else
                res[j] += res[j - 1];
        }
    }
    return res[m - 1];
}

//64. Minimum Path Sum
int minPathSum(vector<vector<int>>& grid)
{
    int m = grid.size();
    int n = grid[0].size();
    vector<vector<int>> dp(m, vector<int>(n, 0));
    dp[0] = grid[0];
    for (int i = 1; i < n; ++i) {
        dp[0][i] = grid[0][i] + dp[0][i - 1];
    }
    for (int j = 1; j < m; ++j) {
        dp[j][0] = grid[j][0] + dp[j - 1][0];
    }
    for (int i = 1; i < m; ++i) {
        for (int j = 1; j < n; ++j) {
            dp[i][j] = grid[i][j] + min(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    return dp[m - 1][n - 1];
}

//65. Valid Number
bool isNumber(string s)
{
    if (!s.empty()) {
        s.erase(0, s.find_first_not_of(" "));
        s.erase(s.find_last_not_of(" ") + 1);
    }

    bool pointSeen = false;
    bool eSeen = false;
    bool numberSeen = false;
    bool numberAfterE = true;
    for(int i = 0; i < s.length(); i++) {
        if('0' <= s[i] && s[i] <= '9') {
            numberSeen = true;
            numberAfterE = true;
        } else if(s[i] == '.') {
            if(eSeen || pointSeen) {
                return false;
            }
            pointSeen = true;
        } else if(s[i] == 'e') {
            if(eSeen || !numberSeen) {
                return false;
            }
            numberAfterE = false;
            eSeen = true;
        } else if(s[i] == '-' || s[i] == '+') {
            if(i != 0 && s[i - 1] != 'e') {
                return false;
            }
        } else {
            return false;
        }
    }

    return numberSeen && numberAfterE;
}

//66. Plus One
vector<int> plusOne(vector<int>& digits)
{
    int n = digits.size();
    for (int i = n - 1; i >= 0; --i) {
        if (digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        digits[i] = 0;
    }
    digits[0] = 1;
    digits.push_back(0);
    return digits;
}

//67. Add Binary
string addBinary(string a, string b)
{
    string res = "";

    int i = a.size() - 1, j = b.size() - 1;
    int c = 0;

    while (i >= 0 || j >= 0 || c > 0) {
        c += (i >= 0 ? a[i--] - '0' : 0);
        c += (j >= 0 ? b[j--] - '0' : 0);
        res = char(c % 2 + '0') + res;
        c /= 2;
    }

    return res;
}

//68. Text Justification
vector<string> fullJustify(vector<string>& words, int maxWidth)
{

}


#endif // LEETCODE100_H
