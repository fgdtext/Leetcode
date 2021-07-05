package ZZmianshi.zijie.Q4;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int p = Integer.parseInt(s[2]);
        int[][] a = new int[p][4];
        for(int i = 0; i < p; i++){
            String[] ss = in.readLine().split(" ");
            for(int j = 0; j < 4; j++){
                a[i][j] = Integer.parseInt(ss[j]);
            }
        }
        HashMap<String,Integer> map = new HashMap<>();
        for(int i = 0; i < p; i++){
            map.put(""+a[i][0]+"a"+a[i][1], i);
        }
        Arrays.sort(a,(o1,o2)->{ return o1[1] - o2[1];});
        // -0.PM序号、1.提出时间、2.优先等级,3.所需时间
        // 1.优先级， 2.所需时间  3. 提出时间， 4.pm序号最小
        PriorityQueue<int[]> que = new PriorityQueue<>((o1,o2)->{
           if(o1[2] != o2[2]){
               return o2[2] - o1[2];
           }else{
               if(o1[3] != o2[3]){
                   return o1[3] - o2[3];
               }else{
                   if(o1[1] != o2[1]){
                       return o1[1] - o2[1];
                   }else{
                       return o1[0] - o2[0];
                   }
               }
           }
        });
        PriorityQueue<Integer> pdui = new PriorityQueue<>();
        int k = 0;
        int time = 1;
        for(int i = 0; i < m; i++){
            pdui.offer(1);
        }
        int[] ans = new int[p];
        while(k < p){
            while(k < p && time >= a[k][1]){
                que.offer(a[k++]);
            }
            // 完成一个任务，执行下一个。
            while(time >= pdui.peek() && !que.isEmpty()){
                int[] nextp = que.poll();
                ans[map.get(""+nextp[0]+"a"+nextp[1])] = time + nextp[3];
                int t = time + nextp[3];
                pdui.poll();
                pdui.offer(t);
            }
            time = pdui.peek();
        }
        for(int i = 0; i < p; i++){
            System.out.println(ans[i]);
        }
    }
}