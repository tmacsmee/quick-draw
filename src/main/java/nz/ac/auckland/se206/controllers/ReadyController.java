package nz.ac.auckland.se206.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.util.JsonParser;
import nz.ac.auckland.se206.util.NormalModeTask;
import nz.ac.auckland.se206.util.ZenModeTask;

public class ReadyController {

  private List<String> easy;
  private List<String> medium;
  private List<String> hard;
  private CanvasController canvasController;
  private MenuController menuController;
  @FXML private Label promptLabel;
  @FXML private Label drawLabel;

  /** Initializes the controller class, creates difficulty arrays and generates prompt. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Ready Controller *****************" + this);
  }

  private void normalReady() {
    NormalModeTask normalModeTask = new NormalModeTask();
    normalModeTask.scheduleTask();
  }

  private void zenReady() {
    ZenModeTask zenModeTask = new ZenModeTask();
    zenModeTask.scheduleTask();
  }

  private void hiddenReady() {}

  /*
   * Runs when ready button is pressed. Takes user to the canvas scene and starts
   * timer.
   */
  @FXML
  private void onReady(ActionEvent event) {
    String prompt = promptLabel.getText();

    canvasController = (CanvasController) App.getController("canvas");
    canvasController.setPrompt(prompt); // Set the prompt on the canvas controller
    canvasController.onClear();

    JsonParser jsonParser = App.getJsonParser(); // Add word to json file
    jsonParser.addWordEncountered(App.getCurrentUser(), prompt);

    menuController = (MenuController) App.getController("menu");
    menuController.setWordsEncounteredListView();

    if (drawLabel.getText().equals("normal")) {
      normalReady();
    } else if (drawLabel.getText().equals("zen")) {
      zenReady();
    } else if (drawLabel.getText().equals("hidden")) {
      hiddenReady();
    }

    Button button = (Button) event.getSource(); // Get button scene and change its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.CANVAS)));
  }

  /** Generates an array of each difficulty from the csv file. */
  public void createDifficultyArrays() {
    easy = new ArrayList<>();
    medium = new ArrayList<>();
    hard = new ArrayList<>();

    JsonParser jsonParser = App.getJsonParser();
    List<String> wordsEncountered =
        (List<String>) jsonParser.getProperty(App.getCurrentUser(), "wordsEncountered");

    try (Scanner scanner = new Scanner(new File("src/main/resources/category_difficulty.csv"))) {
      while (scanner.hasNextLine()) { // Iterate through each line in the csv file
        String line = scanner.nextLine();
        String[] split = line.split(","); // Separate word and difficulty
        switch (split[1]) {
          case ("E"): // If difficulty is easy, add to easy array
            if (!wordsEncountered.contains(split[0])) {
              easy.add(split[0]);
            }
            break;
          case "M": // If difficulty is medium, add to medium array
            if (!wordsEncountered.contains(split[0])) {
              medium.add(split[0]);
            }
            break;
          case "H": // If difficulty is hard, add to hard array
            if (!wordsEncountered.contains(split[0])) {
              hard.add(split[0]);
            }
            break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Generates a new random prompt. */
  public void reset() {
    getPrompt("E");
  }

  public String getPromptLabel() {
    return promptLabel.getText();
  }

  public void setDrawLabel(String gameMode) {
    switch (gameMode) {
      case "normal":
        drawLabel.setText("You have 1 minute to draw this word:");
        break;
      case "zen":
        drawLabel.setText("Draw:");
        break;
      case "hidden":
        drawLabel.setText("A hidden word has been chosen for you to draw:");
        break;
    }
  }

  /**
   * Generates a new prompt based on the difficulty.
   *
   * @param difficulty The difficulty of the prompt.
   */
  public void getPrompt(String difficulty) {
    switch (difficulty) { // Get a random word from the correct array
      case "E": // Generate easy prompt
        promptLabel.setText(easy.get((int) (Math.random() * easy.size())));
        break;
      case "M": // Generate medium prompt
        promptLabel.setText(medium.get((int) (Math.random() * medium.size())));
        break;
      case "H": // Generate hard prompt
        promptLabel.setText(hard.get((int) (Math.random() * hard.size())));
        break;
    }
  }
}
