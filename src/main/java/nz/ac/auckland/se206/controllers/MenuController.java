package nz.ac.auckland.se206.controllers;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nz.ac.auckland.se206.SceneManager;
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

  public void setWelcomeLabel(String username) {
    welcomeLabel.setText("Welcome back " + username + "!");
  }

  public void setNumWinsLabel(String numWins) {
    numWinsLabel.setText(numWins);
  }

  public void setNumLossesLabel(String numLosses) {
    numLossesLabel.setText(numLosses);
  }

  public void setFastestTimeLabel(String fastestTime) {
    fastestTimeLabel.setText(fastestTime);
  }

  public void setWordsEncounteredListView(List<String> wordsEncountered) {
    ObservableList<String> wordsList = FXCollections.observableArrayList(wordsEncountered);
    wordsEncounteredListView.setItems(wordsList);
  }
}
