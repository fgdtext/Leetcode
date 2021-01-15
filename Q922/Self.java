package Q922;

public class Self {
    
}
/*
基于交换。  jishu : 找下一个在偶数位置的 奇数。
oushu : 找下一个在 奇数 位置 的 偶数。


这种题 :  都是 尽可能 的  原地 操作， o(n) 算法。

重点 ：  奇偶性 使用 位运算 来判定。

*/

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int oushu = 1;
        int jishu = 0;
        while(oushu < A.length || jishu < A.length){
            while(oushu < A.length && (A[oushu]&1) == 1){
                oushu += 2;
            }
            while(jishu < A.length && (A[jishu]&1) == 0){
                jishu += 2;
            }
            if(oushu < A.length && jishu < A.length){
                int temp = A[oushu];
                A[oushu] = A[jishu];
                A[jishu] = temp;
            }
        }
        return A;
    }
}