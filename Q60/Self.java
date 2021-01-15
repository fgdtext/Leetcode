package Q60;

public class Self {
    
}

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
class Solution {
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