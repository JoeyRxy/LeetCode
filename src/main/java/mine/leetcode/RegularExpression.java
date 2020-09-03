package mine.leetcode;

public class RegularExpression {

    public boolean isMatch(String s, String p) {
        return dp(s, p);
    }

    /**
     * 另一种思路
     * 
     * @param s
     * @param p
     * @return
     */
    private boolean dp(String s, String p) {
        s = "$" + s;
        p = "$" + p;
        int m = s.length(), n = p.length();
        boolean f[][] = new boolean[m + 1][n + 1];
        f[0][0] = true;
        // f[1][1] = s.charAt(0) == p.charAt(0) || '.' == p.charAt(0);
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    if ('.' == p.charAt(j - 2) || s.charAt(i - 1) == p.charAt(j - 2))
                        f[i][j] = f[i][j - 2] || f[i - 1][j];
                    else
                        f[i][j] = f[i][j - 2];
                } else if ('.' == p.charAt(j - 1) || s.charAt(i - 1) == p.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1];
            }
        }
        return f[m][n];
    }

    /**
     * memo[i][j][0] : i,j是否已存在
     * <p>
     * memo[i][j][1] : i,j处的结果
     */
    private boolean memo[][][];

    private boolean recurWithMemo(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;
        memo = new boolean[s.length() + 1][p.length() + 1][2];
        memo[0][0][0] = true;
        memo[0][0][1] = true;
        return helper(s, p);
    }

    /**
     * 尾递归？
     * <p>
     * f[i][j] :
     * <p>
     * if(j+1 < p.length && p[j+1] == '*')
     * <p>
     * f[i][j-2] || matches(i,j) && f[i-1][j]
     * <p>
     * else
     * <p>
     * matches(i,j) && f[i-1][j-1]
     */
    private boolean helper(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if (pLen == 0)
            return sLen == 0;
        if (memo[sLen][pLen][0])
            return memo[sLen][pLen][1];
        boolean firstMatch = (sLen > 0) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        boolean res;
        if (pLen > 1 && p.charAt(1) == '*')
            res = helper(s, p.substring(2)) // 0个匹配
                    || (firstMatch && helper(s.substring(1), p));// 至少1个匹配
        else
            res = firstMatch && helper(s.substring(1), p.substring(1));
        return res;
    }

    private boolean recursive(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;

        boolean firstMatch = (s.length() > 0) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*')
            return recursive(s, p.substring(2)) // 0个匹配
                    || (firstMatch && recursive(s.substring(1), p));// 至少1个匹配
        else
            return firstMatch && recursive(s.substring(1), p.substring(1));
    }

    public static void main(String[] args) {
        String s = "fc";
        String p = "fa*c";
        boolean match = new RegularExpression().isMatch(s, p);
        System.out.println(match);
    }
}