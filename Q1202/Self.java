package Q1202;

import java.util.*;
public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] p = {{0,3},{1,2}};
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(0); a.add(3);
        List<Integer> b = new ArrayList<>();
        b.add(1); b.add(2);
        pairs.add(a); pairs.add(b);
        so.smallestStringWithSwaps("dcab", pairs);
    }
}


/*
使用并查集： 

我们假设 若下标属于同一组，则其 根相同。 则可以通过并查集来表示。

我们这么想， 若 a 属于集合 set(a) ,b属于 集合 set(b) ，那么ab就会将两个集合合并在一起，要一起参与排序。所以恰好是并查集。

关于排序: 使用int[26] 对每个集合分配一个 int[26] s
将集合中的所有下标，统计其字符频率， 那么最后的顺序就是  int[26]的顺序组合。
*/
class Solution {
    int[] ind;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int lens = s.length();
        ind = new int[lens];
        // 根指向自身。
        for(int i = 0; i < lens; i++){
            ind[i] = i;
        }
        // 对ind 构建并查集。
        for(List<Integer> e : pairs){
            int a = find(e.get(0));
            int b = find(e.get(1));
            if(a != b){  // 此时需要合并。
                ind[b] = a; // b的父结点是a.
            }
        }
        // 这个时间挺快，但是空间利用率比较差。
        int[][] map = new int[lens][26];
        for(int i = 0; i < lens; i++){
            int root = find(i);
            map[root][s.charAt(i)-'a']++;
        }
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < lens; i++){
            int[] fre = map[find(i)];
            for(int j = 0; j < 26; j++){
                if(fre[j] > 0){
                    ans.append((char)(j+'a'));
                    fre[j]--;
                    break;
                }
            }
        }
        return ans.toString();
    }
    // 压缩路径的 并查集查找root方法
    public int find(int x){
        if(ind[x] != x){
            ind[x] = find(ind[x]);
        }
        return ind[x];
    }
}