package cs2410.assn8.view;

import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Scoreboard
{
    private Timer timer = new Timer();

    private LongProperty time = new SimpleLongProperty(0);

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

    public void startScoreboardTimer()
    {
        time.setValue(0);
        timer.purge();
        timer.scheduleAtFixedRate(countSeconds, 1000, 1000);
    }

    public void stopScoreboardTimer()
    {
        countSeconds.cancel();
        timer.purge();
    }

    public LongProperty returnTime()
    {
        return time;
    }

    public String formatTimeString()
    {
        int hrs = (int) (time.getValue()/3600);
        int min = (int) ((time.getValue()/60) % 60);
        int sec = (int) (time.getValue() % 60);
        return String.format("%02d:%02d:%02d", hrs, min, sec);
    }
}
