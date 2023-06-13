/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nikasgig.gomorymethod.service;

import com.nikasgig.gomorymethod.form.MainJFrame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NikAS GiG
 */
public class GomoryMethodService {

    private static MainJFrame frame;

    public GomoryMethodService(MainJFrame frame) {
        this.frame = frame;
    }

    public static Object[] gomoryMethod(double[][] A, double[] b, double[] c, boolean maximize) {
        int n = A[0].length; // количество переменных
        int m = A.length; // количество ограничений

        Object[] result = SimplexMethodService.simplexMethod(A, b, c, maximize, "<=");
        double[][] solution = (double[][]) result[0];
        double objValue = (double) result[1];

        int maxIterations = 10000; // Выберите подходящее число
        int iterations = 0;

        while (true) {
            boolean hasFractionalValue = false;
            int rowWithFractionalValue = -1;

            for (int i = 0; i < m; i++) {
                if (solution[i][n] != Math.floor(solution[i][n])) {
                    hasFractionalValue = true;
                    rowWithFractionalValue = i;
                    break;
                }
            }

            if (!hasFractionalValue) {
                break;
            }

            double fractionalPart = solution[rowWithFractionalValue][n] - Math.floor(solution[rowWithFractionalValue][n]);
            double[] newConstraint = new double[n];
            for (int j = 0; j < n; j++) {
                newConstraint[j] = A[rowWithFractionalValue][j] - Math.floor(A[rowWithFractionalValue][j]);
            }

            A = Arrays.copyOf(A, A.length + 1);
            A[A.length - 1] = newConstraint;
            b = Arrays.copyOf(b, b.length + 1);
            b[b.length - 1] = fractionalPart;

            result = SimplexMethodService.simplexMethod(A, b, c, maximize, sing(A.length));
            solution = (double[][]) result[0];
            objValue = (double) result[1];

            iterations++;
            if (iterations >= maxIterations) {
                System.out.println("Максимальное количество итераций достигнуто.");
                break;
            }
        }

        return new Object[]{solution, objValue};
    }

    public static Map<String, Object> extractResults(Object[] gomoryResult) {
        Map<String, Object> resultsMap = new HashMap<>();

        double[][] table = (double[][]) gomoryResult[0];
        resultsMap.put("table", table);

        double objectiveValue = (double) gomoryResult[1];
        resultsMap.put("objectiveValue", objectiveValue);

        List<Double> variableValues = new ArrayList<>();
        for (double[] row : table) {
            variableValues.add(row[row.length - 1]);
        }
        resultsMap.put("variableValues", variableValues);

        return resultsMap;
    }

    public static String[] getSigns(int limits) {
        String[] signs = new String[limits];
        for (int i = 0; i < limits; i++) {
            signs[i] = (String) frame.jTable1.getValueAt(i, frame.jTable1.getColumnCount() - 1);
        }
        return signs;
    }

    private static String sing(int length) {

        String[] signs = getSigns(length);
        int lessEqCount = 0, greaterEqCount = 0, eqCount = 0;

        for (String sign : signs) {
            switch (sign) {
                case "<=":
                    lessEqCount++;
                    break;
                case ">=":
                    greaterEqCount++;
                    break;
                case "=":
                    eqCount++;
                    break;
            }
        }
        // The priority is <= > >= > =. If count is equal, return the one with higher priority.
        if (lessEqCount >= greaterEqCount && lessEqCount >= eqCount) {
            return "<=";
        } else if (greaterEqCount >= eqCount) {
            return ">=";
        } else if(lessEqCount == 1 && greaterEqCount == 1 && eqCount == 1){
            return "<=";
        } else {
            return "=";
        }
    }
}
