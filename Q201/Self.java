package Q201;


/*
ans 1:
二进制 运算的相关知识：
                    1. 从 n 与到 m 则  若 n 的有效位数，不等于 m 则 结果一定为 0 .
                         m :  01111111111
                         n:   11111111111
                         从 m 与到 n 则 一定有
                              01111111111    位数(1 ~ i)
                              10000000000    位数(1 ~ i+1)
                              ...
                              11111111111    位数(1 ~ i+1)
                              即若位数不相等，则m 自增到n的过程中，一定会经历(1 ~ i)  全为 0的情况。
                              而且， 第 i+1 又不同， 所以最后结果一定为 0. 
                        二进制自增的突变 ： 每当后边构成连续的1，则自增将会引起后部的突变。
                    2. 此时问题转化为 m 和 n 有效位相同的情况，若m 和 n 有效位相同
                       此时将转化为 公共前缀的问题。公共前缀将会被保留。后边的位全部会为 0 .
                       因为公共前缀在 自增过程中永远不会变。
                       此时从左向右看，当第i 位开始不再相同(脱离公共前缀)，则，利用性质1 可知，从该位开始只后一定全为0


ans2 : 利用 Brian Kernighan 算法  n = n & (n - 1) , 一定会将 最右侧的 1 变为0.
       还是公共前缀的思想。 这里利用递减n 来做。
       1 . 每次消除右侧一个位的1  然后和m比较，若m不等于 n ，则继续消除。
            111010100 - > ......->100000000 -> 00000000
       2 . 情况1，m n 有效位数不同，则 n 先被消除到 100000000，此时仍有 m < n. 再次消除 n = 0. 此时说明没有公共前缀。
       3 . 情况2. 若 m n 有公共前缀。 
                       m  11100100001
                       n  11100110111   
                          11100110000   此时仍有m < n ，再次消除
                          11100100000   此时 n <= m  恰好就是公共前缀。

*/



class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        
        int ans = m;
        int len = n - m + 1;  // 横跨 数字个数。
        int k = 0;

        if(m == 0) return 0;
        if(m == 1) return len == 1 ? 1 : 0;           // m == 1 时， 若 数字个数的跨度大于1， 则返回0.
        int max = (1<<31) - 1;                        // pc 初始值： 111111111111111111111111111111111111
        for(int pc = max; k < 31 && (pc&m) != 0;){
            k++;  
            pc = ~((pc << 1) ^ max);                  // 后边补0
        }
        for(int i = 0; i < k; i++){
            if(len > (1<<i)){
                // 该 位 置 0 .
                ans = ans & (~(1<<i));
            }else{
                // m& n 的该位置 是 0， 则 该位置 0 
                if(((m&n)&(1<<i)) == 0) ans = ans & (~(1<<i));
                else ans = ans | (1<<i);   //  置 1
            }
        }
        return ans;
    }
}

/*class
1073741824
2147483647
2147483647
// 这里没写符号位
100 0000    0000 0000     0000 0000     0000 0000
*/

class Solution2 {
    public int rangeBitwiseAnd(int m, int n) {
        
        int ans = m;
        int len = n - m + 1;  // 横跨 数字个数。
        int k = 0;

        if(m == 0) return 0;
        if(m == 1) return len == 1 ? 1 : 0;           // m == 1 时， 若 数字个数的跨度大于1， 则返回0.
        int max = (1<<31) - 1;                        // pc 初始值： 111111111111111111111111111111111111
        for(int pc = max; k < 31 && (pc&m) != 0;){
            k++;  
            pc = ~((pc << 1) ^ max);                  // 后边补0
        }
        for(int i = 0; i < k; i++){

            if(len > (1<<i)){
                // 该 位 置 0 .
                ans = ans & (~(1<<i));
            }else{
                // m& n 的该位置 是 0， 则 该位置 0 
                if(((m&n)&(1<<i)) == 0) ans = ans & (~(1<<i));
                else ans = ans | (1<<i);   //  置 1
            }
        }
        return ans;
    }
}

// ans1 :
class Solution3 {
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }
}

public class Self{
    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.rangeBitwiseAnd(5, 12));
    }
}