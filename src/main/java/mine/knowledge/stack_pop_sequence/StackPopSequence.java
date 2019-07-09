package mine.knowledge.stack_pop_sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@SuppressWarnings("unused")
// TODO
/**
 * StackPopSequence
 * <p>
 * DFS
 * </p>
 * <p>
 * 两种思路：1.遍历所有可能的排列(2^n)，并利用一个线性时间的算法判断该排列是否可能出现
 * </p>
 * <p>
 * 2.模拟过程
 * </p>
 */
public class StackPopSequence {

    // private int len;
    // private String s;

    // private Stack<Character> stack, aux;

    // public StackPopSequence(String s) {
    // len = s.length();
    // this.s = s;
    // stack = new Stack<>();
    // aux = new Stack<>();
    // dfs(0);
    // }

    // public void dfs(int i) {
    // if (i == len) {
    // printStack();
    // System.out.println();
    // return;
    // }
    // // 放入
    // stack.push(s.charAt(i));
    // dfs(i + 1);
    // // 不放入
    // stack.pop();
    // i--;
    // printStack();
    // dfs(i + 1);
    // }

    // private void printStack() {
    // for (Character var : stack) {
    // aux.push(var);
    // }
    // while (!aux.isEmpty()) {
    // System.out.print(aux.pop());
    // }
    // }

    private char[] original;
    private Stack<Character> stack;
    private int N;
    private boolean[] marked;
    private List<Character> tmp_ans;
    private List<List<Character>> ans;

    public StackPopSequence(String str) {
        original = str.toCharArray();
        N = original.length;
        stack = new Stack<>();
        marked = new boolean[N];
        tmp_ans = new ArrayList<>();
        ans = new ArrayList<>();
        dfs(0);
    }

    private void dfs(int idx) {
        if (idx == N) {
            char c = stack.pop();
            tmp_ans.add(c);
            if (stack.isEmpty()) {
                ans.add(tmp_ans);
                tmp_ans = new ArrayList<>();
                stack.push(c);
                return;
            }
            dfs(idx);
            stack.push(c);
        } else {
            stack.push(original[idx]);
            dfs(idx + 1);
            stack.pop();
            if (stack.isEmpty())
                return;
            char c = stack.pop();
            tmp_ans.add(c);
            dfs(idx);
            stack.push(c);
        }
    }

    public void printAns() {
        for (List<Character> _ans : ans) {
            for (char it : _ans) {
                System.out.print(it);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String s = "123";
        StackPopSequence t = new StackPopSequence(s);
        t.printAns();
    }
}