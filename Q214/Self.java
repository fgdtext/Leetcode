package Q214;

/*
题目：
给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
输入: "abcd"
输出: "dcbabcd"

算法 ： kmp。
分析： 若在字符串s前加一段字符使其变为 回文串。 则必定有 s[0~i] 是一个回文串字串，设其为 s1，s1为s的前缀
把 s 逆置,则 s_rev_1 为 s_rev的后缀。 
由于s1 是回文串， 则必有  s1 == s_rev_1. 
现在使用 kmp 
主串：   s_rev = "abcd"   模式串： s = "abcd"  
在主串中查找 模式串。 由kmp算法可以，kmp会在主串中 去不断的 找 更长的 模式串的 前缀。

一般kmp 是找一个比较短的模式串，则模式串可能完整的 存在于 主串中。

但 也可能 模式串 > 主串。  此时 变为 在 主串中 查找最长后缀 匹配 模式串的最长 前缀，。
最终 i ==strlen终止，j恰好停在前缀的后一个位置。 该前缀一定是 主串的后缀

若在过程中使用 index 和 flaglen记录 过程中遇到的 最大前缀位置，和长度，
就可以实现，在任意主串中，查找模式串的最长前缀。该前缀可以存在于 主串的任意位置。


由题意： 要使得输出 最短， 就要 s1最长， 而kmp 可以查找 模式串的最长前缀。满足条件。

*/

class Solution {
    public String shortestPalindrome(String s) {
        // 作为 主串
        StringBuilder s_rev = new StringBuilder(s).reverse();
        int[] next = new int[s.length() + 1];  // 防止越界，不加1 也行
        // 填写next:
        getNext(s, next);
        // kmp ::开始

        int i = 0, j = 0;
        while(i < s_rev.length()){
            if(j == -1 || s_rev.charAt(i) == s.charAt(j)){
                ++i;
                ++j;
            }else{
                j = next[j];
            }
        }
        // kmp 匹配结束， 此时j 停在 前缀的后一个位置

        // s 本身就是一个 回文串
        if(j == s.length()) return s;
        return s_rev.substring(0,s.length() - j) + s;
    }
   // 该 next数组的特点是没有 加 1;
   // next 从 -1开始
    void getNext(String str, int[] next){
        int i = 0, j = -1; // j = -1 是特殊位置
        next[0] = -1;
        while(i < str.length() - 1){
            if(j == -1 || str.charAt(i) == str.charAt(j)){
                i++;
                j++;
                next[i] = j;
            }else{
                j = next[j]; // j = next[0] = -1; 表示回退到头了
            }
        }
    }
}