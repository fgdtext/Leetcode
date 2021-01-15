package Q129;

public class Self {
    
}


   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  class Solution {
    int ans;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    public void dfs(TreeNode cur, int sum){
        if(cur == null) return;
        if(cur.left == null && cur.right == null){
            sum = sum*10 + cur.val;
            ans += sum;
            return;
        }
        sum = sum*10 + cur.val;
        dfs(cur.left, sum);
        dfs(cur.right, sum);
    }
}