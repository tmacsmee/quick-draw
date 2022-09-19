package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class CreateAccountController {
  @FXML private TextField usernameTextField;

  @FXML private PasswordField passwordPasswordField;

  @FXML private PasswordField confirmPasswordPasswordField;

  @FXML private Button createButton;

  @FXML private Button loginButton;
  @FXML private Label errorMessageLabel;

  /** Initializes the createAccount scene. */
  @FXML
  private void initialize() {
    System.out.println(
        "***************** Initialising Create Account Controller *****************");
  }

  /**
   * Creates a new account and changes to menu scene if details are successful, otherwise prints
   * error.
   *
   * @param event button click event
   */
  @FXML
  private void onCreate(ActionEvent event) {
    JsonParser jsonParser = new JsonParser();

    // Checks if username and password fields are populated
    if (usernameTextField.getText().equals("")) {
      errorMessageLabel.setText("Username cannot be empty");
    } else if (passwordPasswordField.getText().equals("")
        || confirmPasswordPasswordField.getText().equals("")) {
      errorMessageLabel.setText("Password cannot be empty");
    } else if (!passwordPasswordField.getText().equals(confirmPasswordPasswordField.getText())) {
      // Checks if password and confirm password fields match
      errorMessageLabel.setText("Passwords do not match");
    } else if (jsonParser.isCorrectUsername(usernameTextField.getText())) {
      // Checks if username is already taken
      errorMessageLabel.setText("Username already exists");
    } else {
      // Creates new account and changes to menu scene
      jsonParser.addUser(usernameTextField.getText(), passwordPasswordField.getText());
      jsonParser.mapToJson();
      Button button =
          (Button) event.getSource(); // Get the scene of the button and switch its root.
      Scene buttonScene = button.getScene();
      buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
    }
  }

  /**
   * Switches to the login scene when the button is clicked
   *
   * @param event the button click event.
   */
  @FXML
  private void onLogin(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.LOGIN));
  }
}
