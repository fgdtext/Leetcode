package Q7;

/*
要注意 ： 最大 正整数 ： (1<<31) - 1
         最大负整数 ：  (1<<31)   或者 0-((1<<31)-1)-1



*/

public class Self{
    public static void main(String[] args) {
        System.out.println((1<<31));
        System.out.println(0-((1<<31)-1)-1);
    }
}


class Solution {
    public int reverse(int x) {
        int flag = x >= 0? 1 : -1;
        long ans = 0;
        x = x >= 0? x : -x;
        while(x > 0){
            ans = ans*10 + x%10;
            x/=10;
        }
        if(ans > (1 << 31)-1){
            if(flag == 1) return 0 ;
            if(flag == -1 && ans > (1 << 31)) return 0;
        }
        return flag > 0 ? (int)ans : -(int)ans;
    }
}

/*

32位有符号整数，最大2147483647，最小-2147483648；

ans 的该判断是多余的。 因为 10位数时才会溢出，但是10位数的第一位， 
一定只能是 1，或则 2.所以反转之后，最后一位一定是 ，1，2
必然不会 > 7.
 (rev == Integer.MAX_VALUE / 10 && pop > 7)

*/

class Solution2 {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 ) return 0;
            if (rev < Integer.MIN_VALUE/10 ) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

class ans {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

