package Q179;
import java.util.*;

/*

换成字符串排序，就成了高位开始的比较排序了。 也就是字典序。 恰好适合高位优先原则。

*/
class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));//lambda表达式
        StringBuilder res = new StringBuilder();
        for(String s : strs) {
            if(res.toString().equals("0")) res.delete(0,1);
            res.append(s);
        }
        return res.toString();
    }
}
