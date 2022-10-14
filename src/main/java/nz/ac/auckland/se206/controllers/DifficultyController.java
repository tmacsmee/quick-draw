package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class DifficultyController {

  @FXML private ChoiceBox<String> cbTopGuess;

  @FXML private ChoiceBox<String> cbLevel;

  @FXML private ChoiceBox<String> cbTimeAllowed;

  @FXML private ChoiceBox<String> cbHowConfident;

  private String[] topGuess = {"3", "2", "1"};

  private String[] level = {"easy", "medium", "hard", "master"};

  private String[] timeAllowed = {"60", "45", "30", "15"};

  private String[] confidence = {"1", "10", "25", "50"};

  /** Initializes the difficulty scene when the app is run. */
  @FXML
  public void initialize() {
    System.out.println("***************** Initialising Difficulty Controller *****************");
    cbTopGuess.getItems().addAll(topGuess);
    cbLevel.getItems().addAll(level);
    cbTimeAllowed.getItems().addAll(timeAllowed);
    cbHowConfident.getItems().addAll(confidence);
  }

  /** Initializes the choice box choices to what the user had selected previously. */
  public void initialiseChoiceBox() {
    JsonParser jsonParser = App.getJsonParser();

    // Choice box for top guesses - 1, 2, 3
    cbTopGuess.setValue(jsonParser.getProperty(App.getCurrentUser(), "topGuess").toString());
    cbTopGuess.setOnAction(this::getTopGuess);

    // Choice box for levels - easy, medium, hard, master
    cbLevel.setValue(jsonParser.getProperty(App.getCurrentUser(), "level").toString());
    cbLevel.setOnAction(this::getLevel);

    // Choice box for time allowed - 60, 45, 30, 15
    cbTimeAllowed.setValue(jsonParser.getProperty(App.getCurrentUser(), "timeAllowed").toString());
    cbTimeAllowed.setOnAction(this::getTimeAllowed);

    // Choice box for confidence - 1, 10, 25, 50
    cbHowConfident.setValue(jsonParser.getProperty(App.getCurrentUser(), "confidence").toString());
    cbHowConfident.setOnAction(this::getHowConfident);
  }

  /**
   * Gets the top guess value from the choice box.
   *
   * @param event change in the choice box
   * @return topGuess
   */
  public String getTopGuess(ActionEvent event) {
    String currentTopGuess = cbTopGuess.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "topGuess", currentTopGuess);
    return (currentTopGuess);
  }

  /**
   * Gets the level value from the choice box.
   *
   * @param event change in the choice box
   * @return level
   */
  public String getLevel(ActionEvent event) {
    String currentLevel = cbLevel.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "level", currentLevel);
    return (currentLevel);
  }

  /**
   * Gets the time allowed value from the choice box.
   *
   * @param event change in the choice box
   * @return timeAllowed
   */
  public String getTimeAllowed(ActionEvent event) {
    String currentTimeAllowed = cbTimeAllowed.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "timeAllowed", currentTimeAllowed);
    return (currentTimeAllowed);
  }

  /**
   * Gets the confidence value from the choice box.
   *
   * @param event change in the choice box
   * @return confidence
   */
  public String getHowConfident(ActionEvent event) {
    String currentConfidence = cbHowConfident.getValue();
    App.getJsonParser().setDifficulty(App.getCurrentUser(), "confidence", currentConfidence);
    return (currentConfidence);
  }

  /**
   * Switches back to the main menu scene.
   *
   * @param event the back button is clicked
   */
  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }
}
