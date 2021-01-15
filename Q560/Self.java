package Q560;

import java.util.HashMap;

public class Self {
    
}
/*
     Hash表法， 当i时，查看 前缀和sum - k的数量，就是以i结尾的子数组的数量。

    时间空间是 双 o(n) 算法。
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                ans += map.get(sum-k);
            }
            if(!map.containsKey(sum)) {
                map.put(sum, 1);
            }else{
                map.replace(sum, map.get(sum)+1);
            }
        }
        return ans;
    }
}

/*
    学习使用该 api
  mp.put(pre, mp.getOrDefault(pre, 0) + 1);
*/

 class Ans {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
