package  Q679;
import java.util.ArrayList;
import java.util.List;


/*
  改了半天， 用循环暴力出来了。 从4个list 可以看出来，每级挑选操作符后都会，改变当前list. 
                             而且下级别挑选操作符要基于上级别改变。
                            但是：：：若当前级别选出的操作符不行。也就是重选当前操作符时，
                            要对原来的list的去改变，而不能基于上次改变的结果。

                            所以： 每级别的改变，都应该去改变改级别的备份list.每一别，每次都去新建list.使得下次循环还是原来的list.

方法太笨了。 应该用回溯法， 递归去做


*/



class Solution {
    int[][] arrange1_4 = { // 12中选择。
            { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 0 }, { 1, 2 }, { 1, 3 }, { 2, 0 }, { 2, 1 }, { 2, 3 }, { 3, 0 },
            { 3, 1 }, { 3, 2 } };
    int[][] arrange1_3 = { { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, };
    int[][] arrange1_2 = { { 0, 1 }, { 1, 0 } };
    ArrayList<Double> list;
    ArrayList<Double> list1;
    ArrayList<Double> list2;
    ArrayList<Double> list3;
    double INF = 1 >> 6;

    public boolean judgePoint24(int[] nums) {
        // 第一次 选择 操作数
        double a, b;
        list = new ArrayList<>();
        for (Integer it : nums) {
            list.add(it * 1.0);
        }
        for (int i = 0; i < 12; i++) {
            int ai0 = arrange1_4[i][0];
            int ai1 = arrange1_4[i][1];
            a = list.get(ai0);
            b = list.get(ai1);
            // 第一次 选择 操作符
            for (int iop = 0; iop < 4; iop++) {
                list1 = new ArrayList<>(list);
                double temp1 = oper(a, b, iop);
                if (temp1 == INF)  continue; // 本次 失败
                dele(ai0, ai1,list1);
                list1.add(temp1);
                // 第二次选择操作数
                for (int j = 0; j < 6; j++) {
                    
                   int aj0 = arrange1_3[j][0];
                   int aj1 = arrange1_3[j][1];
                   double aa = list1.get(aj0);
                   double bb = list1.get(aj1);
                    // 第二次 选择 操作符
                    for (int jop = 0; jop < 4; jop++) {
                        list2 = new ArrayList<>(list1);
                        double temp2 = oper(aa, bb, jop);
                
                        if (temp2 == INF)
                            continue; // 本次 失败
                        dele(aj0, aj1, list2);
                        list2.add(temp2);

                        // 第三次 选择 操作数
                        for (int k = 0; k < 2; k++) {
                            
                            int ak0 = arrange1_2[k][0];
                            int ak1 = arrange1_2[k][1];
                            double aaa = list2.get(ak0);
                            double bbb = list2.get(ak1);
                            // 第三次选择 操作符。
                            for (int kop = 0; kop < 4; kop++) {
                                list3  = new ArrayList<>(list2);
                                double temp3 = oper(aaa, bbb, kop);
           
                                if (Math.abs(temp3 - 24) < 1e-6)
                                    return true;
                                if (temp3 == INF)
                                    continue; // 本次 失败
                                dele(ak0, ak1,list3);
                            }
                  
                        }
                    }
                }
       
            }
        }
        return false;
    }

    public double oper(double a, double b, double c) {
        if (c == 0)
            return a + b;
        else if (c == 1)
            return a - b;
        else if (c == 2)
            return a * b;
        else
            return b == 0 ? INF : a * 1.0 / b;
    }
    public void dele(int i, int j,List<Double> list) {
        int min = Math.min(i, j);
        int max = Math.max(i, j) - 1;
        list.remove(min);
        list.remove(max);
    }
}