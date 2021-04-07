package Q10;

public class Dp {
    
}

/*
dp[i][j] 表示 s[0~ i-1], p[0 ~ j-1] 是否匹配。  面向dp[i][j]是不考虑ij之后的内容的。
dp[i][j]是一个独立的子问题。该子问题，只依赖于他自己的子问题 dp[<=i][<=j]
dp[i][j]  依赖于之前的 dp[<=i][<=j]的结果。

题解
https://leetcode-cn.com/problems/regular-expression-matching/solution/shi-pin-tu-jie-dong-tai-gui-hua-zheng-ze-biao-da-s/
img Q10_01(base case)  Q10_02 
*/ 


class Solution {
    public boolean isMatch(String s, String p) {

        boolean table[][] = new boolean[s.length() + 1][p.length() + 1];

        table[0][0] = true; //空串总是能匹配空串。
        // table[1][1] : 表示位置0和位置0是否匹配。
        // table[0][1] : 由于 题目给出的案例中 *号前边必有其他的字符，所以p串绝不会在位置0出现 * 号。若出现字母或则'.', 那么一定匹配失败。就是默认的false.
        // 以下是，s串为空串，p不为空时的匹配情况
        for (int col=1; col<table[0].length; col++) {
            char ch = p.charAt(col-1);
            // 可能出现 .*.*.*.*.*a*b*c*类似的 是可以匹配空串的。类似这样的以 *结尾的串总是可以匹配空串。
            // 如果出现 .*a*b*cc*.*b* 可以发现该串从第二个*开始就不能匹配空串了。 所以后边的*的位置，都是依赖前边的。
            //不是*号的位置，一定是false。
            if (col > 1 && ch == '*') {
                    table[0][col] = table[0][col-2]; 
            }
        }
        // 
        for (int row=1; row<table.length; row++) {
            char ch1 = s.charAt(row-1);
            for (int col=1; col<table[row].length; col++) {
                char ch2 = p.charAt(col-1);
                if (ch1==ch2 || ch2 == '.') {
                    table[row][col] = table[row-1][col-1];
                } else if (ch2 == '*') {
                    // col = 1时，p为空串。
                    if(col > 1) {
                        if (table[row][col-2]) {
                            table[row][col] = true; // * 前面的字符出现0次
                        } else {
                            char prev = p.charAt(col-2); // *号前边的字符。
                            if (prev== ch1 || prev == '.') {
                                table[row][col] = table[row - 1][col]; // * 前面的字符出现多次  ，每次删除s中一个ch1.直到去查看匹配0个是否匹配
                            }
                            // aabcaaaaaaaaaa 匹配 aab*c*a*
                            // aabcaaaaa 匹配 aab*c*a*
                            // 后边a的长度并不影响匹配 aab*c*a*的结果。所以aabcaaaaaaaaaa 等价于aabcaaaaa 对aab*c*a*的匹配
                            // 最终等价于 aabc对aab*c*a*的匹配结果。 上边table[row][col] = table[row - 1][col]正是这种依赖结果。
                        }

                    }
                }
            }
        }


        boolean lastRow[] = table[table.length-1];
        return lastRow[lastRow.length-1];
    }
}