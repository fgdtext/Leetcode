package Q684;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Self{

}

/*


使用并查集， 如果a的根和b的根是同一个根，那么 边(a,b)就回造成环，那么该边一定要删除。而且还符合删除靠后的特性。因为该边必须删。


*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
       int lene = edges.length;
       int[] parent = new int[lene+1];
       for(int i = 1; i <= lene; i++){
        parent[i] = i;
       }
       for(int[] e : edges){
            int a = find(e[0], parent);
            int b = find(e[1], parent);
            if(a == b){
                return e;
            }else{
                parent[a] = b;
            }
       }
       return null;
    }
    // 压缩路径
    public int find(int x,int[] parent){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}