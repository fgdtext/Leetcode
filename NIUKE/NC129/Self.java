package NIUKE.NC129;

import java.util.*;
/*
给定一个非负整数 N，返回 N! 结果的末尾为 0 的数量。


我们知道 0由5产生， 任意一个 偶数 * 5 都能得到一个0.  我们可以对所有数进行因式分解，必须 10 = 5*2. 那么10! = 5*2 * 9!
也就是说，每当我们找到一个因式含有5的数，那么这个数就能产生一个 0 .

2. 5的倍数，可以产生一个0 . 而 25 = 5*5 含有两个5，则可以产生两个0. 类推 125 = 5*5*5可以残生3个0.

我们可以找到所有5的倍数k个 count += k  但是这k个数中还包含了25，125的倍数。那么 再求25的倍数的个数kk， count += kk 此时kk就不用*2.
因为是25的倍数，一定是5的倍数，已经加过一次了。


*/

 class Solution {
    /**
     * the number of 0
     * @param n long长整型 the number
     * @return long长整型
     */
    public long thenumberof0 (long n) {
        // write code here
       // if(n==0)return 1;
        long count=0;
        while(n!=0){
            count+=n/5;
            n=n/5;
        }
        return count;
    }       
}