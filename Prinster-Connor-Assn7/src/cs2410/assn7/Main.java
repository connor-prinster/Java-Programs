package cs2410.assn7;
import cs2410.assn7.controller.ColorPanelController;
import cs2410.assn7.controller.MainController;
import cs2410.assn7.controller.ScoreBarController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    //making sure that all members of the Main.java are accessible.
    ColorPanelController cpc;
    //ScoreBarController sbc;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MainController mainController;
        BorderPane mainPane;

        AnchorPane cpcPane;
        //MenuBar sbcBar;

        FXMLLoader cpcLoader;
        FXMLLoader sbcLoader;
        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("resources/main.fxml"));
        cpcLoader = new FXMLLoader(getClass().getResource("resources/colorPanel.fxml"));
        //sbcLoader = new FXMLLoader(getClass().getResource("resources/scoreBar.fxml"));
        cpcPane = cpcLoader.load();
        //sbcBar = sbcLoader.load();

        mainPane = loader.load();

        cpc = (ColorPanelController)cpcLoader.getController();
        //sbc = (ScoreBarController)sbcLoader.getController();
        mainController = (MainController)loader.getController();

        cpc.initializeColorPanel();
        mainController.setCpc(cpc);
        mainController.returnColorPanelController().initializeColorPanel();

        cpc.setTopLeftButton(this::pressTLTile);
        cpc.setTopRightButton(this::pressTRTile);
        cpc.setBottomLeftButton(this::pressBLTile);
        cpc.setBottomRightButton(this::pressBRTile);

        mainPane.setCenter(cpcPane);
        //mainPane.setTop(sbcBar);

        primaryStage.setTitle("Sort of Simon Says-ish");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }

    public void pressTLTile(ActionEvent event)
    {
        cpc.flashingColorTopLeft();
    }

    public void pressTRTile(ActionEvent event)
    {
        cpc.flashingColorTopRight();
    }

    public void pressBLTile(ActionEvent event)
    {
        cpc.flashingColorBottomLeft();
    }

    public void pressBRTile(ActionEvent event)
    {
        cpc.flashingColorBottomRight();
    }

    public void playBtnPressed(ActionEvent event)
    {

    }
}
