package ZZmianshi.zijie.Q8;
import java.util.*;

import java.util.*;


public class Main {

    public int solve (String s) {    
        int len = s.length();
        int[] next = new int[len];
        getnext(s,next);
        int j  = 0 , i = 1;
        while(i < len){
            if(j == -1 || s.charAt(i) == s.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        return j == 0 ? -1 : j;
    }
    public static void getnext(String s, int[] next){
        int i = 0, j = -1;
        next[0] = -1;
        while(i < s.length() - 1){
            if(j == -1 || s.charAt(i) == s.charAt(j)){
                i++;
                j++;
                next[i] = j;
            }else{
                j = next[j];
            }
        }
    }
}