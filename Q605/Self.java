package Q605;

public class Self {
    public static void main(String[] args) {
        int[] a = {1,0,0,0,1};
        Solution so = new Solution();
        System.out.print(so.canPlaceFlowers(a,1) );
    }
}



class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int canP = 0;
        int len = flowerbed.length;
        if(len == 1){
            if(flowerbed[0] == 0) return n <= 1;
            else return n == 0;
        }
        int i = 0;
        for(; i < len; i++){
            if(flowerbed[i] == 0) count++;
            else break;
        }
        
        if(i == len){
            canP = canP + (count+1)/2;
            return canP >= n;
        }
        canP = canP + count/2;
        count = 0;

        int j = len-1;
        for(; j >= 0; j--){
            if(flowerbed[j] == 0) count++;
            else break;
        }
        canP = canP + count/2;
        
        count = 0;
        
        for(int k = i; k <= j; k++){
            int num = flowerbed[k];
            if(num == 0){
                count++;
            }else{
                int cur = (count+1)/2 == 0 ? 1 : (count+1)/2;
                canP = canP + cur - 1;
                count = 0;
            }
        }
        return n <= canP;
    }
}