package at.graf.michael;

import at.graf.michael.utils.ViewNavigator;
import at.graf.michael.views.DashboardView;
import at.graf.michael.views.LoginView;
import at.graf.michael.views.SignUpView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Expense Tracker");
        ViewNavigator.setMainStage(stage);

        //new SignUpView().show();
        new DashboardView("email@email.com").show();
    }
}
