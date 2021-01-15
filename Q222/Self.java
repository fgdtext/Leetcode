package Q222;

public class Self {
    
}


   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
时间复杂度：T(n) = T(n / 2) + logn 使用主定理的特殊情况，可以求得时间复杂度是O(logn * logn)

我们来对 root 节点的左右子树进行高度统计，分别记为 left 和 right，有以下两种结果：

left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。
        所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。
        再对右子树进行递归统计。
left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。
        同理，右子树节点 +root 节点，总数为 2^right。再对左子树进行递归查找。



*/
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
           return 0;
        } 
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}
