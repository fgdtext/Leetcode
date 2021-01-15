package Q701;

public class Sself {
    
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
// 递归写法。  空间不是很好， 时间 100% 空间  9%
class Solution2 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newnode = new TreeNode(val);
        if(root == null) return newnode;
        dfs(root, newnode);
        return root;
    }
    public void dfs(TreeNode root, TreeNode newnode){
        if(root.val > newnode.val){
            if(root.left == null){
                root.left = newnode;
                return;
            }
            dfs(root.left, newnode);
        }else{
            if(root.right == null){
                root.right = newnode;
                return;
            }
            dfs(root.right, newnode);
        }
    }
}

// 迭代写法,, 空间好一点了。  时间100% ，， 空间21%
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newnode = new TreeNode(val);
        if(root == null) return newnode;
        TreeNode cur = root;
        while(cur != null){
            if(cur.val > newnode.val){
                if(cur.left == null){
                    cur.left = newnode;
                    break;
                }
                cur = cur.left;
            }else{ 
                if(cur.right == null){
                    cur.right = newnode;
                    break;
                }
                cur = cur.right;
            }
        }
        return root;
    }
}