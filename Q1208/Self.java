package Q1208;

import java.util.*;
/*

滑动窗口

右侧指针向右扩展，若当前剩余花销可以支付right位置，right++
否则left++, 进行退款，用于继续扩展right.

*/
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int left = 0, right = 0;
        int tcost = maxCost;
        int ans = 0;
        while(right < len){
            int rcost = Math.abs(s.charAt(right)-t.charAt(right));
            if(tcost >= rcost){
                tcost -= rcost;
                ++right;
            }else{
                int lcost = Math.abs(s.charAt(left)-t.charAt(left));
                tcost += lcost;
                ++left;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}