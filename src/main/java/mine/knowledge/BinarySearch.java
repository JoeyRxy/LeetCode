package mine.knowledge;

import java.util.Arrays;

/**
 * BinarySearch
 * <p>
 * 非常容易陷入死循环！
 */
public class BinarySearch {

    /**
     * 找到数组里比target大的最小元素的位置
     * 
     * @param <T>
     * @param array
     * @param target
     * @return pos：{@code array[pos] > target} and {@code array[pos - 1] <= taeget}
     */
    public static <T extends Comparable<T>> int leftBoundBiSearch(T[] array, T target) {
        int l = 0, r = array.length;// [a,b)
        int mid;
        while (l < r) {
            // 为什么是小于而不是小于等于
            // 在l+1=r的时候，mid的值其实是l：经过下一次循环，不论是哪种选择，都是l==r的时候就应该退出了;如果不退出，由于我们最终所求为 比
            // target大的元素，那么永远都会执行第一个判断：r=mid;那就跳不出去了
            mid = (l + r) / 2;
            if (array[mid].compareTo(target) > 0) {
                // [l,mid)
                // 如果中点大于target，target就在左边
                r = mid;
            } else {// (mid,r)
                // 如果中点小于target，自然是在右边
                // 但如果中点等于target，所要找的元素其实也是在右边：比target大的最小元素的位置！
                l = mid + 1;
            }
        }
        return r;
    }

    /**
     * 
     * @param <T>
     * @param array
     * @param target
     * @return 比target小的最大元素位置
     */
    public static <T extends Comparable<T>> int biSearch(T[] array, T target) {
        int l = 0, r = array.length;
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (array[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else if (array[mid].equals(target)) {
                return mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Integer[] array = { 0, 1, 2, 2, 4, 7, 9, 10 };
        int pos1 = BinarySearch.leftBoundBiSearch(array, 2);
        int pos2 = BinarySearch.leftBoundBiSearch(array, 5);
        System.out.println(pos1 + ": " + array[pos1] + "\n" + pos2 + ": " + array[pos2]);

        int pos3 = BinarySearch.biSearch(array, 2);
        int pos4 = BinarySearch.biSearch(array, 5);
        System.out.println(pos3 + ": " + array[pos3] + "\n" + pos4 + ": " + array[pos4]);

    }
}