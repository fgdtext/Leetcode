package Q424;

public class Self {
    
}



/*

滑动窗口

*/
 
/*
right 总是要右移， left 总是有条件移动。所以窗口的变化只有两种情况。
1. 只有right 右移， 那么 窗口变大一个，向右扩展，左侧边界不变。 整体 窗口变大。
2. right left 一块右移动， 那么实际窗口只是滑动，大小不变。
从这个情况来看， 窗口的大小是单增的。 那么什么时候增加，什么时候保持不变。？？？

实际上是： maxn 满足时增加，不满足时向右滑动。

假设输入  ： AABCABBB  k = 2
 L
 R
 AABCABBB  maxn = 1 满足right - left + 1 - maxn > k 可以扩大窗口
 LR
 AABCABBB  maxn = 2 满足right - left + 1 - maxn > k 可以扩大窗口
 L R
 AABCABBB  maxn = 2 满足right - left + 1 - maxn > k 可以扩大窗口
 L  R
 AABCABBB  maxn = 2 满足right - left + 1 - maxn > k 可以扩大窗口
 L   R
 AABCABBB  maxn = 3 满足right - left + 1 - maxn > k 可以扩大窗口  
 L    R
 AABCABBB  maxn = 3  不满足right - left + 1 - maxn > k 只能滑动窗口。大小不变。

 因为窗口是递增的， 要想窗口可以继续扩大，那么只能是maxn先扩大。maxn也是单增的。
 窗口滑动时，大小不变，那么滑动之后的窗口必然不需要计算，因为同样大小的窗口已经出现了，ans = right-left
 滑动时 ans 不会继续扩大。

  L    R
 AABCABBB  maxn = 3  不满足right - left + 1 - maxn > k 只能滑动窗口。大小不变。
   L    R
 AABCABBB  maxn = 4  满足right - left + 1 - maxn > k 可以扩大窗口

   L     R
 AABCABBB  

 那么 R-L就是最大窗口值。

 再举一个例子  AAAAAAAAAAAAABBBBBB  K = 2
这个很显然 子串 AAAAA AAAAA AAABB会是答案， 但是 最终 left 到right 窗口包含的是后半段AAAAAABBBBBB
显示ans = right-left 不是说left到right之间的子串，而是说窗口通过单增，窗口最终保持在什么样的大小。
也就是说，right-left是一个历史值，同样maxn也是一个历史值，说的是，在曾经在窗口内出现某个字符的最大次数。


在平移时，先对某个字符频率减1，再对某个字符频率加1.  随着平移的累加，就可能出现另外一个字符的频率超过maxn
因为在 ans1(窗口大小)  maxn1 的情况下，只能平移，那么平移之后，若没有超过maxn, 那么任然只能平移，窗口大小不变。
窗口大小不变，则ans1不变。若出现 超过maxn(记为maxn2) , 那么对于 ans1，maxn2这时，就可以扩大窗口有ans2。
这时，ans2和maxn2看是否满足扩大的条件，某则就平移。

例子  AAAAAAAAAABCDEFGTHJKL K=2 那么最长的是 AAAAAAAAAABC right 指向的后一个位置。  
在平移和扩大时，会出现AAAAAAAAAABC 此时可以扩大窗口到AAAAAAAAAABCD,然后就只能一直平移了。
那么A的频率会一直下降 ，但是可以看到，A频率下降，不会影响 maxn的扩大。


*/

class Ans2 {
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            // 打擂台求最大值 ， 是一个历史最大值
            // ans 何时会变大，
            /*
                若一直进入if(right - left + 1 - maxn > k) 那么 left和right会同时增大，
                窗口实际上右移动。但是大小不变，那么这个移动就是毫无意义的。
            */
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        // 设 right - left = ans
        return right - left;
    }
}