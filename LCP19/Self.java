package LCP19;

public class Self {
    
}

/*
双指针， 做不出来。
动态规划
首先分析题目可得需要三个状态数量：
分别为

替换成全红排列0：所需最小替换次数：f[i][0]=f[i−1][0]+isYellow(i)
替换成红黄排列1：所需最小替换次数：f[i][1]=min{f[i−1][0],f[i−1][1]}+isRed(i)
替换成红黄红排列2：所需最小替换次数：f[i][2]=min{f[i−1][1],f[i−1][2]}+isYellow(i)
约束条件：
替换成红黄红排列的必要条件是：

第一片叶子必须是红色
叶子数量必须大于状态数量
*/


    class Solution {
        public int minimumOperations(String leaves) {
            if(leaves==null || leaves.length() <= 1) return 0;
            //分三个状态数量（红，红黄，红黄红）
            int[][] states=new int[leaves.length()][3];
            //第一片叶必须是红色
            states[0][0]= leaves.charAt(0)=='y'?1:0;
            //遵循叶子的数量必须大于等于状态的数量
            states[0][1]=states[0][2]=states[1][2]=Integer.MAX_VALUE;
            for(int i=1;i<leaves.length();i++){
                int isYellow= leaves.charAt(i)=='y'?1:0;
                int isRed= leaves.charAt(i)=='r'?1:0;
                //全部替换成红叶的最小次数
                states[i][0]=states[i-1][0]+isYellow;
                //替换成红黄排列最小次数
                states[i][1]=Math.min(states[i-1][1],states[i-1][0])+isRed;
                if(i>=2){
                    //替换成红黄红排列的最小次数
                    states[i][2]=Math.min(states[i-1][1],states[i-1][2])+isYellow;
                }
            }
            return states[leaves.length()-1][2];
        }
    }
