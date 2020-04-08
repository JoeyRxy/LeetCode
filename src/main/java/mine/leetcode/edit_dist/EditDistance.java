package mine.leetcode.edit_dist;

/**
 * <p>
 * 给你两个单词&nbsp;<em>word1</em>
 * 和&nbsp;<em>word2</em>，请你计算出将&nbsp;<em>word1</em>&nbsp;转换成&nbsp;<em>word2
 * </em>所使用的最少操作数&nbsp;。
 * </p>
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * </p>
 * <ol>
 * <li>插入一个字符</li>
 * <li>删除一个字符</li>
 * <li>替换一个字符</li>
 * </ol>
 * <p>
 * <strong>示例&nbsp;1：</strong>
 * </p>
 * 
 * <pre>
 * <strong>输入：</strong>word1 = "horse", word2 = "ros"
<strong>输出：</strong>3
<strong>解释：</strong>
horse -&gt; rorse (将 'h' 替换为 'r')
rorse -&gt; rose (删除 'r')
rose -&gt; ros (删除 'e')
 * </pre>
 */
public class EditDistance {

    /**
     * 主要是没有找好的递归方程式。似乎冥冥之中我这种方法和动态规划的那种方法不一样。
     * <p>
     * 我这种方法更像是求解的过程。而动态规划应该不是面向“求解”。
     * <p>
     * 操，但是好像是一样的啊，，哪想错了？
     * 
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int add = add(word1, word2);
        int del = del(word1, word2);
        int update = update(word1, word2);
        return Math.max(add, Math.max(del, update)) + 1;
    }

    private int update(String word1, String word2) {
        byte[] bytes = word1.getBytes();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < bytes.length; i++) {
            for (byte c = 'a'; c <= 'z'; c++) {
                bytes[i] = c;
                int tmp = minDistance(new String(bytes), word2);
                if (tmp < min)
                    min = tmp;
                bytes[i] = (byte) word1.charAt(i);
            }
        }
        return min;
    }

    private int del(String word1, String word2) {
        int len = word1.length();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int tmp = minDistance(word1.substring(0, i) + word1.substring(i + 1), word2);
            if (min > tmp)
                min = tmp;
        }
        return min;
    }

    private int add(String word1, String word2) {
        int len = word1.length();
        int min = Integer.MAX_VALUE;
        if (len >= word2.length())
            return min;
        for (int i = 0; i < len; ++i) {
            for (byte c = 'a'; c <= 'z'; c++) {
                int tmp = minDistance(word1.substring(0, i) + c + word1.substring(i), word2);
                if (tmp < min)
                    min = tmp;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        int attemption = new EditDistance().minDistance(word1, word2);
        System.out.println(attemption);
    }
}