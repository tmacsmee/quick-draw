package nz.ac.auckland.se206.controllers;

import ai.djl.ModelException;
import ai.djl.modality.Classifications;
import ai.djl.translate.TranslateException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.ml.DoodlePrediction;

/** The controller of the canvas scene. */
public class CanvasController {

  private GraphicsContext graphic;
  private DoodlePrediction model;
  private double lastX;
  private double lastY;
  private Color activeColor;
  @FXML private Canvas canvas;
  @FXML private Label predictionList;
  @FXML private Label timerLabel;
  @FXML private Label promptLabel;
  @FXML private Button clearButton;
  @FXML private Button brushButton;
  @FXML private Button eraserButton;
  @FXML private Button blackButton;
  @FXML private Button redButton;
  @FXML private Button orangeButton;
  @FXML private Button yellowButton;
  @FXML private Button greenButton;
  @FXML private Button blueButton;
  @FXML private Button purpleButton;
  @FXML private Button pinkButton;

  // boolean used to check if the canvas is empty or not
  private boolean isCanvasNotEmpty = false;

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

    activeColor = Color.BLACK;
    blackButton.setDisable(true);

    // Get coordinates of mouse on press.
    canvas.setOnMousePressed(
        e -> {
          lastX = e.getX();
          lastY = e.getY();
        });

    onSwitchToBrush();

