package Q341;

import java.util.*;

public class Self {
    
}


interface NestedInteger {
 
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
}
 
 class NestedIterator implements Iterator<Integer> {

    LinkedList<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        for(int i = nestedList.size()-1; i >= 0; i--){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.poll().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(stack.isEmpty()) return false;
        if(stack.peek().isInteger()) return true;
        while(!stack.isEmpty()){
            NestedInteger top = stack.peek();
            if(top.isInteger()){
                return true;
            }else if(top.getList() != null && top.getList().size() > 0){
                stack.poll();
                for(int i = top.getList().size()-1; i >= 0; i--){
                    stack.push(top.getList().get(i));
                }
            }
        }
        return false;
    }
}



 class NestedIterator2 implements Iterator<Integer> {
    // 存储列表的当前遍历位置
    private Deque<Iterator<NestedInteger>> stack;

    public NestedIterator2(List<NestedInteger> nestedList) {
        stack = new LinkedList<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> it = stack.peek();
            if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
                stack.pop();
                continue;
            }
            // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
            // yfg ： 窝在尝试使用迭代器入栈的方法时，就是卡在了这里。
            /*
                若it迭代器通过next划过的是一个数字，那么再想通过it获取该值就不可能了。该迭代器不能回退。
                ans给出的解决方案是，通过一个新的list，加入该值，然后获取迭代器压入栈。
                这样通过栈顶获取迭代器，就一定满足存在next()

            */
            NestedInteger nest = it.next();
            if (nest.isInteger()) {
                List<NestedInteger> list = new ArrayList<NestedInteger>();
                list.add(nest);
                stack.push(list.iterator());
                return true;
            }
            stack.push(nest.getList().iterator());
        }
        return false;
    }
}


/*class


class NestedIntegerImpl implements NestedInteger{

    boolean isInt = true;
    List<NestedInteger> nestedList;

    public NestedIntegerImpl(boolean isInt){
        this.isInt = isInt;
        this.nestedList = new ArrayList<>();
    }

    public boolean isInteger(){
        return isInt;
    }
 
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger(){
        return 0;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList(){
        return null;
    }
}
*/