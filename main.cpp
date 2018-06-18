#include <iostream>
#include "leetcodeeasyarray.h"
#include "leetcodeeasyhashtable.h"
#include "leetcodeeasystring.h"
#include "leetcodebitmanipulation.h"
#include "leetcode50.h"

int main(int argc, char *argv[])
{
    //int a[5] = { 1, 2, 3, 4, 5 };
    int a[7] = {2, 6, 4, 8, 10, 9, 15};

    vector<int> input(a, a + 7);
    findUnsortedSubarray(input);

    vector<string> keys = {"Hello","Alaska","Dad","Peace"};
    vector<string> result = findWords(keys);

    vector<char> chars = {'a', 'a', 'b', 'b', 'b', 'b', 'b'};
    compress(chars);

    uint32_t i = reverseBits(43261596);
    i = hammingWeight(11);
    std::cout << i << std::endl;

    i = lengthOfLongestSubstring("abcabcabb");

    string s = convert("PAYPALISHIRING", 3);

    vector<int> v = {-1};
    threeSum(v);

    vector<string> res = generateParenthesis(3);

    int j = (int)-2147483648 / (int)-1;
    int k = -2147483648;
    int m = -1;
    std::cout << (k & 0x80000000) << std::endl;

    vector<vector<char>> sudoku = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
    isValidSudoku(sudoku);

    vector<int> t = {0,1,0,2,1,0,1,3,2,1,2,1};
    trap(t);

    multiply("2", "0");

    return 0;
}
