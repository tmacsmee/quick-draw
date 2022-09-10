package nz.ac.auckland.se206.controllers;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.TextToSpeechTask;

public class ResultsController {

  @FXML private Button playAgainButton;
  @FXML private Button saveSketchButton;
  @FXML private Label resultLabel;
  @FXML private ImageView sketchImageView;
  @FXML private TextField sketchTextField;
  private CanvasController canvasController;

  /** Initializes the ResultsController. */
  @FXML
  private void initialize() {
    System.out.println(
        "***************** Initialising Results Controller *****************" + this);
    canvasController = (CanvasController) App.getController("canvas");
  }

  /**
   * Runs when the play again button is pressed. Resets the canvas and returns to the 'ready' scene.
   */
  @FXML
  private void onPlayAgain() {
    ReadyController readyController = (ReadyController) App.getController("ready");
    readyController.reset(); // Reset the canvas.

    playAgainButton
        .getScene()
        .setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY)); // Switch to the 'ready' scene.
    TextToSpeechTask textToSpeechTask = new TextToSpeechTask();
    new Thread(textToSpeechTask).start(); // Run text to speech task on new thread.
  }

  /**
   * Runs when the save sketch button is pressed. Saves the current sketch to the user's sketch
   * folder.
   *
   * @throws IOException if the sketch cannot be saved.
   */
  @FXML
  private void onSaveSketch() throws IOException {
    File file = fileChooser();
    canvasController.saveCurrentSnapshotOnFile(file);
  }

  /**
   * Runs file chooser to allow the user to select a file to save the sketch to.
   *
   * @return file the user selects.
   */
  @FXML
  private File fileChooser() {
    DirectoryChooser dc = new DirectoryChooser();
    return dc.showDialog(null);
  }

  public void setResultLabel(String result) {
    resultLabel.setText(result);
  }

  /**
   * Gets the name of the sketch from input field.
   *
   * @return sketch name
   */
  public String getSketchName() {
    if (sketchTextField.getText().isEmpty()) {
      return "sketch" + System.currentTimeMillis();
    } else {
      return sketchTextField.getText();
    }
  }

  public void setSketchImage() {
    Image sketchImage = canvasController.getCanvas().snapshot(null, null);
    sketchImageView.setImage(sketchImage);
  }
}
