package Q49;

import java.util.*;

/*
对数组求 Hash 的方式要谨慎使用。  数组长度较端，元素较小时好一些。
大数组，大元素数组，求hash降低冲突比较难。而且求hash 非常耗时。
这里只当作是  练习 重写  hashcode 和 equals 来配合HashMap吧，。

优化极限了。
执行用时：15 ms, 在所有 Java 提交中击败了27.65%的用户
*/

class Solution2 {
    class MyArr{
        int[] ch = new int[128];
        public MyArr(){}
        public MyArr(int[] a){this.ch = a;}
        public int hashCode(){
            int hash = 0;
            int k = 0;
            int temp = 0;
            for(int i = 0; i < 128; i++){
                if(k == 8) {
                    hash = ((hash * 7 + i) % 10003 + temp) % 10003;
                    k = 0;
                    temp = 0;
                }
                temp += ch[i] * i;
                k++;
            }
            return hash;
        }
        public boolean equals(Object anObject) {
            MyArr another = (MyArr)anObject;
            for(int i = 0 ; i < 128; i++){
                if(this.ch[i] != another.ch[i]) return false;
            }
            return true;
        }
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<MyArr,ArrayList<String> > map = new HashMap();
        for(int i = 0; i < strs.length; i++){
            MyArr cur= countf(strs[i]);
            if(map.containsKey(cur)){
                map.get(cur).add(strs[i]);
            }else{
                  ArrayList<String> tmp = new ArrayList<>();
                tmp.add(strs[i]);
                map.put(cur,tmp);
            }
        }
        List<List<String> > ans = new ArrayList<>(map.values());
        return ans;
    }
    public MyArr countf(String a){
        int[] aa = new int[128];
        for(int i = 0; i < a.length(); i++){
            aa[a.charAt(i)] ++;
        }
        return new MyArr(aa);
    }
}


//换个方法吧。 这样不行。
/*

还是Hash表，， 直接利用 String 的 hashCode 方法来实现。 省略很多时间。

执行用时：7 ms, 在所有 Java 提交中击败了98.65%的用户

重点 ：  只要字符串 内容相同， hashCode 就一定相同。

即使遇到 数组 需要求 hashCode  , 也要先转成 字符串，再来求 hashCode.
多好的方法，为啥要自己写。

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
该方法 可以用于实现任意数组的 hashCode方法。

关于 hashCode中 h 溢出 的解释
字符串过长时可能导致int型溢出，但Java中溢出并不会导致运行时错误，
而只专是溢出位属丢失，程序仍然可以执行。如果int型是作为算术运算结果的，
那溢出当然会导致结果不正确；但此处int型是作为hash使用的，因此溢出也是可以接受的。

即溢出的结果也是 可 接收结果。 并不会有什么异常， 继续 算 下去 就行。
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,ArrayList<String> > map = new HashMap();
        for(int i = 0; i < strs.length; i++){
            char[] a= strs[i].toCharArray();
            Arrays.sort(a);
            String cur = new String(a);
            if(map.containsKey(cur)){
                map.get(cur).add(strs[i]);
            }else{
                ArrayList<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                map.put(cur, temp);
            }
        }
        List<List<String>> ans = new ArrayList<>(map.values());
        return ans;
    }
}

