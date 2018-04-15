package cs2410.assn7.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class ScoreBarController extends HBox
{
    @FXML
    private Button playBtn;
    @FXML
    private Label currentScoreLabel;
    @FXML
    private Label highScoreLabel;

    private int currentScore;
    private double averageScore;    // equals currentScore/totalPointsEarned
    private int gamesPlayed;
    private int totalPointsEarned;

    private boolean playIsPressed;

    //------------------------------------//
    //     Methods for Initialization     //
    //------------------------------------//
    public void initializeAllScoresFromFile()
    {
        if(fileIsVoid())
        {
            currentScore = 0;
            averageScore = 0;
            gamesPlayed = 0;
            totalPointsEarned = 0;
        }
        else
        {
            //read gamesPlayed and totalPointsEarned from gameData.txt
        }
    }

    private void setOnPlayBtn(EventHandler<ActionEvent> e)
    {
        playBtn.setOnAction(e);
    }

    private boolean fileIsVoid()
    {
        String fileName = "resources/gameData.data";
        try
        {
            FileWriter fw = null;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            if(br.readLine() == null)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.getCause();
        }
        return false;
    }

    //-----------------------------------------//
    //     Methods Relating to gamesPlayed     //
    //-----------------------------------------//
    public void incrementGamesPlayed()
    {
        gamesPlayed++;
    }
    public int returnGamesPlayed()
    {
        return gamesPlayed;
    }
    public void printGamesPlayed()
    {
        //will write to first line of gameData.txt
    }

    //-----------------------------------------------//
    //     Methods Relating to totalPointsEarned     //
    //-----------------------------------------------//
    public void increaseTotalPointsEarned()
    {
        totalPointsEarned+=currentScore;
    }
    public int returnTotalPointsEarned()
    {
        return totalPointsEarned;
    }
    public void printTotalPointsEarned()
    {
        //will write to second line of gameData.txt
    }

    //------------------------------------------//
    //     Methods Relating to averageScore     //
    //------------------------------------------//
    private void calculateAverageScore()
    {
        averageScore = totalPointsEarned/gamesPlayed;
    }
    public double returnAverageScore()
    {
        return averageScore;
    }

    public void setOnPlayBtnActivated(EventHandler<ActionEvent> e)
    {
        playBtn.setOnAction(e);
    }
}
