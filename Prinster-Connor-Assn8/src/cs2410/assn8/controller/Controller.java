package cs2410.assn8.controller;

import cs2410.assn8.view.Scoreboard;
import cs2410.assn8.view.SoundPlayer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import cs2410.assn8.view.CustomCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Controller
{
    //===========================================//
    //===========================================//
    //            "GENERIC" VARIABLES            //
    //===========================================//
    //===========================================//

    private ArrayList< ArrayList<CustomCell> > grid = new ArrayList<>(0);
    private ArrayList<CustomCell> cellArray = new ArrayList<>(0);  /**just a simple grid of Cells*/
    private IntegerProperty numBombs = new SimpleIntegerProperty();   /**will be set based on values not yet known*/
    private double numSafeCells;    /**will be set based on numBombs*/
    private double uncoveredCells = 0;
    private int numGridRows = 20;
    private int numGridColumns = 20;
    private int numOfCells;
    boolean hasWon = false;     /**will end the game but will also display a win screen*/
    private BooleanProperty gameOver = new SimpleBooleanProperty(false);    /**---display a lose screen*/
    private BooleanProperty isInitialized = new SimpleBooleanProperty(false);
    private double percentGridBombs = .25;
    private boolean firstCellSelected = true;   /**if the first cell is selected, game starts*/
    private BooleanProperty isGameActive = new SimpleBooleanProperty(true);

    private SoundPlayer soundPlayer = new SoundPlayer();
    private Scoreboard scoreboard = new Scoreboard();


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

    public Controller() {} /**standard constructor with no special stuff, that's for later*/

    public void initialize()
    {
        soundPlayer.endAllSound();
        initializeGamePane();   //just do this before doing it again when making the startButton start things*/
        startButton.setOnAction(event -> {
            isGameActive.setValue(true);
            initializeGamePane();
        });
        timeText.setText("Time:\n 00:00");
        bombsLeftText.setText("Bombs Left:\n" + Integer.toString(numBombs.getValue()));
        numBombs.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                bombsLeftText.setText("Bombs Left:\n" + numBombs.get());
            }
        });
    }

    private void initializeGamePane()
    {
        gamePane.getRowConstraints().clear();       //just clear any excess data
        gamePane.getColumnConstraints().clear();    //---
        grid.clear();   //clear the array of cells
        firstCellSelected = true;
        hasWon = false; //has not won
        timeText.setText("Time:\n00:00");
        gameOver.setValue(false);
        isGameActive.setValue(true);
        uncoveredCells = 0;

        initializeCellArray();
        fillGridPane();

        gameOver.removeListener(gameOverListener);
        gameOver.addListener(gameOverListener);
    }

    /** set up the array with bombs as well as shuffling it */
    private void initializeCellArray()
    {
        gamePane.getChildren().clear();
        cellArray.clear();
        numOfCells = numGridColumns * numGridRows; //the total number of cells will be the area of the grid
        //System.out.println("there are " + numOfCells + " total cells");
        numBombs.setValue(numOfCells * percentGridBombs);  //the bombs will be a percent of the total number of bombs
        //System.out.println("There are " + numBombs.getValue() + " bombs");
        numSafeCells = numOfCells - numBombs.getValue();   //the number of safe cells will be the total amount of cells minus the number of bombs
        //System.out.println("There are " + numSafeCells + " safe cells");

        for(int i = 0; i < numOfCells; i++) //fill array with generic cells
        {
            CustomCell tempCell = new CustomCell();
            tempCell.setMinWidth(30);
            tempCell.setMinHeight(30);
            tempCell.setPrefHeight(30);
            tempCell.setPrefWidth(30);
            cellArray.add(tempCell);
        }
        for(int i = 0; i < numBombs.getValue(); i++)   //assign numBombs cells to bombs
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
           for(int j = 0; j < numGridColumns; j++)
           {
               grid.get(i).add(cellArray.get(cellArrayCounter));    //from the array stored in grid.get(i), store a cell
               assignCellHandler(grid.get(i).get(j), i, j);
               GridPane.setRowIndex(grid.get(i).get(j), i);
               GridPane.setColumnIndex(grid.get(i).get(j), j);
               gamePane.getChildren().add(grid.get(i).get(j));
               cellArrayCounter++;
           }
        }

        for(int i = 0; i < numGridRows; i++)
        {
            for(int j = 0; j < numGridColumns; j++)
            {
                grid.get(i).get(j).setNumNeighborBombs(countNeighbors(i, j));
            }
        }

        isInitialized.setValue(true);
    }


    /**Check how many of the neighboring cells are bombs, increment for each.
     * i is comparable to x
     * and
     * j is comparable to y in a coordinate plane
     * */
    private void assignCellHandler(CustomCell passCell, int i, int j)
    {
       passCell.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               if(isGameActive.getValue())
               {
                   if(event.isPrimaryButtonDown())  //if the left button is clicked
                   {
                       if(!(passCell.getStyleClass().contains("flagged") || passCell.getStyleClass().contains("question")))
                       {
                           if (passCell.getIsBomb())  //will uncover the cell no matter what but will return false if there's a bomb
                           {
                               //open all the bombs
                               isGameActive.setValue(false);

                               passCell.getStyleClass().clear();
                               passCell.getStyleClass().add("openYesBomb");

                               hasWon = false;
                               gameOver.setValue(true); //if a bomb went off, you've lost
                           }
                           else
                           {
                               if(firstCellSelected)
                               {
                                   firstCellSelected = false;
                               }
                               if(!passCell.getIsOpen())
                               {
                                   soundPlayer.openTilePlayer();
                               }
                               openCellsRecursively(i, j);
                           }
                       }
                   }
                   if(event.isSecondaryButtonDown() && !passCell.getIsOpen())    //if the right button is clicked AND the cell is closed
                   {
                       passCell.changeFlagStatus();    //since nothing has been open here and the second right button has been pressed
                       soundPlayer.setToggleFlagPlayer();
                       if(passCell.getStyle().contains("flag")) //if the new FlagStatus is a flag, the numBomb must go down
                       {
                           decrementNumBomb();
                       }
                       if(passCell.getStyle().contains("blank"))    //if the new FlagStatus is blank, increase the number of bombs
                       {
                           incrementNumBomb();
                       }
                   }
               }
           }
       });
    }

    private ChangeListener<Boolean> gameOverListener = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
        {
            scoreboard.stopScoreboardTimer();   //gotta stop before starting
            if(hasWon)
            {
                openAllBombs();
                soundPlayer.winResultPlayer();
                numBombs.setValue(0);
                soundPlayer.winResultPlayer();
                Alert gameOverWinAlert = new Alert(Alert.AlertType.CONFIRMATION);
                gameOverWinAlert.setTitle("Game Over");
                gameOverWinAlert.setHeaderText("You've Won! Solve time: " + scoreboard.returnTime());
                gameOverWinAlert.setContentText("Play Once More?");
                Optional<ButtonType> againQuestion = gameOverWinAlert.showAndWait();
                if(againQuestion.get() == ButtonType.OK)
                {
                    initialize();
                    isGameActive.setValue(true);
                }
            }
            else if(newValue)
            {
                openAllBombs();
                soundPlayer.gameOverPlayer();
                Alert gameOverLoseAlert = new Alert(Alert.AlertType.CONFIRMATION);
                gameOverLoseAlert.setTitle("Game Over");
                gameOverLoseAlert.setHeaderText("You've Lost");
                gameOverLoseAlert.setContentText("Play Once More?");
                Optional<ButtonType> againQuestion = gameOverLoseAlert.showAndWait();
                if(againQuestion.get() == ButtonType.OK)
                {
                    initialize();
                    isGameActive.setValue(true);
                }
            }
        }
    };

    private void openAllBombs()
    {
        for(int i = 0; i < numGridRows; i++)
        {
            for(int j = 0; j < numGridColumns; j++)
            {
                if(grid.get(i).get(j).getIsBomb() && !grid.get(i).get(j).getStyleClass().contains("flagged"))    //if not flagged and is a bomb
                {
                    grid.get(i).get(j).getStyleClass().clear();
                    grid.get(i).get(j).getStyleClass().add("completelyUnmarkedBombCell");
                }
                else if(grid.get(i).get(j).getIsBomb() && grid.get(i).get(j).getStyleClass().contains("flagged"))    //if flagged AND is a bomb
                {
                    grid.get(i).get(j).getStyleClass().clear();
                    grid.get(i).get(j).getStyleClass().add("successfullyMarkedBombCell");
                }
                else if(!grid.get(i).get(j).getIsBomb() && grid.get(i).get(j).getStyleClass().contains("flagged"))   //if flagged but not a bomb
                {
                    grid.get(i).get(j).getStyleClass().clear();
                    grid.get(i).get(j).getStyleClass().add("unsuccessfullyMarkedBombCell");
                }
            }
        }
    }

    private int countNeighbors(int i, int j)
    {
        int sum = 0;
        if (i > 0 && i < numGridRows - 1) {
            if (j > 0 && j < numGridColumns - 1) {
                if (grid.get(i - 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
            } else if (j == 0) {
                if (grid.get(i - 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
            } else {
                if (grid.get(i - 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j).getIsBomb()) {
                    sum++;
                }
            }
        } else if (i == 0) {
            if (j > 0 && j < numGridColumns - 1) {
                if (grid.get(i).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
            } else if (j == 0) {
                if (grid.get(i + 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
            } else {
                if (grid.get(i).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i + 1).get(j).getIsBomb()) {
                    sum++;
                }
            }
        } else {
            if (j > 0 && j < numGridColumns - 1) {
                if (grid.get(i - 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j + 1).getIsBomb()) {
                    sum++;
                }
            } else if (j == 0) {
                if (grid.get(i - 1).get(j).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j + 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j + 1).getIsBomb()) {
                    sum++;
                }
            } else {
                if (grid.get(i - 1).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i).get(j - 1).getIsBomb()) {
                    sum++;
                }
                if (grid.get(i - 1).get(j).getIsBomb()) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private void openCellsRecursively(int x, int y)
    {
        if(!firstCellSelected)
        {

            if(x >= numGridRows || x < 0 || y >= numGridColumns || y < 0)    //if it's out of bounds
            {
                return;
            }
            if(grid.get(x).get(y).getIsOpen())  //if the cell is already opened
            {
                return;
            }
            if(grid.get(x).get(y).getStyleClass().contains("flagged") || grid.get(x).get(y).getStyleClass().contains("question"))   //if there is a flag or question icon
            {
                return;
            }

            if(!grid.get(x).get(y).getIsOpen()) //if this isn't a bomb and the above situations don't apply
            {
                grid.get(x).get(y).uncoverIndividualCell();
                uncoveredCells++;
                //System.out.println("There are " + uncoveredCells + " uncovered cells and " + numSafeCells + " safe cells");
                if(numSafeCells == uncoveredCells)
                {
                    //System.out.println("I made it");
                    hasWon = true;
                }
                if(grid.get(x).get(y).getNeighborCount() == 0)  //all cells that don't have a number in them will be uncovered
                {
                    openCellsRecursively(x + 1, y - 1); //top left
                    openCellsRecursively(x + 1, y);   //top center
                    openCellsRecursively(x + 1, y + 1); //top right
                    openCellsRecursively(x, y - 1);   //left
                    openCellsRecursively(x, y + 1);   //right
                    openCellsRecursively(x - 1, y - 1); //bottom left
                    openCellsRecursively(x - 1, y);   //bottom center
                    openCellsRecursively(x - 1, y + 1); //bottom right
                }
            }
        }
    }

    //---------------------------------------------------------------//
    //  Makes it Easier to Track Where numBombs is getting adjusted  //
    //---------------------------------------------------------------//
    private void decrementNumBomb()
    {
        numBombs.setValue(numBombs.getValue() - 1);
    }
    private void incrementNumBomb()
    {
        numBombs.setValue(numBombs.getValue() + 1);
    }

    public int returnGridRows()
    {
        return numGridRows;
    }
    public int returnGridCols()
    {
        return numGridColumns;
    }
}
