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
        for (double[] ds : mat) {
            builder.append(Arrays.toString(ds)).append("\n");
        }
        return new String(builder);
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        double[][] t = new double[mat.length][];
        for (int i = 0; i < t.length; i++) {
            t[i] = mat[i].clone();
        }
        return new Matrix(t);
    }

    @Override
    public boolean equals(Object obj) {
        int n = mat[0].length;
        double[][] t;
        if (obj instanceof Matrix)
            t = ((Matrix) obj).mat;
        else
            throw new IllegalArgumentException(obj + " is not a instance of " + Matrix.class);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < n; j++) {
                if (t[i][j] != mat[i][j])
                    return false;
            }
        }
        return true;
    }

}