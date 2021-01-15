package Q56;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Self {
    
}

/*

两个区间的关系。

在排序后  区间(a,b)(c,d) : 由于排序必有 :  a <= c 

若 a==c  : 则两个区间一定可以合并，合并后为 (a max(b,d))

若 a < c  : 则有三种可能  1.   b < c     2     c <= b <= d  3.   b > d;
情况1 : 两个区间 分离
情况2 ： 两个区间相交。 
情况3 ：  第一个区间 包含 第二个区间

Arrays.sort(intervals, (nums1, nums2) -> nums1[0] - nums2[0]);  
可以直接替代下边的 快排

还可以这么做。我去。 
// 


*/

class Solution {
    int i = 0;
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return new int[0][0];
        if(intervals.length == 1) return intervals;
        quick_sort(intervals, 0, intervals.length - 1);
        // 开始合并
        HashMap<Integer, Integer> map = new HashMap<>();
        int curl = intervals[0][0];
        int curr = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(curl == intervals[i][0]){
                curr = curr > intervals[i][1] ? curr : intervals[i][1];
            }else if(curl < intervals[i][0]){

                if(curr < intervals[i][0]){ // 独立 区间
                    map.put(curl, curr);
                    curl = intervals[i][0];
                    curr = intervals[i][1];
                }else if (curr >= intervals[i][0] && curr <= intervals[i][1]){
                    curr = intervals[i][1];
                }
            }
        }
        map.put(curl, curr);
        int[][] ans = new int[map.size()][2];
        map.forEach((a,b)->{
            ans[i][0] = a;
            ans[i][1] = b;
            i++;
        });
        return ans;
    }
    // 快排
    // 二维数组 (按照第一列排序)。 第二列跟着变换
    void quick_sort(int[][] s, int l, int r){
        if (l < r){
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l][0]; int y = s[l][1];
            while (i < j){
                while(i < j && s[j][0] >= x) // 从右向左找第一个小于x的数
                    j--;  
                if(i < j){
                    int in = i;
                    i++;
                    s[in][0] = s[j][0]; 
                    s[in][1] = s[j][1];
                }       
                while(i < j && s[i][0] < x) // 从左向右找第一个大于等于x的数
                    i++;  
                if(i < j){
                    int in = j;
                    j--;
                    s[in][0] = s[i][0];
                    s[in][1] = s[i][1];
                }
            }
            s[i][0] = x;
            s[i][1] = y;
            quick_sort(s, l, i - 1); // 递归调用 
            quick_sort(s, i + 1, r);
        }
    }
}
