package Q94;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
/*
自己写的  时间 100% 


*/


class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode cur = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        // 先走到左下角。
        while(cur != null){
            stack.addFirst(cur);
            cur = cur.left;
        }
        while(!stack.isEmpty()){
            cur = stack.removeFirst();
            ans.add(cur.val);
            TreeNode temp = cur.right;
            while(temp != null){
                stack.addFirst(temp);
                temp = temp.left;
            }     
        }
        return ans;
    }
}