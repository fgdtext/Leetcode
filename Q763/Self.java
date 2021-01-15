package Q763;

import  java.util.*;
public class Self {
    
}
/*
二分思想  对一个串划分出多个段，比较困难，可以每次都划分成两段，按照划分原则，已经划分的两个字符串。
而且该二分思想，没有合并环节，因为题目要求划分的尽可能多，那么已经确立划分开的，不必要再去合并。


i 一直走， bmark 一直标记每一个i走过的字符。标记为占用。

当 i 处的字符， 在 amark 中出现过， 则说明左段应包含到 i 的位置。 则 amrk = copy(bmark).

最后  则实现了划分为 两段。

时间不够优秀。  我的 时间 逼近 nlgn 了。


太菜了啊。 只能想到  这个了。
*/

class Solution {
    List<Integer> ans;
    public List<Integer> partitionLabels(String S) {
        ans = new ArrayList();
        erfen(S);
        return ans;
    }
    public void erfen(String s){
        int[] amark = new int[26]; // 主要
        int[] bmark = new int[26]; // 立即增长
        int max = 0;
        amark[s.charAt(0) - 'a'] = 1;
        bmark[s.charAt(0) - 'a'] = 1;
        for(int i = 1; i < s.length(); i++){
            bmark[s.charAt(i) - 'a'] = 1;
            if(amark[s.charAt(i) - 'a'] != 0){
                amark = Arrays.copyOf(bmark, 26);
                max = i;
            }
        }
        // 不可再分
        if(max == s.length() - 1){
            // 加入该字符串到ansStrs中
            /*
                由于遵循 先序 。 所以 加入ans 的顺序就是所求的顺序。
            */
            ans.add(s.length());
            return;
        }
        erfen(s.substring(0, max+1));
        erfen(s.substring(max+1, s.length()));
    }
}

/*class
贪心   想不到啊。  怎么贪 。

贪心总是 想不到。
*/

class Ans {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int length = S.length();
        /*
         妙啊。  记录每个字符最后 出现 的  下标。

        */
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            /*
                妙啊  假设现在已经有  abc.... 则 通过last 知道有 abc........c..  则 end 应该在最后一个c的位置。
                现在，可知 该段字符串最短在 第二个c的位置。 因为第一个c之后的 字符，仍可能使得 end 后置。 
                直到 i == end 说明取到 一个字符串。太妙了啊。

            */
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
