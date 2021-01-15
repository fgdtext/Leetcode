package Q844;

public class Self {
    
}




class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] a = new char[S.length()];
        char[] b = new char[T.length()];
        int i = 0;
        int j = 0;
        while(j < S.length()){
            if(S.charAt(j) != '#'){
                a[i] = S.charAt(j);
                i++;
            }else{
                i--;
                if(i < 0) i = 0;
            }
            j++;
        }
        int k = 0; j = 0;
        while(j < T.length()){
            if(T.charAt(j) != '#'){
                b[k] = T.charAt(j);
                k++;
            }else{
                k--;
                if(k < 0) k = 0;
            }
            j++;
        }
        if(k != i) return false;
        for(j = 0; j < k; j++){
            if(a[j] != b[j]) return false;
        }
        return true;
    }
}