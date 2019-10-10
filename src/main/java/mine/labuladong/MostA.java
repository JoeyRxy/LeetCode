package mine.labuladong;

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
    final byte SELECT_ALL = 0;
    final byte COPIED = 1;
    final byte PASTED = 2;

    private int max = 0;

    public MostA(int N) {
        this.N = N;
        for (int i = 1; i <= N; i++) {// 相当于进行了状态START
            dfs(i, i, i, SELECT_ALL);
        }
    }

    public int ans() {
        return max;
    }

    /**
     * 
     * @param depth 深度，在达到N时结束
     * @param count 当前屏幕中A的数量
     * @param copy  当前剪贴板中的A的数量
     * @param cur   当前状态
     */
    private void dfs(int depth, int count, int copy, byte cur) {
        if (depth == N) {
            if (max < count)
                max = count;
            return;
        }
        switch (cur) {
        case SELECT_ALL:
            dfs(depth + 1, count, copy, COPIED);
            break;

        case COPIED:
            dfs(depth + 1, count, count, PASTED);
            break;
        case PASTED:
            // 一致选择粘贴
            dfs(depth + 1, count + copy, copy, PASTED);
            // 或者选择全选，并进入下一轮
            dfs(depth + 1, count, copy, SELECT_ALL);
            break;
        default:
            break;
        }
    }

    public static void main(String[] args) {
        // int[] N = { 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        // for (var x : N)
        // System.out.println(String.format("%-5d\t:\t%-10d", x, new MostA(x).ans()));

        MostA t = new MostA(11);
        int ans = t.ans();
        System.out.println(ans);
    }
}