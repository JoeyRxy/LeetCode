# LCSubstring <==> LCSubsequence
1. LCSubstring:Longest Common Substring,means the answer is a substring of two strings,the characters must be **continuous**.
2. LCSubsequence:Longest Common Subsequence,the characters don't have to be continuous.

## DP
### LCSubstring

The DP is not the best way to solve this problem,but we will try it first.

the JAVA code is here:

```java
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
```

`table[i][j]`有记忆功能：记录了到目前为止，已经连这几个都一样了。
如图：

![image](LCSubstring.jpg)

* `table[i][j] = table[i - 1][j - 1] + 1` `# if first[i] = second[j]`

### LCSubsequence

the `Intro to Algo` has the DP with O(mn) method to solve this problem.

`dp[i][j]`表示

## Suffix tree

