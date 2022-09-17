package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.SceneManager;

public class loginController {
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
   * Called when the user clicks the create button to create a new account.
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
   * Called when the user clicks the login button.
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
