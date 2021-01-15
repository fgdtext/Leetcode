package Q57;


import java.util.*;


public class Self {
    public static void main(String[] args) {
        int[][] a = {{1,3},{6,9}};
        int[] b = {2,5};
        Solution s = new Solution();
        int[][]  ans = s.insert(a, b);
        for(int i = 0; i < ans.length; i++){
            System.out.println(ans[i][0] + ":::" + ans[i][1]);
        }
    }
}
/*
两个区间的关系，   
1.左交集，右交集(两个区间左右不同)
2.a包含b
3.b包含a
4.无关 a < b  ||  b < a

*/




class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0){
            int[][] temp = new int[1][2];
            temp[0] = newInterval;
            return temp;
        }
        ArrayList<int[]> ans_list = new ArrayList<>();
        int left = newInterval[0];
        int right = newInterval[1];
        int[] pre = intervals[0];
        boolean flag = true;
        for(int[] c : intervals){
            if(c[0] > right && flag){
                int[] temp = {left,right};
                ans_list.add(temp);
                flag = false;
            }
            if(c[0] > right || c[1] < left){ // 不可能相关
                int[] temp = {c[0],c[1]};
                ans_list.add(temp);
            }else if(c[0] > left && c[1] < right) continue; // 新区间完全覆盖该区间。放弃该区间
            else if(c[0] <= left && c[1] >= right){  // 新区间是小区间。
                int[] temp = {c[0],c[1]};
                ans_list.add(temp);
                flag = false;
            }
            else if(c[0] <= left&& c[1] >= left && c[1] < right){ // 合并
                left = c[0];
            }
            else if(c[0] <= right && c[1] >= right && c[0] > left){ // 合并
                right = c[1];
            }
        }
        if(flag && intervals[intervals.length - 1][0] <= right){
                int[] temp = {left,right};
                ans_list.add(temp);
        }
        int[][] ans = new int[ans_list.size()][2];
        for(int i = 0; i < ans_list.size(); i++){
            ans[i][0] = ans_list.get(i)[0];
            ans[i][1] = ans_list.get(i)[1];
        }
        return ans;
    }
}

/*class
ans 的 写法 更清晰


*/
class Ans {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();

        for (int[] interval : intervals) {
            if (interval[0] > right) {           // 无交集，直接加入 ，但要考虑要插入的集合
                // 在插入区间的右侧且无交集
                if (!placed) {  //  出现右侧集合，说明left right 无法再扩展
                    ansList.add(new int[]{left, right});
                    placed = true;                    
                }
                ansList.add(interval);
            } else if (interval[1] < left) {      // 无交集，直接加入
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {                               // 要合并的情况有很多种。但是都是区更大区间的。
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }

        // left right 可能扩展到了最后。
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
