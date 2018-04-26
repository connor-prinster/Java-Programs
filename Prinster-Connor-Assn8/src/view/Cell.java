package view;

import javafx.scene.control.Button;

public class Cell extends Button
{
    private boolean isBomb = false;
    private int flaggedState = 0;   //since there are different flagged states, this applies to a list of potential texts
    private boolean isOpen = false;   //

    public Cell()
    {
        isBomb = false;
        flaggedState = 0;
        isOpen = false;
    }

    //==========================================//
    //  Getter Functions for the Private Stuff  //
    //==========================================//
    public boolean isOpen()
    {
        return isOpen;
    }

    public boolean isBomb()
    {
        return isBomb;
    }

    public int returnFlaggedState()
    {
        return flaggedState;
    }

}
