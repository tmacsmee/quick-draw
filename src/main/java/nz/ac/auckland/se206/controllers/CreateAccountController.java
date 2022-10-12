package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class CreateAccountController {
  @FXML private TextField usernameTextField;
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
    JsonParser jsonParser = App.getJsonParser();
    String username = usernameTextField.getText();

    // Checks if username is populated
    if (username.equals("")) {
      errorMessageLabel.setText("Username cannot be empty");
    } else if (username.length() > 12) {
      errorMessageLabel.setText("Username is too long");
    } else if (jsonParser.isCorrectUsername(username)) {
      // Checks if username is already taken
      errorMessageLabel.setText("Username already exists");
    } else {
      // Add account to json file
      jsonParser.addUser(username);

      // Set user stats labels
      App.setCurrentUser(username);

      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateStats();
      menuController.setWordsEncounteredListView();

      ReadyController readyController = (ReadyController) App.getController("ready");
      readyController.createDifficultyArrays(); // Get an array of each difficulty
      readyController.generatePrompt(
          App.getJsonParser().getProperty(App.getCurrentUser(), "level").toString());

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
