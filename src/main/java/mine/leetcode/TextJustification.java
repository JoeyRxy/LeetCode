package mine.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * TextJustification {@link https://leetcode.com/problems/text-justification/}
 */
public class TextJustification {

    private String[] words;

    public List<String> fullJustify(String[] words, int maxWidth) {
        this.words = words;
        List<String> ans = new ArrayList<>();
        StringBuffer tmp;
        int i, totalWordsLength, totalSapceLength, idx = 0;
        int len, wordslength = words.length;
        int num, j, r;
        while (true) {
            i = idx;
            len = words[i].length();
            tmp = new StringBuffer();
            while (len < maxWidth) {
                ++i;
                if (i == wordslength) {
                    tmp.append(words[idx++]);
                    while (idx != i)
                        tmp.append(" " + words[idx++]);
                    tmp.append(space(maxWidth - len));
                    ans.add(tmp.toString());
                    return ans;
                }
                len += (words[i].length() + 1);
            }

            if (len == maxWidth) {// 刚好装下
                i++;
                tmp.append(words[idx++]);
                while (idx != i) {
                    tmp.append(" " + words[idx++]);
                }
                ans.add(tmp.toString());
                if (i == wordslength)
                    return ans;
                continue;
            }
            // 没装下
            num = i - idx - 1;
            if (num == 0) {// 如果只有一个单词（在len>maxWidth情况下是不可能的！）
                tmp.append(words[idx] + space(maxWidth - words[idx].length()));
                idx++;
                ans.add(new String(tmp));
            } else {
                totalWordsLength = wordsLength(idx, i);
                totalSapceLength = maxWidth - totalWordsLength;
                j = totalSapceLength / num;
                r = totalSapceLength - j * num;
                tmp.append(words[idx++]);
                while (r != 0) {
                    tmp.append(space(1 + j) + words[idx++]);
                    r--;
                }
                while (idx != i)
                    tmp.append(space(j) + words[idx++]);
                ans.add(tmp.toString());
            }
        }
    }

    private int wordsLength(int idx, int i) {
        int len = 0;
        for (int j = idx; j != i; j++)
            len += words[j].length();
        return len;
    }

    private String space(int i) {
        char[] c = new char[i];
        for (int j = 0; j < i; j++) {
            c[j] = ' ';
        }
        return new String(c);
    }

    public static void main(String[] args) {
        // String[] words = { "Science", "is", "what", "we", "understand", "well",
        // "enough", "to", "explain", "to", "a",
        // "computer.", "Art", "is", "everything", "else", "we", "do" };
        String[] words = { "ask", "not", "what", "your", "country", "can", "do", "for", "you", "ask", "what", "you",
                "can", "do", "for", "your", "country" };

        int maxWidth = 16;
        TextJustification textJustification = new TextJustification();
        List<String> ans = textJustification.fullJustify(words, maxWidth);
        for (var it : ans) {
            System.out.println("\"" + it + "\"");
        }
    }
}