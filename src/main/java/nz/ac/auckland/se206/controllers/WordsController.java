package nz.ac.auckland.se206.controllers;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;

public class WordsController {
  @FXML private ListView<String> easyWordsEncounteredListView;
  @FXML private ListView<String> mediumWordsEncounteredListView;
  @FXML private ListView<String> hardWordsEncounteredListView;

  /**
   * Switches to the main menu scene when the user clicks button.
   *
   * @param event the button click event.
   */
  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    App.getSoundManager().playButtonClick();
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /** Separates the list of words encountered by the user by difficulty and sets the list. */
  public void setEncounteredListView() {

    JsonParser jsonParser = App.getJsonParser();

    // Set list of easy words encountered
    ObservableList<String> easyWordsList =
        FXCollections.observableArrayList(
            (List<String>) jsonParser.getProperty(App.getCurrentUser(), "easyWordsEncountered"));
    easyWordsEncounteredListView.setItems(easyWordsList);

    // Set list of medium words encountered
    ObservableList<String> mediumWordsList =
        FXCollections.observableArrayList(
            (List<String>) jsonParser.getProperty(App.getCurrentUser(), "mediumWordsEncountered"));
    mediumWordsEncounteredListView.setItems(mediumWordsList);

    // Set list of hard words encountered
    ObservableList<String> hardWordsList =
        FXCollections.observableArrayList(
            (List<String>) jsonParser.getProperty(App.getCurrentUser(), "hardWordsEncountered"));
    hardWordsEncounteredListView.setItems(hardWordsList);
  }
}
