package Q337;

/**
 *   本题的误区： 这不是分层打劫的问题，因为存在 打劫了叔叔的同时，也打劫了侄子。而他们是相邻层。
 */

/* 
暴力递归：  时间爆炸。
打不打劫根节点，影响了打劫一棵树的收益：
打劫根节点，则不能打劫左右子节点，但是能打劫左右子节点的四个子树。
不打劫根节点，则能打劫左子节点和右子节点，收益是打劫左右子树的收益之和。
*/


class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
}

class Solution1 {
    public int rob(TreeNode root) {
        if(root == null) return 0;
        int maxinclude = root.val;
        if(root.left != null){
            maxinclude += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null){
            maxinclude += rob(root.right.left) + rob(root.right.right);
        }
        int maxexclude = rob(root.left) + rob(root.right);
        return Math.max(maxinclude, maxexclude);
    }
}




