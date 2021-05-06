package HUAWEI.Hj18;
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = 0, b = 0, c = 0, d = 0, e = 0, err = 0, pri = 0;
        String str;
        while((str = br.readLine()) != null) {
            int index = str.indexOf('~');
            //取出 掩码, 判断掩码是否正确
            long ip = ipToNum(str.substring(index + 1));
            if(ip <= 0 || ip >=0XFFFFFFFFL || (((ip ^ 0XFFFFFFFFL) + 1) | ip)!= ip)
            {
                err += 1;
                continue;
            }
            // 取出ip ： 判断 ip是否正确
            ip = ipToNum(str.substring(0, index));
            long partOfIP = ip >> 24;
            if(ip <= 0L || ip >= 0XFFFFFFFFL) {
                err += 1;
            } else if(partOfIP >= 1 && partOfIP <= 126) {
                a += 1;
                if(partOfIP == 10) {
                    pri += 1;
                }
            } else if(partOfIP >= 128 && partOfIP <= 191) {
                b += 1;
                if(ip >> 20 == 0XAC1) {
                    pri += 1;
                }
            } else if(partOfIP >= 192 && partOfIP <= 223) {
                c += 1;
                if(ip >>16 == 0XC0A8) {
                    pri += 1;
                }
            } else if(partOfIP >= 224 && partOfIP <= 239) {
                d += 1;
            } else if(partOfIP >= 240 && partOfIP <= 255) {
                e += 1;
            }  
        }
        System.out.println(a + " " + b + " " + c + " " + d + " " + e + " "
                              + err + " " + pri);
    }
    public static long ipToNum(String ip) {
        long num = 0;
        int flag = 1, temp = 0;
        char[] chars = ip.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == '.') {
                num = num << 8 | temp;
                temp = 0;
                flag ++;
                continue;
            }
            if(flag >= 2) {
                return -1;
            }
            if(chars[i] >= '0' && chars[i] <= '9') {
                temp = temp * 10 + chars[i] - '0';
                flag = 0;
            }
            else {
                flag = 3;
                break;
            }
        }
        if(flag >= 2) {
            return -1;
        }
        num = num << 8 | temp;
        return num;
    }
}
