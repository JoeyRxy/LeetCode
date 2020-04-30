package mine.leetcode;

import java.util.Iterator;
import java.util.LinkedList;

public class WordsReversion {

    public String reverseWords(String s) {
        LinkedList<String> list = new LinkedList<>();
        byte[] b = s.getBytes();
        int len = b.length;
        for (int i = 0; i < len; i++) {
            while (i < len && b[i] == ' ')
                i++;
            if (i >= len)
                break;
            int j = i;
            while (i < len && b[i] != ' ')
                i++;
            list.addFirst(new String(b, j, i - j));
        }
        StringBuilder builder = new StringBuilder();
        Iterator<String> iter = list.iterator();
        if (iter.hasNext())
            builder.append(iter.next());
        while (iter.hasNext())
            builder.append(" ").append(iter.next());
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "   hello    world!    ";
        String reverseWords = new WordsReversion().reverseWords(s);
        System.out.println(reverseWords);
    }
}