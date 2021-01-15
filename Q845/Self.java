package Q845;

public class Self {
    
}

/*

变量的定义一定要准确。 left 就是左山脚  top 就是山顶  right 就是 右山脚。

*/


class Solution {
    public int longestMountain(int[] A) {
        if(A.length < 3) return 0;
        // 每次都是以 right 为左山脚，去找山顶，从山顶去找 右山脚。
        int left = 0, top = 0, right = 0;
        int ans = 0;
        while(top < A.length - 1 &&  right < A.length){
            //找新的左山脚
            // 1.两座山 的 山脚不一定相连，  中间也可能是平地。
            // 2.开头可能是平地或者 下坡， 或者下坡后是平地。
            // 以上两种情况都要越过去。
            while(right <  A.length-1 && A[right] >= A[right+1]){
                right++;
            }
            // 如果发现 要找的左山脚， 已经是最后一个位置，则后边就不可能有山顶和 下坡。
            if(right > A.length - 2) return ans;

            // 找到左山脚。 
            left = top = right;
            // 找 top 
            while(top < A.length - 1 && A[top] < A[top+1]){
                top++;
            }
            // 取不到 山顶了
            // top 到达最后一个,后边没有下坡
            if(top == A.length - 1)   break;

            // 找 right
            right = top+1;
            if(A[right] == A[top]) continue;  //  平顶山的情况，也不合法
            while(right < A.length-1 && A[right] > A[right+1]){
                right++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}