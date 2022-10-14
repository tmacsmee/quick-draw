package nz.ac.auckland.se206.controllers;

import java.io.File;
import java.io.IOException;
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
import nz.ac.auckland.se206.util.HiddenModeTask;
import nz.ac.auckland.se206.util.JsonParser;
import nz.ac.auckland.se206.util.NormalModeTask;
import nz.ac.auckland.se206.util.ZenModeTask;

public class ReadyController {

  private List<String> easy;
  private List<String> medium;
  private List<String> hard;
  private CanvasController canvasController;
  private HiddenModeTask hiddenModeTask;
  private NormalModeTask normalModeTask;
  private ZenModeTask zenModeTask;
  private String prompt;
  @FXML private Label promptLabel;
  @FXML private Label drawLabel;

  /** Initializes the controller class, creates difficulty arrays and generates prompt. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Ready Controller *****************");
  }

  /** Prepares the ready screen for normal mode */
  private void normalReady() {
    canvasController.setPromptLabel(prompt);
    normalModeTask = new NormalModeTask();
    normalModeTask.scheduleTask();
  }

  /** Prepares the ready screen for zen mode */
  private void zenReady() {
    canvasController.setPromptLabel(prompt);
    zenModeTask = new ZenModeTask();
    zenModeTask.scheduleTask();
  }

  /** Prepares the ready screen for hidden mode */
  private void hiddenReady() {
    hiddenModeTask = new HiddenModeTask();
    hiddenModeTask.scheduleTask();
  }

  /*
   * Runs when ready button is pressed. Takes user to the canvas scene and starts
   * timer.
   */
  @FXML
  private void onReady(ActionEvent event) throws IOException {
    // Set the prompt on the canvas controller
    canvasController = (CanvasController) App.getController("canvas");
    canvasController.onClear();

    JsonParser jsonParser = App.getJsonParser(); // Add word to json file

    jsonParser.addWordEncountered(App.getCurrentUser(), prompt, getPromptList(prompt));

    WordsController wordsController = (WordsController) App.getController("wordsEncountered");
    wordsController.setEncounteredListView();
    GameModeController gameModeController = (GameModeController) App.getController("gameMode");
    String gameMode = gameModeController.getGameMode();
    if (gameMode.equals("normal")) {
      normalReady();
    } else if (gameMode.equals("zen")) {
      zenReady();
    } else if (gameMode.equals("hidden")) {
      hiddenReady();
    }

    Button button = (Button) event.getSource(); // Get button scene and change its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.CANVAS)));
  }

  /** Generates an array of each difficulty from the csv file. */
  public void createDifficultyArrays() {

    // Creates array lists for words
    easy = new ArrayList<>();
    medium = new ArrayList<>();
    hard = new ArrayList<>();

    // Get words encountered for each difficulty
    JsonParser jsonParser = App.getJsonParser();
    List<String> easyWordsEncountered =
        (List<String>) jsonParser.getProperty(App.getCurrentUser(), "easyWordsEncountered");
    List<String> mediumWordsEncountered =
        (List<String>) jsonParser.getProperty(App.getCurrentUser(), "mediumWordsEncountered");
    List<String> hardWordsEncountered =
        (List<String>) jsonParser.getProperty(App.getCurrentUser(), "hardWordsEncountered");

    try (Scanner scanner = new Scanner(new File("src/main/resources/category_difficulty.csv"))) {
      while (scanner.hasNextLine()) { // Iterate through each line in the csv file
        String line = scanner.nextLine();
        String[] split = line.split(","); // Separate word and difficulty
        switch (split[1]) {
          case ("E"): // If difficulty is easy, add to easy array
            if (!easyWordsEncountered.contains(split[0])) {
              easy.add(split[0]);
            }
            break;
          case "M": // If difficulty is medium, add to medium array
            if (!mediumWordsEncountered.contains(split[0])) {
              medium.add(split[0]);
            }
            break;
          case "H": // If difficulty is hard, add to hard array
            if (!hardWordsEncountered.contains(split[0])) {
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
    generatePrompt(App.getJsonParser().getProperty(App.getCurrentUser(), "level").toString());
  }

  /**
   * Gets the text of the prompt label.
   *
   * @return prompt label text
   */
  public String getPromptLabel() {
    return promptLabel.getText();
  }

  /** Decreases prompt font size to 20px */
  public void decreasePromptLabelSize() {
    promptLabel.setStyle("-fx-font-size: 20px;");
  }

  /** Resets prompt label font size to 48px */
  public void resetPromptLabelSize() {
    promptLabel.setStyle("-fx-font-size: 48px;");
  }

  /**
   * Gets the current prompt
   *
   * @return prompt
   */
  public String getPrompt() {
    return prompt;
  }

  /** Sets the prompt label to the current prompt */
  public void setPromptLabel(String prompt) {
    promptLabel.setText(prompt);
  }

  /**
   * Finds the difficulty of the prompt and returns the corresponding name of array.
   *
   * @param prompt The prompt to find the difficulty of.
   * @return name of array
   */
  public String getPromptList(String prompt) {
    if (easy.contains(prompt)) {
      return "easyWordsEncountered";
    } else if (medium.contains(prompt)) {
      return "mediumWordsEncountered";
    } else {
      return "hardWordsEncountered";
    }
  }

  /**
   * Sets the draw label depending on game mode
   *
   * @param gameMode The game mode to set the draw label for.
   */
  public void setDrawLabel(String gameMode) {
    switch (gameMode) {
      case "normal":
        drawLabel.setText(
            "You have "
                + App.getJsonParser().getProperty(App.getCurrentUser(), "timeAllowed")
                + " seconds to draw:");
        break;
      case "zen":
        drawLabel.setText("Draw:");
        break;
      case "hidden":
        drawLabel.setText("The word's definition is:");
        break;
    }
  }

  /**
   * Generates a new prompt based on the difficulty.
   *
   * @param difficulty The difficulty of the prompt.
   */
  public void generatePrompt(String difficulty) {
    switch (difficulty) { // Get a random word from the correct array
      case "easy": // Generate easy prompt - easy
        prompt = easy.get((int) (Math.random() * easy.size()));
        break;
      case "medium": // Generate medium prompt - easy/medium
        List<String> easyAndMedium = new ArrayList<>();
        easyAndMedium.addAll(easy);
        easyAndMedium.addAll(medium);
        prompt = easyAndMedium.get((int) (Math.random() * easyAndMedium.size()));
        break;
      case "hard": // Generate hard prompt - easy/medium/hard
        List<String> all = new ArrayList<>();
        all.addAll(easy);
        all.addAll(medium);
        all.addAll(hard);
        prompt = all.get((int) (Math.random() * all.size()));
        break;
      case "master": // Generate master prompt - hard
        prompt = hard.get((int) (Math.random() * hard.size()));
        break;
    }
  }

  /**
   * Gets the normal game mode task
   *
   * @return normal game mode task
   */
  public NormalModeTask getNormalModeTask() {
    return normalModeTask;
  }

  /**
   * Gets the zen game mode task
   *
   * @return zen game mode task
   */
  public HiddenModeTask getHiddenModeTask() {
    return hiddenModeTask;
  }

  /**
   * Gets the hidden game mode task
   *
   * @return hidden game mode task
   */
  public ZenModeTask getZenModeTask() {
    return zenModeTask;
  }
}
