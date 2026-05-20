package at.graf.michael.utils;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiUtil {
    private static final String SPRINGBOOT_URL = "http://localhost:8080";
    public enum RequestMethod{POST, GET, PUT, DELETE}

    public static HttpURLConnection fetchApi(String apiPath, RequestMethod requestMethod, JsonObject jsonData) {
        try {
            // attempt to create connection
            URL url = new URL(SPRINGBOOT_URL + apiPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request type
            conn.setRequestMethod(requestMethod.toString());

            // send data to api
            if (jsonData != null && requestMethod != RequestMethod.GET) {
                // lets api know that some json data will be sent
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                // expects the response body to be of json type
                conn.setRequestProperty("Accept", "application/json");

                // allows us to send data to the connected api
                conn.setDoOutput(true);

                // send JSON data to the server by writing it to the ourput stream (closes the stream automatically)
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonData.toString().getBytes(StandardCharsets.UTF_8);

                    os.write(input, 0, input.length);
                }
            }
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
