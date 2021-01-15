package Q1018;

import java.util.*;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a = {1,0,0,1,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,0,0,1};
        so.prefixesDivBy5(a);
    }
}
/*
实践证明：位移会快很多。

*/

class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int sum = 0;
        List<Boolean> list = new ArrayList<>();
        for(int e : A){
            sum = ((sum << 1) + e) % 5;
            list.add(sum == 0);
        }
        return list;
    }
}
/*
时间 ： 5.28%

用位移试一试
*/
class Self2 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int sum = 0;
        List<Boolean> list = new ArrayList<>();
        for(int e : A){
            sum = sum*2 + e;
            list.add(sum % 5 == 0);
            sum %= 5;
        }
        return list;
    }
}

/*class
[1,0,0,1   ,0,1,0,0,    1,0,1,1    ,1,1,1,1,   1,1,1,1,    0,0,0,0,    1,0,1,0   ,0,0,0,1,   1,0,1,0,   0,0,1]
*/


