package HUAWEI.Hj28;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

/*

匈牙利算法： 参考
https://blog.csdn.net/Dark_Scope/article/details/8880547  

题目： 这是一个二分图， 左侧是男性队列，右侧是女性队列。 每一个男性只和其中几位女性有缘。 现在分配女朋友。
        问： 如何分配，才能使得 情侣对数 最多。 

思路：  先到先得 能抢就抢
        1. 按照男性队列(boy数组)循序， 给每一个男性去找女朋友。 
        2. 先到先得，男性总是按照 女性的顺序去 匹配女性。
        3. 能抢就抢，即使后边有未分配的妹子，还是先去抢第一个感兴趣的，抢不到，则抢第二个，最后才会轮到那个没有分配的妹子。
            可以看到，x总是先标记used标记j,表示妹子x要了。然后设法给j原来的男朋友y重新找个女朋友，此时，y知道j已经被x抢了，他只能再去抢别人的，
            或者按顺序找到一个没有分配的女性。

精髓就是 used虚拟抢占表的使用。 used被标记，如果标记的是之前已经被占用的女性，那么表示，只是发生了女友交换。
        如果标记到了未占用的女性，那么一定会有girl2boy[j]= x， 即给这个新的女性分配男朋友。
        所以，看到一个女生，先used标记抢占。然后尝试给其他人交换女友，或者分配新的妹子。
        被抢了女朋友的男性， 会有find(girl2boy[j] = y)，给y重新分配，y也是进来，就直接抢，并且标记，并给被抢的分配女朋友。
        如果发现，有缘的全部被 used抢占了，就返回false,然后尝试给y分配下一个，但是如果都返回false,即所有方案都没法交换了。
        x就无法给被抢的y分配女友。 那么x就无法真正通过 girl2boy[j]= x，得到女友，即代表x分配失败。x无法分配到女友。

*/
// 伪代码，未做初始化等。
class Self{

    boolean[] used;
    boolean[][] line;
    int[] girl;
    int[] boy;
    int[] girl2boy;

    boolean find(int x){
        int m = girl.length,j;
        for (j = 0; j <= m; j++){    //扫描每个妹子
            //  line[boy[x]][girl[j] = true  代表两个在一起合适。
            if (line[boy[x]][girl[j]] == true && used[j] == false)      
            //如果有暧昧并且还没有标记过(这里标记的意思是这次查找曾试图改变过该妹子的归属问题，但是没有成功，所以就不用瞎费工夫了）
            {
                used[j] = true;
                if (girl2boy[j]==-1 || find(girl2boy[j])) { 
                    //名花无主或者能腾出个位置来，这里使用递归
                    girl2boy[j]= x;    // 位置为 j的 女性的 男友 在 位置 x
                    return true;
                }
            }
        }
        return false;
    }

    public int run(){
        int ans = 0;
        int n = boy.length;
        girl2boy = new int[girl.length];
        // 一开始，每一个女性，都没有男友。  == -1 代表单身
        Arrays.fill(girl2boy, -1);
        for(int i = 0; i < n; i++){
            used = new boolean[girl.length];
            if(find(i)) ans++;
        }

        return ans;
    }
}


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        // 初始化数据到nums数组中，并统计奇数个数
        while((line = br.readLine()) != null){
            int count = Integer.parseInt(line);
            String[] elements = br.readLine().split(" ");
            int[] nums = new int[count];
            int oddCount = 0;
            int index = 0;
            // 统计奇数个数
            for(String ele : elements){
                nums[index] = Integer.parseInt(ele);
                if((nums[index] & 1) == 1)
                       oddCount++;
                index++;
            } 
            int[] oddNums = new int[oddCount];
            int[] evenNums = new int[count - oddCount];
            int oddIndex = 0;
            int evenIndex = 0;
            // 奇偶数分离
            for(int num : nums){
                if((num & 1) == 0){
                    evenNums[evenIndex++] = num;
                }else{
                    oddNums[oddIndex++] = num;
                }
            }
            int pairCount = 0;
            int[] evenPair = new int[evenIndex];
            Arrays.fill(evenPair,-1);
            for(int i = 0; i < oddIndex; i++){
                boolean[] used = new boolean[evenIndex];
                if(find(i,oddNums,evenNums,evenPair,used)) pairCount++;
            }   
            System.out.println(pairCount);
        }
    }
    private static boolean find(int oddIndex, int[] oddNums, int[] evenNums, int[] evenPair, boolean[] used){
        for(int i = 0; i < evenNums.length; i++){
            if(isP(oddNums[oddIndex]+evenNums[i]) && !used[i]){
                used[i] = true;
                if(evenPair[i] == -1 || find(evenPair[i],oddNums,evenNums,evenPair,used)){
                    evenPair[i] = oddIndex;
                    return true;
                }
            }
        }
         return false;
    }
    // 对6求余数，余数为 1或5的才可能是素数
    private static boolean isP(int num) {
        if(num <= 3) {
            return num > 1;    // 2,3都是素数
        }
        //6*n+2;6*n+3;6*n+4;6*n等都不是素数;可过滤掉2/3的判断
        if(num % 6 != 1 && num % 6 !=5) {
            return false;
        }
         
        double sqrt = Math.sqrt(num);
        for (int i = 5; i < sqrt; i += 6) {
            //只变量2类数据num % 6 == 1 num % 6 == 5
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}