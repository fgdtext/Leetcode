package Q11;

public class Slef {
    
}
/*
双指针
ij分别指向 两端。 每次 假设 min = h(i) > h(j)? j : i; max = h(i) >= h(j) ? i : j;
则，固定短边。min 这条边 与其他 边构成的 区域面积 一定 < h[min]*(j-i);
由于水桶效应。 水的高度 一定 <=  min.  那么 其他边的高度不会使得 水的高度 > min. 区域面积取决于宽度。
所以 由min 出发的 所有区域 面积 一定是 h[min]*(j-i) 最大。

此时固定max , 抛弃 min 向内移动， 此时又会有一高一堤，来确定当前区域构成的最大区域。
*/

class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j){
            res = height[i] < height[j] ? 
                Math.max(res, (j - i) * height[i++]): 
                Math.max(res, (j - i) * height[j--]); 
        }
        return res;
    }
}