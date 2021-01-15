package Q557;

class Solution {
    public String reverseWords(String s) {
        StringBuilder sBuilder = new StringBuilder();
        String[] strs;
        strs = s.split(" ");
        for(int i = strs.length - 1; i >= 0; i--){
            sBuilder  = sBuilder.append(strs[i]+" ");
        }
        sBuilder.deleteCharAt(sBuilder.length() - 1);
        return sBuilder.reverse().toString();
    }
}