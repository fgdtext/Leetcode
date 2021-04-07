package Q42;

class Self_2 {
    
}

class Solution2 {
    public int trap(int[] height) {
        int len = height.length;
        if(len < 3) return 0;
        int left = 0;
        int ans = 0;
        int temp = 0;
        for(int i = 1; i < len; i++){
            if(height[i] < height[left]){
                temp += height[left] - height[i];
            }else if(height[i] > height[left]){
                left = i; ans += temp; temp = 0;
            }
        }
        int right = len-1;
        temp = 0;
        for(int i = len-2; i >= 0; i--){
            if(height[i] < height[right]){
                temp += height[right] - height[i];
            }else{
                right = i; ans += temp; temp = 0;
            }
        }
        return ans;
    }
}