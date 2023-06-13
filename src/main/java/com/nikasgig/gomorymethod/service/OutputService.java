/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nikasgig.gomorymethod.service;

import com.nikasgig.gomorymethod.form.MainJFrame;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NikAS GiG
 */
public class OutputService {

    public static double[] toTable(StringBuilder result) {
        double[] table = new double[6];
        String input = result.toString();
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String arrayString = matcher.group(1);
            String[] arrayValues = arrayString.split(", ");
            //double[] table = new double[arrayValues.length];
            for (int i = 0; i < arrayValues.length; i++) {
                table[i] = Double.parseDouble(arrayValues[i]);
            }
            return table;
        }
        return table;
    }

    public static void showResult(MainJFrame frame, double[][] solutionFromExtractResults) {
    int rowCount = solutionFromExtractResults.length;
    int columnCount = solutionFromExtractResults[0].length;

    DefaultTableModel model = new DefaultTableModel(rowCount, columnCount);
    for (int i = 0; i < rowCount; i++) {
    for (int j = 0; j < columnCount; j++) {
        model.setValueAt(solutionFromExtractResults[i][columnCount - j - 1], i, j);
    }
}

//    for (int i = 0; i < rowCount; i++) {
//        for (int j = 0; j < columnCount; j++) {
//            model.setValueAt(solutionFromExtractResults[i][j], i, j);
//        }
//    }
    frame.jTable4.setModel(model);

    StringBuilder xResult = new StringBuilder();
    if (solutionFromExtractResults == null) {
        System.out.println("Unbounded solution");
        JOptionPane.showMessageDialog(null, "Unbounded solution");
    } else {
        System.out.println("Solution:");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        for (int i = 0; i < solutionFromExtractResults.length; i++) {
            xResult.append("x").append(i + 1).append(" = ");
            xResult.append(decimalFormat.format(solutionFromExtractResults[i][solutionFromExtractResults[i].length - 1])).append("; ");
        }
        frame.jTextField3.setText(decimalFormat.format(solutionFromExtractResults[0][solutionFromExtractResults[0].length - 1]));
    }
    frame.jTextField4.setText(xResult.toString());
    System.out.println("=====");
}



//    public static void showResult(MainJFrame frame, double[][] tableResult, double[] extractResults, Object[] result) {
//        int rowCount = tableResult.length;
//        int columnCount = tableResult[0].length;
//        DefaultTableModel model = new DefaultTableModel(rowCount, columnCount);
//        // Заполнение модели данными из массива
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < columnCount; j++) {
//                model.setValueAt(tableResult[i][j], i, j);
//            }
//        }
//        frame.jTable4.setModel(model);
//
////        StringBuilder temp = new StringBuilder();
////        for (double el : extractResults) {
////            temp.append(Double.toString(el));
////        }
//        //////////////////////////////////////////////
//        System.out.println("=====");
//        StringBuilder xResult = new StringBuilder();
//        if (result == null) {
//            System.out.println("Unbounded solution");
//            JOptionPane.showMessageDialog(null, "Unbounded solution");
//        } else {
//            //Get the solution vector and objective function value
//            double[][] solution = (double[][]) result[0];
//            double objValue = (double) result[1];
//
//            // Print the solution vector and objective function value
//            System.out.println("Solution:");
//            DecimalFormat decimalFormat = new DecimalFormat("#.#");
//            for (int i = 0; i < solution.length; i++) {
//                xResult.append("x").append(i + 1).append(" = ");
//                xResult.append(decimalFormat.format(solution[i][0])).append("; ");
//            }
//            System.out.println("Objective function value: " + objValue);
//            frame.jTextField3.setText(Double.toString(objValue));
//        }
//        frame.jTextField4.setText(xResult.toString());
//        System.out.println("=====");
//    }
}
