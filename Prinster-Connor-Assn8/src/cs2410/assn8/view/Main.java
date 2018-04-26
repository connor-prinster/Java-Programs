package cs2410.assn8.view;

import cs2410.assn8.controller.Controller;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cs2410/assn8/resources/Main.fxml"));
       BorderPane mainPane = loader.load();
       Controller ctrl = (Controller) loader.getController();
       mainPane.getStylesheets().add("/cs2410/assn8/resources/data.css");

       ctrl.initialize();

       primaryStage.setTitle("Minesweeper-ish");
       primaryStage.setOnCloseRequest(event -> {System.exit(0);});
       primaryStage.setScene(new Scene(mainPane));
       primaryStage.setResizable(false);
       primaryStage.show();
    }
}
