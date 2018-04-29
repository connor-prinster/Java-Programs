package cs2410.assn8.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer
{
    public SoundPlayer() {}

    private MediaPlayer player;

    private MediaPlayer bombPlayer = new MediaPlayer(new Media(new File("data/bomb.mp3").toURI().toString()));
    private MediaPlayer loseThemePlayer = new MediaPlayer(new Media(new File("data/endGameTheme.mp3").toURI().toString()));
    private MediaPlayer openTilePlayer = new MediaPlayer(new Media(new File("data/openTile.mp3").toURI().toString()));
    private MediaPlayer toggleFlagPlayer = new MediaPlayer(new Media(new File("data/flagToggle.mp3").toURI().toString()));
    private MediaPlayer winPlayer = new MediaPlayer(new Media(new File("data/win.mp3").toURI().toString()));

    public void setToggleFlagPlayer()
    {
        if(player != null)
        {
            player.stop();
        }
        player = toggleFlagPlayer;
        player.play();
    }

    public void endAllSound()
    {
        if(player != null)
        {
            player.stop();
        }
    }

    public void gameOverPlayer()
    {
        if(player != null)
        {
            player.stop();
        }
        player = bombPlayer;
        player.play();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player = loseThemePlayer;
                player.setVolume(.5);
                player.play();
            }
        });
    }

    public void openTilePlayer()
    {
        if(player != null)
        {
            player.stop();
        }
        player = openTilePlayer;
        player.play();
    }

    public void winResultPlayer()
    {
        if(player != null)
        {
            player.stop();
        }
        player = winPlayer;
        player.play();
    }
}