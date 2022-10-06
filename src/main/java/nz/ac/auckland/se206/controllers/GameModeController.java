package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.TextToSpeechTask;

public class GameModeController {

  /**
   * Switches to the drawing scene when the start button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onStartNormal(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));

    TextToSpeechTask textToSpeechTask = new TextToSpeechTask();
    new Thread(textToSpeechTask).start(); // Run the text to speech task on a new thread.
  }

  @FXML
  private void onStartZen(ActionEvent event) {}
}
