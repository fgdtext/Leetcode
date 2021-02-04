package Q123;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a = {1,2,3};
        so.maxProfit(a);

        
    }
}

/*
由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种：

状态0 ： 未进行过任何操作；该状态可以延续前一天。

状态1： 只进行过一次买操作；

状态2：进行了一次买操作和一次卖操作，即完成了一笔交易；

状态3：在完成了一笔交易的前提下，进行了第二次买操作；

状态4：完成了全部两笔交易。

如果某一天什么都不做， 那么这一天的利润就为0， 下一天的状态将直接依赖前一天。
假设 第 i-1 , i i+1天    在第i天什么都不做， 那么第i天的状态和i-1相同。 那么i+1依赖i,则间接依赖i-1.

对于买卖的理解，说是不饿能同时做多笔交易，那么 8点买，9点卖，10再买，11点再卖。 在同一天完成。
不能在第一买，第二天继续买，得先在第一天卖出。

那么对于第一天，就可以对状态1234 

*/


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
