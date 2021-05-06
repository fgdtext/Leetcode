package HUAWEI.Hj82;
import java.io.*;
import java.util.*;
/**
 * 真分数转埃及分数
 * 先化简
 * 步骤一： 用b除以a，得商数q1及余数r1,即b=a*q1+r1
 * 步骤二： a/b=1/(q1+1）+(a-r1)/b(q1+1）
 * 步骤三： 重复步骤2，直到 a == 1 或者 b%a==0
 * 3/7=1/3+2/21=1/3+1/11+1/231
 * 
 * 推到过程  a/b = a/(a*q+r) > a/(a*q+a) = 1/(q+1)
 * a/b = 1/(q+1) + x
 * x = a/b - 1/(q+1) = (a*q+a-b)/(b(q+1)) = (a*q+a-a*q-r)/(b(q+1)) = (a-r)/(b(q+1))
 * 
 * 所以： a/b = 1/(q+1) + (a-r)/(b(q+1)) 
 * 递归求： (a-r)/(b(q+1))
 **/

public class Main {
 
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            while((str = br.readLine())!= null){
                String[] strArr = str.split("\\/");
                int a = Integer.parseInt(strArr[0]);
                int b = Integer.parseInt(strArr[1]);
                String[] resArr = new String[1];
                f(a, b, "", resArr);
                System.out.println(resArr[0]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
     
     
    public static void f(int a, int b, String resStr, String[] resArr){
        if(a==1 || b%a==0){
            int val = b/a;         
            resStr += 1+"/"+val;
            resArr[0] = resStr;
            return ;
        }
        else{
            int q1 = b/a;
            int r1 = b%a;
            int val1 = q1+1;
            resStr += 1+"/"+val1+"+";
             
            a = a - r1;
            b = b*(q1+1);
            f(a, b, resStr, resArr);
        }
        return;
    }
}


/*

自己的方法： 看b/a - 1/c是否够减，够则减去。 
c的递增策略：  c = b / a   b / a是下一个c的下限。
c的递增还是不够快。

*/
 class Self{
    static void test() throws IOException{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str;
            while((str = bf.readLine()) != null && str.length() > 0){
                String[] ss = str.split("/");
                long a = Integer.parseInt(ss[0]);
                long b = Integer.parseInt(ss[1]);
                long c = 2;
                StringBuilder ans = new StringBuilder();
                c = b / a;
                while(a != 0){
                    if(c*a >= b){
                        long m = c*a - b;
                        long n = c*b;
                        ans.append("1/"+c+"+");                    
                        a = m;
                        b = n;  
                        if(a != 0) c = b / a;                   
                    }else{
                        c++;   
                    }                            
                }
                ans.deleteCharAt(ans.length()-1);
                System.out.println(ans);            
            }      
        }
    }
