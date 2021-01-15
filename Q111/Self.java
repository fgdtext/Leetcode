package Q111;

import java.util.ArrayDeque;
import java.util.Deque;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


  /*
        写一个新的方法，使用双端队列。 不需要再为每一个结点 记录 其高度了

  */
 
class Solution {
    int INF = 1 << 9;

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int high = 1;
        TreeNode mid = new TreeNode(INF);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(mid);
        deque.addFirst(root);
        boolean flag = true;  // 用于转向
        while(deque.size() != 1){
            TreeNode top;
            TreeNode temp;
            if(flag) {  // 出头， 加 尾
                top = deque.removeFirst();
                
                 if(top.left != null)   deque.addLast(top.left);
                 if(top.right != null)  deque.addLast(top.right);
                 temp = deque.getFirst();        
            }
            else{ // 往尾 加头
                top = deque.removeLast();
                if(top.left != null)   deque.addFirst(top.left);
                if(top.right != null)  deque.addFirst(top.right);
                temp = deque.getLast();
            }
            // 第一层，全部走万， hight 才变成 2. 走完第2层，hight才变为3.， 所以当 第二层有叶子结点时，则应直接返回hight.
            if(top.left == null && top.right == null) return high;
            if(temp.val == INF){
                flag = flag ? false : true;
                high++;
            }
        }
        return high;
    }
}