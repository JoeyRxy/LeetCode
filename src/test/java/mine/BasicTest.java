package mine;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import mine.basictest.GaussEliminator;
import mine.basictest.Matrix;

/**
 * Test
 */
public class BasicTest {
    @Test
    public void f() {
        final Random random = new Random(System.currentTimeMillis());
        int i = 0;
        while (i < 10) {
            System.out.println(Math.pow(random.nextGaussian(), 3));
            i++;
        }
    }

    @Test
    public void StringFormat() {
        System.out.println(String.format("%f", Math.random()));
    }

    @Test
    public void gaussElimiTest() throws CloneNotSupportedException {
        final double[][] A = { { 0, 1, 1 }, { 2, 4, -2 }, { 0, 3, 15 }, { 2, 8, 14 } };
        final double[] b = { 4, 2, 36, 42 };
        Matrix mat = new Matrix(A);
        double[] ans = GaussEliminator.solve(mat, b);
        System.out.println(Arrays.toString(ans));
        Matrix tmp = mat.clone();
        GaussEliminator.helper(tmp.mat);
        System.out.println(tmp);
    }
}
