/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nikasgig.gomorymethod.service;

import com.nikasgig.gomorymethod.form.MainJFrame;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author NikAS GiG
 */
public class BuildService {
    public static int[] getData(MainJFrame frame){
        int[] result = new int[2];
        try {
            result[0] = Integer.parseInt(frame.jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Error of get text of textField1 and parse INT: " + e.getMessage());
        }
        try {
            result[1] = Integer.parseInt(frame.jTextField2.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Error of get text of textField1 and parse INT: " + e.getMessage());
        }
        return result;
    }
    
    public static void buildTable(MainJFrame frame, int variables, int limits){
        //frame.jTable1 = new JTable(variables, limits);
        DefaultTableModel model = new DefaultTableModel(limits, variables + 1);
        frame.jTable1.setModel(model);
        
        // Set the last column as a ComboBox with "<=", "=", and ">=" options
        TableColumn signColumn = frame.jTable1.getColumnModel().getColumn(variables);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("<=");
        comboBox.addItem("=");
        comboBox.addItem(">=");
        signColumn.setCellEditor(new DefaultCellEditor(comboBox));
        
        DefaultTableModel mode2 = new DefaultTableModel(1, variables);
        frame.jTable2.setModel(mode2);
        DefaultTableModel mode3 = new DefaultTableModel(1, variables);
        frame.jTable3.setModel(mode3);
    }
}
