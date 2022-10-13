package nz.ac.auckland.se206.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class LoginController {

  @FXML private Label userOneLabel;
  @FXML private Label userTwoLabel;
  @FXML private Label userThreeLabel;
  @FXML private Label userFourLabel;
  @FXML private Label userFiveLabel;
  @FXML private Label userSixLabel;
  @FXML private Label userSevenLabel;
  @FXML private Label userEightLabel;
  @FXML private Button userOneButton;
  @FXML private Button userTwoButton;
  @FXML private Button userThreeButton;
  @FXML private Button userFourButton;
  @FXML private Button userFiveButton;
  @FXML private Button userSixButton;
  @FXML private Button userSevenButton;
  @FXML private Button userEightButton;
  @FXML private ImageView userOneImage;
  @FXML private ImageView userTwoImage;
  @FXML private ImageView userThreeImage;
  @FXML private ImageView userFourImage;
  @FXML private ImageView userFiveImage;
  @FXML private ImageView userSixImage;
  @FXML private ImageView userSevenImage;
  @FXML private ImageView userEightImage;

  /** Initializes the login scene. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Login Controller *****************");
  }

  public void setUsernameLabel(int userNumber) throws FileNotFoundException {
    JsonParser jsonParser = App.getJsonParser();
    switch (userNumber) {
      case 1 -> {
        String username = jsonParser.getAllUsernames().get(0);
        String avatar = jsonParser.getAvatar(username);
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/images/" + avatar + ".png");
        Image image = new Image(inputstream);
        userOneLabel.setText(username);
        userOneImage.setImage(image);
      }
    }
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

  public void login(String username) {

    // Set user stats labels
    App.setCurrentUser(username); // Set the current user

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
  }

  @FXML
  private void onSelectUserOne(ActionEvent event) {
    if (userOneLabel.getText().equals(null)) {

    } else {
      App.setCurrentUser(userOneLabel.getText());

      // Change to menu screen
      Button button =
          (Button) event.getSource(); // Get the scene of the button and switch its root.
      Scene buttonScene = button.getScene();
      buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
    }
  }

  @FXML
  private void onSelectUserTwo(ActionEvent event) {}

  @FXML
  private void onSelectUserThree(ActionEvent event) {}

  @FXML
  private void onSelectUserFour(ActionEvent event) {}

  @FXML
  private void onSelectUserFive(ActionEvent event) {}

  @FXML
  private void onSelectUserSix(ActionEvent event) {}

  @FXML
  private void onSelectUserSeven(ActionEvent event) {}

  @FXML
  private void onSelectUserEight(ActionEvent event) {}
}
