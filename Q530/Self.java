package Q530;

public class Self {
    
}


 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  class Solution {
    int min = Integer.MAX_VALUE;
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        in(root);
        return min;
    }
    public void in(TreeNode cur){
        if(cur == null) return;
        in(cur.left);
        if(pre == null){pre = cur;} // 边界
        else{
            min = Math.min(min, Math.abs(pre.val - cur.val)); // 关键
            pre = cur; // 关键
        }
        in(cur.right);
    }
}