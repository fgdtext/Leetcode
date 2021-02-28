package Q230;

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
 
class Solution {
    int ans;
    int kk = 0;
    int k;
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        indfs(root);
        return ans;
    }
    public boolean indfs(TreeNode root){
        if(root == null) return false;
        if(indfs(root.left)) return true;
        if(++kk == k) {
            ans = root.val;
            return true;
        }
        if(indfs(root.right)) return true;
        return false;
    } 
}