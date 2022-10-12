package nz.ac.auckland.se206.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class ResultsController {

  @FXML private Label resultLabel;
  @FXML private ImageView sketchImageView;
  private CanvasController canvasController;

  /** Initializes the ResultsController. */
  @FXML
  private void initialize() {
    System.out.println(
        "***************** Initialising Results Controller *****************" + this);
    canvasController = (CanvasController) App.getController("canvas");
  }

  /**
   * Switches to the menu scene when the button is clicked
   *
   * @param event the button click event.
   */
  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    ReadyController readyController = (ReadyController) App.getController("ready");
    readyController.reset(); // Reset the canvas.

    statsController statsController = (statsController) App.getController("stats");
    statsController.updateStats();

    readyController.resetPromptLabelSize();
    canvasController.resetPromptLabelSize();

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /**
   * Runs when the play again button is pressed. Resets the canvas and returns to the 'ready' scene.
   */
  @FXML
  private void onPlayAgain(ActionEvent event) {
    ReadyController readyController = (ReadyController) App.getController("ready");
    readyController.generatePrompt(
        App.getJsonParser().getProperty(App.getCurrentUser(), "level").toString());

    GameModeController gameModeController = (GameModeController) App.getController("gameMode");

    gameModeController.playAgainHandler(event);
  }

  /**
   * Runs when the save sketch button is pressed. Saves the current sketch to the user's sketch
   * folder.
   *
   * @throws IOException if the sketch cannot be saved.
   */
  @FXML
  private void onSaveSketch() throws IOException {

    // gets the current drawing by the user
    BufferedImage image = canvasController.getCurrentSnapshot();

    // using FileChooser, allow the user to select any file they wish on their
    // machine
    // and enter whatever name they want to save the file as
    FileChooser saveFile = new FileChooser();
    saveFile.setTitle("Choose where to save");
    saveFile.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
    File file = saveFile.showSaveDialog(null);

    // save the image
    canvasController.saveCurrentSnapshotOnFile(file, image);
  }

  /*
   * Sets the result label to the given string.
   */
  public void setResultLabel(String result) {
    resultLabel.setText(result);
  }

  /** Sets the sketch image to the current canvas snapshot. */
  public void setSketchImage() {
    Image sketchImage = canvasController.getCanvas().snapshot(null, null);
    sketchImageView.setImage(sketchImage);
  }
}
