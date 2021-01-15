package Q714;


public class Self{
    public static void main(String[] args) {
        
    }
}

/*





每一天结束获取的总利润价值。 

stock[i] ： 若当天持有股票，获得的总利润。  今天买入， 或者继续持有股票。
money[i] : 若当天持有现金， 能获得的总利润。今天卖出， 或者继续持有现金。

转移方程：

            stock[i] = Math.max(stock[i-1], money[i-1]-prices[i]);
            money[i] = Math.max(money[i-1], stock[i-1]+prices[i]-fee);


*/

class Slef2 {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[] stock = new int[len];
        int[] money = new int[len];

        stock[0] = -prices[0];
        for(int i = 1; i < len; i++){
            stock[i] = Math.max(stock[i-1], money[i-1]-prices[i]);
            money[i] = Math.max(money[i-1], stock[i-1]+prices[i]-fee);
        }
        return money[len-1];
    }
}
// 空间优化  完美了
class Self3 {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
    
        int money = 0;
        int stock = -prices[0];
        
        for(int i = 1; i < len; i++){
            int curmoney = money;
            int curstock = stock;
            stock = Math.max(curstock, curmoney-prices[i]);
            money = Math.max(curmoney, curstock+prices[i]-fee);
        }
        return money;
    }
}

// 贪心应该也可以。
// 这种贪心策略就是， 有利就图。
// Q122 因为没有收学费， 所以可以看相邻两天是否有利差。累计每一分的利差，就是总的利差。

// 但是该题有手续费，就不能这么做， 例如 235   按照Q122.  第一天到第二天赚1，第二天到第三天赚2. 总利润是 3.
// 如果直接用在该题。 则则发现相邻两天都不能获利，因为利润是 1，2， 而手续费是2.  但是若第一天买入，最后卖出。 则可以获利 3-2 = 1

// 本质是 短线交易，会符出更过的手续费。 利润与交易次数挂钩。


// 正确的贪心应该是， 爬山坡。 每次从山脚爬到山顶就看是否有利可图。。
// 双指针即可。就不写了。

class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}