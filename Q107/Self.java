package Q107;

import java.util.*;


/*
层次遍历 ： 从最后一层开始   dfs 版本  ArrayList 随机访问，最后逆置
也可以写成bfs 使用队列。 直接用 LinekList  在首部添加 一层元素（list）
*/

class TreeNode {    
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  class Solution1 {
    List<List<Integer>> list;
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        list = new ArrayList<>();
        dfs(root, 0);
        Collections.reverse(list);
        return list;
    }
    public void dfs(TreeNode node, int cur){
        if(node == null) return;
        if(list.size() <= cur)  list.add(new ArrayList<>());
        list.get(cur).add(node.val);
        dfs(node.left,cur+1);
        dfs(node.right,cur+1);
    }
}
