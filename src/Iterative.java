public class Iterative {
    int size;

    public Iterative(int size) {
        this.size = size;
    }

    public static long[][] iterative(long[][] a, long[][] b) {
        int n = a.length;
        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

}