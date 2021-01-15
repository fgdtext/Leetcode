package Q415_addStrings.ans;

// 先转 char[] 类型
 
class Solution2 {

    public String addStrings(String num1, String num2) {
        StringBuilder s = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        

        char[] num1Char = num1.toCharArray();
        char[] num2Char = num2.toCharArray();
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i < 0 ? 0 : num1Char[i--] - '0';
            int y = j < 0 ? 0 : num2Char[j--] - '0';
            int sum = x + y + carry;
            s.append(sum % 10);//添加到字符串尾部
            carry = sum / 10;
        }
        return s.reverse().toString();//对字符串反转
    }
}


