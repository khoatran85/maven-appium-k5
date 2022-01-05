package Utils.data;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {
    public static <T> T buildJsonDataObject(String filePath, Class<T> dataType) {
        String absoluteFilePath = System.getProperty("user.dir").concat(filePath);

        try (
                Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));
        ) {
            Gson gson = new Gson();
            return gson.fromJson(reader, dataType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static <T> T buildExcelDataObject(String filePath, String sheetName) {
//        String absoluteExcelFilePath = System.getProperty("user.dir").concat(filePath);
//        try {
//            FileInputStream fis = new FileInputStream(absoluteExcelFilePath);
//            Workbook workbook = WorkbookFactory.create(fis);
//            return workbook.getSheet(sheetName);
//            Sheet sheet = workbook.getSheet(sheetName);
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                   return (T) cell;
//                }
//            }

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }





}
