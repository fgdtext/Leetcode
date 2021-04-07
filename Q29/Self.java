package Q29;


/*

数论题： 假设  a/b > 1 否则 ans=0
若 a > 2*b  则 a/b > 2*b /b = 2*1 同理
若 a > 2*2*b 则  a/b > 2*2=4;
若 a < 2*2*2*b 则 a/b < 2*2*2 = 8;
说明 4 < a/b < 8;
a/b = (a-4*b)/b + 4    假设 c = a-4*b  则有  a/b = c/b +4;  b < c < a  这样就可以不断的缩小a。 a每次下降的倍数不止2.
所以很快。

考虑边界： dividend = 0;







*/

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        so.divide(2147483647,2);
    }
}




class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(divisor == 1) return dividend;
        if(divisor == -1) {
            if(dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            return -dividend;
        }
        boolean isneg = dividend < 0 && divisor > 0 || divisor < 0 && dividend > 0;
        if(dividend > 0) dividend = -dividend;
        if(divisor > 0) divisor = -divisor;
        int ans = div(dividend, divisor);
        if(isneg) return -ans;
        return ans;
    }
    // a:被除数  b:除数   都是负数。  保证不适用long, 且中间不会出现越界的数出现。
    int div(int a, int b){
        if(a > b) return 0; // 负数 符号要变
        int num = 1;
        int tb = b;
        while((a >> 1) < tb){  // 如果对b翻倍，就可能越界， 所以算则 a减半。不能用 <= 号  由于 a>>1可能牺牲精度，本来是 tb*2不满足的情况变满足
            num <<= 1;
            tb <<= 1;   // 负数：由于 a/2 < tb  而a没有越界（负数边界） 所以 2*tb也不会越界
        }
        return num + div(a-tb, b);
    }
}

/*class
数论题： 假设  a/b > 1 否则 ans=0
若 a > 2*b  则 a/b > 2*b /b = 2*1 同理
若 a > 2*2*b 则  a/b > 2*2=4;
若 a < 2*2*2*b 则 a/b < 2*2*2 = 8;
说明 4 < a/b < 8;
a/b = (a-4*b)/b + 4    假设 c = a-4*b  则有  a/b = c/b +4;  b < c < a  这样就可以不断的缩小a。 a每次下降的倍数不止2.
所以很快。

考虑边界： dividend = 0;
*/