package Q321;

public class Self {
    public static void main(String[] args) {
        
    }
}
/*

*/

class Self2 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][][] dp = new int[len1][len2][k];
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                for(int l = 1; l < k && i+j+2 >= l; l++){
                    int a = 0;
                    if(i > 0) a = dp[i-1][j][l-1];
                    int b = 0;
                    if(j > 0) b = dp[i][j-1][l-1];
                    dp[i][j][l] = Math.max(a*10+ nums1[i], b*10+nums2[j]); // 选。
                    if(i > 0) a = dp[i-1][j][k];
                    if(j > 0) b = dp[i][j-1][l];
                    dp[i][j][l] = Math.max(dp[i][j][l], b);
                    dp[i][j][l] = Math.max(dp[i][j][l], a);
                }
            }
        }
        return null;
    }
}