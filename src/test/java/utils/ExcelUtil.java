package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtil
{
    public static String getCellValue(String filePath, String sheetName, int row, int col) 
    {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            return workbook.getSheet(sheetName).getRow(row).getCell(col).toString();
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file", e);
        }
    }
}
