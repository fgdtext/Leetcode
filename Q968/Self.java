package Q968;

import java.util.Arrays;

public class Self {
    
}

/*

关于dp 递归和递推的区别。 使用递推就要搞清楚计算顺序，从basecase开始计算，从二维数组来看，某位置的值必须依赖已经求出的部分，。
所以对顺序有要求。
而在很多题里边，例如，树形dp. 因为计算顺序并非沿着某一方向来的，所以使用递归直接来管理顺序即可，需要哪一个值，就去递归求哪一个值。。

递推在计算顺序清晰的情况下，思考会简便。
但是在 某些题中，就要 依赖于 递归来进行。例如树中，就不好去定位一个元素，或者去表示树中的某一个状态。


还有一点： dp 在很多时候用到了贪心的思想， 但是实际上，贪心并不一定正确。或者说贪心并非一定最优。证明很困难。
所以，我们要列举所有情况。

动态规划和贪心对待最优的差别: 
   贪心是默认某种规则可以取得最优，我们直接贪心，一口口吃下去，保证每步按照最优规则来。(很多题的最优规则并不是显而易见的，证明很有难度。)
     该规则使得我们不需要去求所有情况。
   动态规划： 我们不去默认某种规则，我们做所有可能的选择。 在不考虑重复子问题的情况下，我们遍历的是一颗完整的递归树。我们遍历了所有情况。
   例如从树型dp来看。 不管从哪个结点向下看， 我们遍历了她的子节点装或者不装的所有情况，所以，我们可以选出最优。
   由于我们遍历了所有情况，我们我们就一定可以选出最优。

   


*/
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
  class Solution2 {
    int[] dp;
    int count;
    int max;
    public int minCameraCover(TreeNode root) {
        dp = new int[10000];
        Arrays.fill(dp, -1);
        dfs(root, 1);
        for(int i = 0; i <= max; i++){
            System.out.print(dp[i]);
        }
        // 已经准备好dp数组

        // 开始计算base case 
        return 0;
    }
    // cur : 数组下标，表示root在 数组的什么位置。
    public void dfs(TreeNode root,int cur){
        if(root == null) return;
        if(max < cur) max = cur;
        count++;
        dp[cur] = 0;
        dfs(root.left, cur*2);
        dfs(root.right, cur*2+1);
    }
}



class Solution {
    int[] dp;
    int count;
    int max;
    public int minCameraCover(TreeNode root) {
        dp = new int[10000];
        Arrays.fill(dp, -1);
        dfs(root, 1);
        for(int i = 0; i <= max; i++){
            System.out.print(dp[i]);
        }
        // 已经准备好dp数组

        // 开始计算base case 
        return 0;
    }
    // cur : 数组下标，表示root在 数组的什么位置。
    public void dfs(TreeNode root,int cur){
        if(root == null) return;
        if(max < cur) max = cur;
        count++;
        dp[cur] = 0;
        dfs(root.left, cur*2);
        dfs(root.right, cur*2+1);
    }
}
/*
笨猪爆破组 的题解。

1.当前节点 root 放了相机（当前子树的相机数，保底为1） (应该也是四种情况，都有的情况)
    左右儿子都没有放相机，都被父亲监控
    minCam(root.left, false, true) + minCam(root.right, false, true)
    左儿子放了相机，被监控，右儿子没有相机，被父亲监控
    minCam(root.left, true, true) + minCam(root.right, false, true)
    左儿子没有相机，被父亲监控，右儿子放了相机，被监控
    minCam(root.left, false, true) + minCam(root.right, true, true)
2.当前节点 root 没有相机，但被父亲监控了
    两个儿子都放了相机，被监控
    左儿子放了相机，被监控，右儿子没有相机，没有被父亲和自己监控，但被自己儿子监控
    右儿子放了相机，被监控，左儿子没有相机，没有被父亲和自己监控，但被自己儿子监控
    两个儿子都没有相机，没有被父亲和自己监控，但被自己儿子监控
3.当前节点 root 没有相机，也没有被父亲监控，是被儿子监控
    两个儿子都放了相机，被监控
    左儿子有相机，被监控，右儿子没有相机，没被父亲和自己监控，被自己儿子监控
    左儿子没有相机，没被父亲和自己监控，被自己儿子监控。右儿子有相机，被监控

优化 ： 
withCam ：当前子树 root 有相机，情况下的minCam
noCamWatchByDad：当前子树 root 没有相机，被父亲监控，情况下的minCam
        若父节点有监控， 且该结点没有监控，则该结点就是被父节点监控的情况。
noCamWatchBySon ：当前子树 root 没有相机，被儿子监控，情况下的minCam
        若父节点没有监控，该结点也没有监控，则该结点就是被子节点监控的情况。


从上可以看到，dp 考虑了所有可能的情况，遍历了完整的回溯树。所以不是贪心的。而是完备的，一定可行的。

*/
/*
const minCameraCover = (root) => {
    // 以root为根节点的子树，放置的最少相机数
    const minCam = (root, placeCam, watched) => {
      if (root == null) {  // 遍历到null节点
        if (placeCam) {    // 父节点问自己有相机的情况，但臣妾办不到
          return Infinity; // 返回一个无穷大，让这个返回值失效
        } else {
          return 0;
        }
      }
      if (placeCam) {        // root放置相机
        return 1 + Math.min( // root放了相机，相机数有 1 保底
          minCam(root.left, false, true) + minCam(root.right, false, true), 
          minCam(root.left, true, true) + minCam(root.right, false, true), 
          minCam(root.left, false, true) + minCam(root.right, true, true) 
        );  
      } else {
        if (watched) { // root没放相机，但被父亲监控了
          return Math.min(
            minCam(root.left, true, true) + minCam(root.right, true, true),
            minCam(root.left, true, true) + minCam(root.right, false, false), 
            minCam(root.left, false, false) + minCam(root.right, true, true), 
            minCam(root.left, false, false) + minCam(root.right, false, false) 
          );
        } else {      // root没有相机，也没被父亲监控，被儿子监控了
          return Math.min(
            minCam(root.left, true, true) + minCam(root.right, true, true), 
            minCam(root.left, true, true) + minCam(root.right, false, false), 
            minCam(root.left, false, false) + minCam(root.right, true, true) 
          );
        }
      }
    };
    return Math.min(
      minCam(root, true, true),  // 根节点有相机
      minCam(root, false, false) // 根节点没有相机，因为没有父亲，没有被父亲监控，是被儿子监控
    );
  };


  */