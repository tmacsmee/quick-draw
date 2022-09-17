package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.SceneManager;

public class CreateAccountController {
  @FXML private TextField usernameTextField;

  @FXML private PasswordField passwordPasswordField;

  @FXML private PasswordField confirmPasswordPasswordField;

  @FXML private Button createButton;

  @FXML private Button loginButton;

  /** Initializes the createAccount scene. */
  @FXML
  private void initialize() {
    System.out.println(
        "***************** Initialising Create Account Controller *****************");
  }

  /** Called when the user clicks the create button to create a new account. */
  @FXML
  private void onCreate(ActionEvent event) {
    // Add code to create a new account, and store password and username in json.

    // If successful, change to menu scene.
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /** Called when the user clicks the login button to return to the login screen. */
  @FXML
  private void onLogin(ActionEvent event) {
    // Add your code to switch to the login screen here.

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.LOGIN));
  }
}
