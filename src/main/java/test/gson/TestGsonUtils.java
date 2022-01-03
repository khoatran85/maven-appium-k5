package test.gson;

import Utils.data.DataObjectBuilder;

public class TestGsonUtils {
    public static void main(String[] args) {
        String jsonFilePath ="/src/main/java/test/gson/LoginCred.json";
        LoginCred[] loginCreds = DataObjectBuilder.buildDataObject(jsonFilePath, LoginCred[].class);
        for (LoginCred loginCred : loginCreds) {
            System.out.println(loginCred);
            System.out.println(loginCred.getJob().getPosition());
        }

        String animalJsonFilePath ="/src/main/java/test/gson/Animal.json";
        Animal animal = DataObjectBuilder.buildDataObject(animalJsonFilePath, Animal.class);
        System.out.println(animal);
    }
}
