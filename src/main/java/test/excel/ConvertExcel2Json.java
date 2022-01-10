package test.excel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import test.authentication.LoginCred;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ConvertExcel2Json {

//    public static dataProvider(List<LoginCred> loginCred){
//
//        // Step 1: Read Excel File into Java List Objects
//        List<LoginCred> loginCred = readExcelFile("src/main/resources/test-data/ValidCredExcel.xlsx");
//
//        // Step 2: Convert Java Objects to JSON String
//        String jsonString = convertObjects2JsonString(loginCred);
//
//        System.out.println(jsonString);
//    }

    /**
     * Read Excel File into Java List Objects
     *
     * @param filePath
     * @return
     */
    public static LoginCred[] readExcelFile(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(excelFile);

            Sheet sheet = workbook.getSheet("Sheet1");
            Iterator<Row> rows = sheet.iterator();

            List<LoginCred> loginCreds = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                LoginCred loginCred = new LoginCred();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if (cellIndex == 0) { // username
                        loginCred.setUsername(currentCell.getStringCellValue());
                    } else if (cellIndex == 1) { // password
                        loginCred.setPassword(currentCell.getStringCellValue());
                    }

                    cellIndex++;
                }

                loginCreds.add(loginCred);
            }

            // Close WorkBook
            workbook.close();

            return loginCreds.toArray(new LoginCred[0]);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    /**
     * Convert Java Objects to JSON String
     *
     * @param
     * @param
     */
    public static String convertObjects2JsonString(List<LoginCred> loginCred) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(loginCred);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("====" + jsonString);
        return jsonString;

    }
}