package cs2410.assn8.view;

import cs2410.assn8.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cs2410/assn8/resources/Main.fxml"));
       BorderPane mainPane = loader.load();

        Alert openAlert = new Alert(Alert.AlertType.INFORMATION);
        openAlert.setTitle("Instructions");
        openAlert.setHeaderText("Instructions");
        openAlert.setContentText("Clear all the cells\n" + "Don't touch the bombs\n" + "Right click once to place a flag, twice to place question\n" + "Hit the 'START' button to begin");

       Controller ctrl = (Controller) loader.getController();
       mainPane.getStylesheets().add("/cs2410/assn8/resources/data.css");
       ctrl.initialize();

       primaryStage.setHeight(ctrl.returnGridRows() * 30 + 200);
       primaryStage.setWidth(ctrl.returnGridCols() * 30 + 50);
       primaryStage.getIcons().add(new Image("cs2410/assn8/resources/bomb.png"));
       primaryStage.setTitle("Minesweeper-ish");
       primaryStage.setOnCloseRequest(event -> {System.exit(0);});
       primaryStage.setScene(new Scene(mainPane));
       primaryStage.setResizable(false);
       primaryStage.show();
       openAlert.show();
    }
}
