package at.graf.michael.dialogs;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateNewCategoryDialog extends CustomDialog {
    private TextField newCategoryTextField;
    private ColorPicker colorPicker;
    private Button createCategoryBtn;

    public CreateNewCategoryDialog() {
        super();
        setTitle("Create New Category");
        getDialogPane().setContent(createDialogContentBox());
    }

    private VBox createDialogContentBox() {
        VBox dialogContentBox = new VBox();

        newCategoryTextField = new TextField();
        newCategoryTextField.setPromptText("Enter Category Name");
        newCategoryTextField.getStyleClass().addAll("text-size-md", "field-background", "text-light-gray");

        dialogContentBox.getChildren().addAll(newCategoryTextField);
        return dialogContentBox;
    }
}
