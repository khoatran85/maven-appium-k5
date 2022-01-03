package test.gson;

import com.google.gson.Gson;

public class TestGson {
    public static void main(String[] args) {
        String jsonObject = "[\n" +
                "  {\n" +
                "  \"username\": \"teo\",\n" +
                "  \"password\": \"12345678\",\n" +
                "    \"job\": {\n" +
                "      \"position\": \"QA\"\n" +
                "    }\n" +
                "},\n" +
                "{\n" +
                "  \"username\": \"ti\",\n" +
                "  \"password\": \"87654321\",\n" +
                "  \"job\": {\n" +
                "    \"position\": \"DEV\"\n" +
                "  }\n" +
                "}\n" +
                "]";

        Gson gson = new Gson();
        LoginCred[] loginCreds = gson.fromJson(jsonObject, LoginCred[].class);

        for (LoginCred loginCred : loginCreds) {
            System.out.println(loginCred);
            System.out.println(loginCred.getJob().getPosition());
        }
    }
}
