package at.graf.michael.controllers;

import at.graf.michael.dialogs.CreateNewCategoryDialog;
import at.graf.michael.views.DashboardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DashboardController {
    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        initialize();
    }

    private void initialize() {
        addMenuActions();
    }

    private void addMenuActions() {
        dashboardView.getCreateCategoryMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new CreateNewCategoryDialog().showAndWait();
            }
        });
    }
}
