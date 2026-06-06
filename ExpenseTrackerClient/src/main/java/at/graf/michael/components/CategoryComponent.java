package at.graf.michael.components;

import at.graf.michael.controllers.DashboardController;
import at.graf.michael.models.TransactionCategory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class CategoryComponent extends HBox {
    private DashboardController dashboardController;
    private TransactionCategory transactionCategory;

    private TextField categoryTextField;
    private ColorPicker colorPicker;
    private Button editButton, saveButton, deleteButton;

    public CategoryComponent(DashboardController dashboardController, TransactionCategory transactionCategory) {
        this.dashboardController = dashboardController;
        this.transactionCategory = transactionCategory;

        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);
        getStyleClass().addAll("rounded-border", "field-background", "padding-10px");

        categoryTextField = new TextField();
        categoryTextField.setText(transactionCategory.getCategoryName());
        categoryTextField.setMaxWidth(Double.MAX_VALUE);
        categoryTextField.setEditable(false);
        HBox.setHgrow(categoryTextField, Priority.ALWAYS);
        categoryTextField.getStyleClass().addAll("field-background", "text-size-md", "text-light-gray");


        colorPicker = new ColorPicker();
        colorPicker.setDisable(true);
        colorPicker.setValue(Color.valueOf(transactionCategory.getCategoryColor()));
        colorPicker.getStyleClass().addAll("text-size-sm");

        editButton = new Button("Edit");
        editButton.setMinWidth(50);
        editButton.getStyleClass().addAll("text-size-sm");


        saveButton = new Button("Save");
        saveButton.setMinWidth(50);
        saveButton.getStyleClass().addAll("text-size-sm");
        saveButton.setVisible(false);
        saveButton.setManaged(false);

        deleteButton = new Button("Delete");
        deleteButton.setMinWidth(50);
        deleteButton.getStyleClass().addAll("text-size-sm", "bg-light-red", "text-white");

        getChildren().addAll(categoryTextField, colorPicker, editButton, saveButton, deleteButton);
    }
}
