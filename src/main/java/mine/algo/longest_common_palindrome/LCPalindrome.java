package mine.algo.longest_common_palindrome;

/**
 * longestPalindromeSolution
 */
@SuppressWarnings("unused")
public class LCPalindrome {

    public String longestPalindrome(String s) {
        return myAns(s);
    }

    /*
     * public String DP(String s) { int len = s.length(); int[][] dp = new
     * int[len][len]; //init for (int i = 0; i < len; i++) { for (int j = 0; j <
     * len; j++) { dp[i][j]=;//TODO initialize } for (int i = 0; i < max; i++) {
     * 
     * } } }
     */

    public int oddLongestAt(String s, int i) {// the palindrome.length is odd
        int N = Math.min(i, s.length() - i);
        for (int j = 0; j < N; j++) {

        }
        return 0;
    }

    public int evenLongestAt(String s, int i) {// the palindrome.length is even
        return 0;
    }

    public String myAns2(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {// i is the medium of the palindrome

        }
        return null;
    }

    /**
     * 超时了＝ O(n3)
     * 
     * @param s
     * @return
     */
    public String myAns(String s) {
        // Brute
        int max = 0;
        String ans, tmp;
        ans = tmp = new String();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tmp = s.substring(i, j);
                if (isPalindrome(tmp)) {
                    if (j - i > max) {
                        max = j - i;
                        ans = tmp;
                    }
                }
            }
        }
        return ans;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private void tablePrint(boolean[][] table) {
        int N = table.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * DP ???????��???P(i,j)?i...j?????? ???P(i,j)??i...j?????
     * 
     * @param s
     * @return
     */
    public String Palidrome(String s) {
        int N = s.length();
        if (N == 0 || N == 1)
            return s;
        boolean[][] table = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            table[i][i] = true;
            table[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        table[N - 1][N - 1] = true;
        int x, y;
        for (int j = 2; j < N; j++) {
            x = 0;
            y = j;
            while (y < N) {
                table[x][y] = (table[x + 1][y - 1] && (s.charAt(x) == s.charAt(y)));
                x++;
                y++;
            }
        }
        // tablePrint(table);
        // find the substring
        int maxLen = 1;
        int maxI = 0;
        int maxJ = 0;
        int len = 1;
        for (int i = 0; i < N - 1; i++) {
            for (int j = N - 1; j >= i; j--) {
                if (table[i][j] == true) {
                    len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                        maxI = i;
                        maxJ = j;
                    }
                }
            }
        }
        return s.substring(maxI, maxJ + 1);
    }

    public static void main(String[] args) {
        String s1 = "ac";
        String s2 = "abcba";
        LCPalindrome solution = new LCPalindrome();
        String ans1 = solution.Palidrome(s1);
        System.out.println(ans1);
        String ans2 = solution.Palidrome(s2);
        System.out.println(ans2);
    }
}