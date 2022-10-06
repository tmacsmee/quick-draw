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
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

/** The controller of the menu scene. */
public class MenuController {

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
   * Switches to the game mode select scene when the start button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onPlay(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.GAMEMODE));
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

  /** Updates the menu scene with the user's stats */
  public void updateStats() {
    JsonParser jsonParser = App.getJsonParser();

    // Set welcome and stats labels
    welcomeLabel.setText("Welcome back " + App.getCurrentUser() + "!");
    numWinsLabel.setText(jsonParser.getProperty(App.getCurrentUser(), "gamesWon").toString());
    numLossesLabel.setText(jsonParser.getProperty(App.getCurrentUser(), "gamesLost").toString());

    // Set fastest time label (if games have been played)
    if (jsonParser.getProperty(App.getCurrentUser(), "fastestTime") == "0") {
      fastestTimeLabel.setText("No wins yet");
    } else {
      fastestTimeLabel.setText(
          jsonParser.getProperty(App.getCurrentUser(), "fastestTime").toString() + " seconds");
    }
  }

  /** Updates the list of words encountered on the menu scene */
  public void setWordsEncounteredListView() {
    JsonParser jsonParser = App.getJsonParser();
    List<String> wordsEncountered =
        (List<String>) jsonParser.getProperty(App.getCurrentUser(), "wordsEncountered");
    ObservableList<String> wordsList = FXCollections.observableArrayList(wordsEncountered);
    wordsEncounteredListView.setItems(wordsList);
  }
}
