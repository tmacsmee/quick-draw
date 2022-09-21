package nz.ac.auckland.se206.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;
import nz.ac.auckland.se206.util.TextToSpeechTask;

/** The controller of the menu scene. */
public class MenuController {

  @FXML private Button startButton;
  @FXML private Label welcomeLabel;
  @FXML private Label numWinsLabel;
  @FXML private Label numLossesLabel;
  @FXML private Label fastestTimeLabel;
  @FXML private ListView<String> wordsEncounteredListView;

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

  /**
   * Switches to the login scene when the user clicks button to switch account.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSwitchAccount(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.LOGIN));
  }

  /**
   * Switches to the how to play scene when the user clicks button.
   *
   * @param event the button click event.
   */
  @FXML
  private void onHowToPlay(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.HOWTOPLAY));
  }

  public void updateStats() {
    JsonParser jsonParser = new JsonParser();

    welcomeLabel.setText("Welcome back " + App.getCurrentUser() + "!");
    numWinsLabel.setText(jsonParser.getProperty(App.getCurrentUser(), "gamesWon").toString());
    numLossesLabel.setText(jsonParser.getProperty(App.getCurrentUser(), "gamesLost").toString());
    fastestTimeLabel.setText(
        jsonParser.getProperty(App.getCurrentUser(), "fastestTime").toString());
  }

  public void setWordsEncounteredListView(ObservableList<String> words) {
    wordsEncounteredListView.setItems(words);
  }
}