    model = new DoodlePrediction(); // Load the model.
  }

  /**
   * Called when the brush button is pressed, sets the brush to be used for drawing.
   *
   * @throws FileNotFoundException
   */
  @FXML
  private void onSwitchToBrush() throws FileNotFoundException {
    brushButton.setDisable(true); // Toggle to disable the brush button.
    eraserButton.setDisable(false);

    // Passing FileInputStream object as a parameter
    FileInputStream inputstream =
        new FileInputStream(
            System.getProperty("user.dir") + "/src/main/resources/images/curser_image.png");
    Image image = new Image(inputstream);

    canvas.setCursor(new ImageCursor(image));

    canvas.setOnMouseDragged(
        e -> {
          double brushSize = 6.25;

          // canvas declared as not empty when user starts drawing
          isCanvasNotEmpty = true;

          graphic.setLineWidth(brushSize);
          graphic.setStroke(activeColor);

          double thisX = e.getX(); // Get coordinates of mouse on drag.
          double thisY = e.getY();
          graphic.strokeLine(
              lastX, lastY, thisX,
              thisY); // Create a line between the last and current mouse coordinates.
          lastX = thisX;
          lastY = thisY;
        });
  }

  /**
   * Called when the eraser button is pressed, sets the eraser to be used.
   *
   * @throws FileNotFoundException
   */
  @FXML
  private void onSwitchToEraser() throws FileNotFoundException {
    eraserButton.setDisable(true); // Toggle to disable the eraser button.
    brushButton.setDisable(false);

    // Passing FileInputStream object as a parameter
    FileInputStream inputstream =
        new FileInputStream(
            System.getProperty("user.dir") + "/src/main/resources/images/eraser_cursor.png");
    Image image = new Image(inputstream);

    canvas.setCursor(new ImageCursor(image));

    double eraserSize = 12.5;
    canvas.setOnMouseDragged(
        e -> {
          final double x =
              e.getX() - eraserSize / 2; // Get coordinate of top left corner of eraser.
          final double y = e.getY() - eraserSize / 2;
          graphic.clearRect(x, y, eraserSize, eraserSize); // Clear a small rectangle of the canvas.
        });
  }

  /** Clears the entire canvas when called. */
  public void onClear() {
    graphic.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    // when canvas is cleared, canvas declared as empty and predictionList no longer displays
    // anything
    isCanvasNotEmpty = false;
    predictionList.setText("");
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

    // only display predictions if canvas is not empty
    if (isCanvasNotEmpty) {
      predictionList.setText(predictions);
    }
  }

  /**
   * Sets the prompt label to the given prompt.
   *
   * @param prompt randomly generated prompt
   */
  public void setPromptLabel(String prompt) {
    promptLabel.setText(prompt);
  }

  /** Reduces prompt label to allow definition to fit */
  public void decreasePromptLabelSize() {
    promptLabel.setStyle("-fx-font-size: 12px;");
  }

  /** Resets prompt label to default value */
  public void resetPromptLabelSize() {
    promptLabel.setStyle("-fx-font-size: 30px;");
  }

  public DoodlePrediction getModel() {
    return model;
  }

  public Canvas getCanvas() {
    return canvas;
  }

  /**
   * Sets the brush colour to the colour clicked on the palette.
   *
   * @param event the button clicked
   */
  @FXML
  private void onChangeBrushColour(ActionEvent event) {
    // Enable all buttons
    enableColourButtons();

    // Get the button that was clicked
    if (event.getSource() == blackButton) {
      activeColor = Color.BLACK; // Get the colour from the clicked button
      blackButton.setDisable(true); // Disable the clicked colour
    } else if (event.getSource() == redButton) {
      activeColor = Color.RED;
      redButton.setDisable(true);
    } else if (event.getSource() == orangeButton) {
      activeColor = Color.ORANGE;
      orangeButton.setDisable(true);
    } else if (event.getSource() == yellowButton) {
      activeColor = Color.YELLOW;
      yellowButton.setDisable(true);
    } else if (event.getSource() == greenButton) {
      activeColor = Color.GREEN;
      greenButton.setDisable(true);
    } else if (event.getSource() == blueButton) {
      activeColor = Color.BLUE;
      blueButton.setDisable(true);
    } else if (event.getSource() == purpleButton) {
      activeColor = Color.PURPLE;
      purpleButton.setDisable(true);
    } else if (event.getSource() == pinkButton) {
      activeColor = Color.PINK;
      pinkButton.setDisable(true);
    }
    // Set the brush colour to the colour of the clicked button
    graphic.setStroke(activeColor);
  }

  /** Enables all colour buttons. Used to reset the colour palette after a colour is selected. */
  private void enableColourButtons() {
    blackButton.setDisable(false); // Enable all buttons
    redButton.setDisable(false);
    orangeButton.setDisable(false);
    yellowButton.setDisable(false);
    greenButton.setDisable(false);
    blueButton.setDisable(false);
    purpleButton.setDisable(false);
    pinkButton.setDisable(false);
  }

  /**
   * Checks if the prompt is within the top 1, 2, or 3 predictions.
   *
   * @return true if the prompt is within the top 1, 2, or 3 predictions, false otherwise
   * @throws TranslateException If there is an error in translating the prompt to a classification.
   */
  public boolean isCorrect() throws TranslateException {
    ReadyController readyController = (ReadyController) App.getController("ready");
    // only predict if canvas is not empty
    if (isCanvasNotEmpty) {
      for (Classifications.Classification c :
          model.getPredictions(
              getCurrentSnapshot(),
              Integer.parseInt(
                  App.getJsonParser()
                      .getProperty(App.getCurrentUser(), "topGuess")
                      .toString()))) { // Get the top 1, 2, or 3 prediction(s).

        // If the prompt equals one of the top x predictions.
        if (readyController.getPrompt().equalsIgnoreCase(c.getClassName().replace("_", " "))) {

          // If the prompt also has at least confidence percentage specified in the difficulties.
          if (c.getProbability() * 100
              >= Integer.parseInt(
                  App.getJsonParser().getProperty(App.getCurrentUser(), "confidence").toString())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /** Switches to the 'results' scene. */
  public void results() {
    predictionList.setText("");
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
   * @param image the image to save
   * @return the file where the image was saved
   * @throws IOException If the image cannot be saved.
   */
  public File saveCurrentSnapshotOnFile(File file, BufferedImage image) throws IOException {

    // We save the image to a file in the tmp folder.
    final File imageToClassify = new File(file.getAbsolutePath() + ".bmp");

    // Save the image to a file.
    ImageIO.write(image, "bmp", imageToClassify);

    return imageToClassify;
  }

  /**
   * Returns the player to the main menu
   *
   * @param event the button click event
   */
  @FXML
  private void onExit(ActionEvent event) {
    GameModeController gameModeController = (GameModeController) App.getController("gameMode");
    ResultsController resultsController = (ResultsController) App.getController("results");
    ReadyController readyController = (ReadyController) App.getController("ready");

    Button button = (Button) event.getSource(); // Get the button that was clicked.
    Scene buttonScene = button.getScene();

    String gameMode = gameModeController.getGameMode();

    // Generate new prompt
    readyController.generatePrompt(
        App.getJsonParser().getProperty(App.getCurrentUser(), "level").toString());

    // Cancel the task of the game mode that was being played
    switch (gameMode) {
      case "zen" -> { // Cancel zen mode task and take user to results screen
        readyController.getZenModeTask().cancel();
        resultsController.setResultLabel("Here's your drawing:"); // Present drawing to user
        resultsController.setSketchImage();
        buttonScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.RESULTS)));
      }
      case "hidden" -> { // Cancel hidden mode task
        resetPromptLabelSize(); // Reset labels to original size
        readyController.resetPromptLabelSize();
        readyController.getHiddenModeTask().cancel();
        buttonScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.MENU)));
      }
      case "normal" -> { // Cancel normal mode task
        readyController.getNormalModeTask().cancel();
        buttonScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.MENU)));
      }
    }
  }
}
