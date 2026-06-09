package at.graf.michael.dialogs;

import at.graf.michael.components.CategoryComponent;
import at.graf.michael.controllers.DashboardController;
import at.graf.michael.models.TransactionCategory;
import at.graf.michael.models.User;
import at.graf.michael.utils.SqlUtil;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.util.List;

public class ViewOrEditTransactionCategoryDialog extends CustomDialog {
    private DashboardController dashboardController;

    public ViewOrEditTransactionCategoryDialog(User user, DashboardController dashboardController) {
        super(user);
        this.dashboardController = dashboardController;

        // configure the dialog
        setTitle("View Categories");
        setWidth(815);
        setHeight(500);

        ScrollPane mainContainer = createMainContainerContent();
        getDialogPane().setContent(mainContainer);


    }

    private ScrollPane createMainContainerContent() {
        VBox dialogVBox = new VBox(20);


        ScrollPane scrollPane = new ScrollPane(dialogVBox);
        scrollPane.setMinHeight(getHeight() - 40);
        scrollPane.setFitToWidth(true);

        List<TransactionCategory> transactionCategories = SqlUtil.getAllTransactionCategoriesByUser(user);
        for (TransactionCategory transactionCategory : transactionCategories) {
            CategoryComponent categoryComponent = new CategoryComponent(dashboardController, transactionCategory);
            dialogVBox.getChildren().add(categoryComponent);
        }

        return scrollPane;
    }
}
