package mine.leetcode.construc_target_array_with_multi_sums;

/**
 * Solution2
 */
public class Solution2 {
    public boolean isPossible(int[] target) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < target.length; i++) {
            if (max < target[i]) {
                max = target[i]; // find the max value
                index = i; // find the index of the max
            }
        }
        if (max == 1)
            return true; // it means we finally get an array full of 1, then we return true;

        for (int i = 0; i < target.length; i++) {
            if (i != index) { // skip the max one's index
                max -= target[i]; // subtract the other num in the array.
                if (max <= 0)
                    return false;// max must be one more than the sum of rest of the target [i].
            }
        }
        target[index] = max; // change the current one to the new max.
        return isPossible(target); // recursively call the function
    }

    public static void main(String[] args) {
        int[] target = { 9, 3, 5 };
        boolean possible = new Solution2().isPossible(target);
        System.out.println(possible);
    }
}