package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class statsController {

  @FXML private Label numWinsLabel;
  @FXML private Label numLossesLabel;
  @FXML private Label fastestTimeLabel;
  @FXML private Text txtBadgeDescription;

  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /** Updates the menu scene with the user's stats */
  public void updateStats() {
    JsonParser jsonParser = App.getJsonParser();

    // Set welcome and stats labels
    numWinsLabel.setText(jsonParser.getProperty(App.getCurrentUser(), "gamesWon").toString());
    numLossesLabel.setText(jsonParser.getProperty(App.getCurrentUser(), "gamesLost").toString());

    // Set fastest time label (if games have been played)
    if (jsonParser.getProperty(App.getCurrentUser(), "fastestTime") == "0") {
      fastestTimeLabel.setText("No wins yet");
    } else {
      fastestTimeLabel.setText(
          jsonParser.getProperty(App.getCurrentUser(), "fastestTime").toString() + " seconds");
    }
  }

  @FXML
  private void onBadgeOne() {
    txtBadgeDescription.setText("win more games that you have lost");
  }

  @FXML
  private void onBadgeTwo() {
    txtBadgeDescription.setText("Win 20 times against easy words");
  }

  @FXML
  private void onBadgeThree() {
    txtBadgeDescription.setText("Win 20 times against medium words");
  }

  @FXML
  private void onBadgeFour() {
    txtBadgeDescription.setText("Win 20 times against hard words");
  }

  @FXML
  private void onBadgeFive() {
    txtBadgeDescription.setText("Win in under 5 seconds");
  }

  @FXML
  private void onBadgeSix() {
    txtBadgeDescription.setText("Win 3 times in a row");
  }
}
