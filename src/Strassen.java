public class Strassen {

    int size;

    public Strassen(int n) {
        this.size = size;
    }

    public static long[][] strassen(long[][] a, long[][] b) {
        int n = a.length;
        long[][] result = new long[n][n];

        if (n == 1)
            result[0][0] = a[0][0] * b[0][0];
        else {
            long[][] a11 = new long[n / 2][n / 2];
            long[][] a12 = new long[n / 2][n / 2];
            long[][] a21 = new long[n / 2][n / 2];
            long[][] a22 = new long[n / 2][n / 2];
            long[][] b11 = new long[n / 2][n / 2];
            long[][] b12 = new long[n / 2][n / 2];
            long[][] b21 = new long[n / 2][n / 2];
            long[][] b22 = new long[n / 2][n / 2];

            copyPart(a, a11, 0, 0);
            copyPart(a, a12, 0, n / 2);
            copyPart(a, a21, n / 2, 0);
            copyPart(a, a22, n / 2, n / 2);

            copyPart(b, b11, 0, 0);
            copyPart(b, b12, 0, n / 2);
            copyPart(b, b21, n / 2, 0);
            copyPart(b, b22, n / 2, n / 2);

            long[][] m1 = strassen(add(a11, a22), add(b11, b22));
            long[][] m2 = strassen(add(a21, a22), b11);
            long[][] m3 = strassen(a11, minus(b12, b22));
            long[][] m4 = strassen(a22, minus(b21, b11));
            long[][] m5 = strassen(add(a11, a12), b22);
            long[][] m6 = strassen(minus(a21, a11), add(b11, b12));
            long[][] m7 = strassen(minus(a12, a22), add(b21, b22));

            long[][] c11 = add(minus(add(m1, m4), m5), m7);
            long[][] c12 = add(m3, m5);
            long[][] c21 = add(m2, m4);
            long[][] c22 = add(minus(add(m1, m3), m2), m6);

            copyWhole(c11, result, 0, 0);
            copyWhole(c12, result, 0, n / 2);
            copyWhole(c21, result, n / 2, 0);
            copyWhole(c22, result, n / 2, n / 2);
        }
        return result;
    }

    private static void copyPart(long[][] source, long[][] destination, int row, int col) {
        for (int i = 0; i < destination.length; i++) // row
            for (int j = 0; j < destination.length; j++)  // col
                destination[i][j] = source[i + row][j + col];
    }


    private static void copyWhole(long[][] source, long[][] destination, int row, int col) {
        for (int i = 0; i < source.length; i++)
            for (int j = 0; j < source.length; j++)
                destination[i + row][j + col] = source[i][j];
    }


    private static long[][] add(long[][] m1, long[][] m2) {
        long result[][] = new long[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++)
                result[i][j] = m1[i][j] + m2[i][j];
        }
        return result;
    }

    private static long[][] minus(long[][] m1, long[][] m2) {
        long result[][] = new long[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }
}
