package Q543;

public class Self {
    
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
class Solution {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }
    public int dfs(TreeNode cur){
        if(cur == null) return 0;
        int left = dfs(cur.left);
        int right = dfs(cur.right);
        ans = Math.max(ans, left+right);
        return Math.max(left, right)+1;

    }
}