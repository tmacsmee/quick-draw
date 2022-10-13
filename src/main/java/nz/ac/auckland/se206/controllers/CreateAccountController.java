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
  private static String chosenAvatar;

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
    if (chosenAvatar == null) {
      errorMessageLabel.setText("Select an avatar");
    } else if (username.equals("")) {
      errorMessageLabel.setText("Username cannot be empty");
    } else if (username.length() > 12) {
      errorMessageLabel.setText("Username is too long");
    } else if (jsonParser.isCorrectUsername(username)) {
      // Checks if username is already taken
      errorMessageLabel.setText("Username already exists");
    } else {
      // Add account to json file
      jsonParser.addUser(username, chosenAvatar);
      jsonParser.getAllUsernames().add(username);

      // Set user stats labels
      App.setCurrentUser(username);

      StatsController statsController = (StatsController) App.getController("stats");
      WordsController wordsController = (WordsController) App.getController("wordsEncountered");
      MenuController menuController = (MenuController) App.getController("menu");

      menuController.updateWelcome();
      statsController.updateStats();
      wordsController.setEncounteredListView();
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

  @FXML
  private void onChooseCat(ActionEvent event) {
    System.out.println("chose cat");
    chosenAvatar = "cat";
  }

  @FXML
  private void onChooseChicken(ActionEvent event) {
    chosenAvatar = "chicken";
  }

  @FXML
  private void onChooseDog(ActionEvent event) {
    chosenAvatar = "dog";
  }

  @FXML
  private void onChooseSnake(ActionEvent event) {
    chosenAvatar = "snake";
  }

  @FXML
  private void onChoosePufferFish(ActionEvent event) {
    chosenAvatar = "puffer-fish";
  }

  @FXML
  private void onChooseRabbit(ActionEvent event) {
    chosenAvatar = "rabbit";
  }

  @FXML
  private void onChooseSealion(ActionEvent event) {
    chosenAvatar = "sealion";
  }

  @FXML
  private void onChoosePanda(ActionEvent event) {
    chosenAvatar = "panda";
  }
}
