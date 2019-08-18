package mine.leetcode.ugly_number_ii;

/**
 * Solution
 * <p>
 * <a href="https://leetcode.com/problems/ugly-number-ii/">leetcode</a>
 * <p>
 * <a href="https://www.geeksforgeeks.org/ugly-numbers/">geeks for geeks</a>
 */
@SuppressWarnings("unused")
public class UglyNum2 {

    private boolean[] list;
    Node top;
    Node root;

    public int nthUglyNumber(int n) {
        // return first(n);//太费空间，实际上也是时间
        // return (int) second(n);
        // return (int) third(n);//超级慢
        return fourth(n);
    }

    // ===============fourth===================//
    // ATTENTION : DP
    private int fourth(int n) {
        int[] list = new int[n + 1];
        list[1] = 1;
        /*
         * 不行 int index = 0;// cur index int cur_2_index = 0; int cur_3_index = 0; int
         * cur_5_index = 0; int tmp; for (int i = 0; i < n; i++) { tmp =
         * Math.min(list[cur_2_index], Math.min(list[cur_3_index], list[cur_5_index]));
         * list[i] = tmp; }
         */
        int _2_index = 0, _3_index = 0, _5_index = 0;
        int next;
        int next_2, next_3, next_5;
        for (int i = 2; i <= n; i++) {
            next_2 = 2 * (list[_2_index + 1]); // 一开始写成了next_2 = 2 * (_2_index + 1)表示的是
                                               // 2*1,2*2,...,2*7,...,2*_2_index
                                               // 这样一列数，但是注意2*7这个数，是不正确的。
                                               // 实际上这里应该使用DP的思想，使用前边已经得到的结果来得到下一个结果————即应该
                                               // ___2*1___,___2*2___,...,___2*5___,___2*6___,___2*8___,...，___2*10___,___2*12...
                                               // 2*list[1],2*list[2],...,2*list[5],2*list[6],2*list[7],...,2*list[9],2*list[10]...
            next_3 = 3 * (list[_3_index + 1]);
            next_5 = 5 * (list[_5_index + 1]);
            next = Math.min(next_2, Math.min(next_3, next_5));
            list[i] = next;
            if (next == next_2)
                _2_index++;
            if (next == next_3)// 不能用else if，因为这个数可能等于多个因子的组合——ex: 30
                _3_index++;
            if (next == next_5)
                _5_index++;
        }
        return list[n];
    }

    // ===============forth===================//

    // ===========third====================//
    private long third(int n) {
        int uglyCount = 0;
        long ans = 0;
        while (uglyCount != n) {
            ans++;
            if (isUgly(ans))
                uglyCount++;
        }
        return ans;
    }

    private boolean isUgly(long n) {
        while (n % 2 == 0)
            n >>= 1;
        while (n % 3 == 0)
            n /= 3;
        while (n % 5 == 0)
            n /= 5;
        return n == 1;
    }

    // ===========third : 太慢了====================//

    // ===========================second===============//

    private class Node {
        Node next;
        Node prev;
        long _value;

        Node(int val) {
            _value = val;
        }

        Node(long val, Node next, Node prev) {
            _value = val;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 在排好序的链表中插入数据{@code val}，从{@code from}开始
     */
    private void insert(Node from, long val) {
        Node tmp = from;
        while (tmp._value < val) {
            if (tmp.next != null)
                tmp = tmp.next;
            else {// next node is null
                tmp.next = new Node(val, null, tmp);
                top = tmp.next;// update top
                return;
            }
        }
        if (tmp._value == val)
            return;
        Node cur = new Node(val, tmp, tmp.prev);
        tmp.prev.next = cur;
        tmp.prev = cur;
    }

    private long valueOf(int index) {
        Node tmp = root;
        int count = 0;
        while (count != index) {
            tmp = tmp.next;
            count++;
        }
        return tmp._value;
    }

    private long second(int n) {
        root = new Node(1);
        top = root;
        Node cur = root;
        int size = 1;
        long _2, _3, _5;
        while (size != n) {
            _2 = cur._value << 1;
            insert(cur, _2);
            if (size == n)
                break;
            _3 = cur._value * 3;
            insert(cur, _3);
            if (size == n)
                break;
            _5 = cur._value * 5;
            insert(cur, _5);
            size++;
            cur = cur.next;
        }
        return valueOf(n - 1);
    }

    private int first(int n) {
        int len = 2 * 3 * 5 * n; // 真正能保证的长度应该是5^n，但是太浪费空间了
        list = new boolean[len + 1];
        list[1] = true;
        dfs(1);
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (list[i]) {
                count++;
                if (count == n)
                    return i;
            }
        }
        return -1;
    }

    private void dfs(int max) {
        if (max >= list.length)
            return;
        list[max] = true;
        dfs(max << 1);
        dfs(max * 3);
        dfs(max * 5);
    }

    public static void main(String[] args) {
        System.out.print(new UglyNum2().nthUglyNumber(1600));
        // System.out.print(Integer.MAX_VALUE);
    }
}