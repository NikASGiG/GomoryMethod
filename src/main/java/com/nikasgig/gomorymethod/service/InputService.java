/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nikasgig.gomorymethod.service;

import com.nikasgig.gomorymethod.form.MainJFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author NikAS GiG
 */
public class InputService {
    
    private double[][] A;
    private double[] b;
    private double[] c;
    private final int row;
    private final int col;

    public InputService(int row, int col){
        A = new double[col][row];
        b = new double[row];
        c = new double[row];
        this.row = row;
        this.col = col;
    }
    
    public void doTask(MainJFrame frame) {
        //MainJFrame frame = new MainJFrame();
        
        try {
            TableModel model1 = frame.jTable1.getModel();
            
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                Object value = model1.getValueAt(i, j);
                if (value instanceof Number) {
                    A[i][j] = ((Number) value).doubleValue();
                } else {
                    try {
                        A[i][j] = Double.parseDouble(value.toString());
                    } catch (NumberFormatException e) {
                        A[i][j] = 0;
                    }
                }
                System.out.println("i = " + i + ", j = " + j);
                System.out.println(A[i][j]);
            }
        
        // System.out.println("i = " + i + ", j = " + j);
        //  System.out.println(A[i][j]);
        }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Error of get data of jTable1: " + e.getMessage());
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Exception of get data of jTable1: " + e.getMessage());
            return;
        }
        try {    
            TableModel model2 = frame.jTable2.getModel();
            for (int i = 0; i < row; i++) {
                // Получаем значение ячейки таблицы
                Object value = model2.getValueAt(0, i);
                if (value instanceof Number) {
                    // Преобразуем значение в double и записываем в массив
                    b[i] = ((Number) value).doubleValue();
                } else {
                    // Если значение не является числом, записываем 0
                    try {
                        b[i] = Double.parseDouble(value.toString());
                    } catch (NumberFormatException e) {
                        b[i] = 0;
                    }
                }
                System.out.println("i = " + i);
                System.out.println(b[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Error of get data of jTable2: " + e.getMessage());
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Exception of get data of jTable2: " + e.getMessage());
            return;
        }
        try {    
            TableModel model3 = frame.jTable3.getModel();
            for (int i = 0; i < row; i++) {
                // Получаем значение ячейки таблицы
                Object value = model3.getValueAt(0, i);
                if (value instanceof Number) {
                    // Преобразуем значение в double и записываем в массив
                    c[i] = ((Number) value).doubleValue();
                } else {
                    // Если значение не является числом, записываем 0
                    try {
                        c[i] = Double.parseDouble(value.toString());
                    } catch (NumberFormatException e) {
                        c[i] = 0;
                    }
                }
                System.out.println("i = " + i);
                System.out.println(c[i]);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Error of get data of jTable3: " + e.getMessage());
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Exception of get data of jTable3: " + e.getMessage());
            return;
        }
    }

    public double[][] getA() {
        return A;
    }

    public void setA(double[][] A) {
        this.A = A;
    }

    public double[] getB() {
        return b;
    }

    public void setB(double[] b) {
        this.b = b;
    }

    public double[] getC() {
        return c;
    }

    public void setC(double[] c) {
        this.c = c;
    } 
}
