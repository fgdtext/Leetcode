package Q190;

public class Self{
    public static void main(String[] args) {
        int a = -2;
        for(int i = 0; i < 32; i++){
            if(i % 8 == 0) System.out.print(" ");
            System.out.print((a&(1 << i)) == 0 ? 0 : 1);
        }
        /*
1111 1111 1111 1111 1111 1111 1111 001

1001 1111 1111 1111 1111 1111 1111 111
        */
        for(int k = 0; k < 32; k++){
            System.out.println();
            a >>= -1;
            System.out.println(a);
            for(int i = 0; i < 32; i++){
                if(i % 8 == 0) System.out.print(" ");
                System.out.print((a&(1 << i)) == 0 ? 0 : 1);
            }
        }
}
}
/*

负数以补码表示。 正数的补码就是自身

>>> 无符号右移 ： 将数看作是无符号数， 右移时高位补0. 
                    若原值是负数，第一次相当于先变为正数，之后每次都相当于 / 2
>>  有符号右移 ： 将数看作是有符号数，右移动时补符号位。是1补1，是0补0  。不管正负其值相当于 / 2
                    溢出的结果是，32位1，或者32位0.
<<  无符号左移 ： 左移动时相当于无符号左移动，32位整体左移，
                    若负数次高位是0，则左移会吞掉符号
                    每次都相当于 * 2 ， 当越来越大时，会溢出，导致变正数。



*/

/*
前后十六位先交换位置。可以通过一次性左移和右移16位来获取移动后的结果
再对每一段16， 的前后8位，做一次性移动减缓。

*/

 class Solution {
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits(int n) {
        n = n >>> 16 | n << 16;
        n = n >>> 8 & M8 | (n & M8) << 8;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 2 & M2 | (n & M2) << 2;
        return n >>> 1 & M1 | (n & M1) << 1;
    }
}
