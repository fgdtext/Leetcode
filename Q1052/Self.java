package Q1052;

public class Self {
    
}

class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        int left = 0, right = 0;
        int ans = 0;
        int sum = 0;
        int sumX = 0;
        while(right < len){
            if(grumpy[right] == 0) sum += customers[right];
            else sumX += customers[right];
            right++;
            if(right - left > X){
                if(grumpy[left] == 1) sumX -= customers[left];
                left++;
            }
            ans = Math.max(ans, sumX);
        }
        return ans+=sum;
    }
}