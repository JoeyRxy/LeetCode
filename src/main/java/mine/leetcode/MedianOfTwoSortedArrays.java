package mine.leetcode;

/**
 * MedianOfTwoSortedArrays
 */
public class MedianOfTwoSortedArrays {
    private double findMedian(int[] a) {
        int m = a.length >> 1;
        if (a.length % 2 == 1) {
            return (double) a[m];
        } else {
            return (double) (a[m] + a[m - 1]) / 2;
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0)
            return findMedian(nums2);
        else if (nums2.length == 0)
            return findMedian(nums1);

        // merge
        int n1 = nums1.length, n2 = nums2.length;
        int len = n1 + n2;
        int[] res = new int[len];
        int i, i1 = 0, i2 = 0, t;
        for (i = 0; i < len; i++) {
            if (nums1[i1] > nums2[i2]) {
                t = nums2[i2++];
                if (i2 == n2) {
                    res[i++] = t;
                    while (i < len)
                        res[i++] = nums1[i1++];
                    break;
                }
            } else {
                t = nums1[i1++];
                if (i1 == n1) {
                    res[i++] = t;
                    while (i < len)
                        res[i++] = nums2[i2++];
                    break;
                }
            }
            res[i] = t;
        }
        // find median
        return findMedian(res);
    }

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = { 2 };
        double res = new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
}