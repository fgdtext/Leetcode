package BaseStruct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.*;
/*


专注于 各种 api的使用， 有些厂的面试，
1. 要求输入输出用标准输入输出， 所以要熟悉输入和输出相关的api  ********重点
2. 字符串相关api                    *********重点
3. 数组相关api                      ******** 重点
4. 关于排序相关
5. 集合框架的使用。







*/
public class Api {
    public static void main(String[] args) {
        in();
    }
    




    /*  Scanner: Scanner缓冲区总是读取一行，然后使用next遍历，开始输出。 但是每行结束后，如果下一行继续输入，仍然会继续读取。
                Scanner总是在等待下一行的输入。 永远不会停止等待。除非人为中断。
        nextByte(): 只能读 -128到 127的数字 数字以空格结束。 读完一行后，仍然会等待下一行的输入.
        hasNextLine(): 若当前行的光标没有走到end，那么返回true表示输入是当前行。此时使用nextLine()返回的是光标到end的内容。
                        下一个hasNextLine()才表示是否有下一行。
                        "12 123 \n 1234" ： 两个nextByte读取12,123, 光标后还有一个空格，没有到end。 所以第一个nextLine读取的是一个空格。
        
        当题目说,有多组输入,但是没说有几组时,不需要理会程序是否会终止,只要输入总是对应正确的输出即可.
        输入终止判断： 当输入一个空行时判定输入终止。要用该条件结束输入 while((str = in.readLine())!=null)
    */
    public static void in(){

        // Scanner sc = new Scanner("12 123 \n 100");
        // System.out.println(sc.nextByte()); // 12:ok 
        // System.out.println(sc.nextByte()); // 123 : ok
        // System.out.println(sc.hasNextByte());
        // System.out.println(sc.nextByte()); //1233 :  InputMismatchException: Value out of range
        // //System.out.println(sc.nextByte()); //kjlkad :  java.util.InputMismatchException
        // System.out.println(sc.hasNextLine());
        // System.out.println(sc.nextLine());
        // System.out.println(sc.hasNextLine());
        // System.out.println(sc.nextLine());
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
        /*
            BufferedReader : 推荐使用这个方式，要快很多。 比Scanner快很多。 推荐每次读一行的，然后通过 split方法进行分割，
            这样也会比 Scanner快很多。效率差的比较多。
        */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    }
/*
迭代器 set, map 的迭代器使用

*/
    public static void diedaiqi(){
        HashSet<String> set = new HashSet<>();
        for(String s : set){

        }
        HashMap<String,String> map = new HashMap<>();
        for(String s : map.keySet()){

        }
    }
/*
    java保留小数--四舍五入--想保留几位就几位
    String.format("%.nf",d);----表示保留N位！！！format("%.nf",double)
      double d = 5.3333333;
      String str1 = String.format("%.2f", d);
      String str2 = String.format("%.3f", d);
      System.out.println(str1);
      System.out.println(str2);
*/


}