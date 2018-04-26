package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Controller
{
    //==================//
    //  Center Objects  //
    //==================//
    @FXML
    private GridPane cellPane;
    //===============//
    //  Top Objects  //
    //===============//
    //-----------//
    //  MenuBar  //
    //-----------//
    @FXML
    private MenuBar topMenuBar; //menubar containing helpMenuOption and aboutMenuItem
    @FXML
    private Menu helpMenuOption;    //a menu reading "help"
    @FXML
    private MenuItem aboutMenuItem; //will generate a popup window that will give instructions on how to play
    //--------//
    //  Hbox  //
    //--------//
    @FXML
    private HBox textButtonHbox;    //contains bombsLeftText, startButton, and timeText
    @FXML
    private Text bombsLeftText; //will be continually updated to say how many bombs are left on the board
    @FXML
    private Button startButton; //will set a boolean for either started or not
    @FXML
    private Text timeText;  //will continually update to keep a valid time going

    public void initialize()
    {
        System.out.println("ballsack");
    }



}
