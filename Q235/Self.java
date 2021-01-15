package Q235;



public class Self {
    
}

 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
/*
 ::::: !!!!!!!!   pq 指向的是树中的结点， 不是另外的空间



*/


class Solution {  
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val){
            TreeNode t = p;
            p = q;
            q = t;
        }
        
        return biSeach(root, p, q);
    }
    public TreeNode biSeach(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        if(root.val < p.val) return  biSeach(root.right, p, q);
        if(root.val > q.val) return  biSeach(root.left, p, q);
        return root;
    }
}