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
    private TimerTask countTheSeconds = new TimerTask() {
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
        timer.scheduleAtFixedRate(countTheSeconds, 1000, 1000);
    }

    public void stopScoreboardTimer()
    {
        countTheSeconds.cancel();
        timer.purge();
    }

    public LongProperty returnTime()
    {
        return time;
    }

}
