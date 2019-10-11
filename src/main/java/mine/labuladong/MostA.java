package mine.labuladong;

import java.util.*;

/**
 * MostA
 */
public class MostA {
    /** 总按键次数 */
    private int N;

    /** 四种状态 */
    // private static enum States {
    // START, SELECT_ALL, COPIED, PASTED
    // }
    final byte SELECTED_COPIED = 0;
    // final byte COPIED = 1;
    final byte PASTED = 2;

    private int max = 0;

    public MostA(int N) {
        this.N = N;
        for (int i = 1; i <= N; i++) {// 相当于进行了状态START
            dfs(i + 2, i, i, PASTED);
        }
    }

    public int ans() {
        return max;
    }

    /**
     * 含有重叠子问题：对于同一组(depth,count,copy,cur)应当只计算一次。记忆化技巧
     * 
     * @param depth 深度，在达到N时结束
     * @param count 当前屏幕中A的数量
     * @param copy  当前剪贴板中的A的数量
     * @param cur   当前状态
     */
    private void dfs(int depth, int count, int copy, byte cur) {
        if (depth >= N) {
            if (max < count)
                max = count;

        }
        switch (cur) {
        case SELECTED_COPIED:
            // System.out.println(String.format("Selected_Copied count:%-5d copy:%-5d",
            // count, copy));
            // dfs(depth + 2, count + copy, copy, PASTED);
            dfs(depth + 1, count + copy, count, PASTED);
            break;

        // case COPIED:
        // dfs(depth + 1, count, count, PASTED);
        // break;
        case PASTED:
            // System.out.println(String.format("Pasted count:%-5d copy:%-5d", count,
            // copy));
            // 一致选择粘贴
            dfs(depth + 1, count + copy, copy, PASTED);
            // 或者选择全选并，并进入下一轮
            dfs(depth + 2, count, count, SELECTED_COPIED);
            break;
        default:
            break;
        }
    }

    private class Tuple {
        int depth, count, copy;
        byte cur;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Tuple) {
                Tuple tmp = (Tuple) obj;
                if (cur == tmp.cur && count == tmp.count && copy == tmp.copy && depth == tmp.depth)
                    return true;
            }
            return false;
        }

        public Tuple(int depth, int count, int copy, byte cur) {
            this.depth = depth;
            this.count = count;
            this.copy = copy;
            this.cur = cur;
        }

    }

    private Set<Tuple> dict = new HashSet<>();

    @Deprecated
    /**
     * 重叠子问题的判定有问题；
     * 
     * 应该是距离叶节点相等距离（N-depth），不需要考虑其他几类；
     * 
     */
    public void memoDFS(int depth, int count, int copy, byte cur) {// IMPORTANT:可能必须需要返回值才能进行memo和dp
        if (depth >= N) {
            if (max < count)
                max = count;
            return;
        }
        if (dict.contains(new Tuple(depth, count, copy, cur))) {

        }
    }

    public static void main(String[] args) {
        // for (int i = 3; i < 20; i++) {
        // System.out.println(String.format("%-5d\t:\t%-10d", i, new MostA(i).ans()));
        // }

        System.out.println(new MostA(50).ans());
    }
}