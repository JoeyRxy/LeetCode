package mine.leetcode.longest_common_substring;

/**
 * LongestCommonSubstring
 */
@SuppressWarnings("unused")
public class LCSubstring {

    public LCSubstring() {

    }

    /**
     * Brute Solution---O(n1^2n2)
     * 
     * @param s1
     * @param s2
     * @return
     */
    public String Brute(String s1, String s2) {
        int N1 = s1.length();
        int N2 = s2.length();
        for (int i = 0; i < N1; i++) {
            for (int j = i; j < N1; j++) {
                if (hasIJ(s2, i, j, s1)) {

                }
            }
        }
        return null;
    }

    /**
     * a pattern search method,we can do it better by KMP or something,but we just
     * use the brute way
     */
    private boolean hasIJ(String s, int i, int j, String p) {
        int N = s.length();
        int len = j - i + 1;
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < len; l++) {
                // TODO
            }
        }
        return false;
    }

    /**
     * Maybe we should find a recursive solution first
     * 
     */
    public String DP(String s1, String s2) {
        return null;
    }

    public static int longestSubstr(String first, String second) {
        int maxLen = 0;
        int fl = first.length();
        int sl = second.length();
        int[][] table = new int[fl + 1][sl + 1];

        for (int i = 1; i <= fl; i++) {
            for (int j = 1; j <= sl; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if (table[i][j] > maxLen)
                        maxLen = table[i][j];
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String first = "dfajirpur";
        String second = "iefajuriaf";
        int len = longestSubstr(first, second);
        System.out.println(len);
    }
}