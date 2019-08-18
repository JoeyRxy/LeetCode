package mine.leetcode;

// import java.util.ArrayList;
// import java.util.List;

/**
 * HammingDistance
 * <p>
 * Easy
 * </p>
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int count = 0;
        while (x != 0 || y != 0) {
            if (x % 2 == y % 2)
                count++;
            x >>= 1;
            y >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int x = 1, y = 4;
        System.out.println(new HammingDistance().hammingDistance(x, y));
    }
}