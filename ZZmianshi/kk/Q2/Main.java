package ZZmianshi.kk.Q2;
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int[][] arr = new int[n][k];
        for(int i = 0; i < n; i++){
            s = in.readLine().split(" ");
            for(int j = 0; j < k; j++){
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<String,Integer> map2 = new HashMap<>();
        
        int p = 0;
        for(int i = 0; i < n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < k; j++){
                min = Math.min(min,arr[i][j]);
            }
            StringBuilder str = new StringBuilder();
            boolean flag = true;
            for(int j = 0; j < k; j++){
                arr[i][j] -= min;
                if(arr[i][j] != 0) flag = false;
                str.append(" "+arr[i][j]);
            }
            if(!flag){
                String sss = str.toString();
                map1.put(sss,map1.getOrDefault(sss,0)+1);
                if(!map2.containsKey(sss)) map2.put(sss,i);
            }else{
                p++;
            }
        }
        int ans = 0;
        ans = p*(p-1);
        HashSet<String> set = new HashSet<>();
        for(String str : map1.keySet()){
            if(!set.contains(str)){
                int max = Integer.MIN_VALUE;
                int[] a = arr[map2.get(str)];
                for(int j = 0; j < k; j++){
                    max = Math.max(max,a[j]);
                }
                StringBuilder ss = new StringBuilder();
                for(int j = 0; j < k; j++){
                    ss.append(" "+(max-a[j]));
                }
                int m1 = map1.get(str);
                int m2 = 0;
                if(map1.containsKey(ss.toString())){
                    m2 = map1.get(ss.toString());
                }
                ans += m1*m2;
                set.add(str);
            }
        }
        System.out.println(ans/2);
    }
}