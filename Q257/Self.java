package Q257;

import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
/*
每次递归创建 新的 字符串。
*/


class Solution {
    List<String> ans;
    TreeNode root;
    public List<String> binaryTreePaths(TreeNode root) {
        ans = new ArrayList<>();
        this.root = root;
        if(root == null) return ans;
        dfs(root,new StringBuilder());
        return ans;
    }
    public void dfs(TreeNode node, StringBuilder sBuilder){
        if(node == null) return;
        if(root == node) sBuilder.append(node.val);
        else sBuilder.append("->").append(node.val);
        if(node.left == null && node.right == null) {
            ans.add(sBuilder.toString());
            return;
        }
        dfs(node.left, new StringBuilder(sBuilder));
        dfs(node.right, new StringBuilder(sBuilder));
    }
}
