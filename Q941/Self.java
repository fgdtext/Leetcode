package Q941;

public class Self {
    
}


class Solution {
    public boolean validMountainArray(int[] A) {
        int time = 0;
        for(int i = 1; i < A.length -1; i++){
            if(A[i] > A[i-1] && A[i] > A[i+1]){
                time++;
            }else if(A[i] == A[i-1] || A[i] == A[i+1]){
                return false;
            }
        }
        return time == 1;
    }
}
/*
答案的比较次数更少。
找第一个 山峰
*/

class Ans {
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // 递增扫描
        while (i + 1 < N && A[i] < A[i + 1]) {
            i++;
        }

        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;
    }
}
