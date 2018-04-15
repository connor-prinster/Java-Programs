package cs2410.assn7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;

public class MainController
{
    //------------------------//
    //     All FXML Items     //
    //------------------------//
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuBar topMenuBar;
    @FXML
    private HBox highScoreBar;
    @FXML
    private AnchorPane fourGrid;

    private ColorPanelController cpc = new ColorPanelController();

    public MainController() {}

    //--------------------------//
    //     All FXML Getters     //
    //--------------------------//
    public BorderPane returnBorderPane()
    {
        return borderPane;
    }

    public ColorPanelController returnColorPanelController()
    {
        return cpc;
    }

    public void setCpc(ColorPanelController cpc) {
        this.cpc = cpc;
    }

    public void initializeCP()
    {
        cpc.initializeColorPanel();
    }

    public HBox returnHighSchoreBar()
    {
        return highScoreBar;
    }

    public MenuBar returnTopMenuBar()
    {
        return topMenuBar;
    }
}
