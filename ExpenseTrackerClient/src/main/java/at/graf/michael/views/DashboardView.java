package at.graf.michael.views;


import at.graf.michael.controllers.DashboardController;
import at.graf.michael.utils.Utilitie;
import at.graf.michael.utils.ViewNavigator;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.w3c.dom.ls.LSException;

public class DashboardView {
    private String email;

    private Label currentBalanceLabel, currentBalance;
    private Label totalIncomeLabel, totalIncome;
    private Label totalExpenseLabel, totalExpense;

    private Button addTransactionButton;
    private VBox recentTransactionBox;
    private ScrollPane recentTransactionScrollPane;

    private MenuItem createCategoryMenuItem, viewCategoriesMenuItem;

    public DashboardView(String email) {
        this.email = email;

        currentBalanceLabel = new Label("Current Balance:");
        totalIncomeLabel = new Label("Total Income:");
        totalExpenseLabel = new Label("Total Expense:");
        addTransactionButton = new Button("+");

        currentBalance = new Label("€ 0,00");
        totalIncome = new Label("€ 0,00");
        totalExpense = new Label("€ 0,00");
    }

    public void show() {
        Scene scene = createScene();
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        new DashboardController(this);
        ViewNavigator.switchViews(scene);


    }

    private Scene createScene() {
        MenuBar menuBar = createMenuBar();

        VBox mainContainer = new VBox();
        mainContainer.getStyleClass().addAll("main-background");

        HBox balanceSummaryBox = createBalanceSummaryBox();
        GridPane contentGridPane = createContentGridPane();

        mainContainer.getChildren().addAll(menuBar, balanceSummaryBox, contentGridPane);
        return new Scene(mainContainer, Utilitie.APP_WIDTH, Utilitie.APP_HEIGHT);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        createCategoryMenuItem = new MenuItem("Create Category");
        viewCategoriesMenuItem = new MenuItem("View Categories");

        fileMenu.getItems().addAll(createCategoryMenuItem, viewCategoriesMenuItem);
        menuBar.getMenus().addAll(fileMenu);
        return menuBar;
    }

    private HBox createBalanceSummaryBox() {
        HBox balanceSummaryBox = new HBox();

        VBox currentBalanceBox = new VBox();
        currentBalanceLabel.getStyleClass().addAll("text-size-lg", "text-light-gray");
        currentBalance.getStyleClass().addAll("text-size-lg", "text-white");
        currentBalanceBox.getChildren().addAll(currentBalanceLabel, currentBalance);

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);

        VBox totalIncomeBox = new VBox();
        totalIncomeLabel.getStyleClass().addAll("text-size-lg", "text-light-gray");
        totalIncome.getStyleClass().addAll("text-size-lg", "text-white");
        totalIncomeBox.getChildren().addAll(totalIncomeLabel, totalIncome);

        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);

        VBox totalExpenseBox = new VBox();
        totalExpenseLabel.getStyleClass().addAll("text-size-lg", "text-light-gray");
        totalExpense.getStyleClass().addAll("text-size-lg", "text-white");
        totalExpenseBox.getChildren().addAll(totalExpenseLabel, totalExpense);

        balanceSummaryBox.getChildren().addAll(currentBalanceBox, region1, totalIncomeBox, region2, totalExpenseBox);
        return balanceSummaryBox;
    }

    private GridPane createContentGridPane() {
        GridPane gridPane = new GridPane();

        // recent transactions
        VBox recentTransactionsVBox = creatRecentTransactionVBox();

        gridPane.add(recentTransactionsVBox,1,0);
        return gridPane;
    }

    private VBox creatRecentTransactionVBox() {
        VBox recentTransactionsVBox = new VBox();

        // label and add button
        HBox recentTransactionLabelAndBtnBox = new HBox();
        Label recentTransactionsLabel = new Label("Recent Transactions");

        Region labelAndButtonSpaceRegion = new Region();
        HBox.setHgrow(labelAndButtonSpaceRegion, Priority.ALWAYS);

        recentTransactionLabelAndBtnBox.getChildren().addAll(recentTransactionsLabel, labelAndButtonSpaceRegion, addTransactionButton);

        // recent transaction box
        recentTransactionBox = new VBox();
        recentTransactionScrollPane = new ScrollPane(recentTransactionBox);
        recentTransactionScrollPane.setFitToWidth(true);
        recentTransactionScrollPane.setFitToHeight(true);



        recentTransactionsVBox.getChildren().addAll(recentTransactionLabelAndBtnBox, recentTransactionScrollPane);
        return recentTransactionsVBox;
    }

    //region Getter and Setter

    public MenuItem getCreateCategoryMenuItem() {
        return createCategoryMenuItem;
    }

    public void setCreateCategoryMenuItem(MenuItem createCategoryMenuItem) {
        this.createCategoryMenuItem = createCategoryMenuItem;
    }

    public MenuItem getViewCategoriesMenuItem() {
        return viewCategoriesMenuItem;
    }

    public String getEmail() {
        return email;
    }

    //endregion
}
