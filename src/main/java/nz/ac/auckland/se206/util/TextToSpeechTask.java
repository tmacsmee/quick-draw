package nz.ac.auckland.se206.util;

import javafx.concurrent.Task;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.controllers.GameModeController;
import nz.ac.auckland.se206.controllers.ReadyController;

/** Handles text-to-speech output */
public class TextToSpeechTask extends Task<Void> {

  private final ReadyController readyController;
  private final GameModeController gameModeController;

  /** Constructor for TextToSpeechTask */
  public TextToSpeechTask() {
    readyController = (ReadyController) App.getController("ready");
    gameModeController = (GameModeController) App.getController("gameMode");
  }

  /**
   * Speaks the prompt in the ready scene based on the game mode.
   *
   * @return null
   */
  @Override
  protected Void call() {
    String gameMode = gameModeController.getGameMode();
    String timeAllowed =
        App.getJsonParser().getProperty(App.getCurrentUser(), "timeAllowed").toString();
    switch (gameMode) {

        // Let users know the time allowed and what to draw
      case "normal" -> App.voice.speak(
          "You have" + timeAllowed + "seconds to draw" + readyController.getPrompt());

        // Let users know what to draw
      case "zen" -> App.voice.speak("Draw" + readyController.getPrompt());

        // Let users know the time allowed
      case "hidden" -> App.voice.speak("You have" + timeAllowed + "seconds to draw this word");
    }
    return null;
  }
}
