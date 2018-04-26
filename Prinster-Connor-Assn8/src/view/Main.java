package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/resources/Main.fxml"));
        Controller ctrl = new Controller();
        BorderPane basePane = mainLoader.load();


        ctrl.initialize();
        primaryStage.setTitle("Minesweeper-ish");
        primaryStage.setScene(new Scene(basePane, 500, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
