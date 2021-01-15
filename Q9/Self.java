package Q9;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        so.isPalindrome(123);
    }
}
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        if(x == 0) return true;
        if(x % 10 == 0) return false;
        int y = 0;
        int t = x;
        while(t != 0){
            y = y*10 + t%10;
            t /= 10;
        }

        return x == y;
    }
}



/*class
    一次遍历即可。 自己写的是把整个数反转。再比较。但是问题是，数字反转后可能回溢出int。所以反转后半截。
    问题: 如何才是反转到一半，怎么找一半。  当剩余没有反转部分 <= 后半截反转部分说名到一半
    为什么呢: 因为这两个数的大小和数字无关，只和位数有管，剩余的小了，说明所占位数少了。
    答案是 logn. 整体反转是 ：2logn. n为数值。  logn:位数。
*/

class Ans {
    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
