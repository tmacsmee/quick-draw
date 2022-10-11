package nz.ac.auckland.se206.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class DifficultyController implements Initializable {

  @FXML private ChoiceBox<String> cbTopGuess;

  @FXML private ChoiceBox<String> cbLevel;

  @FXML private ChoiceBox<String> cbTimeAllowed;

  @FXML private ChoiceBox<String> cbHowConfident;

  private String[] topGuess = {"3", "2", "1"};

  private String[] level = {"easy", "medium", "hard", "master"};

  private String[] timeAllowed = {"60", "45", "30", "15"};

  private String[] confidence = {"1", "10", "25", "50"};

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    cbTopGuess.getItems().addAll(topGuess);
    cbTopGuess.setValue(topGuess[0]);
    cbTopGuess.setOnAction(this::getTopGuess);

    cbLevel.getItems().addAll(level);
    cbLevel.setValue(level[0]);
    cbLevel.setOnAction(this::getLevel);

    cbTimeAllowed.getItems().addAll(timeAllowed);
    cbTimeAllowed.setValue(timeAllowed[0]);
    cbTimeAllowed.setOnAction(this::getTimeAllowed);

    cbHowConfident.getItems().addAll(confidence);
    cbHowConfident.setValue(confidence[0]);
    cbHowConfident.setOnAction(this::getHowConfident);
  }

  public String getTopGuess(ActionEvent event) {
    String currentTopGuess = cbTopGuess.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "topGuess", currentTopGuess);
    return (currentTopGuess);
  }

  public String getLevel(ActionEvent event) {
    String currentLevel = cbLevel.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "level", currentLevel);
    return (currentLevel);
  }

  public String getTimeAllowed(ActionEvent event) {
    String currentTimeAllowed = cbTimeAllowed.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "timeAllowed", currentTimeAllowed);
    return (currentTimeAllowed);
  }

  public String getHowConfident(ActionEvent event) {
    String currentConfidence = cbHowConfident.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "confidence", currentConfidence);
    return (currentConfidence);
  }

  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }
}
