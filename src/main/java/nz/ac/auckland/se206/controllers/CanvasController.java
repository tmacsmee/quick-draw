package nz.ac.auckland.se206.controllers;

import ai.djl.ModelException;
import ai.djl.modality.Classifications;
import ai.djl.translate.TranslateException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.ml.DoodlePrediction;
import nz.ac.auckland.se206.util.TimeLimitTask;

/** The controller of the canvas scene. */
public class CanvasController {

  private GraphicsContext graphic;
  private DoodlePrediction model;
  private double lastX;
  private double lastY;
  @FXML private Canvas canvas;
  @FXML private Label predictionList;
  @FXML private Label timerLabel;
  @FXML private Label promptLabel;
  @FXML private Button clearButton;
  @FXML private Button brushButton;
  @FXML private Button eraserButton;

  /**
   * Initializes the drawing canvas, mouse input, and machine learning model
   *
   * @throws ModelException If there is an error in reading the input/output of the DL model.
   * @throws IOException If the model cannot be found on the file system.
   */
  @FXML
  public void initialize() throws ModelException, IOException {
    System.out.println("***************** Initialising Canvas Controller *****************");

    graphic = canvas.getGraphicsContext2D();

    // Get coordinates of mouse on press.
    canvas.setOnMousePressed(
        e -> {
          lastX = e.getX();
          lastY = e.getY();
        });

    onSwitchToBrush();

    model = new DoodlePrediction(); // Load the model.
  }

  /** Called when the brush button is pressed, sets the brush to be used for drawing. */
  @FXML
  private void onSwitchToBrush() {
    brushButton.setDisable(true); // Toggle to disable the brush button.
    eraserButton.setDisable(false);

    canvas.setOnMouseDragged(
        e -> {
          double brushSize = 6.25;
          double thisX = e.getX(); // Get coordinates of mouse on drag.
          double thisY = e.getY();

          graphic.setLineWidth(brushSize);
          graphic.setStroke(Color.BLACK);
          graphic.strokeLine(
              lastX, lastY, thisX,
              thisY); // Create a line between the last and current mouse coordinates.
          lastX = thisX;
          lastY = thisY;
        });
  }

  /** Called when the eraser button is pressed, sets the eraser to be used. */
  @FXML
  private void onSwitchToEraser() {
    eraserButton.setDisable(true); // Toggle to disable the eraser button.
    brushButton.setDisable(false);

    double eraserSize = 12.5;
    canvas.setOnMouseDragged(
        e -> {
          final double x =
              e.getX() - eraserSize / 2; // Get coordinate of top left corner of eraser.
          final double y = e.getY() - eraserSize / 2;
          graphic.clearRect(x, y, eraserSize, eraserSize); // Clear a small rectangle of the canvas.
        });
  }

  /** Calls the method to clear the canvas when the "Clear" button is pressed, clears the canvas. */
  @FXML
  private void onClear() {
    clear();
  }

  /** Clears the entire canvas when called. */
  public void clear() {
    graphic.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  /** Creates and starts the task that manages the time limit. */
  public void startTimer() {
    TimeLimitTask task = new TimeLimitTask(this);
    task.scheduleTask();
  }

  /**
   * Sets the timer label to the given time.
   *
   * @param time the time to be set
   */
  public void setTimerLabel(String time) {
    timerLabel.setText(time);
  }

  /**
   * Sets the prediction list to the given list of predictions.
   *
   * @param predictions list of predictions
   */
  public void setPredictionList(String predictions) {
    predictionList.setText(predictions);
  }

  /**
   * Sets the prompt label to the given prompt.
   *
   * @param prompt randomly generated prompt
   */
  public void setPrompt(String prompt) {
    promptLabel.setText("Draw: " + prompt);
  }

  public DoodlePrediction getModel() {
    return model;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  /**
   * Checks if the prompt is within the top 3 predictions.
   *
   * @return true if the prompt is within the top 3 predictions, false otherwise
   * @throws TranslateException If there is an error in translating the prompt to a classification.
   */
  public boolean isCorrect() throws TranslateException {
    for (Classifications.Classification c :
        model.getPredictions(getCurrentSnapshot(), 3)) { // Get the top 3
      // predictions.
      if (promptLabel.getText().substring(6).equalsIgnoreCase(c.getClassName().replace("_", " "))) {
        return true; // If the prompt is in the top 3 predictions, return true.
      }
    }
    return false;
  }

  /** Switches to the 'results' scene. */
  public void results() {
    Scene thisScene = clearButton.getScene();
    thisScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.RESULTS)));
  }

  /**
   * Get the current snapshot of the canvas.
   *
   * @return The BufferedImage corresponding to the current canvas content.
   */
  public BufferedImage getCurrentSnapshot() {
    final Image snapshot = canvas.snapshot(null, null);
    final BufferedImage image = SwingFXUtils.fromFXImage(snapshot, null);

    // Convert into a binary image.
    final BufferedImage imageBinary =
        new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

    final Graphics2D graphics = imageBinary.createGraphics();

    graphics.drawImage(image, 0, 0, null);

    // To release memory we dispose.
    graphics.dispose();

    return imageBinary;
  }

  /**
   * Save the current snapshot on a bitmap file.
   *
   * @param image
   * @return
   * @throws IOException If the image cannot be saved.
   */
  public File saveCurrentSnapshotOnFile(File file, BufferedImage image) throws IOException {

    // We save the image to a file in the tmp folder.
    final File imageToClassify = new File(file.getAbsolutePath() + ".bmp");

    // Save the image to a file.
    ImageIO.write(image, "bmp", imageToClassify);

    return imageToClassify;
  }
}
