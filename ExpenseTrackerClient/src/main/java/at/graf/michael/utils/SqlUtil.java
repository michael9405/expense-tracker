package at.graf.michael.utils;

import com.google.gson.JsonObject;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SqlUtil {
    //region GET
    public static boolean getUserByEmail(String email) {
        // authenticate email and password
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user?email=" + email,
                    ApiUtil.RequestMethod.GET, null
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

    //endregion


    //region POST
    public static boolean postLoginUser(String email, String password) {
        // authenticate email and password
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user/login?email=" + email + "&password" + password,
                    ApiUtil.RequestMethod.POST, null
            );

            if (conn.getResponseCode() != 200) {
                Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Failed to authenticate!");
            } else {
                Utilitie.showAlertDialog(Alert.AlertType.INFORMATION, "Login Sucessful! ");
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
