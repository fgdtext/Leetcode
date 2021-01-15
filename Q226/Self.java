package Q226;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
class Solution {
    public TreeNode invertTree(TreeNode root) {
        pre(root);
        return root;
    }
    public void pre(TreeNode root){
        if(root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        pre(root.right);
        pre(root.left);
    }
}