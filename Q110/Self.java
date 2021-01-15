package Q110;


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
/*
判断一棵树 是否是 平衡二叉树

*/



class Solution {
    // 这个flag用的妙啊， hhhhhhhhh
    //   其实这里 不需要 这个flag , 如果根节点的 after(root) 返回高度 > 0 则说明， 整课树是平衡的。
    boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        after(root);
        return flag;
    }
    // 返回 该 树 的高度， 若 发现不平衡，则直接返回  -1
    public int after(TreeNode root){
        if(root == null) return 0;
        int l = after(root.left);
        if(l == -1) return -1; // 
        int r = after(root.right);
        if(r == -1) return -1;

        // 这里不需要 这个判断。因为是后序遍历，反正上边都已经递归完了。如果是中序，那么中间可以判断一下，剪枝。
        // 所以该判断应该放在 l r 中间
       // if(l == -1 || r == -1) return -1;
        if(Math.abs(l-r) > 1) {flag = false; return -1;}
        return l > r ? l+1 : r+1;
    }
}