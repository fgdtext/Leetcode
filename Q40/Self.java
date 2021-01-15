package Q40;


import java.util.*;


/*
第 39 题：candidates 中的数字可以无限制重复被选取；
第 40 题：candidates 中的每个数字在每个组合中只能使用一次。

数组去重 : 要手动实现对数组的Hash. util不支持。


*/
class Solution2 {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        boolean[] vis = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(0, 0, candidates, target, new ArrayList<>(), vis);
        return ans;
    }
    public void dfs(int cur,int add, int[] candi, int target, List<Integer> list,boolean[] vis){
        if(add == target){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(add > target) return;
        if(cur == candi.length) return;
        //  当前结点 向 子结点发展时， 不能减 相同 的 元素。
        HashSet<Integer> set = new HashSet<>();
        // 使用 target  减数法 时 ，一般都是使用 for 循环。
        for(int i = cur; i < candi.length; i++){
            //  每层使用的数， 一定是 上一层 后边的。不能对同一个位置重复使用。
            // cur 在上一层传来是， 加了 1.
            
            if(!set.contains(candi[i]) && !vis[i]){
                set.add(candi[i]);
                list.add(candi[i]);
                // 标记 该位置的数字已经使用过。
                vis[i] = true;
                dfs(i+1, add+candi[i], candi, target, list,vis);
                list.remove(list.size()-1);
                vis[i] = false;
            }
        }
    }
}

/*
以下， 不用Set 更快。


*/


class Solution3 {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        boolean[] vis = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(0, 0, candidates, target, new ArrayList<>(), vis);
        return ans;
    }
    public void dfs(int cur,int add, int[] candi, int target, List<Integer> list,boolean[] vis){
        if(add == target){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(add > target) return;  // 向下递归的减枝
        if(cur == candi.length) return;
        //  当前结点 向 子结点发展时， 不能减 相同 的 元素。
        // 使用 target  减数法 时 ，一般都是使用 for 循环。
        for(int i = cur; i < candi.length; i++){
            //  每层使用的数， 一定是 上一层 后边的。不能对同一个位置重复使用。
            // cur 在上一层传来是， 加了 1.    
            if(target - add < candi[i]) break;
            if(i != cur && candi[i] == candi[i-1]) continue;  // 小减枝
            if(!vis[i]){

                list.add(candi[i]);
                // 标记 该位置的数字已经使用过。
                vis[i] = true;
                dfs(i+1, add+candi[i], candi, target, list,vis);
                list.remove(list.size()-1);
                vis[i] = false;
            }
        }
    }
}



// 再优化
// if(add > target) return;  不再向下发展。
//if(target - add < candi[i]) break;   横向 优化。 即该层， 不再向右发展。 右边的枝条都不要
// if(i != cur && candi[i] == candi[i-1]) continue;  只减去当前枝条。右边的还要继续判断

class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        boolean[] vis = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(0, 0, candidates, target, new ArrayList<>(), vis);
        return ans;
    }
    public void dfs(int cur,int add, int[] candi, int target, List<Integer> list,boolean[] vis){
        if(add == target){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(add > target) return;
        if(cur == candi.length) return;
        //  当前结点 向 子结点发展时， 不能减 相同 的 元素。
        // 使用 target  减数法 时 ，一般都是使用 for 循环。
        for(int i = cur; i < candi.length; i++){
            if(target - add < candi[i]) break;
            //  每层使用的数， 一定是 上一层 后边的。不能对同一个位置重复使用。
            // cur 在上一层传来是， 加了 1.    
            if(i != cur && candi[i] == candi[i-1]) continue;
            if(!vis[i]){

                list.add(candi[i]);
                // 标记 该位置的数字已经使用过。
                vis[i] = true;
                dfs(i+1, add+candi[i], candi, target, list,vis);
                list.remove(list.size()-1);
                vis[i] = false;
            }
        }
    }
}