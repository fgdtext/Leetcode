package Q896;

public class Self {
    
}
// more quickly
class Solution {
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if(len < 2) return true;
        int in = 0, de = 0;
        for(int i = 1; i < len; i++){
            if(A[i] >= A[i-1]) in++;
            if(A[i] <= A[i-1]) de++;
        }
        if(in == len-1 || de == len-1) return true;
        return false;
    }
}
// 排除法， 假设原始：递增递减都可能。 那么通过排除，看剩余的可能。
class Ans {
    public boolean isMonotonic(int[] a) {
        boolean up = true, down = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) up = false; // 出现则置false, 表明不可能再是 递增数列
            if (a[i] < a[i + 1]) down = false;  
            if (!up && !down) return false;  // 
        }
        return up || down;  // 若两个都是false，则表明既不是递增，又不是递减。
    }
}

