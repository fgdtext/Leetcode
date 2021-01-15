package Q77;

import java.util.ArrayList;
import java.util.List;

public class Self {
    
}

/*
回溯法。 
求  c(n,k) 的组合数(1，n) 选出k个数。得到所有组合。 减枝


*/


class Solution {
    int n;
    int k;
    int[] arr;
    List<List<Integer>> ans;
    public List<List<Integer>> combine(int n, int k) {
        arr = new int[n+1];
        ans = new ArrayList<>();
        this.n = n;
        this.k = k;
        dfs(1,0);
        return ans;
    }

    public void dfs(int cur, int kk){
        if(k-kk > n+1 - cur) return;  // 减枝， 因为 所需要的个数 大于 后边所有个数，所以该分支已经不可能成功了。
        if(kk == k){
            List<Integer> list = new ArrayList<>();
            for(int it : arr){
                if(it == 0) continue;
                list.add(it);
            }
            ans.add(list);
            return;
        }
        if(cur == n+1){
            return;
        }
        arr[cur] = cur;  // 有
        dfs(cur+1,kk+1);

        arr[cur] = 0; // 无
        dfs(cur+1,kk);
    }
}