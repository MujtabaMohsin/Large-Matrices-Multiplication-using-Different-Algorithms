public class DivideConquer {

    int size;

    public DivideConquer(int size) {

        this.size = size;

    }


//Divide & conquer function

    public static long[][] divideConquer(long[][] a, long[][] b) {

        int n = a.length;

        long[][] result = new long[n][n]; //creating resultant matrix

// Base case if n = 1
        if (n == 1) {
            result[0][0] = a[0][0] * b[0][0];
        }
// If n > 1
        else {

// Davide matrix a to 4 sub-matrices
            long[][] a11 = new long[n / 2][n / 2];
            long[][] a12 = new long[n / 2][n / 2];
            long[][] a21 = new long[n / 2][n / 2];
            long[][] a22 = new long[n / 2][n / 2];

// Davide matrix b to 4 sub-matrices
            long[][] b11 = new long[n / 2][n / 2];
            long[][] b12 = new long[n / 2][n / 2];
            long[][] b21 = new long[n / 2][n / 2];
            long[][] b22 = new long[n / 2][n / 2];

//divide and conquer  for matrix a
            copyToPart(a, a11, 0, 0);
            copyToPart(a, a12, 0, n / 2);
            copyToPart(a, a21, n / 2, 0);
            copyToPart(a, a22, n / 2, n / 2);

//divide and conquer for matrix b
            copyToPart(b, b11, 0, 0);
            copyToPart(b, b12, 0, n / 2);
            copyToPart(b, b21, n / 2, 0);
            copyToPart(b, b22, n / 2, n / 2);

//divide and conquer for result matrix
            long[][] c11 = add(divideConquer(a11, b11), divideConquer(a12, b21));
            long[][] c12 = add(divideConquer(a11, b12), divideConquer(a12, b22));
            long[][] c21 = add(divideConquer(a21, b11), divideConquer(a22, b21));
            long[][] c22 = add(divideConquer(a21, b12), divideConquer(a22, b22));

//Combining all results together
            copyToWhole(c11, result, 0, 0);
            copyToWhole(c12, result, 0, n / 2);
            copyToWhole(c21, result, n / 2, 0);
            copyToWhole(c22, result, n / 2, n / 2);

        }

        return result;


    }

    //Defining copyToWhole function which copies the entire matrix to other matrix

    public static void copyToWhole(long[][] source, long[][] destination, int row, int col) {

        for (int i = 0; i < source.length; i++)

            for (int j = 0; j < source.length; j++)

                destination[i + row][j + col] = source[i][j];

    }

//Defining copyToPart function which copies the selected part of matrix to other matrix

    public static void copyToPart(long[][] source, long[][] destination, int row, int col) {

        for (int i = 0; i < destination.length; i++) // row

            for (int j = 0; j < destination.length; j++) // col

                destination[i][j] = source[i + row][j + col];

    }

//Defining add function which adds the elements oftwo matrix.

    public static long[][] add(long[][] m1, long[][] m2) {

        long result[][] = new long[m1.length][m1.length];

        for (int i = 0; i < m1.length; i++) {

            for (int j = 0; j < m1.length; j++)

                result[i][j] = m1[i][j] + m2[i][j];

        }

        return result;

    }
}
