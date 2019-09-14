package mine.labuladong;

import java.util.ArrayList;
import java.util.Random;

/**
 * SubString
 */
public class SubString {

    // O(n)
    public boolean naive(String s, String t) {
        int i = 0, j = 0;
        int lens = s.length();
        int lent = t.length();
        while (i < lens && j < lent) {
            // while (s.charAt(i) != s.charAt(j))
            // j++;
            // i++;
            if (s.charAt(i) == t.charAt(j))
                i++;
            j++;
        }
        if (i == lens)
            return true;
        return false;
    }

    private int leftBiSearch(ArrayList<Integer> a, int target) {
        int l = 0, r = a.size() - 1;
        int mid = l;
        int valMid;
        while (l < r) {
            mid = l + 1 + (r - l) / 2;
            valMid = a.get(mid);
            if (valMid == target)
                return mid;
            else if (valMid < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return mid;
    }

    /**
     * 二分查找法 需要预处理数组
     */
    public boolean improved(String s, String t) {
        ArrayList<Integer>[] indexDict = new ArrayList[127];

        // 预处理构成字典：得到t中每一个字母出现的位置的集合
        int tlen = t.length();
        int slen = s.length();
        int tmp = tlen / 1000 + 1;
        for (int i = 0; i < 127; i++)
            indexDict[i] = new ArrayList<>(tmp);
        for (int i = 0; i < tlen; i++) {
            indexDict[t.charAt(i)].add(i);
        }

        // 开始搜索
        // 依次遍历s，对每一个字符，以二分查找的方式找到上述index[s.charAt(i)]数组中大于当前上次搜索结束位置j的位置

        ArrayList<Integer> index;
        int i, j = 0;
        for (i = 0; i < slen; i++) {
            index = indexDict[s.charAt(i)];
            // 再index数组中搜寻j（以及比j大的）
            j = leftBiSearch(index, j) + 1;
            if (j > tlen)
                break;
        }
        return i == tlen;
    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        String s, t;
        int slen = 5;
        int tlen = 20;
        char[] sbuilder = new char[slen], tbuilder = new char[tlen];
        int r;
        for (int i = 0; i < slen; i++) {
            r = Math.abs(random.nextInt() % 127);
            if (r < 32)
                r += 32;
            sbuilder[i] = (char) r;
        }
        random.setSeed(System.currentTimeMillis());
        for (int i = 0; i < tlen; i++) {
            r = Math.abs(random.nextInt() % 127);
            if (r < 32)
                r += 32;
            tbuilder[i] = (char) r;
        }
        s = new String(sbuilder);
        t = new String(tbuilder);
        SubString foo = new SubString();
        long start, end, duration;
        start = System.currentTimeMillis();
        System.out.println(foo.naive(s, t));
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("duration of naive is " + duration + " ms");

        start = System.currentTimeMillis();
        System.out.println(foo.improved(s, t));
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("duration of improved is " + duration + " ms");
    }
}