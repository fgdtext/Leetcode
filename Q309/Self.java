package Q309;

public class Self {
    
}
/*
参考 ：Q122

cash[i] :    第i天卖出后， 能获得的 最大利润，且进入冷冻期，i+1天不能买入 。必须是当天卖出，则前一天必然是 手持 股票。

hold[i] :    第i天结束后，手持股票，能获得的 最大利润。 前一天可能手持现金，也可能手持股票

cashlong[i] :  第i天结束后,手持现金， 能获得的 最大利润，且进入解冻期，i+1天可以买入。 前一天可能是手持刚卖出的cash,或者已经是解冻期cashlong

由于 最后一天结束，手持现金的情况有两种，可能在冰冻期，也可能进入解冻期。
ans = Math.max(cash[len-1], cashlong[len-1]);  都是手持现金，但是情况不一样。

*/
class Self1 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1) return 0;
        if(len == 2) return Math.max(0, prices[1]-prices[0]);
        int[] cash = new int[len];
        int[] hold = new int[len];
        int[] cashlong = new int[len];

        cash[0] = 0; // 第一天结束，手持现金， 利润必然是 0
        hold[0] = -prices[0]; // 第一天结束，手持股票，利润是 -prices[0]
        cashlong[0] = 0;// 无意义。

        cash[1] = Math.max(cash[0], hold[0]+prices[1]);  // 第二天可能继续手持现金，也可能是把第一天的股票卖了。
        hold[1] = Math.max(hold[0], -prices[1]); // 可能手持昨天买的股票，也可能今天才买入。
        cashlong[1] = 0; // 还是无意义。

        cash[2] = Math.max(cash[1], hold[1]+prices[2]);
        hold[2] = Math.max(hold[1], cash[1]-prices[1]);
        cashlong[2] =  cash[1];  // 昨天结束必然是 手持现金。今天结束才进入解冻期，昨天卖。明天才能买,今天不能买入。
        // 第一个解冻期最早出现在第3天。
        if(len == 3) return Math.max(cash[2], cashlong[2]);

        for(int i = 2; i < prices.length; i++){
            cash[i] = hold[i-1] + prices[i]; //  今天卖出，则昨天必然是手持股票
            hold[i] = Math.max(hold[i-1], cashlong[i-1]-prices[i]);  // 今天手持股票，则昨天可能是手持股票，也可能是完成解冻期。
            // 今天结束，手持解冻期的股票，则昨天结束可能是手持新鲜股票，也可能手持解冻期股票。
            cashlong[i] = Math.max(cash[i-1], cashlong[i-1]); 
        }
        return Math.max(cash[len-1], cashlong[len-1]);
    }
}

/*class
中间单独写 第一天和第二天，是因为第一个解冻期出现在第三天。害怕，没有循环不能完成初始化。

但是仔细考虑， 恰好可以。


*/

class Self2 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1) return 0;
        if(len == 2) return Math.max(0, prices[1]-prices[0]);
        int[] cash = new int[len];
        int[] hold = new int[len];
        int[] cashlong = new int[len];

        cash[0] = 0; // 第一天结束，手持现金， 利润必然是 0
        hold[0] = -prices[0]; // 第一天结束，手持股票，利润是 -prices[0]
        cashlong[0] = 0;// 无意义。
        for(int i = 1; i < prices.length; i++){
            cash[i] = hold[i-1] + prices[i]; //  今天卖出，则昨天必然是手持股票
            hold[i] = Math.max(hold[i-1], cashlong[i-1]-prices[i]);  // 今天手持股票，则昨天可能是手持股票，也可能是完成解冻期。
            // 今天结束，手持解冻期的股票，则昨天结束可能是手持新鲜股票，也可能手持解冻期股票。
            cashlong[i] = Math.max(cash[i-1], cashlong[i-1]); 
        }
        return Math.max(cash[len-1], cashlong[len-1]);
    }
}


/*class
空间优化。因为只依赖前一项

*/

class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1) return 0;
        if(len == 2) return Math.max(0, prices[1]-prices[0]);

        int cashp = 0; // 第一天结束，手持现金， 利润必然是 0
        int holdp = -prices[0]; // 第一天结束，手持股票，利润是 -prices[0]
        int cashlongp = 0;// 无意义。
        int cash = 0 ,hold = 0 ,cashlong = 0;
        for(int i = 1; i < prices.length; i++){
            cash = holdp + prices[i]; //  今天卖出，则昨天必然是手持股票
            hold = Math.max(holdp, cashlongp-prices[i]);  // 今天手持股票，则昨天可能是手持股票，也可能是完成解冻期。
            // 今天结束，手持解冻期的股票，则昨天结束可能是手持新鲜股票，也可能手持解冻期股票。
            cashlong = Math.max(cashp, cashlongp); 
            cashp = cash;
            holdp = hold;
            cashlongp = cashlong;
        }
        return Math.max(cash, cashlong);
    }
}


