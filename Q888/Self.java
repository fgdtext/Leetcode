package Q888;

import java.util.HashSet;
/*

两数之和。


一开始想到的思路。
x+a = suma   y+b = sumb

x+b = y+a = (suma+sumb)/2 = mid

x+b = y+a = mid  又 x = suma-a
所以
suma-a+b = mid
b = mid-suma+a = (sumb-suma)/2+a

这样实际上麻烦了，

这样想简单
设要交换的是 a,b
则   suma-a+b = sumb-b+a
则   b = (sumb-suma)/2 + a;


*/
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int suma = 0, sumb = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int e : A){ suma += e;}
        for(int e : B){ 
            sumb += e;
            if(!set.contains(e)) set.add(e);
        }
        int mid = (suma+sumb) >> 2;
        int[] ans = new int[2];
        for(int e : A){
            if(set.contains(mid-suma+e)){
                ans[0] = e;
                ans[1] = mid-suma+e;
                break;
            }
        }
        return ans;
    }
}