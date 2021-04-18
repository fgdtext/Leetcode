package Q538;

public class Self {
    
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
/*
没做出来，一直漏case

如果不用 反 中序 遍历，就是一个 中等难度的题。
*/

  class Solution2 {
    public TreeNode convertBST(TreeNode root) {
        dfs1(root);
        dfs2(root);
        return root;
    }
    public int dfs1(TreeNode root){
        if(root == null) return 0;
        int a = dfs1(root.left);
        int b = dfs1(root.right);
        int c = root.val;
        root.val = root.val + b;
        return a + b + c;
    }
    public void dfs2(TreeNode root){
        if(root == null) return;
        if(root.left != null) root.left.val = root.left.val + root.val;
        if(root.left != null && root.left.right != null){
            root.left.right.val = root.left.right.val + root.val;
        }
        dfs2(root.left);
        dfs2(root.right);
    }
    // public int dfs3(TreeNode root){
    //     if(root == null) return 0;
    //     return dfs3(root.left) + root.val;
    // }
}


/*class
使用中序的原因就是， 二叉搜索树的中序遍历 有序。。 
*************888
树的相关概念

反中序遍历

sum 用的 妙 
全局变量在树的递归的作用。 sum 可以跟随 遍历的 线索， 不断的累积 线索顺序的和。这是递归本身不能办到的。
因为从反中序的线索，来看，指针从右子树跳到左子树不是连贯的，无法把 一个值 直接送到 左子树的第一个遍历结点中去。

而递归的返回值，只能按照层的上下关系去 传递。是非线索顺序的。

另外通过参数值，也可以实现 线索顺序。

*/
// 通过全局变量 记录 线索累积值。
class Ans {
    int sum = 0;  

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}


// 通过函数参数 ，记录 线索 累计值。
/*
使用函数参数的方式要 结合 返回值，或者 使用 自定义类型 使得参数为 引用类型传递。可以追踪 线索。

还是全局变量省事
*/

class Solution {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }
    
    public int dfs(TreeNode root, int val){  // 值引用，结合 返回值。否者递归中val的变化，不会返回到本层。
        if(root != null){
            val = dfs(root.right, val);

            val = val + root.val;
            root.val = val;

            val = dfs(root.left, val);
        }
        return val;
    }
}
