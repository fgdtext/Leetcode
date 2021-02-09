package Q978;

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] arr = {4,8,12,16};
        so.maxTurbulenceSize(arr);
        System.out.println(-1 >>> 31);
    }
}
/*
            // 判断两个整数是否同号，不能简单的 curflag*preflag 与0比较，因为会溢出。最好的办法就是右移动31位
            // 判断符号位。 也不能通过  curflag ^ preflag， 亦或，符号位相同为0(正)，不同为1(负)。
            // 但是只用亦或，不能正确表达 curflag*preflag == 0的情况。而此处需要表示。
            // 这题很多的时间都是浪费在这里。写了好长时间，不知道为什么错。最后才发现是溢出。4
*/
class Self2{
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if(len < 2) return len;
        int left = 0, right = 1;
        int preflag = 0;
        int curflag = 0;
        int ans = arr[0] != arr[1]? 2 : 1;
        while(right < len){
            curflag = arr[right] - arr[right - 1];
            // 看两个数相乘是否同号。不包含任意一个为0 这里千万要考虑 curflag*preflag的溢出问题。
            int f = (curflag >> 31) == (preflag >> 31) ? 1 : -1;
            if(curflag == 0){ // 遇到等号,直接后移
                ans = Math.max(ans, right-left);
                left = right;
            }
            else if(f > 0){  // 没有反转，则left向右移动
                ans = Math.max(ans, right-left);
                left = right-1;
            }
            // 其他情况都是直接后置right,即右窗口继续扩大。
            preflag = curflag;
            right++;
        }
        // 假设从位置i 到末尾都是合法的，那么应该包含最后一个。
        // 但是上边的循环会导致当right == len-1时若curflag*preflag < 0 时，并没有去取max值。
        // 因为对于所有 curflag*preflag < 0 的位置都没取max值，会导致到末尾没有去识别。
        ans = Math.max(ans, right-left);
        return ans;
    }
}

/*class

*/

class Ans {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }
}

