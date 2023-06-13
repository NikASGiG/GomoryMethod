/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nikasgig.gomorymethod.util;
import com.nikasgig.gomorymethod.form.MainJFrame;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author NikAS GiG
 */

public class ExportXLS {
    public static void export(MainJFrame frame) throws IOException {
        // Создание новой рабочей книги
        HSSFWorkbook workbook = new HSSFWorkbook();

        // Создание нового листа
        Sheet sheet = workbook.createSheet("Sheet1");

        // Создание новой строки
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Variables:");
        cell = row.createCell(1);
        cell.setCellValue(frame.jTextField1.getText());
        cell = row.createCell(2);
        cell.setCellValue("Limit:");
        cell = row.createCell(3);
        cell.setCellValue(frame.jTextField2.getText());
        int counter = 3;
        counter++;
        row = sheet.createRow(counter);
        cell = row.createCell(0);
        cell.setCellValue("A:");
        counter++;
        
        
        //int counter2 = 0;
        row = sheet.createRow(counter);
        
        TableModel model1 = frame.jTable1.getModel();
        int rowCount = model1.getRowCount();
        int columnCount = model1.getColumnCount();

        for (int i = counter; i < (counter + rowCount); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j);
                cell.setCellValue(model1.getValueAt(i-counter, j).toString());
            }
        }
        counter += rowCount;
        ////////////////
        
        counter++;
        row = sheet.createRow(counter);
        cell = row.createCell(0);
        cell.setCellValue("b:");
        counter++;
        row = sheet.createRow(counter);
        
        TableModel model2 = frame.jTable2.getModel();
        rowCount = model2.getRowCount();
        columnCount = model2.getColumnCount();

        for (int i = counter; i < (counter + rowCount); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j);
                cell.setCellValue(model2.getValueAt(i-counter, j).toString());
            }
        }
        counter += rowCount;
        //////////////////////
        counter++;
        row = sheet.createRow(counter);
        cell = row.createCell(0);
        cell.setCellValue("c:");
        counter++;
        row = sheet.createRow(counter);
        
        TableModel model3 = frame.jTable3.getModel();
        rowCount = model3.getRowCount();
        columnCount = model3.getColumnCount();

        for (int i = counter; i < (counter + rowCount); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j);
                cell.setCellValue(model3.getValueAt(i-counter, j).toString());
            }
        }
        counter += rowCount;
        ////////////////
        //Intermediate table simplex method
        
        counter++;
        row = sheet.createRow(counter);
        cell = row.createCell(0);
        cell.setCellValue("Intermediate table simplex method:");
        counter++;
        row = sheet.createRow(counter);
        
        TableModel model4 = frame.jTable4.getModel();
        rowCount = model4.getRowCount();
        columnCount = model4.getColumnCount();

        for (int i = counter; i < (counter + rowCount); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j);
                cell.setCellValue(model4.getValueAt(i-counter, j).toString());
            }
        }
        counter += rowCount;
        
        // answer
        counter++;
        row = sheet.createRow(counter);
        cell = row.createCell(0);
        cell.setCellValue("Answer:");
        counter++;
        row = sheet.createRow(counter);
        cell = row.createCell(0);
        cell.setCellValue("F:");
        cell = row.createCell(1);
        cell.setCellValue(frame.jTextField3.getText());
        cell = row.createCell(2);
        cell.setCellValue("x:");
        cell = row.createCell(3);
        cell.setCellValue(frame.jTextField4.getText());
        
        

        // Сохранение книги в файл
        FileOutputStream fileOut = new FileOutputStream("report.xls");
        workbook.write(fileOut);
        fileOut.close();

        // Закрытие книги
        workbook.close();
    }
}