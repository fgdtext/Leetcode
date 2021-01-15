package Q925;

public class Self {
    
}

class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while(i < name.length()){
            if(name.charAt(i) == typed.charAt(j)){
                i++;j++;
            }else{
                if(i == 0){
                    return false;
                }
                if(name.charAt(i-1) == typed.charAt(j)){
                    j++;
                }else{
                    return false;
                }
            }
            if(j == typed.length()) break;
        }
        while(j < typed.length()){
            if(name.charAt(i-1) != typed.charAt(j++)) return false;
        }
        return i == name.length();
    }
}