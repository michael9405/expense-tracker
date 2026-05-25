package at.graf.michael.controllers;

import at.graf.michael.utils.ApiUtil;
import at.graf.michael.utils.SqlUtil;
import at.graf.michael.utils.Utilitie;
import at.graf.michael.views.LoginView;
import at.graf.michael.views.SignUpView;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }

    public void initialize() {
        loginView.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!validateUser()) return;

                String email = loginView.getUsernameField().getText();
                String password = loginView.getPasswordField().getText();

                SqlUtil.postLoginUser(email, password);
            }
        });

        loginView.getSignupLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new SignUpView().show();
            }
        });


    }

    private boolean validateUser() {
        // empty username
        if(loginView.getUsernameField().getText().isEmpty()){
            return false;
        }
        // empty password
        if(loginView.getPasswordField().getText().isEmpty()){
            return false;
        }

        return true;
    }
}
