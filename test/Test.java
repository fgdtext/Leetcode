package test;import java.util.*;

public class Test{
    public static void main(String[] args) {
        String s3 = new String("1") + new String("2");
        s3.intern();
        String s4 = "12";
        
        System.out.println(s3 == s4);

    }
}