import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {


// Reading from System.in
        Scanner reader = new Scanner(System.in);


// Getting n value
        System.out.print("\nEnter a value of n where your matrices are of size 2^n X 2^n: ");
        int n = reader.nextInt();

// Getting input file path
        System.out.print("\nEnter input file path:\n");
        String path = reader.next();

// Getting algorithm type
        System.out.println("\nEnter multiplication algorithm number:\n1- Iterative\n2- Divide and conquer recursive\n3- Strassen’s algorithm  with base case of n = 1" +
                "\n4- Strassen’s algorithm  with base case of n > 1");
        int choice = reader.nextInt();


        int powN = (int) Math.pow(2, n);

        long[][] matrix1 = new long[powN][powN];

        long[][] matrix2 = new long[powN][powN];

        long[][] resultMatrix = new long[powN][powN];


        double startTime = 0;
        double endTime = 0;
        double totalTime;


        File file = new File(path);
        Scanner input = new Scanner(file);


        int i = 0, j = -1, count = 0, k = 0;


// Read two input matrices from input file
        while (input.hasNext()) {
            String number = input.next();
            if (i == (powN - 1) && j == (powN - 1)) {
                i = 0;
                j = -1;
            }
            if (count < (powN * powN)) {
                if (j < powN - 1) {
                    j++;
                    matrix1[i][j] = Integer.parseInt(number);
                } else {
                    j = 0;
                    i++;
                    matrix1[i][j] = Integer.parseInt(number);
                }
            } else {
                if (j < powN - 1) {
                    j++;
                    matrix2[i][j] = Integer.parseInt(number);
                } else {
                    j = 0;
                    i++;
                    matrix2[i][j] = Integer.parseInt(number);
                }
            }
            count++;
        }


// choice = 1 Iterative
        if (choice == 1) {
            Iterative multiply = new Iterative(powN);
            // Starting time
            startTime = System.currentTimeMillis();
            // Call algorithm
            resultMatrix = multiply.iterative(matrix1, matrix2);
            // Ending time
            endTime = System.currentTimeMillis();
        }


// choice = 2 Divide & Conquer
        else if (choice == 2) {
            DivideConquer multiply = new DivideConquer(powN);
            // Starting time
            startTime = System.currentTimeMillis();
            // Call algorithm
            resultMatrix = multiply.divideConquer(matrix1, matrix2);
            // Ending time
            endTime = System.currentTimeMillis();
        }

// choice = 3 Strassen with base case value = 1
        else if (choice == 3) {
            Strassen multiply = new Strassen(powN);
            // Starting time
            startTime = System.currentTimeMillis();
            // Call algorithm
            resultMatrix = multiply.strassen(matrix1, matrix2);
            // Ending time
            endTime = System.currentTimeMillis();
        }

// choice = 4 Strassen with base case value > n
        else if (choice == 4) {
            // Getting base value in case of choice 4
            System.out.print("\nEnter a base case: ");

            int base = reader.nextInt();

            int powBase = (int) Math.pow(2, base);

            StrassenBaseN multiply = new StrassenBaseN(powN, powBase);
            // Starting time
            startTime = System.currentTimeMillis();
            // Call algorithm
            resultMatrix = multiply.strassenBaseN(matrix1, matrix2);
            // Ending time
            endTime = System.currentTimeMillis();

        }


// Get total time in seconds
        totalTime = (endTime - startTime) / 1000;

        reader.close();

// Write resulting matrix to output.txt
        File foutupt = new File("output.txt");
        FileOutputStream output = new FileOutputStream(foutupt);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));

        int reesultSize = resultMatrix.length;


        bw.write("Elapsed Time in seconds : " + totalTime);
        bw.newLine();
        bw.newLine();

        for (int ir = 0; ir < reesultSize; ir++) {
            for (int jr = 0; jr < reesultSize; jr++) {
                bw.write(resultMatrix[ir][jr] + "\t");
            }
            bw.newLine();
        }
        bw.newLine();

        System.out.println("\nDone result matrix stored in output.txt");
        bw.close();


    }


}

