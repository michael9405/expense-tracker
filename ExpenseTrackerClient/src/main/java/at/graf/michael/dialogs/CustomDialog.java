package at.graf.michael.dialogs;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class CustomDialog extends Dialog {
    public CustomDialog(){
        // add the stylesheet
        getDialogPane().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        getDialogPane().getStyleClass().addAll("main-background");

        getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        Button okbutton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okbutton.setVisible(false);
        okbutton.setDisable(true);
    }
}
