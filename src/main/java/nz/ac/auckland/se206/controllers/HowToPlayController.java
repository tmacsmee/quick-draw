package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class HowToPlayController {

  @FXML private Button playButton;
  @FXML private Button mainMenuButton;

  /** Initializes the login scene when the app is run. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising How To Play Controller *****************");
  }
  /**
   * Switches to the menu scene when the main menu button is clicked
   *
   * @param event the button click event.
   */
  @FXML
  private void onSwitchToMenu(ActionEvent event) {

    App.getSoundManager().playButtonClick();

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));

    if (App.getCurrentUser() != null) {
      // If the user is logged in, go to the main menu
      buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
    } else {
      // If the user is not logged in, go to the welcome screen
      buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.WELCOME));
    }
  }

  /**
   * Switches to the ready scene when the play button is clicked.
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

  /** Takes out the play button from the how to play scene when the user has not yet logged in. */
  public void showPlayButton(boolean show) {
    playButton.setVisible(show);
  }
}
