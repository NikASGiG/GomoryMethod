/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nikasgig.gomorymethod.service;

/**
 *
 * @author NikAS GiG
 */
public class SimplexMethodNew {
    public static Object[] simplexMethod(double[][] A, double[] b, double[] c, boolean isMax, String sign) {
        int m = A.length;
        int n = A[0].length;
        double[][] tableau = new double[m+1][n+1];
        
        // Fill the simplex tableau
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tableau[i][j] = A[i][j];
            }
            tableau[i][n] = b[i];
        }
        for (int j = 0; j < n; j++) {
            tableau[m][j] = c[j] * (isMax ? -1 : 1);
        }
        
        // Simplex algorithm
        while (true) {
            // Find pivot column (most negative entry in last row)
            int pivotCol = -1;
            for (int j = 0; j < n; j++) {
                if (tableau[m][j] < 0) {
                    pivotCol = j;
                    break;
                }
            }
            if (pivotCol == -1) break; // No negative entries in last row => optimal solution found
            
            // Find pivot row (minimum positive ratio test)
            int pivotRow = -1;
            for (int i = 0; i < m; i++) {
                if (tableau[i][pivotCol] > 0) {
                    if (pivotRow == -1) {
                        pivotRow = i;
                    } else if (tableau[i][n] / tableau[i][pivotCol] < tableau[pivotRow][n] / tableau[pivotRow][pivotCol]) {
                        pivotRow = i;
                    }
                }
            }
            if (pivotRow == -1) return null; // No positive entries in pivot column => problem is unbounded
            
            // Pivot operation
            double pivot = tableau[pivotRow][pivotCol];
            for (int j = 0; j <= n; j++) {
                tableau[pivotRow][j] /= pivot;
            }
            for (int i = 0; i <= m; i++) {
                if (i != pivotRow) {
                    double ratio = tableau[i][pivotCol];
                    for (int j = 0; j <= n; j++) {
                        tableau[i][j] -= tableau[pivotRow][j] * ratio;
                    }
                }
            }
        }

        // Prepare output
        double[][] solution = new double[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(tableau[i], 0, solution[i], 0, n);
        }
        double objectiveValue;
        if (sign.equals("<=")) {
            objectiveValue = tableau[m][n];
        } else if (sign.equals(">=")) {
            objectiveValue = -tableau[m][n];
        } else {
            objectiveValue = 0;
        }
        
        return new Object[] { solution, objectiveValue };
    }
}

