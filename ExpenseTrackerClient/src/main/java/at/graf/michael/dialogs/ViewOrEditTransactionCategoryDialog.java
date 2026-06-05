package at.graf.michael.dialogs;

import at.graf.michael.controllers.DashboardController;
import at.graf.michael.models.User;

public class ViewOrEditTransactionCategoryDialog extends CustomDialog {
    private DashboardController dashboardController;

    public ViewOrEditTransactionCategoryDialog(User user, DashboardController dashboardController) {
        super(user);
        this.dashboardController = dashboardController;

        // configure the dialog
        setTitle("View Categories");
        setWidth(815);
        setHeight(500);

    }
}
