package com.nikasgig.gomorymethod.service;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 *
 * @author NikAS GiG
 */
public class SimplexMethodService {

    public static int num = 1;
    
    public static Object[] simplexMethod(double[][] A, double[] b, double[] c, boolean isMax, String sign) {
    // Initialize the tableau
    double[][] table = new double[A.length + 1][A[0].length + 2];
    for (int i = 0; i < A.length; i++) {
        System.arraycopy(A[i], 0, table[i + 1], 0, A[0].length);
    }
    System.arraycopy(c, 0, table[0], 0, c.length);
    if (!isMax) { // multiply objective function by -1 if we want to minimize
        for (int i = 0; i < c.length; i++) {
            table[0][i] *= -1;
        }
    }
    for (int i = 0; i < b.length; i++) {
        table[i + 1][A[0].length] = b[i];
    }
    table[0][A[0].length] = 0; // placeholder for objective function value
    table[0][A[0].length + 1] = isMax ? 1 : -1; // objective function sign
    int m = table.length;
    int n = table[0].length;
    while (true) {
        // Find the entering variable
        Integer entering = null;
        for (int j = 0; j < n - 2; j++) {
            if (isMax ? table[0][j] > 0 : table[0][j] < 0 && (entering == null || table[0][j] > table[0][entering])) {
                entering = j;
            }
        }

        if (entering == null) {
            // Optimal solution found
            break;
        }
        // Find the leaving variable
        Integer leaving = null;
        for (int i = 1; i < m; i++) {
            if (table[i][entering] > 0) {
                if (leaving == null) {
                    leaving = i;
                } else if (table[i][n - 2] / table[i][entering]
                        < table[leaving][n - 2] / table[leaving][entering]) {
                    leaving = i;
                }
            }
        }

        if (leaving == null) {
            // Unbounded solution
            return null;
        }

        // Pivot around the entering and leaving variables
        double pivot = table[leaving][entering];
        for (int j = 0; j < n; j++) {
            table[leaving][j] /= pivot;
        }
        for (int i = 0; i < m; i++) {
            if (i != leaving) {
                double factor = table[i][entering];
                for (int j = 0; j < n; j++) {
                    table[i][j] -= factor * table[leaving][j];
                }
            }
        }
    }

    // Set objective function value
    double objValue;
    switch (sign) {
        case "<=":
            objValue = table[0][n - 2];
            break;
        case ">=":
            objValue = -table[0][n - 2];
            break;
        default:
            objValue = 0;
            break;
    }
    
    // Create a new matrix to hold the results
    double[][] result = new double[m - 1][];
    for (int i = 0; i < result.length; i++) {
        result[i] = Arrays.copyOfRange(table[i + 1], 0, table[i + 1].length - 1);
        //result[i] = Arrays.copyOfRange(table[i + 1], n - m, n - 1);
    }
    
    // Return the results
    return new Object[]{result, objValue};
}
    
    public static double[][] simplexMethodOld(double[][] A, double[] b, double[] c) {
        // Initialize the tableau
        double[][] table = new double[A.length + 1][A[0].length + 1];
        for (int i = 0; i < A.length; i++) {
            System.arraycopy(A[i], 0, table[i + 1], 0, A[0].length);
        }
        System.arraycopy(c, 0, table[0], 0, c.length);
        for (int i = 0; i < b.length; i++) {
            table[i + 1][A[0].length] = b[i];
        }
        int m = table.length;
        int n = table[0].length;
        while (true) {
            // Find the entering variable
            Integer entering = null;
            for (int j = 0; j < n - 1; j++) {
                if (table[0][j] > 0 && (entering == null || table[0][j] > table[0][entering])) {
                    entering = j;
                }
            }

            if (entering == null) {
                // Optimal solution found
                break;
            }
            // Find the leaving variable
            Integer leaving = null;
            for (int i = 1; i < m; i++) {
                if (table[i][entering] > 0) {
                    if (leaving == null) {
                        leaving = i;
                    } else if (table[i][n - 1] / table[i][entering]
                            < table[leaving][n - 1] / table[leaving][entering]) {
                        leaving = i;
                    }
                }
            }

            if (leaving == null) {
                // Unbounded solution
                return null;
            }

            // Pivot around the entering and leaving variables
            double pivot = table[leaving][entering];
            for (int j = 0; j < n; j++) {
                table[leaving][j] /= pivot;
            }
            for (int i = 0; i < m; i++) {
                if (i != leaving) {
                    double factor = table[i][entering];
                    for (int j = 0; j < n; j++) {
                        table[i][j] -= factor * table[leaving][j];
                    }
                }
            }
        }

        return table;
    }

    public static double[] extractResults(double[][] table) {
        int m = table.length;
        int n = table[0].length;
        System.out.println("I am here 1");
        double[] result = {0,0};
        for (int j = 0; j < n - 1; j++) {
            final int colIndex = j;
            List<double[]> nonzeroRows;
            nonzeroRows = Arrays.stream(table)
                    .map((double[] row) -> new double[]{row[colIndex], 0})
                    .filter(pair -> pair[0] != 0)
                    .collect(Collectors.toList());
            if (nonzeroRows.size() == 1 && nonzeroRows.get(0)[0] == 1) {
                result[1] = table[(int) nonzeroRows.get(0)[1]][n - 1];
                if (j == n - 2) {
                    result[0] = table[0][n - 1];
                }
            }
            System.out.println("I am here 2 + " + j);
        }
        return result;
    }
}
