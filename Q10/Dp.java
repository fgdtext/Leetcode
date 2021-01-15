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

        table[0][0] = true;

        for (int col=1; col<table[0].length; col++) {
            char ch = p.charAt(col-1);
            if (col > 1) {
                if (ch == '*') {
                    table[0][col] = table[0][col-2]; 
                } else {
                    table[0][col] = false;
                }
            } else {
                if (ch == '*') {
                    table[0][col] = true;
                }
            }
        }

        for (int row=1; row<table.length; row++) {
            char ch1 = s.charAt(row-1);
            for (int col=1; col<table[row].length; col++) {
                char ch2 = p.charAt(col-1);
                if (ch1==ch2 || ch2 == '.') {
                    table[row][col] = table[row-1][col-1];
                } else if (ch2 == '*') {
                    if(col > 1) {
                        if (table[row][col-2]) {
                            table[row][col] = true; // * 前面的字符出现0次
                        } else {
                            char prev = p.charAt(col-2);
                            if (prev== ch1 || prev == '.') {
                                table[row][col] = table[row - 1][col]; // * 前面的字符出现多次  ，每次删除s中一个ch1.直到去查看匹配0个是否匹配
                            }
                        }

                    }
                }
            }
        }


        boolean lastRow[] = table[table.length-1];
        return lastRow[lastRow.length-1];
    }
}