package cs2410.assn4;

import cs2410.assn4.controller.Controller;
import cs2410.assn4.interfaces.CustomPane;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application
{
    public static void main(String [] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        CustomPane cP = new CustomPane();
        primaryStage.setScene(cP.customPane());
        primaryStage.show();
        Controller cont = new Controller();
        cont.resetCounter();
    }
}
