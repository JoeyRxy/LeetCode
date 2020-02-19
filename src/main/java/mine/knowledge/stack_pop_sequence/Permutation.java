package mine.knowledge.stack_pop_sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Permutation
 */
public class Permutation {

    private boolean[] marked;
    private int N;
    private List<int[]> ans;
    private int[] tmp;

    public Permutation(int n) {
        this.N = n;
        marked = new boolean[N];
        ans = new ArrayList<>();
        tmp = new int[N];
        dfs(0);
    }
    // not work!
    // private void dfs(int x) {
    // tmp[x] = x + 1;
    // marked[x] = true;
    // if (x == N - 1) {
    // int[] _ans = new int[N];
    // for (int i = 0; i != N; i++) {
    // _ans[i] = tmp[i];
    // }
    // ans.add(_ans);
    // marked[x] = false;
    // return;
    // }
    // for (int i = 0; i != N; i++) {
    // if (!marked[i])
    // dfs(i);
    // }
    // marked[x] = false;
    // }

    // private void dfs(int x) {
    // // marked[x] = true;
    // if (x == N - 1) {
    // int[] _ans = new int[N];
    // for (int i = 0; i != N; i++) {
    // _ans[i] = tmp[i] + 1;
    // }
    // ans.add(_ans);
    // return;
    // }
    // for (int i = 0; i != N; i++) {
    // if (!marked[i]) {
    // marked[i] = true;
    // tmp[i] = i;
    // dfs(i);
    // marked[i] = false;
    // }
    // }
    // }

    private void dfs(int depth) {
        if (depth == N) {
            ans.add(Arrays.copyOf(tmp, tmp.length));
            return;
        }
        for (int i = 0; i != N; i++) {
            if (!marked[i]) {
                marked[i] = true;
                tmp[depth] = i + 1;
                dfs(depth + 1);
                marked[i] = false;
            }
        }
    }

    public List<int[]> ans() {
        return ans;
    }

    public void printAns() {
        for (int[] ansi : ans) {
            for (int x : ansi) {
                System.out.print(Integer.toHexString(x).toUpperCase());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Permutation t = new Permutation(10);
        long end = System.currentTimeMillis();
        // t.printAns();

        System.out.println(end - start);
        System.out.println(t.ans().size());
    }
}