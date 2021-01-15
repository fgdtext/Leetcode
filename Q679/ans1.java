package Q679;

import java.util.ArrayList;
import java.util.List;

class Solution3 {
    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    public boolean solve(List<Double> list) {
        if (list.size() == 0) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<Double>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        // 加法和乘法，不需要交换两个数的位置。
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            list2.add(list.get(i) - list.get(j));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue; //list.get(j) ==  0 不能除
                            } else {
                                list2.add(list.get(i) / list.get(j));
                            }
                        }
                        if (solve(list2)) {
                            return true;
                        }
                        // 改回原数组。 回溯法， 归位置。
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}




class Solution5 {

    public boolean judgePoint24(int[] nums) {
        return backTrack(nums, 0);
    }

    // 第一步：求出所有排列，一一验证
    public boolean backTrack(int[] nums, int index) {
        if (index == 4) {
            return judge(nums[0], nums[1], nums[2], nums[3]);
        }
        for (int i = index; i < 4; i++) {
            swap(nums, index, i);
            if (backTrack(nums, index + 1)) return true;
            swap(nums, index, i);
        }
        return false;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 第二步：由于已经全排列，a、b、c、d 都是等价的，利用四种运算符选出三个数继续
    public boolean judge(double a, double b, double c, double d) {
        return judge(a + b, c, d) ||
               judge(a - b, c, d) ||
               judge(a * b, c, d) ||
               judge(a / b, c, d);
    }

    // 第三步：a 是有两个数组成的，b、c 只表示一个数，等价
    public boolean judge(double a, double b, double c) {
        // 情况一：a 和 b(c) 组合，a 和 b(c) 不等价，- 号和 / 号需要考虑两种情况
        return judge(a + b, c) ||
               judge(a - b, c) ||
               judge(a * b, c) ||
               judge(a / b, c) ||
               judge(b - a, c) ||
               judge(b / a, c) ||
               // 情况二：b 和 c 组合
               judge(a, b - c) ||
               judge(a, b + c) ||
               judge(a, b * c) ||
               judge(a, b / c);
               /*
                    假设顶层来是  9 7 5 3.  通过加法 9+7    得到三个数传进来  16, 5, 3. 分别是新的 a b c.
                    来到本层。 那么 16 -5.  和 5 -16 是两种情况。得能得到两种情况才行。。上层会传来 5，16，3吗。显然不会，
                    因为 上层传来的 是  (a,b), c, d ,c 不可能是 16. 16只能靠一次或多次计算得到。所以这里就说明 
                    组合出来的数，和原本的数 传进来，是不等价的。需要手动调换位置。

                    又由于， b,c 等价， 所以 a 和 b 计算和 ac计算，效果是一样的。
                    但是 a -b 和 b -a 是两种情况。         
                    再然后 bc 之间也要计算。

                    第一层是等价的是因为，全排列可以得到所有情况。
 
               */
    }

    // 第四步：a 和 b 不等价
    public boolean judge(double a, double b) {
        return Math.abs(a + b - 24) < 0.001 ||
               Math.abs(a - b - 24) < 0.001 ||
               Math.abs(a * b - 24) < 0.001 ||
               Math.abs(a / b - 24) < 0.001 ||
               Math.abs(b - a - 24) < 0.001 ||
               Math.abs(b / a - 24) < 0.001;
    }
}

