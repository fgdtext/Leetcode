package Q114;

public class Self {
    
}



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
    都用右指针连接下一个。
    使用前序遍历，  需要解决的一个问题是 ： 递归过程会改变 pre 结点的指针。
    假设 pre == root .  在对root 的左结点递归时， 会改变 root.right的指向。导致右侧子树丢失。
    所以 使用 变量 right 保存 右侧子树。

    还有 由于要改为单链表， 左针要置空。 置空时机，放在 后序的位置， 一定不会导致 丢失结点。



  */
class Self1 {
    TreeNode pre;
    public void flatten(TreeNode root) {
        predfs(root);
    }
    public void predfs(TreeNode root){
        if(root == null) return;
        TreeNode right = root.right;
        if(pre != null){
            pre.right = root;
        }
        pre = root;
        predfs(root.left);
        predfs(right);
        root.left = null;
    }
}
/*
简化版本。

*/

class Solution {
    TreeNode pre;
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode right = root.right;
        if(pre != null){
            pre.right = root;
        }
        pre = root;
        flatten(root.left);
        flatten(right);
        root.left = null;
    }
}