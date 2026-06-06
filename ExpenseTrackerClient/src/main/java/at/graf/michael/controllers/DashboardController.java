package at.graf.michael.controllers;

import at.graf.michael.dialogs.CreateNewCategoryDialog;
import at.graf.michael.dialogs.ViewOrEditTransactionCategoryDialog;
import at.graf.michael.models.User;
import at.graf.michael.utils.SqlUtil;
import at.graf.michael.views.DashboardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DashboardController {
    private DashboardView dashboardView;

    private User user;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        fetchUserData();
        initialize();
    }

    private void fetchUserData() {
        user = SqlUtil.getUserByEmail(dashboardView.getEmail());
    }

    private void initialize() {
        addMenuActions();
    }

    private void addMenuActions() {
        dashboardView.getCreateCategoryMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new CreateNewCategoryDialog(user).showAndWait();
            }
        });

        dashboardView.getViewCategoriesMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ViewOrEditTransactionCategoryDialog(user, DashboardController.this).showAndWait();
            }
        });
    }
}
