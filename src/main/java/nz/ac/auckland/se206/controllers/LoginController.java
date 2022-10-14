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

  /** Initializes the login scene when app is run. */
  @FXML
  private void initialize() {
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
        // Set username and avatar image
        userOneLabel.setText(username);
        userOneImage.setImage(getAvatarImage(username));
      }

        // For user 2
      case 2 -> {
        // Set username and avatar image
        userTwoLabel.setText(username);
        userTwoImage.setImage(getAvatarImage(username));
      }

        // For user 3
      case 3 -> {
        // Set username and avatar image
        userThreeLabel.setText(username);
        userThreeImage.setImage(getAvatarImage(username));
      }

        // For user 4
      case 4 -> {
        // Set username and avatar image
        userFourLabel.setText(username);
        userFourImage.setImage(getAvatarImage(username));
      }

        // For user 5
      case 5 -> {
        // Set username and avatar image
        userFiveLabel.setText(username);
        userFiveImage.setImage(getAvatarImage(username));
      }

        // For user 6
      case 6 -> {
        // Set username and avatar image
        userSixLabel.setText(username);
        userSixImage.setImage(getAvatarImage(username));
      }

        // For user 7
      case 7 -> {
        // Set username and avatar image
        userSevenLabel.setText(username);
        userSevenImage.setImage(getAvatarImage(username));
      }

        // For user 8
      case 8 -> {
        // Set username and avatar image
        userEightLabel.setText(username);
        userEightImage.setImage(getAvatarImage(username));
      }
    }
  }

  /**
   * Switches to the main menu scene and logs into user one when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserOne(ActionEvent event) {

    // Get username and select this user
    String username = userOneLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user two when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserTwo(ActionEvent event) {

    // Get username and select this user
    String username = userTwoLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user three when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserThree(ActionEvent event) {

    // Get username and select this user
    String username = userThreeLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user four when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserFour(ActionEvent event) {

    // Get username and select this user
    String username = userFourLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user five when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserFive(ActionEvent event) {

    // Get username and select this user
    String username = userFiveLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user six when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserSix(ActionEvent event) {

    // Get username and select this user
    String username = userSixLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user seven when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserSeven(ActionEvent event) {

    // Get username and select this user
    String username = userSevenLabel.getText();
    selectUser(username, event);
  }

  /**
   * Switches to the main menu scene and logs into user eight when the button is clicked.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSelectUserEight(ActionEvent event) {

    // Get username and select this user
    String username = userEightLabel.getText();
    selectUser(username, event);
  }

  /**
   * Sets the buttons for all the profiles of existing users.
   *
   * @throws FileNotFoundException if the image file is not found.
   */
  public void setProfiles() throws FileNotFoundException {
    for (int i = 1; i <= 8; i++) {

      // If a user exists, set their profile
      if (App.getJsonParser().getListUsernames().size() >= i) {
        setUserProfile(i);
      } else {
        // If a user does not exist, set the button to be invisible
        setVisibility(i, false);
      }
    }
  }

  /**
   * Sets the visibility of the button, image and name for a user.
   *
   * @param userNumber the number of the user.
   * @param visible
   */
  public void setVisibility(int userNumber, boolean visible) {
    switch (userNumber) {
        // User one
      case 1 -> {
        userOneLabel.setVisible(visible);
        userOneImage.setVisible(visible);
        userOneButton.setVisible(visible);
      }
        // User two
      case 2 -> {
        userTwoLabel.setVisible(visible);
        userTwoImage.setVisible(visible);
        userTwoButton.setVisible(visible);
      }
        // User three
      case 3 -> {
        userThreeLabel.setVisible(visible);
        userThreeImage.setVisible(visible);
        userThreeButton.setVisible(visible);
      }
        // User four
      case 4 -> {
        userFourLabel.setVisible(visible);
        userFourImage.setVisible(visible);
        userFourButton.setVisible(visible);
      }
        // User five
      case 5 -> {
        userFiveLabel.setVisible(visible);
        userFiveImage.setVisible(visible);
        userFiveButton.setVisible(visible);
      }
        // User six
      case 6 -> {
        userSixLabel.setVisible(visible);
        userSixImage.setVisible(visible);
        userSixButton.setVisible(visible);
      }
        // User seven
      case 7 -> {
        userSevenLabel.setVisible(visible);
        userSevenImage.setVisible(visible);
        userSevenButton.setVisible(visible);
      }
        // User eight
      case 8 -> {
        userEightLabel.setVisible(visible);
        userEightImage.setVisible(visible);
        userEightButton.setVisible(visible);
      }
    }
  }

  /**
   * Changes the scene to the main menu.
   *
   * @param event the button click event.
   */
  public void changeToMenu(ActionEvent event) {

    // Get the scene of the button.
    Button button = (Button) event.getSource();
    Scene buttonScene = button.getScene();

    // Switch its root.
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /**
   * Selects a user and updates current user and the scenes.
   *
   * @param userNumber the number of the user.
   */
  public void selectUser(String username, ActionEvent event) {
    // Login and set the current user
    App.changeUser(username);
    App.setCurrentUser(username);

    // Update the welcome message and change to main menu
    MenuController menuController = (MenuController) App.getController("menu");
    menuController.updateWelcome();
    changeToMenu(event);
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
   * Gets the avatar image for user profile image.
   *
   * @param username
   * @return image of the avatar.
   * @throws FileNotFoundException if the image file is not found.
   */
  private Image getAvatarImage(String username) throws FileNotFoundException {
    JsonParser jsonParser = App.getJsonParser();

    // Get user directory and png of avatar in images folder
    FileInputStream inputstream =
        new FileInputStream(
            System.getProperty("user.dir")
                + "/src/main/resources/images/"
                + jsonParser.getProperty(username, "avatar")
                + ".png");

    Image image = new Image(inputstream);
    return image;
  }
}
