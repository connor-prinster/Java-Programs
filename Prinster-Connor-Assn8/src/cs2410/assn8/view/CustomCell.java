package cs2410.assn8.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class CustomCell extends Button
{
    private boolean isBomb = false;
    private int flaggedState = 0;   //since there are different flagged states, this applies to a list of potential texts
    private boolean isOpen = false; //a simple bool to see if the cell is opened or not

    public CustomCell()
    {
        isBomb = false;
        flaggedState = 0;
        isOpen = false;
        setAlignment(Pos.CENTER);
        getStyleClass().add("blank");   //just setting the style to something, but not something of import
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

    public int getFlaggedState()
    {
        return flaggedState;
    }

    //==========================================//
    //  Setter Functions for the Private Stuff  //
    //==========================================//
    public void setIsOpen(boolean whatToSetTo)
    {
        isOpen = whatToSetTo;
    }

    public void setIsBomb(boolean whatToSetTo)
    {
        isBomb = whatToSetTo;
    }


}
