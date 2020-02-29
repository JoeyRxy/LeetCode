package mine.leetcode.gen_parens;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * GenParentheses
 * </p>
 * <p>
 * Oh,Yeah!
 * </p>
 */
public class Parentheses {
    private List<String> ans;
    private int N;
    private char[] stack;
    private int balance;
    private int numLeft;

    public Parentheses(int n) {
        N = n << 1;
        ans = new ArrayList<>();
        stack = new char[N];
        dfs(0, true);
    }

    public List<String> generateParenthesis() {
        return ans;
    }

    // initial: depth = 0,left
    private void dfs(int depth, boolean left) {
        if (left) {
            stack[depth] = '(';
            balance--;
            numLeft++;
        } else {
            stack[depth] = ')';
            balance++;
        }
        if (numLeft < N >> 1) {
            dfs(depth + 1, true);
            balance++;
            numLeft--;
        }
        if (balance < 0) {
            dfs(depth + 1, false);
            balance--;
        }
        if (depth == N - 1)
            ans.add(new String(stack));
    }

    public static void main(String[] args) {
        Parentheses test = new Parentheses(10);
        List<String> ans = test.generateParenthesis();
        for (String s : ans) {
            System.out.print(s + "\n");
        }
    }
}