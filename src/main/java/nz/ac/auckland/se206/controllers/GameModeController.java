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
  public void onPlayNormal(ActionEvent event) {
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

  @FXML
  public void onPlayZen(ActionEvent event) {
    gameMode = "zen";

    readyController = (ReadyController) App.getController("ready");
    String prompt = readyController.getPrompt();
    readyController.setPromptLabel(prompt);

    readyController.setDrawLabel("zen");

    Button button = (Button) event.getSource();
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));
  }

  @FXML
  public void onPlayHidden(ActionEvent event) throws IOException {
    gameMode = "hidden";

    readyController = (ReadyController) App.getController("ready");
    CanvasController canvasController = (CanvasController) App.getController("canvas");
    DictionaryLookup dictionary = new DictionaryLookup();
    String definition = dictionary.getDefinition(readyController.getPrompt());
    System.out.println(readyController.getPrompt());
    readyController.setPromptLabel(definition);
    canvasController.setPromptLabel(definition);

    readyController.decreasePromptLabelSize();
    canvasController.decreasePromptLabelSize();

    readyController.setDrawLabel("hidden");

    Button button = (Button) event.getSource();
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));
  }

  public String getGameMode() {
    return gameMode;
  }
}
