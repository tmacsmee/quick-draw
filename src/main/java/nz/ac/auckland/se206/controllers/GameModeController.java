package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.DictionaryLookup;
import nz.ac.auckland.se206.util.TextToSpeechTask;

public class GameModeController {

  private ReadyController readyController;
  private String gameMode;

  /** Initializes the createAccount scene. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Game Mode Controller *****************");
  }

  /**
   * Switches to the drawing scene when the start button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onPlayNormal(ActionEvent event) {
    gameMode = "normal";

    readyController = (ReadyController) App.getController("ready");
    String prompt = readyController.getPrompt();
    readyController.setPromptLabel(prompt);

    readyController.setDrawLabel("normal");

    TextToSpeechTask textToSpeechTask = new TextToSpeechTask();
    new Thread(textToSpeechTask).start(); // Run the text to speech task on a new thread.

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));
  }

  /**
   * Launches the canvas in zen mode
   *
   * @param event the button click event.
   */
  @FXML
  private void onPlayZen(ActionEvent event) {
    gameMode = "zen";

    // Set prompt labels to reflect the game mode
    readyController = (ReadyController) App.getController("ready");
    String prompt = readyController.getPrompt();
    readyController.setPromptLabel(prompt);
    readyController.setDrawLabel("zen");

    // Switch to ready scene
    Button button = (Button) event.getSource();
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));
  }

  /**
   * Launches the canvas in zen mode
   *
   * @param event the button click event.
   */
  @FXML
  public void onPlayHidden(ActionEvent event) throws IOException {
    gameMode = "hidden";

    readyController = (ReadyController) App.getController("ready");
    CanvasController canvasController = (CanvasController) App.getController("canvas");
    DictionaryLookup dictionary = new DictionaryLookup();

    // Get the prompt definition
    try {
      String definition = dictionary.getDefinition(readyController.getPrompt());
      readyController.setPromptLabel(definition); // Set the prompt label to the definition
      canvasController.setPromptLabel(definition);

      readyController.decreasePromptLabelSize(); // Make definition fit on screen
      canvasController.decreasePromptLabelSize();

      readyController.setDrawLabel("hidden");

      // Switch to ready scene
      Button button = (Button) event.getSource();
      Scene buttonScene = button.getScene();
      buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));

    } catch (Exception e) { // Try a new word if the definition is not found
      readyController.setPrompt(App.getJsonParser().getDifficulty(App.getCurrentUser(), "level"));
      onPlayHidden(event);
    }
  }

  /**
   * Handles which mode is started when play again is clicked
   *
   * @param event the button click event.
   */
  public void playAgainHandler(ActionEvent event) {
    switch (gameMode) {
        // Play in normal mode
      case "normal":
        onPlayNormal(event);
        break;

        // Play in zen mode
      case "zen":
        onPlayZen(event);
        break;

        // Play in hidden mode
      case "hidden":
        try {
          onPlayHidden(event);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
    }
  }

  public String getGameMode() {
    return gameMode;
  }
}
