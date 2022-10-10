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
  String prompt;
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
    jsonParser.addWordEncountered(App.getCurrentUser(), prompt);

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
    setPrompt(App.getJsonParser().getDifficulty(App.getCurrentUser(), "level"));
  }

  public String getPromptLabel() {
    return promptLabel.getText();
  }

  public void decreasePromptLabelSize() {
    promptLabel.setStyle("-fx-font-size: 20px;");
  }

  public void resetPromptLabelSize() {
    promptLabel.setStyle("-fx-font-size: 48px;");
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPromptLabel(String prompt) {
    promptLabel.setText(prompt);
  }

  public void setDrawLabel(String gameMode) {
    switch (gameMode) {
      case "normal":
        drawLabel.setText("You have 1 minute to draw:");
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
  public void setPrompt(String difficulty) {
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

  public NormalModeTask getNormalModeTask() {
    return normalModeTask;
  }

  public HiddenModeTask getHiddenModeTask() {
    return hiddenModeTask;
  }

  public ZenModeTask getZenModeTask() {
    return zenModeTask;
  }
}
