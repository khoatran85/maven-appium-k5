package test.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {
    public static void main(String[] args) {
        File excelFile = new File(System.getProperty("user.dir").concat("/src/main/resources/test-data/ValidCredExcel.xlsx"));

        try {
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet("Sheet1");
            for(Row row :sheet){
                for (Cell cell : row){
                    System.out.println(cell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
