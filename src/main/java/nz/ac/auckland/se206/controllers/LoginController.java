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

public class LoginController {
  @FXML private TextField usernameTextField;
  @FXML private PasswordField passwordPasswordField;
  @FXML private Button createAccountButton;
  @FXML private Button loginButton;
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
    JsonParser jsonParser = new JsonParser();
    String username = usernameTextField.getText();
    String password = passwordPasswordField.getText();

    // Check username exists and password is correct, otherwise display error message.
    if (!jsonParser.isCorrectUsername(username)) {
      errorMessageLabel.setText("Username does not exist");
    } else if (!jsonParser.isCorrectPassword(username, password)) {
      errorMessageLabel.setText("Password is incorrect");
    } else {
      // Set user stats labels
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.setWelcomeLabel(username);
      menuController.setNumWinsLabel((String) jsonParser.getProperty(username, "gamesWon"));
      menuController.setNumLossesLabel((String) jsonParser.getProperty(username, "gamesLost"));
      menuController.setFastestTimeLabel((String) jsonParser.getProperty(username, "fastestTime"));
      menuController.setWordsEncounteredListView(
          (List<String>) jsonParser.getProperty(username, "wordsEncountered"));

      // Change to menu screen
      Button button =
          (Button) event.getSource(); // Get the scene of the button and switch its root.
      Scene buttonScene = button.getScene();
      buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
    }
  }
}
