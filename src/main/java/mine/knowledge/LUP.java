package mine.knowledge;

/**
 * LUP
 */
public class LUP {
    private final double epsilon = 1e-5;

    private double[][] a;

    public LUP(Matrix A, Matrix B) {
        int[] sizeA = A.size();
        int[] sizeB = B.size();
        int colsA = sizeA[1];
        int colsB = sizeB[1];
        if (sizeA[0] != sizeB[0])
            throw new IllegalArgumentException();

        a = new double[sizeA[0]][colsA + colsB];
        for (int i = 0; i < a.length; i++) {
            int j;
            for (j = 0; j < colsA; j++)
                a[i][j] = A.get(i, j);

            for (; j < a[0].length; j++)
                a[i][j] = B.get(i, j - colsA);
        }
        for (int j = 0, i = 0; j < colsA && i < a.length; j++, i++) {
            // 找列j中的最大值
            int maxRow = maxRowOf(i, j);
            if (is0(a[maxRow][j]))
                continue;
            // swap行
            swap(i, maxRow);
            // uniform行
            uniform(i, j);
            // pivot
            pivot(i, j);
        }
    }

    private void pivot(int row, int startCol) {
        assert is0(a[row][startCol] - 1);
        for (int i = 0; i < a.length; i++) {
            if (i == row)
                continue;
            double x = a[i][startCol];
            for (int j = startCol; j < a[0].length; j++) {
                a[i][j] -= x * a[row][j];
                if (is0(a[i][j]))
                    a[i][j] = 0;
            }
        }
    }

    private int maxRowOf(int startRow, int j) {
        int maxi = startRow;
        for (int i = startRow; i < a.length; i++)
            if (!is0(a[i][j]) && a[i][j] > a[maxi][j])
                maxi = i;
        return maxi;
    }

    private void uniform(int row, int startCol) {
        double u = a[row][startCol];
        a[row][startCol] = 1;
        for (int j = startCol + 1; j < a[0].length; j++)
            a[row][j] /= u;
    }

    private boolean is0(double x) {
        if (Math.abs(x) < epsilon)
            return true;
        else
            return false;
    }

    private void swap(int row1, int row2) {
        double[] tmp = a[row1];
        a[row1] = a[row2];
        a[row2] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[\n\t");
        for (int i = 0; i < a.length - 1; i++) {
            builder.append(arrayString(a[i])).append(",\n\t");
        }
        builder.append(arrayString(a[a.length - 1])).append("\n]");
        return builder.toString();
    }

    private String arrayString(double[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0;; i++) {
            b.append(String.format("%.3f", a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

}