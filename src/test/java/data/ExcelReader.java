package data;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private FileInputStream fileInputStream;

    private FileInputStream getFileInputStream() {
        FileInputStream inputStream = null;
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\test_data.xlsx";
        File excelFile = new File(filePath);
        try {
            inputStream = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error! Can't find Excel file.\n" + e.getMessage());
        }
        return inputStream;
    }

    public Object[][] getExcelData(int sheetIndex, int columnsNumber) throws IOException {
        fileInputStream = getFileInputStream();
        XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
        int numberOfRows = sheet.getLastRowNum() + 1;
        int numberOfColumns = columnsNumber;
        String excelDataTable[][] = new String[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                XSSFRow row = sheet.getRow(i);
                excelDataTable[i][j] = row.getCell(j).toString();
            }
        }
        wb.close();
        return excelDataTable;
    }
}
