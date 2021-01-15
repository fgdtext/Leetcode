package Q52;

public class Self {
    
}
/*

i : 标记当前是 第几行，  每行 只给 一个 皇后 确定 位置， 然后 标记 她占用的 列 对角线。

该 标记 是  回溯 的。

需要3个数组， 分别 表示  列， 正对角线， 副对角线。

colmark[n] : 标记占用 列

a_mark[2*n - 1]  : 标记占用 正对角线  

b_mark[2*n - 1]  : 标记占用 副对角线

皇后坐标 (i,j) 和 正负对角线的关系。


对每一个 i  遍历  0 ~ j-1

n 皇后 还是 很简单的 啊 。 哈哈哈哈哈哈哈。


*/
class Solution {
    int ans;
    public int totalNQueens(int n) {
        // 果然是周末了哈 一个 hard 
        dfs(0, n, new boolean[n], new boolean[2*n-1], new boolean[2*n-1]);
        return ans;
    }
    public void dfs(int i, int n, boolean[] colmark, boolean[] a_mark, boolean[] b_mark){
        if(i == n){
            ans++;
            return;
        }
        for(int j = 0; j < n; j++){
            if(!colmark[j] && !a_mark[i-j+n-1] && !b_mark[i+j]){
                colmark[j] = true; a_mark[i-j+n-1] = true; b_mark[i+j] = true;
                dfs(i+1, n, colmark, a_mark, b_mark);
                colmark[j] = false; a_mark[i-j+n-1] = false; b_mark[i+j] = false;
            }
        }
    }
}