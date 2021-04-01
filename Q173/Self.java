package Q173;

import java.util.LinkedList;

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
 
class BSTIterator {
    LinkedList<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new LinkedList<>();
        TreeNode t = root;
        while(t != null){
            stack.push(t);
            t = t.left;
        }
    }
    public int next() {
        if(stack.isEmpty()) return -1;
        TreeNode t = stack.pop();
        if(t.right != null){
            TreeNode l = t.right;
            while(l != null){
                stack.push(l);
                l = l.left;
            }
        }
        return t.val;
    }
    public boolean hasNext() {
        if(stack.isEmpty()) return false;
        return true;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */