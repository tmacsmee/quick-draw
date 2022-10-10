package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class LoginController {

  @FXML private TextField usernameTextField;
  @FXML private Label errorMessageLabel;

  /** Initializes the login scene. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Login Controller *****************");
  }

  /**
   * Switches to the create account scene when the button is clicked
   *
   * @param event the button click event.
   */
  @FXML
  private void onCreateAccount(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.CREATEACCOUNT));
  }

  /**
   * Switches to the menu scene when the user logs in.
   *
   * @param event the button click event.
   */
  @FXML
  private void onLogin(ActionEvent event) {
    String username = usernameTextField.getText();

    // Set user stats labels
    App.setCurrentUser(username); // Set the current user

    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateStats();
    menuController.setWordsEncounteredListView();

    ReadyController readyController = (ReadyController) App.getController("ready");
    readyController.createDifficultyArrays(); // Get an array of each difficulty
    readyController.setPrompt("E");

    // Change to menu screen
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }
}
