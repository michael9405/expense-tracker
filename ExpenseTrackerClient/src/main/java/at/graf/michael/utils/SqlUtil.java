package at.graf.michael.utils;

import at.graf.michael.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;

public class SqlUtil {
    //region GET
    public static User getUserByEmail(String userEmail) {
        // authenticate email and password
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user?email=" + userEmail,
                    ApiUtil.RequestMethod.GET, null
            );

            if(conn.getResponseCode() != 200) {
                System.out.println("Error(getUserByEmail): " + conn.getResponseCode());
                return null;
            }

            String userDataJson = ApiUtil.readApiResponse(conn);

            JsonObject jsonObject = JsonParser.parseString(userDataJson).getAsJsonObject();

            // extract the json data
            int id = jsonObject.get("id").getAsInt();
            String name = jsonObject.get("name").getAsString();
            String email = jsonObject.get("email").getAsString();
            String password = jsonObject.get("password").getAsString();
            LocalDateTime createdAt = new Gson().fromJson(jsonObject.get("created_at"), LocalDateTime.class);

            return new User(id, name, email, password, createdAt);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }

    //endregion


    //region POST
    public static boolean postLoginUser(String email, String password) {
        // authenticate email and password
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user/login?email=" + email + "&password=" + password,
                    ApiUtil.RequestMethod.POST, null
            );

            if (conn.getResponseCode() != 200) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return true;
    }


    public static boolean postCreateUser(JsonObject userData) {
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user",
                    ApiUtil.RequestMethod.POST,
                    userData
            );

            if(conn.getResponseCode() != 200) {
                return false; // failes to create an account
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return true; // account successfully created and stored into our database
    }

    //endregion

}
