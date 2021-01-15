package Q104;

  class TreeNode {
    int val;
      TreeNode left;
     TreeNode right;
    TreeNode(int x) { val = x; }}
    
class Self1 {
    public int maxDepth(TreeNode root) {
        return dfs(root);
    }
    public int dfs(TreeNode cur){
        if(cur == null) return 0;
        int a = dfs(cur.left);
        int b = dfs(cur.right);
        return a > b? a+1 : b+1;
    }
}


/*
极简形式。
*/

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}