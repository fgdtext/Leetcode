package Q46;

import java.lang.reflect.Array;
import java.util.*;

public class Self {
    
}

/*
全排列



*/



class Solution {
    List<List<Integer>> ans;
    int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 0) return new ArrayList();
        ans = new ArrayList();
        if(nums.length == 1) {
            ArrayList<Integer> t = new ArrayList();
            t.add(nums[0]);
            ans.add(t);
            return ans;
        }
        this.nums = nums;
        dfs(0, new boolean[nums.length], new int[nums.length]);
        return ans;
    }

    public void dfs(int cur, boolean[] vis, int[] temp){
        if(cur == nums.length){
            List<Integer> t = new ArrayList();
            for(int c : temp){
                t.add(c);
            }
            ans.add(t);
        }
        for(int i = 0; i < nums.length; i++){
            if(!vis[i]){
                vis[i] = true;
                temp[cur] = nums[i];
                dfs(cur+1, vis, temp);
                vis[i] = false;
            }
        }
    }
}