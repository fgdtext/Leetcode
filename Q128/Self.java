package Q128;

import java.util.*;

public class Self {
    
}

/*
时间是 o(n)  但是 10 ms, 在所有 Java 提交中击败了19.40%的用户。 很差劲。

39.5 MB, 在所有 Java 提交中击败了23.74%的用户


空间占用高，可以理解。  但是 o(n) 算法，击败率为什么这么低。


算法 ：  不断的合并区间。
leftmap  ： 通过区间左端点，查找 右端点。
rightmap ： 通过区间右端点，查找 左端点。

每看到一个数， 先看是否 已经处理过， 重复的数，直接跳过。通过exist实现。

对于e : 
1. 可能使得 两边的区间 一块合并。
2. 和左侧合并。
3. 和右侧合并。
4。自成一个区间。



*/



class Slef1 {
    public int longestConsecutive(int[] nums) {
        // left -> right
        HashMap<Integer, Integer> leftmap = new HashMap<>();
        // right -> left
        HashMap<Integer, Integer> rightmap = new HashMap<>();
        HashSet<Integer> exist = new HashSet<>();
        int max = 0;
        for(int e : nums){
            if(exist.contains(e)) continue;
            exist.add(e);
            // 可能同时合并两侧   该判断 ： 两侧都是区间
            if(rightmap.containsKey(e-1) && leftmap.containsKey(e+1)){
                int left = rightmap.get(e-1);
                int right = leftmap.get(e+1);
                // 删除左区间
                rightmap.remove(e-1);
                leftmap.remove(left);
                // 删除右区间
                leftmap.remove(e+1);
                rightmap.remove(right);
                rightmap.put(right, left);
                leftmap.put(left, right);
                max = Math.max(max, right - left + 1);
            // 下边 可能向单侧合并 左 右 
            }else if(rightmap.containsKey(e-1)){   // 左侧是区间
                int left = rightmap.get(e-1);
                rightmap.remove(e-1);
                rightmap.put(e, left);
                leftmap.replace(left,e);
                max = Math.max(max, e - left + 1);
            }else if(leftmap.containsKey(e+1)){  // 右侧是区间
                int right = leftmap.get(e+1);
                leftmap.remove(e+1);
                leftmap.put(e, right);
                rightmap.replace(right, e);
                max = Math.max(max, right - e + 1);
            // 可能自成一个区间。
            }else{
                rightmap.put(e, e);  leftmap.put(e, e);
                max = Math.max(max, 1);
            }
        }
        return max;
    }
}




/*class



答案只用一个HashSet   这种思想很好。不是去测量数据本身，而是去 测量标尺。
我是通过数每一个数是否连续。 而答案是 看下一个位置是否有数。
*/

class Ans {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            // 对于不是 起始数字的 不需要去测量
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                // 取最值
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

