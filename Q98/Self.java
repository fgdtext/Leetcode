package Q98;

public class Self {
    
}


  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  class Solution {
    TreeNode pre;
    TreeNode mark;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        pre = new TreeNode(Integer.MIN_VALUE);
        mark = pre;
        return dfs(root);
    }
    public boolean dfs(TreeNode cur){
        if(cur == null) return true;
        
        boolean a =  dfs(cur.left);
        if(!a) return false;
        boolean b;
        if(cur.val == Integer.MIN_VALUE && pre.val == Integer.MIN_VALUE){
            if(pre == mark) b = true; //  pre 可能还没有 改变。
            else b = false;
        }
        else if(cur.val == Integer.MIN_VALUE) b = false;
        else if(pre.val == Integer.MIN_VALUE) b = true;
        else  b = cur.val > pre.val;
        if(!b) return false;
        pre = cur;
        boolean c =  dfs(cur.right);
        if(!c) return false;
        return true;
    }
}
/*
答案 写的 更好。  pre 不需要给指向， 用 null 来区分就好。


*/
class Ans {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(node.right, val, upper)) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        return true;
    }
}
