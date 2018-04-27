package cs2410.assn8.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer
{
    private MediaPlayer player;

    private MediaPlayer endGameBomb = new MediaPlayer(new Media(new File("/cs2410/assn8/resources/endGameTheme.mp3").toURI().toString()));
    private MediaPlayer endGameMusic = new MediaPlayer(new Media(new File("/cs2410/assn8/resources/bomb.mp3").toURI().toString()));

    private void gameOverPlayer()
    {
        player.stop();
        player = endGameBomb;
        player.play();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player = endGameMusic;
                player.setVolume(.5);
                player.play();
            }
        });
    }
}
