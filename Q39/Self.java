package Q39;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Self {

    public static void main(String[] args) {
        Solution solution  = new Solution();
        int[] arr = {2,3,6,7};
        List<List<Integer>> ans = solution.combinationSum(arr, 7);
        for(List<Integer> list : ans){
            for(int it : list){
                System.out.print(it+" ");
            }
            System.out.println();
        }

    }
}



/*
可重复挑选， 这就难受了啊。

下边是自己写的。。 效率低下。
*/
class Solution {
    int[] candidates;
    int len;
    int target;
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.len = candidates.length;
        this.target = target;
        ans = new ArrayList<>();
        dfs(0, 0,new ArrayList<>());
        return ans;
    }
    public void dfs(int cur,int add, List<Integer> cur_list){
        if(add == target){
            ans.add(cur_list);
            return;
        }
        if(cur == len) return;
        if(candidates[cur] > target - add) return;
        if(add > target) return;

        int k = 0;     
        int list_len = cur_list.size();
        // 一次性分化出  多种对(不同次数)对 当前cur 的选择， 下次递归时，就不允许再挑选当前值。
        if( list_len == 0 || cur_list.get(list_len - 1) != candidates[cur]) {
            int temp = add;
            while(temp < target){
                    k++;
                    temp += candidates[cur];
                    cur_list.add(candidates[cur]);
                    dfs(cur+1, temp, new ArrayList<>(cur_list));
            }
        }
        for(int i = 0; i < k; i++){
            cur_list.remove(cur_list.size() - 1);
        }
        dfs(cur+1, add, new ArrayList<>(cur_list));
    }
}