package Q103;

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

双栈替换。

 */
class Self2 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        stack1.push(root);
        boolean direct = false;

        while(!stack1.isEmpty()){
            List<Integer> curlist = new ArrayList<>();
            while(!stack1.isEmpty()){
                TreeNode cur = stack1.pop();
                curlist.add(cur.val);

                if(cur.left != null && cur.right != null){
                    // 左右子树的添加顺序每层都要变。
                    if(direct){
                        stack2.push(cur.right); stack2.push(cur.left);
                    }else{
                        stack2.push(cur.left); stack2.push(cur.right);
                    }
                    continue;
                }
                if(cur.left != null){
                    stack2.push(cur.left);
                }
                if(cur.right != null){
                    stack2.push(cur.right);
                }
            }
            LinkedList<TreeNode> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
            ans.add(curlist);
            direct = !direct;
        }
        return ans;
    }
}


/*
  bfs单队列 + 方向转变  也可以。


*/
