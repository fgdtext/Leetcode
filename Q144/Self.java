package Q144;
import java.util.*;


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
DFS 版本  使用栈来完成  


 */


class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return  new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        stack.addFirst(root);
        while(!stack.isEmpty()){
            TreeNode top = stack.removeFirst();
            ans.add(top.val);
            if(top.right != null) stack.addFirst(top.right);
            if(top.left != null) stack.addFirst(top.left);
        }
        return ans;
    }
}