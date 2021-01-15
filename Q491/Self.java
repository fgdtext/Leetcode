package Q491;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
对比 自己的和答案， 关于去重： 我直接使用Hash来保证了 唯一性， 答案则是通过限制不选择该元素来实现
我们需要给「不选择」做一个限定条件，只有当当前的元素不等于上一个选择的元素的时候，
才考虑不选择当前元素，直接递归后面的元素。因为如果有两个相同的元素，我们会考虑这样四种情况：
前者被选择，后者被选择
前者被选择，后者不被选择
前者不被选择，后者被选择
前者不被选择，后者不被选择

逻辑怪：：：
简单来说： 若 靠前的 7 出现，则后边的 7 必须出现，
若靠没有 7 出现 则，该位置 的 7 才可以不出现。

123777   123777  123.77  123..7   123...  只要出现7 则后边的7 必须出现  
有 123777  12377  1237 123  这几种，不出现重复

所以这里，限制不出现的 条件



关于枚举全集 ： 普通回溯， 二进制法(很重要)


ans : 普通递归加剪枝  100% 的算法，
*/


class Solution {
    Set<List<Integer>> set;
    List<List<Integer>> ans;
    boolean[] vis;
    public List<List<Integer>> findSubsequences(int[] nums) {
        set = new HashSet<>();
        vis = new boolean[nums.length];
        int max = -Integer.MAX_VALUE;
        ans = new ArrayList<>();
        huisuo(max, nums, 0);
       
        return ans;
    }
    public void huisuo(int max,int[] nums,int cur){
        int len = nums.length;
        
        if(cur == len){
            List<Integer> zList = new ArrayList<>();
            for(int i = 0; i < len; i++){
                if(vis[i]){
                    zList.add(nums[i]);
                }
            }
           // System.out.println(zList);
            if(zList.size() > 1 && !set.contains(zList)){
                set.add(zList);
                ans.add(zList);
            }
            return;
        }
        if(nums[cur] >= max){
            vis[cur] = true;
            huisuo(nums[cur], nums, cur+1);
            vis[cur] = false;
            huisuo(max, nums, cur+1);
            
        }else{
            vis[cur] = false;
            huisuo(max, nums, cur+1);
        }  
    }
}

public class Self{
    public static void main(String[] args) {
        Solution solution = new Solution();
         int[] nums = {1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
        //int[] nums = {4,6,7,7};
        System.out.println(solution.findSubsequences(nums));
        //solution.findSubsequences(nums);
    }
}

/*class
[[1,2],[1,2,3],[1,2,3,4],[1,2,3,4,5],[1,2,3,4,5,6],[1,2,3,4,5,6,7],[1,2,3,4,5,6,7,8],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9,10],[1,2,3,4,5,6,7,8,10],[1,2,3,4,5,6,7,9],[1,2,3,4,5,6,7,9,10],[1,2,3,4,5,6,7,10],[1,2,3,4,5,6,8],[1,2,3,4,5,6,8,9],[1,2,3,4,5,6,8,9,10],[1,2,3,4,5,6,8,10],[1,2,3,4,5,6,9],[1,2,3,4,5,6,9,10],[1,2,3,4,5,6,10],[1,2,3,4,5,7],[1,2,3,4,5,7,8],[1,2,3,4,5,7,8,9],[1,2,3,4,5,7,8,9,10],[1,2,3,4,5,7,8,10],[1,2,3,4,5,7,9],[1,2,3,4,5,7,9,10],[1,2,3,4,5,7,10],[1,2,3,4,5,8],[1,2,3,4,5,8,9],[1,2,3,4,5,8,9,10],[1,2,3,4,5,8,10],[1,2,3,4,5,9],[1,2,3,4,5,9,10],[1,2,3,4,5,10],[1,2,3,4,6],[1,2,3,4,6,7],[1,2,3,4,6,7,8],[1,2,3,4,6,7,8,9],[1,2,3,4,6,7,8,9,10],[1,2,3,4,6,7,8,10],[1,2,3,4,6,7,9],[1,2,3,4,6,7,9,10],[1,2,3,4,6,7,10],[1,2,3,4,6,8],[1,2,3,4,6,8,9],[1,2,3,4,6,8,9,10],[1,2,3,4,6,8,10],[1,2,3,4,6,9],[1,2,3,4,6,9,10],[1,2,3,4,6,10],[1,2,3,4,7],[1,2,3,4,7,8],[1,2,3,4,7,8,9],[1,2,3,4,7,8,9,10],[1,2,3,4,7,8,10],[1,2,3,4,7,9],[1,2,3,4,7,9,10],[1,2,3,4,7,10],[1,2,3,4,8],[1,2,3,4,8,9],[...

*/

//  推荐 该方式。  使用vis 标记， 最后再处理的方式，不够直观。脱裤子放屁
// 该方式，不标记，直接放进去
class Solution2 {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<Integer>(temp));
            }
            return;
        }
        // 有该 位置
        if (nums[cur] >= last) {
            // 放进去
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            // 再拿出来
            temp.remove(temp.size() - 1);
        }
        // 没有该位置
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }
}

