package nz.ac.auckland.se206.util;

import javafx.concurrent.Task;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.controllers.ReadyController;
import nz.ac.auckland.se206.speech.TextToSpeech;

/** Handles text-to-speech output */
public class TextToSpeechTask extends Task<Void> {

  private final ReadyController readyController;

  /** Constructor for TextToSpeechTask */
  public TextToSpeechTask() {
    readyController = (ReadyController) App.getController("ready");
  }

  /**
   * Speaks the given list of sentences.
   *
   * @return null
   */
  @Override
  protected Void call() {
    TextToSpeech textToSpeech = new TextToSpeech();
    textToSpeech.speak("You have 1 minute to draw" + readyController.getPromptLabel());
    return null;
  }
}
