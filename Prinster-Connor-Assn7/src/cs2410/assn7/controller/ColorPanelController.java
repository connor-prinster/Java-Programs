package cs2410.assn7.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class ColorPanelController
{
    @FXML
    private GridPane colorPanelGrid;
    @FXML
    private Button topLeftBtn;
    @FXML
    private Button topRightBtn;
    @FXML
    private Button bottomLeftBtn;
    @FXML
    private Button bottomRightBtn;

    private int numberOfPanelsNeededInArray = 1;

    public int NUM_OF_TILES = 4;

    private Color topLeftColor = Color.GREEN;   //just an initial value, user can change colors if they wish.
    private Color topRightColor = Color.RED;        //----------
    private Color bottomLeftColor = Color.YELLOW;   //----------
    private Color bottomRightColor = Color.BLUE;    //----------

    private String[] topLeftStringifiedStyle = {"-fx-base: #" + topLeftColor.brighter().brighter().brighter().toString().replace("0x", ""), "-fx-base: #" + topLeftColor.toString().replace("0x", "")};
    private String[] topRightStringifiedStyle = {"-fx-base: #" + topRightColor.brighter().darker().toString().replace("0x", ""), "-fx-base: #" + topRightColor.toString().replace("0x", "")};
    private String[] bottomLeftStringifiedStyle = {"-fx-base: #" + bottomLeftColor.darker().darker().darker().toString().replace("0x", ""), "-fx-base: #" + bottomLeftColor.toString().replace("0x", "")};
    private String[] bottomRightStringifiedStyle = {"-fx-base: #" + bottomRightColor.brighter().darker().toString().replace("0x", ""), "-fx-base: #" + bottomRightColor.toString().replace("0x", "")};

    private long timeStamp = 0;
    private long tmp = 0;
    private long diff = 0;
    private int colorIterate = 0;
    //=========================================================================================================
    //=========================================================================================================
    //=========================================================================================================
    private AnimationTimer animationTimerTopLeft = new AnimationTimer() {
        @Override
        public void handle(long now) {
            diff = now - tmp;

            if(now - timeStamp > 999999999 && colorIterate < topLeftStringifiedStyle.length)
            {
                timeStamp = now;
                changeColorTopLeft();
            }
        }
    };

    private AnimationTimer animationTimerTopRight = new AnimationTimer() {
        @Override
        public void handle(long now) {
            diff = now - tmp;

            if(now - timeStamp > 999999999 && colorIterate < topRightStringifiedStyle.length)
            {
                timeStamp = now;
                changeColorTopRight();
            }
        }
    };

    private AnimationTimer animationTimerBottomLeft = new AnimationTimer() {
        @Override
        public void handle(long now) {
            diff = now - tmp;

            if(now - timeStamp > 999999999 && colorIterate < bottomLeftStringifiedStyle.length)
            {
                timeStamp = now;
                changeColorBottomLeft();
            }
        }
    };

    private AnimationTimer animationTimerBottomRight = new AnimationTimer() {
        @Override
        public void handle(long now) {
            diff = now - tmp;

            if(now - timeStamp > 999999999 && colorIterate < bottomRightStringifiedStyle.length)
            {
                timeStamp = now;
                changeColorBottomRight();
            }
        }
    };

    //=========================================================================================================
    //=========================================================================================================
    //=========================================================================================================

    private void changeColorTopLeft()
    {
        topLeftBtn.setStyle(topLeftStringifiedStyle[colorIterate% topLeftStringifiedStyle.length]);
        colorIterate++;
    }

    private void changeColorTopRight()
    {
        topRightBtn.setStyle(topRightStringifiedStyle[colorIterate% topRightStringifiedStyle.length]);
        colorIterate++;
    }

    private void changeColorBottomLeft()
    {
        bottomLeftBtn.setStyle(bottomLeftStringifiedStyle[colorIterate% bottomLeftStringifiedStyle.length]);
        colorIterate++;
    }

    private void changeColorBottomRight()
    {
        bottomRightBtn.setStyle(bottomRightStringifiedStyle[colorIterate% bottomRightStringifiedStyle.length]);
        colorIterate++;
    }

    //=========================================================================================================
    //=========================================================================================================
    //=========================================================================================================

    public void flashingColorTopLeft()
    {
        animationTimerTopLeft.start();
        colorIterate = 0;
    }

    public void flashingColorTopRight()
    {
        animationTimerTopRight.start();
        colorIterate = 0;
    }

    public void flashingColorBottomLeft()
    {
        animationTimerBottomLeft.start();
        colorIterate = 0;
    }

    public void flashingColorBottomRight()
    {
        animationTimerBottomRight.start();
        colorIterate = 0;
    }

    //=========================================================================================================
    //=========================================================================================================
    //=========================================================================================================

    public void goToFCTL()
    {
        flashingColorTopLeft();
    }

    public void goToFCTR()
    {
        flashingColorTopRight();
    }

    public void goToFCBL()
    {
        flashingColorBottomLeft();
    }

    public void goToFCBR()
    {
        flashingColorBottomRight();
    }

    //=========================================================================================================
    //=========================================================================================================
    //=========================================================================================================

    public Button getTopLeftBtn()
    {
        return topLeftBtn;
    }
    public Button getTopRightBtn()
    {
        return topRightBtn;
    }

    public boolean userHasChosenCustomColors;

    public ColorPanelController() { }

    public void initializeColorPanel()
    {
        //string versions of the color
        String colorNameGreen = "-fx-base: #33FF57";
        String colorNameRed = "-fx-base: #FF5733";
        String colorNameYellow = "-fx-base: #DBFF33";
        String colorNameBlue = "-fx-base: #0000ff";
        //based on the string, we assign the buttons to it
        topLeftBtn.setStyle(colorNameGreen);
        topRightBtn.setStyle(colorNameRed);
        bottomLeftBtn.setStyle(colorNameYellow);
        bottomRightBtn.setStyle(colorNameBlue);
    }

    public int returnNumberOfPanelsNeeded() //not sure if necessary, but I think it's just a generally good idea to have a "getter"
    {
        return numberOfPanelsNeededInArray;
    }

    public void incrementNumberOfPanelsNeeded() //just so it can remain private, it'll be easier to track if I just change it here with each successful click
    {
        numberOfPanelsNeededInArray++;
    }

    public Color returnRandomColor()    //returns one of four colors
    {
        Random rand = new Random(System.currentTimeMillis());   //creates a seeded random number
        int remainderMod = rand.nextInt(100) % 4;   //generates a mod4 value from the random number so (0, 1, 2, or 3)

        //below is an if else functions that will return any number of random values.
        //The reason there isn't return GREEN, RED, etc is because values can change based on user input
        if(remainderMod == 0)
            return topLeftColor;
        else if(remainderMod == 1)
            return topRightColor;
        else if(remainderMod == 2)
            return bottomLeftColor;
        else
            return bottomRightColor;
    }

    public void setTopLeftButton(EventHandler<ActionEvent> e)
    {
        topLeftBtn.setOnAction(e);
    }

    public void setTopRightButton(EventHandler<ActionEvent> e)
    {
        topRightBtn.setOnAction(e);
    }

    public void setBottomLeftButton(EventHandler<ActionEvent> e)
    {
        bottomLeftBtn.setOnAction(e);
    }

    public void setBottomRightButton(EventHandler<ActionEvent> e)
    {
        bottomRightBtn.setOnAction(e);
    }

};

