package Q43;



/*

字符串数字相乘。
方法：  竖式。      
                     12345
                       567
                ----------------------
                        35                用7 乘每一位，加在res的对应位置上 上
                       28
                      21
                     14
                     7
                ---------------------
                       30                用 6 
                      24
                     18
                    12
                    6
                --------------------
                      25
                     20
                    15
                   10
                   5
                ----------------------
               



*/
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }
}
