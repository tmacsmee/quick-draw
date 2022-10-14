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

  /**
   * Sets the username and avatar of the user in the login scene, displaying profiles of the user
   * with the userNumber.
   *
   * @param userNumber the number of the user
   * @throws FileNotFoundException
   */
  public void setUserProfile(int userNumber) throws FileNotFoundException {
    JsonParser jsonParser = App.getJsonParser();

    // Get the username of the user with the userNumber from array of usernames
    String username = jsonParser.getListUsernames().get(userNumber - 1);

    switch (userNumber) {

        // For user 1
      case 1 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);

        // Set username and avatar image
        userOneLabel.setText(username);
        userOneImage.setImage(image);
      }

        // For user 2
      case 2 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);

        // Set username and avatar image
        userTwoLabel.setText(username);
        userTwoImage.setImage(image);
      }

        // For user 3
      case 3 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);

        // Set username and avatar image
        userThreeLabel.setText(username);
        userThreeImage.setImage(image);
      }

        // For user 4
      case 4 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);
        // Set username and avatar image
        // Set username and avatar image
        userFourLabel.setText(username);
        userFourImage.setImage(image);
      }

        // For user 5
      case 5 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);

        // Set username and avatar image
        userFiveLabel.setText(username);
        userFiveImage.setImage(image);
      }

        // For user 6
      case 6 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);

        // Set username and avatar image
        userSixLabel.setText(username);
        userSixImage.setImage(image);
      }

        // For user 7
      case 7 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");
        Image image = new Image(inputstream);

        // Set username and avatar image
        userSevenLabel.setText(username);
        userSevenImage.setImage(image);
      }

        // For user 8
      case 8 -> {

        // Find the image for the user's avatar
        FileInputStream inputstream =
            new FileInputStream(
                System.getProperty("user.dir")
                    + "/src/main/resources/images/"
                    + jsonParser.getProperty(username, "avatar")
                    + ".png");

        Image image = new Image(inputstream);

        // Set username and avatar image
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

  /**
   * Switches to the main menu scene when the button is clicked
   *
   * @param username
   */
  public void login(String username) {

    App.setCurrentUser(username); // Set the current user

    // Get controllers for the scenes
    StatsController statsController = (StatsController) App.getController("stats");
    WordsController wordsController = (WordsController) App.getController("wordsEncountered");
    MenuController menuController = (MenuController) App.getController("menu");
    ReadyController readyController = (ReadyController) App.getController("ready");

    readyController.createDifficultyArrays(); // Get an array of each difficulty
    readyController.generatePrompt(
        App.getJsonParser().getProperty(App.getCurrentUser(), "level").toString());
    menuController.updateWelcome(); // Update the welcome message in the menu scene
    statsController.updateStats(); // Update the stats in the stats scene
    wordsController
        .setEncounteredListView(); // Update the list of words encountered in the words scene
  }

  @FXML
  private void onSelectUserOne(ActionEvent event) throws FileNotFoundException {

    login(userOneLabel.getText());
    App.setCurrentUser(userOneLabel.getText());
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();
    changeToMenu(event);
  }

  @FXML
  private void onSelectUserTwo(ActionEvent event) {

    String username = userTwoLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();
    changeToMenu(event);
  }

  @FXML
  private void onSelectUserThree(ActionEvent event) {
    String username = userThreeLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();

    changeToMenu(event);
  }

  @FXML
  private void onSelectUserFour(ActionEvent event) {
    String username = userFourLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();

    changeToMenu(event);
  }

  @FXML
  private void onSelectUserFive(ActionEvent event) {
    String username = userFiveLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();

    changeToMenu(event);
  }

  @FXML
  private void onSelectUserSix(ActionEvent event) {
    String username = userSixLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();

    changeToMenu(event);
  }

  @FXML
  private void onSelectUserSeven(ActionEvent event) {
    String username = userSevenLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();

    changeToMenu(event);
  }

  @FXML
  private void onSelectUserEight(ActionEvent event) {
    String username = userEightLabel.getText();
    login(username);
    App.setCurrentUser(username);
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();

    changeToMenu(event);
  }

  public void setProfiles() throws FileNotFoundException {
    for (int i = 1; i <= 8; i++) {

      if (App.getJsonParser().getListUsernames().size() >= i) {
        setUserProfile(i);
      } else {
        setVisibility(i, false);
      }
    }
  }

  public void setVisibility(int i, boolean visible) {
    switch (i) {
      case 1 -> {
        userOneLabel.setVisible(visible);
        userOneImage.setVisible(visible);
        userOneButton.setVisible(visible);
      }
      case 2 -> {
        userTwoLabel.setVisible(visible);
        userTwoImage.setVisible(visible);
        userTwoButton.setVisible(visible);
      }
      case 3 -> {
        userThreeLabel.setVisible(visible);
        userThreeImage.setVisible(visible);
        userThreeButton.setVisible(visible);
      }
      case 4 -> {
        userFourLabel.setVisible(visible);
        userFourImage.setVisible(visible);
        userFourButton.setVisible(visible);
      }
      case 5 -> {
        userFiveLabel.setVisible(visible);
        userFiveImage.setVisible(visible);
        userFiveButton.setVisible(visible);
      }
      case 6 -> {
        userSixLabel.setVisible(visible);
        userSixImage.setVisible(visible);
        userSixButton.setVisible(visible);
      }
      case 7 -> {
        userSevenLabel.setVisible(visible);
        userSevenImage.setVisible(visible);
        userSevenButton.setVisible(visible);
      }
      case 8 -> {
        userEightLabel.setVisible(visible);
        userEightImage.setVisible(visible);
        userEightButton.setVisible(visible);
      }
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
