package HUAWEI.Hj107;

import java.io.*;
import java.util.*;
/*

牛顿求根法。输入 n  则设有曲线 y = x^3 - n     那么曲线的根就是方程的解。

先有x1, 那么就对应 y1 = x1^3 - n     从(x1,y1)处做曲线的切线，
与x轴相交与((num-x1*x1*x1)/(3*x1*x1) + x1, 0)  该点的横坐标为 x2 = (num-x1*x1*x1)/(3*x1*x1) + x1
然后更新x1 = x2;

定理： 对于该迭代过程总有 abs(x2-root) < abs(x2-x1) x2总是更靠近root.

那么只要 diff = abs(x2-x1) < 0.01 说明当前精度已经达到所需。然后x1= x2 
x1 及为符合精度的解。
*/
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = bf.readLine()) != null && str.length() > 0){
            double num = Double.parseDouble(str);
            double x1 = num;
            double x2 = 0;
            double diffx = 100;
            while(diffx >= 0.01){
                x2 = (num-x1*x1*x1)/(3*x1*x1) + x1;
                diffx = x2 - x1 >=0 ? x2-x1 : x1-x2;
                x1 = x2;
            }
            String result = String.format("%.1f", x1);
            System.out.println(result);
        }
    }
}