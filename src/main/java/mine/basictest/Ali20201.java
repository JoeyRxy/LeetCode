package mine.basictest;

/**
 * Ali20201
 */
public class Ali20201 {

    public static int f(int n) {
        return n * (1 << (n - 1));
    }

    public static void main(String[] args) {
        int n = 10000;
        System.out.println(n * (1 << (n - 1)) % 1000000007);
    }
}