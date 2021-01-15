package Q204;
/*

埃式筛选。
*/
class Solution {
    public int countPrimes(int n) {
        if(n < 2) return 0;
        boolean[] arr = new boolean[n];
        arr[0] = true;
        arr[1] = true;
        int ans = 0;
        for(int i = 2; i < n; i++){
            if(!arr[i]){
                ans++;
                for(int j = 2*i; j < n; j = j + i){
                        arr[j] = true;
                }
            }
        }
        return ans;
    }
}