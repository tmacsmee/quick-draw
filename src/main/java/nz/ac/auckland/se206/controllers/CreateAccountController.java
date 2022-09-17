package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {
  @FXML private TextField usernameTextField;

  @FXML private PasswordField passwordPasswordField;

  @FXML private PasswordField confirmPasswordPasswordField;

  @FXML private Button createButton;

  @FXML private Button loginButton;

  /** Called when the user clicks the create button to create a new account. */
  @FXML
  private void onCreate() {
    // Add code to create a new account, and store password and username in json.
  }

  /** Called when the user clicks the login button to return to the login screen. */
  @FXML
  private void onLogin(ActionEvent event) {
    // Add your code to switch to the login screen here.

    // Button button = (Button) event.getSource(); // Get the scene of the button
    // and switch its root.
    // Scene buttonScene = button.getScene();
    // buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.READY));

  }
}
