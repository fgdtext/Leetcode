package Q395;

import java.util.*;
/*

枚举26个字符。利用分治算法。

在一个串中，若字符 x的频率小于k, 那么任何一个含有字符x的串都是非法的，因为含有x，但其频率一定小于k.
所以可以根据该字符x分割。递归。
每次都统计区间内的字符频率，然后找到非法字符，再进行分割，直到找不到非法字符，那么该子串就是合法的。

*/
class Ans1 {
    public int longestSubstring(String s, int k) {
        int len = s.length();
        return dfs(s, 0, len-1, k);
    }
    public int dfs(String s, int l, int r, int k){
        if(r < l) return 0;
        int[] freq = new int[26];
        for(int i = l; i <= r; i++) freq[s.charAt(i)- 'a']++;
        char split = 0;
        for(int i = 0; i < 26; i++){
            if(freq[i] > 0 && freq[i] < k){
                split = (char)(i+'a');
                break;
            }
        }
        if(split == 0) return r-l+1;
        int ret = 0;
        for(int i = l; i < r; i++){
            if(s.charAt(i) == split){
                ret = Math.max(ret,dfs(s, l, i-1, k));
                l = i+1;
                if(l > r) break;
            }
        }
        if(s.charAt(r) == split){
            ret = Math.max(ret,dfs(s, l, r-1, k));
        }else{
            // 虽然当前区间没有 关于 split字符的分割， 但是可能还含有其他字符需要分割的地方。交给递归，唯一的出口就是在子串中找不到split.
            ret = Math.max(ret,dfs(s, l, r, k)); 
        }
        return ret;
    }
}



/*class
1.
大循环枚举最长子串的字符种类数量。 最长子串的字符总类是 1~26. 通过t枚举1~26， 那么对于给定的t,这次枚举，
    我们只找字符总类恰好为 t的子串，来判断是否合法                   恰好： 不大于，也不小于。
2.
扩张有边界， 若窗口内字符总类total < t, 那么就要继续扩张。 若右 total > t, 种类超出t，就不符合当前枚举要求。
        不断的通过l收缩，直到 total <= t.  那么对于每一个 r,  我们都能找到一个 Lmin 使得当前 Lmin到r窗口内字符种类为t.

    对于任何一组 Lmin~r   是否合法呢?  若存在一个字符次数<k 那么 那么Lmin~r是非法的，且对于每一个l>Lmin  l~r的子串都是非法的。
    因为，如果收缩Lmin到l,使得 该字符次数将为0， 那么当前子串l~r 的字符总类就<t（不满足枚举要求）
    若 将为非0， 那么该字符以然使得该子串非法。

所以我们在枚举t的条件下，对于每一个r，都可以固定一个Lmin. 

左指针L的移动: 对于一个种类恰好为t的窗口，此时r继续右移动，若移动导致total>t,此时需要移动L。直到再次满足total = t



如果快速判窗口合法， 计数字符总类，不超过k的字符种类数，字符频率统计。
*/

class Solution {
    public int longestSubstring(String s, int k) {
        int ans = 0;
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for (int p = 1; p <= 26; p++) {
            Arrays.fill(cnt, 0);
            // tot 代表 [j, i] 区间所有的字符种类数量；sum 代表满足「出现次数不少于 k」的字符种类数量
            // j,i 代表左右窗口边界， 对于每一个右边界，找最小 Lmin
            for (int i = 0, j = 0, tot = 0, sum = 0; i < n; i++) {
                int u = cs[i] - 'a';
                cnt[u]++;
                // 如果添加到 cnt 之后为 1，说明字符总数 +1
                if (cnt[u] == 1) tot++;
                // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                if (cnt[u] == k) sum++;
                // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 p，那么我们要删除掉一些字母，即「左指针」右移
                while (tot > p) {
                    int t = cs[j++] - 'a';
                    cnt[t]--;
                    // 如果添加到 cnt 之后为 0，说明字符总数-1
                    if (cnt[t] == 0) tot--;
                    // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                    if (cnt[t] == k - 1) sum--;
                }
                // 当所有字符都符合要求，更新答案
                if (tot == sum) ans = Math.max(ans, i - j + 1);
            }
        }
        return ans;
    }
}
