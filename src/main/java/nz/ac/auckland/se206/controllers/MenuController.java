package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.TextToSpeechTask;

/** The controller of the menu scene. */
public class MenuController {

  @FXML private Button startButton;
  @FXML private Button statsButton;
  @FXML private Label startLabel;

  /** Initializes the menu scene. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Menu Controller *****************" + this);
  }

  /**
   * Switches to the drawing scene when the start button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onStart(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));

    TextToSpeechTask textToSpeechTask = new TextToSpeechTask();
    new Thread(textToSpeechTask).start(); // Run the text to speech task on a new thread.
  }
}
