package nz.ac.auckland.se206.controllers;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class CreateAccountController {
  private String currentUser;
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
    String username = usernameTextField.getText();
    String password = passwordPasswordField.getText();

    // Checks if username and password fields are populated
    if (username.equals("")) {
      errorMessageLabel.setText("Username cannot be empty");
    } else if (password.equals("") || confirmPasswordPasswordField.getText().equals("")) {
      errorMessageLabel.setText("Password cannot be empty");
    } else if (!password.equals(confirmPasswordPasswordField.getText())) {
      // Checks if password and confirm password fields match
      errorMessageLabel.setText("Passwords do not match");
    } else if (jsonParser.isCorrectUsername(username)) {
      // Checks if username is already taken
      errorMessageLabel.setText("Username already exists");
    } else {
      // Add account to json file
      jsonParser.addUser(username, password);
      jsonParser.mapToJson();

      // Set user stats labels
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.setWelcomeLabel(username);
      menuController.setNumWinsLabel((String) jsonParser.getProperty(username, "gamesWon"));
      menuController.setNumLossesLabel((String) jsonParser.getProperty(username, "gamesLost"));
      menuController.setFastestTimeLabel((String) jsonParser.getProperty(username, "fastestTime"));
      menuController.setWordsEncounteredListView(
          (List<String>) jsonParser.getProperty(username, "wordsEncountered"));

      App.setCurrentUser(username);
      ReadyController readyController = (ReadyController) App.getController("ready");
      readyController.createDifficultyArrays(); // Get an array of each difficulty
      readyController.getPrompt("E");

      // Switch to menu scene
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
