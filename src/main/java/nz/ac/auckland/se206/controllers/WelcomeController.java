package nz.ac.auckland.se206.controllers;

import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class WelcomeController {

  /** Initializes the login scene */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Welcome Controller *****************");
  }

  /**
   * Takes user to how to play scene when how to play button is pressed
   *
   * @param event the button click event
   */
  @FXML
  private void onHowToPlay(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.HOWTOPLAY));
  }

  /**
   * Takes user to the create account scene when create account button is pressed
   *
   * @param event the button click event
   */
  @FXML
  private void onAddNewPlayer(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.CREATEACCOUNT));
  }

  /**
   * Sets account profiles and takes user to the login scene when login button is pressed
   *
   * @param event the button click event
   * @throws FileNotFoundException if the file is not found
   */
  @FXML
  private void onSelectPlayer(ActionEvent event) throws FileNotFoundException {

    // Set account profiles
    LoginController loginController = (LoginController) App.getController("login");
    loginController.setProfiles();

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.LOGIN));
  }
}
