package at.graf.michael.controllers;

import at.graf.michael.utils.SqlUtil;
import at.graf.michael.utils.Utilitie;
import at.graf.michael.views.LoginView;
import at.graf.michael.views.SignUpView;
import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class SignUpController {
    private SignUpView signUpView;

    public SignUpController(SignUpView signUpView) {
        this.signUpView = signUpView;
        initialize();
    }

    private void initialize() {
        signUpView.getLoginLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new LoginView().show();
            }
        });

        signUpView.getRegisterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!validateInput()) {
                    Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Invalid Input");
                    return;
                }

                // extract data in the fiels
                String name = signUpView.getNameField().getText();
                String username = signUpView.getUsernameField().getText();
                String password = signUpView.getPasswordField().getText();

                // create json data to send to POST request
                JsonObject jsonData = new JsonObject();
                jsonData.addProperty("name", name);
                jsonData.addProperty("email", username);
                jsonData.addProperty("password", password);

                // send in our POST request to create the user
                boolean postCreateAccountStatus = SqlUtil.postCreateUser(jsonData);

                // depending on the result we will display the corresponding alert message
                if(postCreateAccountStatus) {
                    Utilitie.showAlertDialog(Alert.AlertType.INFORMATION, "Successfully created new account!");
                    new LoginView().show();
                } else {
                    Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Failed to create new accournt...");
                }

            }
        });
    }

    private boolean validateInput() {
        if (signUpView.getNameField().getText().isEmpty()) return false;
        if (signUpView.getUsernameField().getText().isEmpty()) return false;
        if (signUpView.getPasswordField().getText().isEmpty()) return false;
        if (signUpView.getRePasswordField().getText().isEmpty()) return false;

        if (!signUpView.getPasswordField().getText().equals(signUpView.getRePasswordField().getText())) return false;

        // entered email doesn't already exist in the db
        if(SqlUtil.getUserByEmail(signUpView.getUsernameField().getText())) return false;

        return true;
    }
}
