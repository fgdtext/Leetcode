package Q60;

import java.util.*;



class Solution2 {
    int count;
    char[] str;
    int n;
    int k;
    String ans;
    boolean[] vis;
    public String getPermutation(int n, int k) {
        str = new char[n];
        vis = new boolean[n+1];
        this.n = n;
        this.k = k;
        return dfs(0);
    }
    public String dfs(int cur){
        if(cur == n){
            count++;
            if(count == k) return new String(str);
            return null;
        }
        for(int i = 1; i <= n; i++) if(!vis[i]){
            str[cur] = (char)(i+'0');
            // 回溯
            vis[i] = true;
            String ret = dfs(cur+1);
            if(ret != null) return ret;
            vis[i] = false;
        }
        return null;
    }
}

/*
根据当前层数， 直接减去 该选择后，会有多少种可能，减去这些可能。


*/
class Solution3 {
    char[] str;
    int n;
    int k;
    String ans;
    boolean[] vis;
    int[] factor;
    public String getPermutation(int n, int k) {
        str = new char[n];
        vis = new boolean[n+1];
        factor = new int[n];
        factor[0] = 1;
        //  factor[i] = i! 
        for(int i = 1; i < n; i++){
            factor[i] = factor[i-1] * i;
        }
        this.n = n;
        this.k = k;
        k--;
        return dfs(0);
    }
    //  使用Cur 记录层数。
    public String dfs(int cur){
        if(cur == n){
            return new String(str);
        }
        for(int i = 1; i <= n; i++) if(!vis[i]){

            if(k > factor[n-cur- 1]){
                k -= factor[n-cur- 1];
                continue;
            }
            str[cur] = (char)(i+'0');
            // 回溯
            vis[i] = true;
            String ret = dfs(cur+1);
            if(ret != null) return ret;
            vis[i] = false;
        }
        return null;
    }
}

/*
cur == 0 则确定第一位数， 后边是 每种可能是(n-1)!种可能。用k-(n-1)!



*/
public class Self {
    public static void main(String[] args) {
        Solution2 so = new Solution2();
        so.getPermutation(5, 5);
    }
}

class Solution {
    public String getPermutation(int n, int k) {
        final int[] arr = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        StringBuilder permutation = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int cnt = arr[i];
            for (int j = 1; j <= n; j++) {
                if (visited[j]) { // 先排除已经用过的数字
                    continue;
                } 
                if (k > cnt) {   // 在没有用过的数字中选出满足 k <= cnt的j.
                    k -= cnt;
                    continue;
                }
                visited[j] = true;
                permutation.append(j);
                break;
            }
        }
        return permutation.toString();
    }
}

class Solution6 {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;
        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1; // 当前位置应该放 第order小
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];   // 如果用过了减0，没用过减1， 这样减到0，恰好是 第order小。
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }
}

