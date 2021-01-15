package Q100;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }

/*
后序遍历： 验证两颗树是否完全相同。

提交成功
*/


public class self {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(q == null && p == null) return true;

        if(q != null && p != null){
            boolean leftis = isSameTree(p.left, q.left);
            boolean rightis = isSameTree(p.right, q.right);
            return leftis && rightis && (p.val == q.val);
        }
        return false;
    }
}