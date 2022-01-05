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


}
