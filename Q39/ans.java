package Q39;

import java.util.*;
/*
输入: candidates = [2, 3, 6, 7]，target = 7。

target 可以 -2 -3 -6 -7 到第一层

但是对于 -3  : target = 4 此时，要求 -3的分支 只能 -3， -6 -7 即 -3之前的不能再减。
目的就是 ： 避免  -2 -2 -3  -3 -2 -2 的重复出现。也就是说，减去的数，总是在递增的。

这里就要求， 要提前对 candidates 递增排序。

begin 的目的就是 限制 下一层 选择的开始位置。

*/
class Solution2 {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(path,candidates,target,0,0);
        return res;
    }




    private void backtrack(List<Integer> path,int[] candidates,int target,int sum,int begin) {
        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin;i < candidates.length;i++) {
            int rs = candidates[i] + sum;
            if(rs <= target) {
                path.add(candidates[i]);
                backtrack(path,candidates,target,rs,i);
                path.remove(path.size()-1);
            } else {
                
                break;
            }
        }
    }
}