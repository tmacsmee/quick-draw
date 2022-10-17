package nz.ac.auckland.se206;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.controllers.MenuController;
import nz.ac.auckland.se206.controllers.ReadyController;
import nz.ac.auckland.se206.controllers.StatsController;
import nz.ac.auckland.se206.controllers.WordsController;
import nz.ac.auckland.se206.speech.TextToSpeech;
import nz.ac.auckland.se206.util.JsonParser;
import nz.ac.auckland.se206.util.SoundManager;

/** This is the entry point of the JavaFX application. */
public class App extends Application {

  // initialise voice in app so that it can be terminated when app is closed
  public static TextToSpeech voice = new TextToSpeech();

  private static final HashMap<String, Object> controllerMap = new HashMap<>();
  private static String currentUser;
  private static JsonParser jsonParser;
  private static SoundManager soundManager;

  /**
   * Launches the JavaFX application.
   *
   * @param args should be left empty.
   */
  public static void main(final String[] args) {
    launch();
  }

  /**
   * Return the controller for the given fxml name.
   *
   * @param fxml the name of the fxml controller
   * @return the controller for the given fxml name
   */
  public static Object getController(String fxml) {
    return controllerMap.get(fxml);
  }

  public static void setCurrentUser(String username) {
    currentUser = username;
  }

  public static String getCurrentUser() {
    return currentUser;
  }

  public static JsonParser getJsonParser() {
    return jsonParser;
  }

  public static SoundManager getSoundManager() {
    return soundManager;
  }

  /**
   * Returns the node associated to the input file. The method expects that the file is located in
   * "src/main/resources/fxml".
   *
   * @param fxml The name of the FXML file (without extension).
   * @return The node of the input file.
   * @throws IOException If the file is not found.
   */
  private Parent loadFxml(final String fxml) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxml + ".fxml"));
    Parent root = loader.load();
    controllerMap.put(fxml, loader.getController()); // Store the controller instance in the map.
    return root;
  }

  /**
   * This method is invoked when the application starts. It loads and shows the "Canvas" scene.
   *
   * @param stage The primary stage of the application.
   * @throws IOException If "src/main/resources/fxml/canvas.fxml" is not found.
   */
  @Override
  public void start(final Stage stage) throws IOException {

    // Load each fxml and add to map.

    SceneManager.addUi(SceneManager.AppUi.CREATEACCOUNT, loadFxml("createAccount"));
    SceneManager.addUi(SceneManager.AppUi.LOGIN, loadFxml("login"));
    SceneManager.addUi(SceneManager.AppUi.HOWTOPLAY, loadFxml("howToPlay"));
    SceneManager.addUi(SceneManager.AppUi.MENU, loadFxml("menu"));
    SceneManager.addUi(SceneManager.AppUi.GAMEMODE, loadFxml("gameMode"));
    SceneManager.addUi(SceneManager.AppUi.READY, loadFxml("ready"));
    SceneManager.addUi(SceneManager.AppUi.CANVAS, loadFxml("canvas"));
    SceneManager.addUi(SceneManager.AppUi.RESULTS, loadFxml("results"));
    SceneManager.addUi(SceneManager.AppUi.DIFFICULTY, loadFxml("difficulty"));
    SceneManager.addUi(SceneManager.AppUi.STATS, loadFxml("stats"));
    SceneManager.addUi(SceneManager.AppUi.WORDS, loadFxml("wordsEncountered"));
    SceneManager.addUi(SceneManager.AppUi.WELCOME, loadFxml("welcome"));

    jsonParser = new JsonParser();
    soundManager = new SoundManager();

    // Show the login scene.
    Scene scene = new Scene(SceneManager.getUiRoot(SceneManager.AppUi.WELCOME), 800, 600);
    scene
        .getStylesheets()
        .add(getClass().getResource("/css/styles.css").toExternalForm()); // Initialize css.
    stage.setScene(scene);
    stage.show();

    // terminates textToSpeech on closing the app
    stage.setOnCloseRequest(
        e -> {
          Platform.exit();
          voice.terminate();
        });
  }

  /**
   * This method changes the current user of the application and updates labels to be user specific.
   *
   * @param username the username of the user
   * @throws FileNotFoundException
   */
  public static void changeUser(String username) throws FileNotFoundException {
    App.setCurrentUser(username);

    // Get all controllers
    StatsController statsController = (StatsController) App.getController("stats");
    WordsController wordsController = (WordsController) App.getController("wordsEncountered");
    MenuController menuController = (MenuController) App.getController("menu");
    ReadyController readyController = (ReadyController) App.getController("ready");

    menuController.updateWelcome(); // Update welcome label
    statsController.updateStats(); // Update users stats
    statsController.checkBadges();
    wordsController.setEncounteredListView(); // Updates users encountered lists

    readyController.createDifficultyArrays(); // Get an array of each difficulty
    readyController.generatePrompt(
        App.getJsonParser().getProperty(App.getCurrentUser(), "level").toString());
  }
}
