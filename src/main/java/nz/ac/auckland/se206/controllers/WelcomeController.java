package nz.ac.auckland.se206.controllers;

import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class WelcomeController {
  /**
   * Initializes the login scene.
   *
   * @throws FileNotFoundException
   */
  @FXML
  private void initialize() throws FileNotFoundException {
    System.out.println("***************** Initialising Welcome Controller *****************");
  }

  @FXML
  private void onHowToPlay(ActionEvent event) {
    HowToPlayController howToPlayController = (HowToPlayController) App.getController("howToPlay");
    howToPlayController.showPlayButton(false);

    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.HOWTOPLAY));
  }

  @FXML
  private void onAddNewPlayer(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.CREATEACCOUNT));
  }

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
