package at.graf.michael.controllers;

import at.graf.michael.utils.Utilitie;
import at.graf.michael.views.LoginView;
import at.graf.michael.views.SignUpView;
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
            }
        });
    }

    private boolean validateInput() {
        if (signUpView.getNameField().getText().isEmpty()) return false;
        if (signUpView.getUsernameField().getText().isEmpty()) return false;
        if (signUpView.getPasswordField().getText().isEmpty()) return false;
        if (signUpView.getRePasswordField().getText().isEmpty()) return false;

        if (!signUpView.getPasswordField().getText().equals(signUpView.getRePasswordField().getText())) return false;

        return true;
    }
}
