package cs2410.assn8.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import cs2410.assn8.view.CustomCell;

import java.util.ArrayList;
import java.util.Collections;

public class Controller
{
    //===========================================//
    //===========================================//
    //            "GENERIC" VARIABLES            //
    //===========================================//
    //===========================================//

    private ArrayList<ArrayList<CustomCell>> grid = new ArrayList<>(0);
    private ArrayList<CustomCell> cellArray = new ArrayList<>(0);  /**just a simple grid of Cells*/
    private int numBombs;   /**will be set based on values not yet known*/
    private int numSafes;    /**will be set based on numBombs*/
    private int numGridRows = 20;
    private int numGridCols = 20;
    private int numOfCells;
    boolean hasWon = false;     /**will end the game but will also display a win screen*/
    boolean hasLost = false;    /**---display a lose screen*/
    private double percentGridBombs = .25;
    private boolean wasFirstCellSelected = false;   /**if the first cell is selected, game starts*/
    private CustomCell c = new CustomCell();
    private BooleanProperty isGameActive = new SimpleBooleanProperty(true);


    //===================================//
    //===================================//
    //            FXML VALUES            //
    //===================================//
    //===================================//
    //==================//
    //  Center Objects  //
    //==================//
    @FXML
    private GridPane gamePane;
    //===============//
    //  Top Objects  //
    //===============//
    //-----------//
    //  MenuBar  //
    //-----------//
    @FXML
    private MenuBar topMenuBar; /**menuBar containing helpMenuOption and aboutMenuItem*/
    @FXML
    private Menu helpMenuOption;    /**a menu reading "help"*/
    @FXML
    private MenuItem aboutMenuItem; /**will generate a popup window that will give instructions on how to play*/
    //--------//
    //  Hbox  //
    //--------//
    @FXML
    private HBox textButtonHbox;    /**contains bombsLeftText, startButton, and timeText*/
    @FXML
    private Text bombsLeftText; /**will be continually updated to say how many bombs are left on the board*/
    @FXML
    private Button startButton; /**will set a boolean for either started or not*/
    @FXML
    private Text timeText;  /**will continually update to keep a valid time going*/



    public Controller() {
    }

    //===========================================//
    //===========================================//

    public void initialize()
    {
        initializeGamePane();
        initializeCellArray();  //set all the bombs
        fillGridPane();
        System.out.println("ballsack");
    }

    private void initializeGamePane()
    {
        gamePane.getRowConstraints().clear();
        gamePane.getColumnConstraints().clear();
        grid.clear();
        wasFirstCellSelected = true;
    }

    /** set up the array with bombs as well as shuffling it */
    private void initializeCellArray()
    {
        cellArray.clear();
        numOfCells = numGridCols * numGridRows; //the total number of cells will be the area of the grid
        numBombs = numOfCells * (int)percentGridBombs;  //the bombs will be a percent of the total number of bombs
        numSafes = numOfCells - numBombs;   //the number of safe cells will be the total amount of cells minus the number of bombs

        for(int i = 0; i < numOfCells; i++) //fill array with generic cells
        {
            CustomCell tempCell = new CustomCell();
            tempCell.setMinWidth(30);
            tempCell.setMinHeight(30);
            tempCell.setPrefHeight(30);
            tempCell.setPrefWidth(30);
            cellArray.add(tempCell);
        }
        for(int i = 0; i < numBombs; i++)   //assign numBombs cells to bombs
        {
            cellArray.get(i);
            cellArray.get(i).setIsBomb(true);
        }

        Collections.shuffle(cellArray); //because the first numBombs cells have been assigned bombs, it must be shuffled so the top row isn't just a bunch of bombs
    }

    private void fillGridPane()
    {
        int cellArrayCounter = 0;
        for(int i = 0; i < numGridRows; i++)
        {
           grid.add(new ArrayList<>());
           for(int j = 0; j < numGridCols; j++)
           {
               grid.get(i).add(cellArray.get(cellArrayCounter));    //from the array stored in grid.get(i), store a cell
               //set the cell handler
               GridPane.setRowIndex(grid.get(i).get(j), i);
               GridPane.setColumnIndex(grid.get(i).get(j), j);
               gamePane.getChildren().add(grid.get(i).get(j));
               cellArrayCounter++;
           }
        }
    }

    private void assignCellHandler(CustomCell passCell, int i, int j)
    {
        passCell.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isGameActive.getValue())
                {
                    if(event.isPrimaryButtonDown())
                    {
                        if(!passCell.getStyleClass().contains("flagged") || !passCell.getStyleClass().contains("question"))
                        {
                            if(passCell.getIsBomb())
                            {
                                //open the bombs
                                //the game is over
                            }
                            else
                            {
                                if(wasFirstCellSelected)
                                {
                                    //start the timer
                                    wasFirstCellSelected = false;
                                }
                                if(!passCell.getIsOpen())
                                {
                                    //play the cell opening sound
                                }
                                //open the cell at i, j openCells(x,y)
                            }
                        }
                    }
                    if(event.isSecondaryButtonDown() && !passCell.getIsOpen())
                    {
                        passCell.changeFlagStatus();
                    }
                }
            }
        });
    }

    //---------------------------------------------------------------//
    //  Makes it Easier to Track Where numBombs is getting adjusted  //
    //---------------------------------------------------------------//
    private void decrementNumBomb()
    {
        numBombs--;
    }
    private void incrementNumBomb()
    {
        numBombs++;
    }


}
