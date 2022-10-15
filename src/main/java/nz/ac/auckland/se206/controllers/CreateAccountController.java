package nz.ac.auckland.se206.controllers;

import java.io.FileNotFoundException;
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
  @FXML private Button buttonCatAvatar;
  @FXML private Button buttonChickenAvatar;
  @FXML private Button buttonDogAvatar;
  @FXML private Button buttonSnakeAvatar;
  @FXML private Button buttonPufferFishAvatar;
  @FXML private Button buttonRabbitAvatar;
  @FXML private Button buttonSealionAvatar;
  @FXML private Button buttonPandaAvatar;

  private static String chosenAvatar = null;

  /** Initializes the createAccount scene when first created. */
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
   * @throws FileNotFoundException
   */
  @FXML
  private void onCreate(ActionEvent event) throws FileNotFoundException {
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
    } else if (jsonParser.getListUsernames().size() == 8) {
      errorMessageLabel.setText("Too many users");
    } else {
      usernameTextField.setText("");
      errorMessageLabel.setText("");

      // Add account to json file
      jsonParser.addUser(username, chosenAvatar);
      jsonParser.getListUsernames().add(username);

      // Set user stats labels
      App.changeUser(username);

      LoginController loginController = (LoginController) App.getController("login");

      loginController.setProfiles();
      loginController.setVisibility(jsonParser.getListUsernames().size(), true);

      App.getSoundManager().playButtonClick();
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
   * @throws FileNotFoundException
   */
  @FXML
  private void onLogin(ActionEvent event) throws FileNotFoundException {
    App.getSoundManager().playButtonClick();
    ((LoginController) App.getController("login")).setProfiles();
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.LOGIN));
  }

  @FXML
  private void onChooseCat(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "cat");
    chosenAvatar = "cat";
  }

  @FXML
  private void onChooseChicken(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "chicken");
    chosenAvatar = "chicken";
  }

  @FXML
  private void onChooseDog(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "dog");
    chosenAvatar = "dog";
  }

  @FXML
  private void onChooseSnake(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "snake");
    chosenAvatar = "snake";
  }

  @FXML
  private void onChoosePufferFish(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "puffer-fish");
    chosenAvatar = "puffer-fish";
  }

  @FXML
  private void onChooseRabbit(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "rabbit");
    chosenAvatar = "rabbit";
  }

  @FXML
  private void onChooseSealion(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "sealion");
    chosenAvatar = "sealion";
  }

  @FXML
  private void onChoosePanda(ActionEvent event) {
    setChosenAvatar(chosenAvatar, "panda");
    chosenAvatar = "panda";
  }

  public void setChosenAvatar(String avatarOld, String avatarNew) {
    if (avatarOld != null) {
      getAvatarButton(avatarOld).setStyle("-fx-background-color: #FFFFFF");
    }
    getAvatarButton(avatarNew).setStyle("-fx-background-color: #FF8E5E");
  }

  public Button getAvatarButton(String avatar) {
    switch (avatar) {
      case "cat":
        return buttonCatAvatar;
      case "chicken":
        return buttonChickenAvatar;
      case "dog":
        return buttonDogAvatar;
      case "snake":
        return buttonSnakeAvatar;
      case "puffer-fish":
        return buttonPufferFishAvatar;
      case "rabbit":
        return buttonRabbitAvatar;
      case "sealion":
        return buttonSealionAvatar;
      case "panda":
        return buttonPandaAvatar;
      default:
        return null;
    }
  }
}
