package mine.knowledge.dynamic_programming;

import java.util.Set;

/**
 * KMP
 */
public class KMP {

    private char[] pat, text;
    int lenPat;
    private int[][] dfa;

    public KMP(char[] pat, char[] text) {
        this.pat = pat;
        this.lenPat = pat.length;
        this.text = text;
        for (int i = 0; i < lenPat; i++) {
            dfa[i] = new int[256];
            dfa[i][pat[i]] = i + 1;
        }

    }

    public KMP(String pat, String text) {
        this(pat.toCharArray(), text.toCharArray());
    }

    public KMP(char[] pat, char[] text, Set<Character> charSet) {
        this.pat = pat;
        this.lenPat = pat.length;
        this.text = text;
        int l = charSet.size();
        for (int i = 0; i < lenPat; i++) {
            dfa[i] = new int[l];
            dfa[i][pat[i]] = i + 1;
        }
    }

    public KMP(String pat, String text, Set<Character> charSet) {
        this(pat.toCharArray(), text.toCharArray(), charSet);
    }

    private void constructDFA() {

    }

    public static void main(String[] args) {
        String pat = "ABABC";

    }
}