package HUAWEI.Hj89;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String[] op = new String[]{"+","-","*","/"};
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader re=new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = re.readLine()) != null && !"".equals(input)){
            String[] ss = input.split(" ");
            int a = getInputNum(ss[0]);
            int b = getInputNum(ss[1]);
            int c = getInputNum(ss[2]);
            int d = getInputNum(ss[3]);
            // 只要有joker，直接返回ERROR
            if(a==-1||b==-1||c==-1||d==-1){
                System.out.println("ERROR");
                continue;
            }
            compute(a,b,c,d);
        }
    }
 
    /**
     * 24点计算方法穷举
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public static void compute(int a,int b,int c,int d) {
        int[] arr={a,b,c,d};
        // 运算符穷举数组
        String[][] arr1 = symbol();
        for(int i=0;i<4;i++){// 第一个数字
            for(int j=0;j<4;j++){// 第二个数字
                for(int k=0;k<4;k++){// 第三个数字
                    for(int p=0;p<4;p++){// 第四个数字
                        if((i!=j)&&(i!=k)&&(i!=p)&&(j!=k)&&(j!=p)&&(k!=p)){// 如果四个数字互不相等才计算，不然一个字符就会出现两次
                            // 遍历运算符穷举数组
                            for(String[] str:arr1){
                                // 依次计算，得出最终结论
                                int sum = sumNum(arr[i], arr[j], str[0]);
                                sum=sumNum(sum, arr[k], str[1]);
                                sum=sumNum(sum, arr[p], str[2]);
                                if(sum==24){
                                    // 如果结果等于24，返回结果
                                    String str1=change2(arr[i])+str[0]+change2(arr[j])+str[1]+change2(arr[k])+str[2]+change2(arr[p])+"";
                                    System.out.println(str1);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        // 穷举之后仍然没有结果，返回none
        System.out.println("NONE");
    }
 
    /**
     * 穷举所有可能的运算符组合
     * @return
     */
    public static String[][] symbol() {
        //运算符共三个，每个四种可能性，4*4*4中运算符组合，每个组合有三个运算符
        String[][] symbol = new String[64][3];
        int p =0;
        for(int i=0;i<4;i++){// 第一个运算符
            for(int j=0;j<4;j++){// 第二个运算符
                for(int k=0;k<4;k++){// 第三个运算符
                    symbol[p++]=new String[]{op[i],op[j],op[k]};
                }
            }
        }
        return symbol;
    }
 
 
    /**
     * 两个数字计算结果
     * @param a
     * @param b
     * @param symb
     * @return
     */
    public static int sumNum(int a, int b, String symb) {
        switch(symb){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            default:
                return 0;
        }
    }
 
 
 
 
 
    /**
     * 字符串转数字
     * @param str
     * @return
     */
    public static int getInputNum(String str){
        switch(str.toUpperCase()){
            case "A":
                return 1;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "JOKER":
                return -1;
            default:
                return Integer.parseInt(str);
        }
    }
    /**
     * 数字转化为字符串
     * @param i
     * @return
     */
    public static String change2(int i) {
        switch(i){
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(i);
        }
    }
}
