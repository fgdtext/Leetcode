package Q404;

public class Self {
    
}


/*
计算给定二叉树的所有左叶子之和。


*/

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
class Solution {
    int ans;
    public int sumOfLeftLeaves(TreeNode root) {
        sum(root);
        return ans;
    }
    public void sum(TreeNode root){
        if(root == null) return;
        if(root.left != null && root.left.left == null && root.left.right == null) ans += root.left.val;
        sum(root.left);
        sum(root.right);
    }
}