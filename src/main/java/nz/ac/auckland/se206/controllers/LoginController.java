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

  /**
   * Initializes the login scene.
   *
   * @throws FileNotFoundException
   */
  @FXML
  private void initialize() throws FileNotFoundException {
    System.out.println("***************** Initialising Login Controller *****************");
  }

  public void setUserProfile(int userNumber) throws FileNotFoundException {
    JsonParser jsonParser = App.getJsonParser();
    String username = jsonParser.getListUsernames().get(userNumber - 1);
    switch (userNumber) {
      case 1 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userOneLabel.setText(username);
        userOneImage.setImage(image);
      }
      case 2 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userTwoLabel.setText(username);
        userTwoImage.setImage(image);
      }
      case 3 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userThreeLabel.setText(username);
        userThreeImage.setImage(image);
      }
      case 4 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userFourLabel.setText(username);
        userFourImage.setImage(image);
      }

      case 5 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userFiveLabel.setText(username);
        userFiveImage.setImage(image);
      }
      case 6 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userSixLabel.setText(username);
        userSixImage.setImage(image);
      }
      case 7 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userSevenLabel.setText(username);
        userSevenImage.setImage(image);
      }
      case 8 -> {
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        userEightLabel.setText(username);
        userEightImage.setImage(image);
      }
    }
  }

  /**
   * Switches to the create account scene when the button is clicked
   *
   * @param event the button click event.
   * @throws FileNotFoundException
   */
  @FXML
  private void onCreateAccount(ActionEvent event) throws FileNotFoundException {

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
  private void onSelectUserOne(ActionEvent event) throws FileNotFoundException {
    if (App.getJsonParser().getListUsernames().size() > 0) {

      App.setCurrentUser(userOneLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserTwo(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 1) {

      App.setCurrentUser(userTwoLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserThree(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 2) {

      App.setCurrentUser(userThreeLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserFour(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 3) {

      App.setCurrentUser(userFourLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserFive(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 4) {

      App.setCurrentUser(userFiveLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserSix(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 5) {

      App.setCurrentUser(userSixLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserSeven(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 6) {

      App.setCurrentUser(userSevenLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  @FXML
  private void onSelectUserEight(ActionEvent event) {
    if (App.getJsonParser().getListUsernames().size() > 7) {

      App.setCurrentUser(userEightLabel.getText());
      MenuController menuController = (MenuController) App.getController("menu");
      menuController.updateWelcome();

      changeToMenu(event);
    }
  }

  public void setProfiles() throws FileNotFoundException {
    for (int i = 1; i < App.getJsonParser().getListUsernames().size() + 1; i++) {
      System.out.println(i);
      setUserProfile(i);
    }
  }

  public void changeToMenu(ActionEvent event) {
    // Change to menu screen
    Button button = (Button) event.getSource();

    // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }
}
