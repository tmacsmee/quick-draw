package nz.ac.auckland.se206.util;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
  public void playButtonClick() {
    Media sound =
        new Media(new File("src/main/resources/sounds/button_click.wav").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
  }

  public void playGameWin() {
    Media sound = new Media(new File("src/main/resources/sounds/game_win.wav").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
  }

  public void playGameLoss() {
    Media sound = new Media(new File("src/main/resources/sounds/game_loss.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
  }
}
