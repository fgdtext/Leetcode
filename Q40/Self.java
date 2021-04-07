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
            if(i != cur && candi[i] == candi[i-1]) continue;  // 小减枝   set:作用和set一样
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



/*

    压根不需要 vis 数组， 仔细想， 每次选择位置i的时候，一定是当前分支第一次 使用位置i. 所以同一个分支不可能对同一个位置重复使用。
    这里使用 if(i != cur && candi[i] == candi[i-1]) continue; 和使用set,来避免同节点的子节点使用相同的元素。以此来避免重复结果。
*/
class Solution5  {
    List<List<Integer>> ans;
       public List<List<Integer>> combinationSum2(int[] candidates, int target) {
           ans = new ArrayList<>();
           Arrays.sort(candidates);
           dfs(0, 0, candidates, target, new ArrayList<>());
           return ans;
       }
       public void dfs(int cur,int add, int[] candi, int target, List<Integer> list){
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
               if(i != cur && candi[i] == candi[i-1]) continue;  // 小减枝   set:作用和set一样
                   list.add(candi[i]);
                   dfs(i+1, add+candi[i], candi, target, list);
                   list.remove(list.size()-1);
    
           }
       }
   }


/*
    方式1 ： 即将y个x看作一个结点，向下扩展， 根据选择x的不同次数，分为不同的多分支，

*/
   class Solution6 {
    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);  // 选 0次

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);  // 选多次
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        // 向上层回溯，使得sequence 返回后，和传入时不变。
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }
}

