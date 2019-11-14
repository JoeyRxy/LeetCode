package mine.knowledge.dynamic_programming;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * KMP
 */
public class KMP {

    private char[] pat;
    int lenPat;
    /** dfa[i][c]在状态i下，遇见字符c后状态转移到dfa[i][c] */
    private int[][] dfa;
    private int l;
    private int[] idxCharSet = new int[256];

    /**
     * 
     * @param pat
     * @param text
     */
    public KMP(char[] pat) {
        this.pat = pat;
        this.lenPat = pat.length;
        dfa = new int[lenPat][];
        for (int i = 0; i < lenPat; i++)
            dfa[i] = new int[256];
        constructDFA();
    }

    /**
     * 
     * @param pat
     * @param text
     */
    public KMP(String pat) {
        this(pat.toCharArray());
    }

    public KMP(char[] pat, char[] charSet) {
        this.pat = pat;
        this.lenPat = pat.length;
        l = charSet.length;
        dfa = new int[lenPat][];
        for (int i = 0; i < lenPat; i++) {
            dfa[i] = new int[l];
        }
        for (int i = 0; i < l; i++) {
            idxCharSet[charSet[i]] = i;
        }
        constructDFA(charSet);
    }

    public KMP(String pat, char[] charSet) {
        this(pat.toCharArray(), charSet);
    }

    /**
     * lenPat+1个状态：0,...,lenPat
     */
    private void constructDFA(char[] charSet) {
        dfa[0][idxCharSet[pat[0]]] = 1;
        int prev = 0;
        for (int state = 1; state < lenPat; state++) {
            for (char c = 0; c < l; c++) {
                dfa[state][c] = dfa[prev][c];
            }
            int c = idxCharSet[pat[state]];
            dfa[state][c] = state + 1;
            prev = dfa[prev][c];
        }
    }

    public List<Integer> searchWithCharSet(String text) {
        int n = text.length();
        int state = 0;// 初始状态
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            state = dfa[state][idxCharSet[text.charAt(i)]];
            if (state == lenPat) {
                res.add(i - lenPat + 1);
                state = 0;
            }
        }
        return res;
    }

    /**
     * 
     * @return
     */
    public List<Integer> search(String text) {
        int n = text.length();
        int state = 0;// 初始状态
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            state = dfa[state][text.charAt(i)];
            if (state == lenPat) {
                res.add(i - lenPat + 1);
                state = 0;
            }
        }
        return res;
    }

    /**
     * 状态转移图： s_0 -> pat[0] -> s_1 -> pat[1] -> ... -> pat[j] -> ...
     * <p>
     * 
     */
    private void constructDFA() {
        dfa[0][pat[0]] = 1;
        int prevState = 0;
        for (int state = 1; state < lenPat; state++) {
            // for (char c = 0; c < 256; c++) {
            // if (pat[state] == c)
            // dfa[state][c] = state + 1;
            // else
            // dfa[state][c] = dfa[prevState][c];
            // }
            for (char c = 0; c < 256; c++) {
                dfa[state][c] = dfa[prevState][c];
            }
            dfa[state][pat[state]] = state + 1;
            prevState = dfa[prevState][pat[state]];
        }
    }

    public static void main(String[] args) throws IOException {
        String pat = "ABABABC";
        // char[] charSet = { 'A', 'B', 'C' };
        // KMP kmp = new KMP(pat, charSet);
        KMP kmp = new KMP(pat);
        // Random random = new Random(System.currentTimeMillis());
        // int capacity = 200000000;
        // FileOutputStream fileOutputStream = new FileOutputStream("KMP.txt", true);
        // for (int i = 0; i < capacity; i++) {
        // fileOutputStream.write((char) (random.nextInt(15) + 'A'));
        // }
        // fileOutputStream.close();

        InputStream inputStream = new FileInputStream("KMP.txt");
        String text = new String(inputStream.readNBytes(1000000000));
        System.out.println(text.length());
        inputStream.close();

        long start = System.currentTimeMillis();
        List<Integer> res = kmp.search(text);
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("RESULT: duration is " + duration + " ms\n===========================");
        // System.out.println(text);
        System.out.println("res nums: " + res.size());
        for (var x : res) {
            System.out.println(x + "\t" + text.substring(x, x + pat.length()));
        }
    }
}