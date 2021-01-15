#include <bits/stdc++.h>
using namespace std;

/**
 * 和我的写法不同，更块一些。
 * 
 * 这里使用 hashmap 更快？ 为啥？？居然比int map[128]; 快一些。 
 * 这里 hashmap  存放的是每个字母出现的次数， 若次数 > 1, 则 说明重复，left++ 减去hashmap中对应的字母数， 直到 hashmap[right] == 1
 * 总体来说，快一些，区别不大。
 */

class Solution {
public:
    int hashmap[130];
    int lengthOfLongestSubstring(string s) { //双指针滑动窗口
        int max=0;
        int n = s.size();
        for(int i=0,j=0;j<n;j++){//当前判断是否重复的串为s[i..j]
            hashmap[s[j]]++;
            while(hashmap[s[j]]>1){
                hashmap[s[i++]]--;  //i指针右移
            }
            if(j-i+1>max)
                max = j-i+1;
        }
        return max;
    }
};