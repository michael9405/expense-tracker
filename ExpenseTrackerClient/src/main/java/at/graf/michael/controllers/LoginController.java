package at.graf.michael.controllers;

import at.graf.michael.views.LoginView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        initialize();
    }

    public void initialize() {
        loginView.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Click");
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
