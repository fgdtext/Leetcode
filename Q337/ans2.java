package Q337;

import java.util.HashMap;


/*

暴力递归：时间爆炸。 
优化： 记忆化搜索。(动态规划---其实就是 递归 + 记忆化

HashMap : key ： node    value : sumval
node: 结点     sumval 打劫当前结点及其子树能获得的最大值。
HashMap ： 是一个好用的 记忆化搜索的 记录方式。 
树的记忆化方法：
1. (i,j) 第i层，第j个结点 表示一个结点。(i,j) 的儿子是(i+1,j) (i,j+1)
2. HashMap : key,value .直接拿结点的Hash值来索引。


使用 HashMap 也会造成时间的损耗。 想办法规避使用map 。

本答案的状态定义方式 : 若偷该结点，则不能偷儿子， 只能去偷几个孙子。
              若不偷该结点， 则该点最大 ，为   两个儿子最大的和。

优化： 问题：上边的定义会导致，爷爷到孙子的跨越。
    定义： 若偷该结点，则该点最大为  ： 两个儿子不偷的时候的最大值和，加上本结点，
           若不偷该结点： 则为  ： 两个孩子(不一定偷不偷)的最大和。
    
*/


class Solution2 {
    HashMap<TreeNode,Integer> map;
    public int rob(TreeNode root) {
        map = new HashMap<TreeNode,Integer>();
        return digui(root);
    }

    public int digui(TreeNode root){

        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root);



       int maxinclude = root.val;
       if(root.left != null){
           maxinclude += digui(root.left.left) + digui(root.left.right);
       }
       if(root.right != null){
           maxinclude += digui(root.right.left) + digui(root.right.right);
       }
       int maxexclude = digui(root.left) + digui(root.right);
       int max = Math.max(maxinclude, maxexclude);
       map.put(root, max);
       return max;
    }
}