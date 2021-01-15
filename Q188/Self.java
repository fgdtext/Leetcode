package Q188;


import java.util.Arrays;
/*

2
[1,2,4,2,5,7,2,4,9,0]
*/
public class Self {
    public static void main(String[] args) {
        int[] arr = {1,2,4};
        Solution so = new Solution();
        so.maxProfit(2, arr);
    }
}
/*

如何控制k笔交易。 背包的特性 和 股票特性结合

另 k = 1 -> k  
hold[i][k] :  在第i天晚上， 且在进行第k次交易（未完成卖出），，手持股票时的收益   必然已经完成了 k-1次交易，且在进行第k次
money[i][k] : 在第i天晚上，且完成了k次交易，手持现金获取的最大收益

状态怎么转移  ？？？ 

hold[i][k]  ： 若今天没有购买股票，则说明是继承前一天的状态，若购买了，则前一天是手持现金的状态。
money[i][k] ： 若是当天卖出，则前一天必然手持股票，否则继承前一天的状态。

                    money[i][j] = Math.max(money[i-1][j], hold[i-1][j]+prices[i]);
                    hold[i][j] = Math.max(money[i-1][j-1]-prices[i], hold[i-1][j]);


重点是边界值的初始化。  即第0列的初始化，以及2*j-1 < lent边界的初始化。

*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int lent = prices.length;
        if(k == 0) return 0;
        if(lent == 0) return 0;
        if (k > lent / 2) {
            return maxProfitNormal(prices);
        }

        int[][] hold = new int[lent][k];
        int[][] money = new int[lent][k];
        // 在只能做一笔交易的情况下，
        int premin = prices[0];  // 记录 <=j 的 最小值。
        int left = 0;
        int maxcash = 0;


      /*
        上三角是无用的。 因为i >= j*2 也就是说要交易j天至少要2*j天才够。
        但是在求i = j*2的位置上时，可能会遇到边界值，依赖于 i > j*2的位置。所以要对该边界初始化。
      */
        for(int j = 0; j < k; j++){
            if(2*j-1 < lent && j > 0)
            hold[2*j-1][j] = Integer.MIN_VALUE;
        }


        //  初始化左上角的 一个 位置。
        hold[0][0] = -prices[0];
        money[0][0] = 0;

        // 根据 [0][0] 初始化 第0列 ,,,,,该位置转化为 线性dp 求只有一次交易的hold
        for(int j = 1; j < lent; j++){
            if(premin > prices[j]) premin = prices[j];
            hold[j][0] = -premin;
            // 这应该是left.   left,j构成双指针。 还是那句话， 不要在j循环内 再使用left ,right构成双指针，存粹没事找事。
            if(prices[j] < prices[left]){
                left = j;
            }
            maxcash = Math.max(maxcash, prices[j] - prices[left]);
            money[j][0] = maxcash;
        }


        // 根据第0列初始化后边的每一列    
        for(int j = 1; j < k; j++){
            for(int i = 1; i < lent; i++){
                // 最大交易次数 <= i天数。
                if(i >= j*2){
                    money[i][j] = Math.max(money[i-1][j], hold[i-1][j]+prices[i]);
                    hold[i][j] = Math.max(money[i-1][j-1]-prices[i], hold[i-1][j]);
                }
            }
        }

        return Arrays.stream(money[lent-1]).max().getAsInt();
    }
    // 还有一个优化点 ： 当 k > lent/2 时， 此时题目转化为 ： 可以任意买卖，不限制次数了。就变成了线性dp。 时间为n
    private int maxProfitNormal(int[] prices) {
        int length = prices.length;
        if (length < 2) {
            return 0;
        }
        int notHold = 0;
        int hold = -prices[0];

        for (int i = 1; i < length; i++) {
            int price = prices[i];
//            donTHold = Math.max(hold + price, donTHold);
//            hold = Math.max(donTHold - price, hold);

            if (hold < notHold - price) {
                hold = notHold - price;
            }
            if (notHold < hold + price) {
                notHold = hold + price;
            }
        }
        return notHold;
    }
}


/*
由依赖顺序可知，可以优化到1维度的数组。

这就不写了
*/