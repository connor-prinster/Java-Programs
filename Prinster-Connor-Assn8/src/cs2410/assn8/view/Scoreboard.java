package cs2410.assn8.view;

import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Scoreboard
{
    private Timer timer = new Timer();  /**timer for the whole program*/

    private LongProperty time = new SimpleLongProperty(0);  /**will be used for formatting*/

    /**counts the seconds and updates it continuously*/
    private TimerTask countSeconds = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    time.setValue(time.getValue() + 1);
                }
            });
        }
    };

    /**starts the timer*/
    public void startScoreboardTimer()
    {
        time.setValue(0);
        timer.purge();
        timer.scheduleAtFixedRate(countSeconds, 1000, 1000);
    }

    /**stops the timer*/
    public void stopScoreboardTimer()
    {
        countSeconds.cancel();
        timer.purge();
    }

    /**returns a nice string for what will appear on the time: * on the top of the gameboard*/
    public String formatTimeString()
    {
        int hrs = (int) (time.getValue()/3600);
        int min = (int) ((time.getValue()/60) % 60);
        int sec = (int) (time.getValue() % 60);
        return String.format("%02d:%02d:%02d", hrs, min, sec);
    }

    /**getter for 'time'*/
    public LongProperty returnTime()
    {
        return time;
    }
}
