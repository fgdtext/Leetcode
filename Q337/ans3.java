package Q337;

/*

优化： 问题：上边的定义会导致，爷爷到孙子的跨越。
    定义： 若偷该结点，则该点最大为  ： 两个儿子不偷的时候的最大值和，加上本结点，
           若不偷该结点： 则为  ： 两个孩子(不一定偷不偷)的最大和。
所以 每个结点都有两种状态需要记录。

记录方式：  使用两个map 分别记录偷于不偷时，每个结点的最大偷钱数。可以，但是好像不必要。
            分析可得， 父结点，总是只需要其子结点的情况即可。 所以变成了后序遍历的问题。
            只需要先求出 两个子节点的偷与不偷的情况，就可以得到本结点偷与不偷的情况。

该dp 问题具有一个特性是 ： 状态转移总是相邻的。 dfs 后返回到本层恰好是本层所依赖的。 本质就是个后序递归。
该dp 只有规划， 本质没有记忆。 没有向其他记忆化递归一样。 可能依赖比较远的状态。
*/


class Solution {
    public int rob(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }
}