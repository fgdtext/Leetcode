package Q134;

public class Self {
    
}
/*
卧槽，居然是猜对了。 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

1.先不考虑循环问题。

-2 -2 -2 3 3  这是每一个站加了油到下一个站能剩余的量(gas[i] - cost[i])（不考虑之前站加的油。）
从这个数组可以看出来， 是负数的必然不是起点，因为，就不可能走到下一个站。所以必然是正数的才可能是起点。
再猜想： 该数组的前缀和最小处可能是循环的终点，下一个位置可能是起点。 假设该点为a, 若a之前的某个点b是起点，
它的差数组接下来一段b->a和是负值，必然不能从b的下一个出发到a点。  （因为到达a才是前缀和最小，a前的一段和必然是负值）
再假设，终点在a的后边的某个位置b ， 则a->b这一段和必然是正数，否则前缀和到b才是最小。在该段和是正数的情况下，从b的下一个出发，很可能无法到达a.
因为，该段和是正数，它在最小前缀的后边，需要最后经过a之后才能取到，但是可能恰好缺少该段和而不能到达a。

2.循环 对 前缀和 的影响。   若原差数组是 -2 3 3 -2 -2  求最小前缀和依然可以定位到第一个3作为出发点。 但是为什么循环没有影响呢。
为什么会等价于-2 -2 -2 3 3呢？   其实求前缀和的意义，就是划分，最小最大子段和， 对于 -2 3 3 -2 -2 完全肯伊等价于 -2 2 的问题把后边写成一个和
其就和 -2 -2 -2 3 3 是一致的的了。都是在前边存在最小前缀和， 后边为起点。


3. 不可行的判断

  （1） ： 差数组的和 为负数， 必然不能走完循环。显然
  （2）： 那么对于和为正数的差数组，一定可以吗？ 不显然，但是是的。
            对于 该问题 总能划分为 -2 -2 -2 3 3  类似的形式， 如果后半段有负数如-2 3 3 -2 -2 也等价于 -2 2 问题 ： 
            总是分为最小前缀和部分，和后边和为正数的形式。正数  >=  最小前缀 就一定能走完。

4 ： 如果最小前缀是在最后的位置，如果sum >= 0 则 说明正数段在前边。

5. code
    将求差 和 求最小前缀和的过程合并。
*/
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;
        int minsum = gas[0] - cost[0];
        int presum = gas[0] - cost[0];
        for(int i = 1; i < gas.length; i++){
            int tempsum = presum + gas[i] - cost[i];
            if(tempsum < minsum) {
                index = i;
                minsum = tempsum;
            }
            presum = tempsum;
        }
        if(presum < 0) return -1;
        return (index+1) % gas.length;
    }
}



/*class
题解的方法 ：   假设x 是起点，然后向后走， 假设恰好不能到达 y点(可以到达xy之间的任意一点)
    现在任取xy中间一点z,  若z可以到y点， 则差数组z->y的和>=0.   但是差数组x->y的 < 0..  所以若z->y的和>=0，则会有 x->z的和<0,即x无法到z
        与前边x必然可到z矛盾， 所以xy之间不存在点可以到 y.
    从该解论可以看出，从x出可以排除x到y的所有点。 然后再从y出发。 


*/

