package Q102;

import java.util.*;

public class Self {
    
}


   class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  /*
    使用 加入 特殊 结点的 方法。
  */
  class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        List<Integer> temp = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode mark = new TreeNode(1);
        queue.addLast(root);
        queue.addLast(mark);
        while(!queue.isEmpty()){
            TreeNode front = queue.removeFirst();
            if(front == mark){
                ans.add(temp);
                temp = new ArrayList<>();
                if(queue.size() == 0) return ans; // 很重要的点， 否则会循环放入 mark。死锁
                queue.addLast(mark);
            }else{
                temp.add(front.val);
                if(front.left != null)
                    queue.addLast(front.left);
                if(front.right != null)
                    queue.addLast(front.right);
            }
        }
        return ans;
    }
}