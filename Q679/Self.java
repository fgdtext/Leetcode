package Q679;

import java.util.LinkedList;

/*
        困难处： 
        1.分数如何处理   ？？？？
        解决： 假设有后序表达式： 1234///  => 1/(2/(3/4))  进行入栈操作
              1.1234进行入栈操作
              2.   取出 234 => 2*4/3 = 》 8/3 消号一个除号。 再入栈   得到  183//   
              3    取出 183 => 1*3/8 => 3/8  再入栈。  38
              4 此时栈中只有两个数字。取不到第三个数字，则判断  a == 24 *b.
              问题： 如何得到初始的除法栈
                      入栈应该是 : 4321   计算时，反着来。

        2.运算符可以重复选  4*4*4  循环


        error : 方法错了， 漏了太多， 不能把数字和运算符分开。 例如 (9-1) * (2+1) 的后序是 91-21+* 。 
                 不能用  9121 -+* 代替。
        

                

*/


class Solution2 {
    // //  3个数的全排列// 这里不饿能这么写了。因为运算符可以重复。4*4*4的循环来实现
    // int[][] arrange1_3 = {  // 0 - 5
    //    {0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,1,0},{2,0,1} 
    // };
    // 4 个数的全排列
    int[][] arrange1_4 = {  //  0-23
        {0,1,2,3},{0,1,3,2},{0,2,1,3},{0,2,3,1},{0,3,1,2},{0,3,2,1},
        {1,0,2,3},{1,0,3,2},{1,2,0,3},{1,2,3,0},{1,3,0,2},{1,3,2,0},
        {2,1,0,3},{2,1,3,0},{2,0,1,3},{2,0,3,1},{2,3,1,0},{2,3,0,1},
        {3,1,2,0},{3,1,0,2},{3,2,1,0},{3,2,0,1},{3,0,1,2},{3,0,2,1},
    };
    char[] operator = {'+','-','*','/'};

    public boolean judgePoint24(int[] nums) {
        int INF = 1 << 9;
        LinkedList<Integer> sstack = new LinkedList<>();
       // LinkedList<Character> divisionStack = new LinkedList<>();
        LinkedList<Character> op = new LinkedList<>();
        // 构成后序 序列。
        boolean flag = false;
        for(int i = 0; i < 24; i++){
            // 放字符。 
            sstack.clear();
            for(int k = 0; k <4; k++){
                sstack.addLast((nums[arrange1_4[i][k]]));
            }
            // 运算符共有4中选择
            for(int k = 0; k < 4; k++){
                for(int l = 0; l < 4; l++){
                    for(int j = 0; j < 4; j++){
                        op.clear();
                        op.push(operator[k]);
                        op.push(operator[l]);
                        op.push(operator[j]);
                        // 栈准备好了，可以开始后序表达式求值了。
                        // 维护除数 与 被除数
                        int  mu = 1; // 分母
                        int  zi = 0;//  分子
                        LinkedList<Integer> stack = new LinkedList<>(sstack);
                        while(!op.isEmpty()){
                            System.out.println("op::"+op.size()+" stack::"+stack.size());
                            System.out.println("fenzi::" + zi + "  mu::" + mu);
                            int b = stack.pop();
                            int a = stack.pop();
                            
                            char c = op.pop();
                            System.out.println("a::"+a +" op::" + c+ "  b::"+b);
                            if(c == '/'){
                                if(b != INF) {
                                    mu = b;
                                    zi = a;
                                }else{ //    ::: a / (zi/mu)
                                    int temp = zi;
                                    zi =  a * mu;
                                    mu = temp;
                                }
                                stack.push(INF);
                                continue;
                            }
                            if(c == '*'){
                                //  b不为 INF 则a 一定不为e。stack中只有一个INF
                                if(b != INF){
                                    // a*b 入栈
                                    stack.push(a*b);
                                }else{ // b == INF, 说明b是一个分数。乘分子。得到新的分子。
                                    zi = a * zi;
                                    stack.push(INF);
                                }
                                continue;
                            }
                            if(c == '-'){
                                if(b != INF){
                                    stack.push(a-b);
                                }else{ // :::  a - (zi/mu)
                                    zi = a * mu - zi;
                                    stack.push(INF);
                                }
                                continue;
                            }
                            if(c == '+'){
                                if(b != INF){
                                    stack.push(a+b);
                                    System.out.println("wo lai le ");
                                }else{  // :::  a + (zi/mu)
                                    zi = a * mu + zi;
                                    stack.push(INF);
                                }
                                continue;
                            }
                        }
                        System.out.println("------------------------------");
                        flag = stack.getFirst() == 24 ? true : false;
                        flag =  zi == (mu * 24) || flag;   
                        if(flag) return flag; 
                    }
                }
            }
        }
        return flag;
    }
}

public class Self{
    public static void main(String[] args) {
        int[] a = {1,7,4,5};
        Solution solution = new Solution();
        System.out.println(solution.judgePoint24(a));
    }

}

/*class

op::3 stack::4
fenzi::0  mu::1
a::1 op::+  b::4
op::2 stack::3
fenzi::0  mu::1
a::8 op::+  b::5
op::1 stack::2
fenzi::0  mu::1
a::7 op::+  b::13
op::3 stack::1
fenzi::0  mu::1


*/

