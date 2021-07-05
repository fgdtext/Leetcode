package ZZZZZ;




import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        HashMap<String,Integer> map = new HashMap<>();
        
        int[][] cyc = {{3,2,1,5},{0,2,4,5},{1,0,3,4},{4,2,0,5},{1,2,3,5},{1,4,3,0}};
        
        
        for(int i = 0; i < n; i++){
            char[] s = in.readLine().replace(" ","").toCharArray();
            int ind = 0;
            for(; ind < 6; ind++){
                if(s[ind] == '1'){
                    break;
                }
            }
            LinkedList<Character> list = new LinkedList<>();
            int minv = 7;
            for(int j = 0; j < 4; j++){
                list.offerLast(s[cyc[ind][j]]);
                minv = Math.min(minv,s[cyc[ind][j]]-'a');
            }
            while(list.peekFirst() != minv+'a'){
                list.offerLast(list.pollFirst());
            }
           
            StringBuilder keyb = new StringBuilder();
            for(char c : list){
                keyb.append(c);
            }
            String key = keyb.toString();
            map.put(key,map.getOrDefault(key,0)+1);
        }
        ArrayList<Integer> l = new ArrayList<>();
        for(String e : map.keySet()){
            l.add(map.get(e));
        }
        Collections.sort(l);
        System.out.println(l.size());
        for(int i = l.size()-1; i >= 0; i--){
            System.out.print(l.get(i)+" ");
        }
    }
}
/*class

10
2 5 1 3 4 6
5 4 3 2 1 6
1 4 6 2 3 5
1 5 6 3 4 2
6 4 2 1 5 3
3 6 4 5 2 1
1 6 3 4 2 5
5 1 4 2 6 3
6 2 3 1 5 4
5 3 6 1 4 2
*/