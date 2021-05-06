package BaseStruct;
import java.util.*;

public class ShuLun{
     //判断 num 是否是素数
     private static boolean isP(int num) {
        if(num <= 3) {
            return num > 1;    // 2,3都是素数
        }
        //6*n+2;6*n+3;6*n+4;6*n等都不是素数;可过滤掉2/3的判断
        if(num % 6 != 1 && num % 6 !=5) {
            return false;
        }
        // 只有 对6求余数，是 1，或者 5的是素数
         
        double sqrt = Math.sqrt(num);
        for (int i = 5; i < sqrt; i += 6) {
            //只变量2类数据num % 6 == 1 num % 6 == 5
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}