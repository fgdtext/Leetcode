package Q354;

import java.util.Arrays;


public class Self {
    
}

class Self2 {
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if(len == 0) return 0;
        Arrays.sort(envelopes, (o1,o2)->{return o1[0] - o2[0];});
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i = 1; i < len; i++){
            // 因为第一维度有序， 大于等于 当前第一位都的 都不需要再比较。  二分查找。 
            // 因为第一维度有序，找第一个小于当前值的位置，是非常靠后的。从后往前找可能更快。
            int k = erfenserch(envelopes, i, envelopes[i][0]);
            for(int j = 0; j < k; j++){
                if(envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int erfenserch(int[][] arr, int k, int tar){
        int len = k;
        int left = 0, right = k - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(arr[mid][0] >= tar) right = mid;
            else left = mid + 1;
        }
        if(arr[left][0] < tar) return k;
        return left;
    }

}



class Self3 {
    public int maxEnvelopes(int[][] envelopes) {
         int len = envelopes.length;
         if(len == 0) return 0;
         Arrays.sort(envelopes, (o1,o2)->{return o1[0] - o2[0];});
         int[] dp = new int[len];
         Arrays.fill(dp, 1);
         int ans = 1;
         for(int i = 1; i < len; i++){
             // 因为第一维度有序， 大于等于 当前第一维度的 都不需要再比较。
             int k = serch(envelopes, i);
             for(int j = 0; j < k; j++){
                 if(envelopes[j][1] < envelopes[i][1]){
                     dp[i] = Math.max(dp[i], dp[j]+1);
                 }
             }
             ans = Math.max(ans, dp[i]);
         }
         return ans;
     }
 
     public int serch(int[][] arr, int k){
         int tar = arr[k][0];
         for(int i = k-1; i >= 0; i--){
             if(arr[i][0] < tar) return i+1;
         }
         return 0;
     }
 }


 