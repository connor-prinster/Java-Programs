package cs2410.assn8.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class CustomCell extends Button
{
    private boolean isBomb = false;
    private int flaggedState = 0;   //since there are different flagged states, this applies to a list of potential texts
    private boolean isOpen = false; //a simple bool to see if the cell is opened or not
    private int cellSize = 20;
    private int numNeighborBombs = 0;   //initialize neibhors as 0;

    public CustomCell()
    {
        isBomb = false;
        flaggedState = 0;
        isOpen = false;
        cellInitializer(cellSize);
    }

   //--------------------------------------//
   //  Change Style Based on flaggedState  //
   //--------------------------------------//
    public void changeFlagStatus()
    {
        flaggedState++; //increase flaggedState by one
        flaggedState = flaggedState%3;  //will be a z-mod three

        if(flaggedState == 0) //set it to blank
        {
            getStyleClass().removeAll("flagged", "question");   //remove potential values
            getStyleClass().add("blank");   //add the "blank" tag
        }
        else if(flaggedState == 1)  //set it to flagged
        {
            getStyleClass().removeAll("blank", "question"); //remove potential values
            getStyleClass().add("flagged"); //add the "flagged" tag
        }
        else    //set it to question
        {
            getStyleClass().removeAll("blank", "flagged");  //remove potential values
            getStyleClass().add("question");    //add the "question" tag
        }
    }

    private void cellInitializer(int passSize)
    {
        setMinWidth(passSize);
        setMinHeight(passSize);
        setWidth(passSize);
        setHeight(passSize);
        cellSize = passSize;
        getStyleClass().add("blank");
        setAlignment(Pos.CENTER);
    }

    public void uncoverIndividualCell()
    {
        isOpen = true;
        if (getStyleClass().contains("completelyUnmarkedBombCell"))
        {
            getStyleClass().clear();
            getStyleClass().add("completelyUnmarkedBombCell");
        }
        else if (getStyleClass().contains("successfullyMarkedBombCell"))
        {
            getStyleClass().clear();
            getStyleClass().add("successfullyMarkedBombCell");
        }
        else if (getStyleClass().contains("unsuccessfullyMarkedBombCell"))
        {
            getStyleClass().clear();
            getStyleClass().add("unsuccessfullyMarkedBombCell");
        }
        else
            {
            getStyleClass().clear();
        }
        getStyleClass().add("openNoBomb");
        if (isBomb)
        {
            getStyleClass().add("openYesBomb");
        }
        else if (numNeighborBombs > 0)
        {
            setText(Integer.toString(numNeighborBombs));
        }
    }


    //==========================================//
    //  Getter Functions for the Private Stuff  //
    //==========================================//
    public boolean getIsOpen()
    {
        return isOpen;
    }

    public boolean getIsBomb()
    {
        return isBomb;
    }

    public int getNeighborCount()
    {
        return numNeighborBombs;
    }

    //==========================================//
    //  Setter Functions for the Private Stuff  //
    //==========================================//
    public void setIsBomb(boolean whatToSetTo)
    {
        isBomb = whatToSetTo;
    }

    public void setNumNeighborBombs(int sum)
    {
        numNeighborBombs = sum;
    }
}
