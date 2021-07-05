package ZZmianshi.xiaomi.Q1;
import java.util.*;
import java.io.*;


/*

判断 ip是否合法， 注意：包含非法字符，>255 等问题。

*/
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = in.readLine()) != null && !s.equals("")){
            String[] ss = s.split("\\.");
            if(ss.length != 4){
                System.out.println("NO");
                continue;
            }
            boolean flag = true;
            for(int i = 0; i < 4; i++){
                String e = ss[i];
                int num = 0;
                for(int j = 0; j < e.length(); j++){
                    num = num*10 + e.charAt(j) - '0';
                }
                if((e.length() > 1 && e.charAt(0) == '0') || e.length() > 3 || num > 255){
                     flag = false;
                     break;
                }
            }
            if(flag)
                System.out.println("YES");
            else 
                System.out.println("NO");
        }
    }
}