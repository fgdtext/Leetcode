package Q216;

import java.util.ArrayList;
import java.util.List;

/*
连着几天写的组合， 
这次是， 1~9元素的，不重复组合。和为 n 的 k 个数

*/

class Solution {
    List<List<Integer>> ans;
    int n;
    int k;
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.n = n;
        this.k = k;
        ans = new ArrayList<>();
        dfsss(1, 0, 0, new ArrayList<>());
        return ans;     
    }
    void dfsss(int cur,int add,int count, List<Integer> list){
        if(add == n && count == k){
             ans.add(new ArrayList<>(list));
             return;
        }
        if(count == k || add == n) return;
        if(cur == 10) return;
        list.add(cur);
        dfsss(cur+1,add+cur,count+1,list);
        list.remove(list.size()-1);
        dfsss(cur+1,add,count, list);
    }
}