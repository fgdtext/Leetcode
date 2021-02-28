package Q832;


public class Self {
    
}

class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        for(int i = 0; i < m; i++){
            int left = 0, right = n-1;
            while(left < right){
                int t = A[i][left];
                A[i][left] = A[i][right] ^ 1;
                A[i][right] = t ^ 1;
                left++;
                right--;
            }
            if(left == right) A[i][left] = A[i][left] ^ 1;
        }
        return A;
    }
}


/*
A[i][left] == A[i][right]相等时，互换，然后反转，等于不换，直接取反。

A[i][left] != A[i][right] 不相等时，互换，然后反转。 就相当于不变，0->1->0  又变回来了。

*/
class Ans {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        for(int i = 0; i < m; i++){
            int left = 0, right = n-1;
            while(left < right){
                if(A[i][left] == A[i][right]){
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++; right--;
            }
            if(left == right) A[i][left] ^= 1;
        }
        return A;
    }
}

