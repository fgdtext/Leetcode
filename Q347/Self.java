package Q347;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Self {
    
}
/*
Q347  ：  统计数字出现频率，求出 频率 最大的k 个数字
*/


class Solution {
    static class Node{
        int val;
        int time;
        // 用数组  最好了
        public Node(int val, int time){
            this.val = val;
            this.time = time;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2)->{return o2.time - o1.time;});
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);
                continue;
            }
            map.replace(nums[i], map.get(nums[i])+1);
        }
        for(Integer key : map.keySet()){
            q.add(new Node(key,map.get(key)));
        }
        int i = 0;
        while(!q.isEmpty()){
            if(i == k) break;
            ans[i++] = q.poll().val;
        }
        return ans;
    }
}