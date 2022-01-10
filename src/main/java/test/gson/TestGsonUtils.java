package test.gson;

import Utils.data.DataObjectBuilder;
import test.authentication.LoginCred;

public class TestGsonUtils {
    public static void main(String[] args) {
        String jsonFilePath ="/src/main/java/test/gson/LoginCred.json";
        LoginCred[] loginCreds = DataObjectBuilder.buildJsonDataObject(jsonFilePath, LoginCred[].class);
        for (LoginCred loginCred : loginCreds) {
            System.out.println(loginCred);
            System.out.println(loginCred.getJob().getPosition());
        }

        String animalJsonFilePath ="/src/main/java/test/gson/Animal.json";
        Animal animal = DataObjectBuilder.buildJsonDataObject(animalJsonFilePath, Animal.class);
        System.out.println(animal);
    }
}
