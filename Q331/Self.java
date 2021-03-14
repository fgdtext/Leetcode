package Q331;

import java.util.LinkedList;

public class Self {
    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        Solution so = new Solution();
        so.isValidSerialization(preorder);
    }
}
/*

模拟线索二叉树的前序遍历： 当经过一个结点3次时，该结点即可出栈。
在遇到数字，则需要在栈中push(1),表示有一个节点入栈，且当前经过1次。


*/
class Self2 {
    public boolean isValidSerialization(String preorder) {
        int len = preorder.length();
        char[] chs = preorder.toCharArray();
        if(chs[0] == '#' && len == 1) return true;
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < len; i++){
            if(chs[i] == ',') continue;
            if(Character.isDigit(chs[i])) { // 分割完整的数字
                while(i < len && Character.isDigit(chs[i])) i++;
                stack.push(1); // 分割出完整的数字后，即表示该数字入栈，为第一访问
                i--;
            }else{
                // 遇到 # ，而当前栈是空的，则一定不合法。遇到 #前栈中必定还有未满3次访问的结点存在。
                if(stack.isEmpty()) return false;
                int t = stack.pop();  // 下边两个操作是把栈顶+1，遇到#，就会向上返回访问父节点，则栈顶访问次数+1
                stack.push(t+1);
                while(stack.peek() == 3){  // 因为伴随着出栈，所以非栈顶元素可能随着栈顶出栈，访问次数也达到3次
                    stack.pop(); // 栈顶出栈
                    if(stack.isEmpty()){ // 如果出栈后，栈为空。说明现在遍历过的元素已经构成合法的二叉树。后序元素可能构成新的二叉树，导致结果为true
                        if(i < len - 1) return false; // 但是这样就是两课树了，所以返回false
                        break;
                    }
                    t = stack.pop();
                    stack.push(t+1);
                }
            }
        }
        return stack.isEmpty();
    }
}


/*
问题转化： 出度：入度考虑。没有出度为1的结点。数字结点都是非叶子结点，且总是有两个子节点，子节点可能是数字，也可能是#
            # : 全为叶子结点。一颗没有出度为1的二叉树。的性质。
            结点总数为n  数字结点为 n2  #结点为 n0
            则 n2 + n0 = n
            2*n2 + 1 = n = n2 + n0       所以 ： n2 + 1 = n0  就是说：这样的树总是满足： 数字结点+1 = #结点。
            现在我们再考虑前序遍历的特点：总是先遍历 数字结点，再遍历叶子结点。 那么在遍历过程中。
            数字结点+1应该总是 > #结点。  因为我们在遇到最后一个 # 结点时 才会有 n2 + 1 = n0 也就是说前边遍历时
            总是有 n0 < n2 + 1  只有在i == len-1 时 才会有 n2 + 1 = n0 其余时候，必有n0 < n2 + 1

            只有当 n0太大时，才能发现这颗树有错误。

            台秀了。 卧槽
*/

class Solution {
    public boolean isValidSerialization(String preorder) {
        int len = preorder.length();
        char[] chs = preorder.toCharArray();
        int n2 = 0, n0 = 0;
        if(chs[0] == '#' && len == 1) return true;
        for(int i = 0; i < len; i++){
            if(chs[i] == ',') continue;
            if(chs[i] == '#') n0++;
            else{
                while(i < len && Character.isDigit(chs[i])) i++;
                n2++;
            }
            if(i < len - 1 && n0 >= n2+1) return false;
        }
        return n0 == n2 +1;
    }
}