package mine.algo.longest_common_substring;

import mine.algo.longest_common_substring.LCSubstring;

/**
 * lengthOfLongestSubstring No Repeat
 * <p>
 * <a
 * =href-"https://leetcode.com/problems/longest-substring-without-repeating-characters/">leetcode</a>
 */
@SuppressWarnings("unused")
public class LongStrNoRep {

    public int lengthOfLongestSubstring(String s) {
        return answer(s);
    }

    /**
     * 最优子结构？动态规划？我觉得没有动态规划啊？
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringDP(String s) {
        int[][] dp = new int[128][128];// dp[i][j]代表[i,j)内的最长字串长度
        return 0;
    }

    public int myAns(String s) {
        int max = 0;
        int len = 0;
        int[] t = new int[128];// A table denotes a Mapping from char to int.
        // for (int i = 0; i < t.length; i++) {
        // t[i] = -1;// initialized with -1
        // }
        for (int i = 0; i < s.length(); i++) {
            if (t[s.charAt(i)] < len) {
                if (len > max)
                    max = len;
                len = i - t[s.charAt(i)];
            } else
                len++;
            for (int j = 0; j < len; j++)
                t[s.charAt(i - j)] = j;
        }
        return max;
    }

    // ATTENTION : DP
    public int anotherAns(String s) {
        int[] table = new int[256];// is i-th char already in the ansStr?table[i] record the index of the nearest
                                   // char to curChar
        for (int i = 0; i < 256; i++) {
            table[i] = -1;
        }
        int len = s.length();
        int maxLen = 0;
        int curLen = 0;
        for (int i = 0; i < len; i++) {
            char curChar = s.charAt(i);
            curLen = Math.min(curLen + 1, i - table[curChar]);
            table[curChar] = i;
            maxLen = Math.max(curLen, maxLen);
        }
        return maxLen;
    }

    /**
     * the leetcode answer I think the thought of this solution is just same as
     * mine! BUT where am i wrong? Amazing though.
     * 
     * @param s
     * @return
     */
    public int answer(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcdbb";
        LongStrNoRep t = new LongStrNoRep();
        int len = t.anotherAns(s);
        System.out.println("my ans:" + len);
        System.out.println("the real ans:" + t.answer(s));
    }
}