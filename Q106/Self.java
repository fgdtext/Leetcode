package Q106;

import java.util.*;

public class Self {

    
}


 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  class Solution {
    Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }
        return dfs(0, inorder.length - 1, 0, postorder.length -1 , inorder, postorder);
    }

    public TreeNode dfs(int l1, int r1, int l2, int r2, int[] inorder, int[] postorder){
        if(l1 > r1 || l2 > r2) return null;
        if(l1 == r1){
            return new TreeNode(inorder[l1]);
        }
        TreeNode root = new TreeNode(postorder[r2]);
        int mid = idx_map.get(postorder[r2]);
        root.left = dfs(l1, mid - 1, l2, l2 + mid - l1- 1, inorder, postorder);
        root.right = dfs(mid + 1, r1,l2 + mid - l1 , r2 - 1, inorder, postorder);
        return root;
    }
}