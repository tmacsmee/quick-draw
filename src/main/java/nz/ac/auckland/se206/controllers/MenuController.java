package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

/** The controller of the menu scene. */
public class MenuController {

  @FXML private Label welcomeLabel;

  /** Initializes the menu scene. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Menu Controller *****************" + this);
  }

  public void updateWelcome() {
    welcomeLabel.setText("Welcome back " + App.getCurrentUser() + "!");
  }

  /**
   * Switches to the game mode select scene when the start button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onPlay(ActionEvent event) {
    App.getSoundManager().playButtonClick();
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
    App.getSoundManager().playButtonClick();
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
    App.getSoundManager().playButtonClick();
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.HOWTOPLAY));
  }

  /**
   * Switches to the difficulty scene when the user clicks button.
   *
   * @param event the button click event.
   */
  @FXML
  private void onDifficulty(ActionEvent event) {
    DifficultyController difficultyController =
        (DifficultyController) App.getController("difficulty");
    difficultyController.initialiseChoiceBox();
    App.getSoundManager().playButtonClick();
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.DIFFICULTY));
  }

  /**
   * Switches to the badges scene when the user clicks button.
   *
   * @param event the button click event.
   */
  @FXML
  private void onBadges(ActionEvent event) {
    App.getSoundManager().playButtonClick();
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.STATS));
  }

  /**
   * Switches to the words encountered scene when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onWordsEncountered(ActionEvent event) {
    WordsController wordsController = (WordsController) App.getController("wordsEncountered");
    wordsController.setEncounteredListView(); // Set the lists for the words encountered.
    App.getSoundManager().playButtonClick();
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.WORDS));
  }
}
