package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.SceneManager;

public class LoginController {
  @FXML private TextField usernameTextField;

  @FXML private PasswordField passwordPasswordField;

  @FXML private Button createAccountButton;

  @FXML private Button loginButton;

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
    // Add code to check if username and password are correct.

    // If correct, change to menu scene.

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }
}
