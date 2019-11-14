package mine.basictest;

import java.util.Arrays;

/**
 * Matrix
 */
public class Matrix {

    public double[][] mat;

    public Matrix(double[][] a) {
        mat = a;
    }

    public Matrix(int m, int n) {
        mat = new double[m][n];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Matrix :\n");
        for (double[] ds : mat) {
            System.out.println(Arrays.toString(ds));
        }
        return new String(builder);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

}