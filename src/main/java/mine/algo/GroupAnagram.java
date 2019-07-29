package mine.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * GroupAnagram
 * <p>
 * 对于键值对的数据结构，要记得<code>Map</code>.
 */
@SuppressWarnings("unused")
public class GroupAnagram {

    /**
     * 为什么使用我自己写的基于基数排序的排序反而不如调用系统的Arrays.sort()快呢？
     * 
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new TreeMap<>();
        String sortedString;
        for (String str : strs) {
            sortedString = sort(str);
            if (!map.containsKey(sortedString))
                map.put(sortedString, new ArrayList<>());// ATTENTION 重复键的不同值的集合——用一个List的数据结构保存

            map.get(sortedString).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private char charAt(String s, int i) {
        if (i >= s.length())
            return 96;
        return s.charAt(i);
    }

    /**
     * 不用再为整体排序了，使用<code>Map</code>数据结构就行了
     * 
     * @param strs
     * @param d
     * @param lo
     * @param hi
     */
    private void arraySort(String[] strs, int d, int lo, int hi) {
        int[] count = new int[27];
        for (int i = lo; i != hi; i++)
            count[charAt(strs[i], d) - 96]++;

        int k = 0;
        for (int i = 0; i != 27; i++) {
            // strs[]
        }
    }

    private String sort(String s) {
        // O(n) sort
        int[] count = new int[26];
        byte[] ans = s.getBytes();
        int len = ans.length;
        for (int i = 0; i != len; i++)
            count[ans[i] - 'a']++;

        int k = 0;
        for (byte i = 0; i != 26; i++) {
            while (count[i] != 0) {
                ans[k++] = (byte) ('a' + i);
                count[i]--;
            }
        }
        return new String(ans);
    }
}