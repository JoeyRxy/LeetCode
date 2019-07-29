package mine.knowledge.stack_pop_sequence;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * StackGenerability
 */
public class StackGenerability {

    private String original;
    private int N;
    private int[] rank;

    public StackGenerability(@NotNull String original) {
        this.original = original;
        this.N = original.length();
        this.rank = new int[128];
        for (int i = 0; i != N; i++)// 记录原始字符串中每个字符串的位置
            rank[original.charAt(i)] = i;
    }

    public boolean isGenable(@NotNull String str) {
        if (str.length() != N)
            return false;
        Stack<Character> stack = new Stack<>();
        int i = 0, k = 0;
        char c, tmp;
        int rnk1, rnk2;
        while (i != N) {
            c = str.charAt(i);
            while ((tmp = original.charAt(k++)) != c) {
                stack.push(tmp);
            }
            stack.push(c);
            while (!stack.isEmpty()) {
                rnk1 = rank[stack.peek()];
                rnk2 = rank[str.charAt(i)];
                if (rnk1 < rnk2)
                    break;
                else if (rnk1 > rnk2)// 如果栈顶元素的rank大于当前元素的rank；例如：对43152这个字符串，当栈(--1,2)顶为2，str[i(=2)]为1，则即刻宣告不可能
                    return false;
                // 如果相等----即栈顶元素和字符串中的当前元素相同
                stack.pop();
                i++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String originalString = "12345";
        String[] testString = { "35421", "12345", "54321", "23451", "43521", "43251", "45321", "35412", "43152" };
        StackGenerability t = new StackGenerability(originalString);
        for (String str : testString)
            System.out.println(str + "\t" + t.isGenable(str));
    }
}