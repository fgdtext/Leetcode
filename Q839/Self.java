package Q839;


public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        String[] ss = {"tars","rats","arts","star"};
        so.numSimilarGroups(ss);
    }
}
/*
并查集， 枚举任意两个字符串，
              判断是否在同一组，若在同一组，把两个字符串所在的集合合并。
              并查集 两个元素相通，则连接两个元素所在的集合，初始每个元素独自一个集合。
*/
class Solution {
    public int numSimilarGroups(String[] strs) {
        int len = strs.length;

        int[] parent = new int[len];
        for(int i = 0; i < len; ++i) parent[i] = i;
        for(int i = 0; i < len; ++i){
            //"tars","rats","arts","star"
            for(int j = i+1; j < len; j++){
                if(isConcat(i, j, parent)) continue; // 在一组中的不需要再去判断。
                if(isConnect(strs[i], strs[j])){
                    concat(i, j, parent);
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < len; ++i){
            if(parent[i] == i) ans++;
        }
        return ans;
    }
    public boolean isConcat(int x, int y,int[] parent){
        return find(x, parent) == find(y, parent);
    }
    public void concat(int x, int y,int[] parent){
        int a = find(x, parent);
        int b = find(y, parent);
        if(a != b){
            parent[a] = b;
        }
    }
    public int find(int x, int[] parent){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
    //"tars","rats","arts","star"
    public boolean isConnect(String a,String b){
        int num = 0;
        for(int i = 0; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}