package Q122;

/*

贪心 ： 从山脚 到 山顶 的价格差 = 每天卖出，

贪心 ： 从昨天到今天，能挣钱，就挣钱。

*/
class Ans {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}

/*

不能同时 多笔 交易 

即 当前是买入状态，则不能再买入， 当前是 卖出状态，下一个状态可以是不操作，也可以是买入， 但不能再卖出。

从树的角度来看  ： 两个买入操作之间， 必须有 卖出。

从每天的状态来看： 要不手持现金， 要不持有股票。 都是有价值的。

***** 其实答案是，每次买入卖出一股可以赚多少钱。

*/
/*

股票交易的实质是：每次买入卖出一股 最后能获得的最大利润，
这种题其实有隐藏限制， 就是买入一股，卖出一股。  然后利润累加。买入利润减少， 卖出利润增加。

*/
 class Ans2 {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // cash：持有现金(利润)
        // hold：持有股票

        // 状态数组
        // 状态转移：cash → hold → cash → hold → cash → hold → cash    都是每一天结束的状态。
        int[] cash = new int[len]; // 每一天都是两种可能，cash前一天可能是cash ，也可能是hold
        int[] hold = new int[len];// 每一天都是两种可能，hold前一天可能是hold ，也可能是cash
        /*
            cash ： 如果在当天结束手里没有股票， 自己能获得的利润利润。
            hold ： 如果在当前结束手里有股票， 自己能获得的最大利润。 因为手里有一部分股票，在这一刻没有变成钱，所以不是利润的一部分，
                    只能说，可能会转变为利润。而且，hold状态，只是手持股票，而不一定是手持当前的股票。
            当天买入 ： 那么前一天的状态就不能是hold，必然是cash；
            当前卖出 ： 那么前一天的状态就不能是cash，必然是hold；
            当天是hold， 昨天可能是cash(表明今天买入了)，也可能是hold(表明昨天手持股票，今天不操作)
            当天是cash， 昨天可能是hold(表明今天卖出了)，也可能是cash(表明昨天手持现金，今天不操作)

            cash ，hold 都表示当天结束能获得的最大利润，
            只是一个手里有股票，一个手里没有股票。
        */

        // 第0天结束， 手持现金  利润一定是0.  手持股票 则利润是负数。
        // 卖出则可获利，买入则要出利。
        cash[0] = 0; 
        hold[0] = -prices[0]; // 买了股票，所以利润为负数。

        for (int i = 1; i < len; i++) {
            // 这两行调换顺序也是可以的
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
        }
        return cash[len - 1];
    }
}


// 优化 空间

 class Ans3 {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // cash：持有现金
        // hold：持有股票
        // 状态转移：cash → hold → cash → hold → cash → hold → cash

        int cash = 0;
        int hold = -prices[0];

        int preCash = cash;
        int preHold = hold;
        for (int i = 1; i < len; i++) {
            cash = Math.max(preCash, preHold + prices[i]);
            hold = Math.max(preHold, preCash - prices[i]);

            preCash = cash;
            preHold = hold;
        }
        return cash;
    }
}

public class Self{
    public static void main(String[] args) {
        int[] a = {7,1,5,3,6,4};
        Solution so = new Solution();
        int ans = so.maxProfit(a);
        System.out.print(ans);
    }
}

/*

山脚 ， 山顶的方法不是一个好的方法。 

上山，下山 ： 用while + 固定的 双指针 low，top .  双指针，就不要想着用 for 

下边low 都必须定位在当前位置，满足条件，则++ 到下一个位置。
采用 prices[low] >= prices[low+1] : 如果可以继续下降，则继续下降。配合 low+1 < len
不用 prices[low] < prices[low-1]  : 这种只能说明，当前小于前一个，可能继续下降，而不是一定可以下降。所以这个定义不好。


股票问题， 最好就是  贪心 or 动态规划 。动态规划 的 思想 可以 解决 很多问题。凡事就是要有规划
*/
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(prices.length < 2) return 0;
        if(prices.length == 2) return prices[0] < prices[1] ? prices[1]-prices[0] : 0;
        int ans = 0;
        int low = 0;
        int top = 0;
        while(low < len){
            low = top;   //    重点 ，，low 每次都是从 top开始找
            while(low+1 < len && prices[low] >= prices[low+1]){
                low++;
            }
            if(low == len - 1) return ans;
            top = low;     //    重点 top 每次都是从 low 开始找
            while(top+1 < len && prices[top] <= prices[top+1]){
                top++;
            }
            if(top == low) return ans;
            ans = ans + prices[top] - prices[low];
        }
        return ans;
    }
}