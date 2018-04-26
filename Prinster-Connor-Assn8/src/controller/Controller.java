package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.Cell;

import java.util.ArrayList;

public class Controller
{
    //===================================//
    //===================================//
    //            FXML VALUES            //
    //===================================//
    //===================================//
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

    //===========================================//
    //===========================================//
    //            "GENERIC" VARIABLES            //
    //===========================================//
    //===========================================//

    private ArrayList<Cell> cellArray = new ArrayList<>(0);  //just a simple grid of Cells
    private int numBombs;    //will be set based on values not yet known
    private int numSafes;    //will be set based on numBombs
    int numGridRows = 20;
    int numGridCols = 20;
    boolean hasWon = false;     //will end the game but will also display a win screen
    boolean hasLost = false;    //---display a lose screen
    double percentGridBombs = .25;
    boolean wasFirstCellSelected = false;   //if the first cell is selected, game starts





    public void initialize()
    {
        System.out.println("ballsack");

    }



}
