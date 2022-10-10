package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.TextToSpeechTask;

public class HowToPlayController {

  /** Initializes the login scene. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising How To Play Controller *****************");
  }

  /**
   * Switches to the menu scene when the main menu button is clicked
   *
   * @param event the button click event.
   */
  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /**
   * Switches to the ready scene when the play button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onPlay(ActionEvent event) {

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));

    TextToSpeechTask textToSpeechTask = new TextToSpeechTask();
    new Thread(textToSpeechTask).start();
  }
}
