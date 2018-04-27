package cs2410.assn8.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer
{
    public SoundPlayer() {System.out.println("I have been called");}

    private MediaPlayer player;

    private MediaPlayer bombPlayer = new MediaPlayer(new Media(new File("data/bomb.mp3").toURI().toString()));
    private MediaPlayer losePlayerTheme = new MediaPlayer(new Media(new File("data/endGameTheme.mp3").toURI().toString()));

    public void gameOverPlayer()
    {
        //player.stop();
        player = bombPlayer;
        player.play();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player = losePlayerTheme;
                player.setVolume(.5);
                player.play();
            }
        });
    }
}
