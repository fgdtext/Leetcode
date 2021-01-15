package Q101;

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
把一棵树 拆开成两半，同步来对比两颗树，是否是镜像的。


*/



/*
学习了

对比两课树是否是镜像的。 树中的同步指针

*/

class Ans {
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }
    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        //  队列，对比 入队顺序  [1,2,2,3,4,4,3]
       // 22 33 44 .。。
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            // 入队遵循对称原则
            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}



