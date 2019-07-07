package mine.knowledge.stack_pop_sequence;

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
    private char[] stack;
    private int N;
    private int top;

    public StackPopSequence(String str) {
        original = str.toCharArray();
        N = original.length;
        stack = new char[N];
        marked = new boolean[N];
        top = -1;
        // stack[++top] = original[0];
        push(0);
        // dfs(1);
    }

    private void push(int idx) {
        stack[++top] = original[idx];
    }

    private char pop() {
        return stack[top--];
    }

    private boolean isEmpty() {
        return top == -1;
    }

    private boolean[] marked;

    private void dfs(int idx) {
        for (int i = 0; i < N; i++) {
            if (!marked[i]) {
                marked[i] = true;
                push(idx);
                dfs(idx + 1);
                marked[i] = false;
                pop();

            }
        }

    }

    public static void main(String[] args) {
        String s = "1234";
        StackPopSequence t = new StackPopSequence(s);

        //

        System.out.println("another ans");
        Permutation t2 = new Permutation(4);
        StackGenerability test = new StackGenerability("1234");
        for (int[] var : t2.ans()) {
            if (test.isGenable(var.toString().replace(" ", ""))) {
                System.out.println(var.toString());
            }
        }
    }
}