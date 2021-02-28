package Q89;
import java.util.*;
public class Self {
    
}

class Solution {
    public List<Integer> grayCode(int n) {

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        if(n == 0) return ans;
        ans.add(1);
        if(n == 1) return ans;
        int k = 1;
        int head = 2;
        while(k < n){
            int len = ans.size();
            for(int i = len-1; i >= 0; i--){
                ans.add(ans.get(i)|head);
            }
            head <<= 1;
            k++;
        }
        return ans;
    }
}

class Ans {
    public List<Integer> grayCode(int n) {
        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<1<<n;i++){
            list.add(i^i>>1);
        }
        return list;
    }
}