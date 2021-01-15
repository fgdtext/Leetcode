#include <bits/stdc++.h>
using namespace std;


/**
 * 这里将字符映射到 一个长为128的数组，遇到该字符，则在map 中对应位置存他在字符串中的索引，若再遇到，则可以直接发现重复。功能类似 哈希表。
 * 直接滑动窗口判断。
 * 规则：向右移动 right 若仍不重复，继续移动 right .
 *       否则，和i重复，则移动left 到 重复元素第一次出现的后一个位置i+1。然后继续移动right.
 * 
 * 
 * 提交成功，目前 时间为  8ms. 和 ans 相同。
 */

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        // 题目不明确字符的种类，所以这里映射到 asc码
        int strlen = s.length();
        int map[128]; // 存字符存在的位置。 没有则为-1
        memset(map, -1, sizeof(map));
        int mmax = 0; // 保存最长字串的长度。
        int left =0, right = 0;
        while(right < strlen){

            // 映射 s[right]到 map
            if(map[s[right]] != -1){
                // 出现重复, 移动 left
                int temp = map[s[right]];
                // 把 map中 left 到 temp之间的清空掉,temp 位置是重复位置。
                for(int i = left; i <= temp; i++){
                    map[s[i]] = -1;
                }
                left = temp + 1;
            }
            // 将位置 存在map 中。
            map[s[right]] = right;
            mmax = max(mmax, right - left + 1);
            ++right;
        }
        return mmax;
    }
};



int main()
{ 



    // 防止黑框消失
    char a, b;
    printf("\n--------------------------\n");
    scanf("%d", a);
    return 0;
}