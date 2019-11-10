package mine.knowledge.stack_pop_sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// TODO
/**
 * StackPopSequence
 * <p>
 * DFS：不用优化，没法优化
 * </p>
 * <p>
 * 两种思路：1.遍历所有可能的排列(2^n)，并利用一个线性时间的算法判断该排列是否可能出现
 * </p>
 * <p>
 * 2.模拟过程
 * </p>
 */
public class StackPopSequence {

    // 1. 可以使用多个stack用来回溯时的记录，但是也可以使用一个stack，只要回溯的时候能够恢复状态；
    // 整个过程中最关键的部分也就是回溯时的恢复问题了；

    // 2. 感觉仔细思考回溯的过程很麻烦，不知道能不能有一种能够用语言描述清楚的“巧妙”的过程能够一次完成而不必考虑一次一次地恢复；
    private char[] original;
    private Stack<Character> stack;// 用来模拟整个过程
    private int N;
    private Stack<Character> tmp_ans;// 每次到叶节点后会得到一个结果
    private List<List<Character>> ans;// 最终答案

    public StackPopSequence(String str) {
        original = str.toCharArray();
        N = original.length;
        stack = new Stack<>();
        tmp_ans = new Stack<>();
        ans = new ArrayList<>();
        dfs(0);
    }

    private void dfs(int idx) {
        if (idx == N) {
            char c = stack.pop();
            tmp_ans.push(c);
            if (stack.isEmpty()) {
                ans.add(new ArrayList<>(tmp_ans));// 之前是把tmp_ans存进去然后再new一个新的空间，但是这样就只能记录之后的pop出来的元素————因为很多时候不会回溯到根节点。
                stack.push(c);// 坑1：不只是51行需要push回去，这里也需要；但貌似只有debug的时候才能发现这个坑吧？！
                tmp_ans.pop();// 坑2：恢复答案比较好；最终的答案另外记录一下就行了：`line51\56\67`
                return;
            }
            dfs(idx);
            stack.push(c);
            tmp_ans.pop();
        } else {
            stack.push(original[idx]);
            dfs(idx + 1);
            stack.pop();
            if (stack.isEmpty())
                return;
            char c = stack.pop();
            tmp_ans.push(c);
            dfs(idx);
            stack.push(c);
            tmp_ans.pop();
        }
    }

    public int count() {
        return ans.size();
    }

    public void printAns() {
        for (List<Character> _ans : ans) {
            for (char it : _ans)
                System.out.print(it);
            System.out.println();
        }
    }

    public StackPopSequence(int n) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < n; i++)
            buffer.append(i + 1);
        original = buffer.toString().toCharArray();
        N = original.length;
        stack = new Stack<>();
        tmp_ans = new Stack<>();
        ans = new ArrayList<>();
        dfs(0);
    }

    public static void main(String[] args) {
        // String s = "1234";
        StackPopSequence t = new StackPopSequence(4);
        System.out.println("========     " + t.count() + "      ==========");
        t.printAns();
    }
}