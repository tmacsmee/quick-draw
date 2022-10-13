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

  @FXML private ListView<String> wordsEncounteredListView;

  @FXML
  private void onSwitchToMenu(ActionEvent event) {
    Button button = (Button) event.getSource(); // Get the scene of the button and switch its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot(SceneManager.AppUi.MENU));
  }

  /** Updates the list of words encountered on the menu scene */
  public void setWordsEncounteredListView() {
    JsonParser jsonParser = App.getJsonParser();
    List<String> wordsEncountered =
        (List<String>) jsonParser.getProperty(App.getCurrentUser(), "wordsEncountered");
    ObservableList<String> wordsList = FXCollections.observableArrayList(wordsEncountered);
    wordsEncounteredListView.setItems(wordsList);
  }
}
