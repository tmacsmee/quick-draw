package nz.ac.auckland.se206;

import java.io.IOException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** This is the entry point of the JavaFX application. */
public class App extends Application {

  private static final HashMap<String, Object> controllerMap = new HashMap<>();

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
    SceneManager.addUi(SceneManager.AppUi.MENU, loadFxml("menu"));
    SceneManager.addUi(SceneManager.AppUi.READY, loadFxml("ready"));
    SceneManager.addUi(SceneManager.AppUi.CANVAS, loadFxml("canvas"));
    SceneManager.addUi(SceneManager.AppUi.RESULTS, loadFxml("results"));

    // Show the login scene.
    Scene scene = new Scene(SceneManager.getUiRoot(SceneManager.AppUi.LOGIN), 800, 600);
    scene
        .getStylesheets()
        .add(getClass().getResource("/css/styles.css").toExternalForm()); // Initialize css.
    stage.setScene(scene);
    stage.show();
  }
}
