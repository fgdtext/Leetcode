package Q124;

public class Self {
    
}


   class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
    TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 
/*

铁定的动态规划吧  . 不是动态规划。 槽

直接递归就好。

秀 。  全局变量 max 保存 最大值。  dfs 返回值总是向上返回 终点是左(或右)子结点的最大路径。 该路径可以继续向上拼接。
*/


class Solution {
  private int max = Integer.MIN_VALUE;
  public int maxPathSum(TreeNode root) {
      dfs(root);
      return max;
  }
  public int dfs(TreeNode root) {
      //返回当前子树的最大值
      if (root == null) return 0;
      int left = dfs(root.left);
      int right = dfs(root.right);
      //当前节点为n,对于当前节点来说，最大值可以为n+left,n+right,n,n+left+right
      //上面四项中的一个，但是返回是不能返回第四个的，那种路径是不成立的
      // 因为第四种路径是不能向上拼接的， 起点和 终点分别在左右子树中。
      int p = Math.max(root.val,Math.max(root.val+left,root.val+right));
      max = Math.max(max,p);
      int p2 = Math.max(p,root.val+left+right);
      max = Math.max(max,p2);
      return p;
  }
}