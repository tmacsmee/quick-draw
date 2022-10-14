package nz.ac.auckland.se206.util;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
  public void playSound() {
    Media sound =
        new Media(new File("src/main/resources/sounds/button_click.wav").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.play();
  }
}
