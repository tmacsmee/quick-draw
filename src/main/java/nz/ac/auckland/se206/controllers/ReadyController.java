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

public class ReadyController {

  private List<String> easy;
  private List<String> medium;
  private List<String> hard;
  @FXML private Button readyButton;
  @FXML private Label promptLabel;

  /** Initializes the controller class, creates difficulty arrays and generates prompt. */
  @FXML
  private void initialize() {
    System.out.println("***************** Initialising Ready Controller *****************" + this);
    createDifficultyArrays(); // Get an array of each difficulty
    getPrompt("E");
  }

  /*
   * Runs when ready button is pressed. Takes user to the canvas scene and starts
   * timer.
   */
  @FXML
  private void onReady(ActionEvent event) {
    String prompt = promptLabel.getText();

    CanvasController canvasController = (CanvasController) App.getController("canvas");
    canvasController.setPrompt(prompt); // Set the prompt on the canvas controller
    canvasController.clear();

    canvasController.startTimer();

    Button button = (Button) event.getSource(); // Get button scene and change its root.
    Scene buttonScene = button.getScene();
    buttonScene.setRoot(SceneManager.getUiRoot((SceneManager.AppUi.CANVAS)));
  }

  /** Generates an array of each difficulty from the csv file. */
  private void createDifficultyArrays() {
    easy = new ArrayList<>();
    medium = new ArrayList<>();
    hard = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File("src/main/resources/category_difficulty.csv"))) {
      while (scanner.hasNextLine()) { // Iterate through each line in the csv file
        String line = scanner.nextLine();
        String[] split = line.split(","); // Separate word and difficulty
        switch (split[1]) {
          case ("E"): // If difficulty is easy, add to easy array
            easy.add(split[0]);
            break;
          case "M": // If difficulty is medium, add to medium array
            medium.add(split[0]);
            break;
          case "H": // If difficulty is hard, add to hard array
            hard.add(split[0]);
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

  /**
   * Generates a new prompt based on the difficulty.
   *
   * @param difficulty The difficulty of the prompt.
   */
  private void getPrompt(String difficulty) {
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
