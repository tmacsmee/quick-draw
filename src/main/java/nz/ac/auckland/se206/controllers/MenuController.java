package nz.ac.auckland.se206.controllers;

import java.io.FileNotFoundException;
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
  /** Initializes the menu scene when the app is run. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Menu Controller *****************" + this);
  }
  /** Updates the welcome label to display the user's name. */
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
   * @throws FileNotFoundException if the file is not found.
   */
  @FXML
  private void onSwitchAccount(ActionEvent event) throws FileNotFoundException {
    LoginController loginController = (LoginController) App.getController("login");
    loginController.setProfiles();
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

    // Enable the play button on the how to play scene.
    HowToPlayController howToPlayController = (HowToPlayController) App.getController("howToPlay");
    howToPlayController.showPlayButton(true);

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
  private void onChangeToDifficulty(ActionEvent event) {
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
