package nz.ac.auckland.se206;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
  public enum AppUi {
    CREATEACCOUNT,
    LOGIN,
    HOWTOPLAY,
    MENU,
    GAMEMODE,
    READY,
    CANVAS,
    RESULTS
  }

  private static final HashMap<AppUi, Parent> sceneMap = new HashMap<>();

  public static void addUi(AppUi appUi, Parent uiRoot) {
    sceneMap.put(appUi, uiRoot);
  }

  public static Parent getUiRoot(AppUi appUi) {
    return sceneMap.get(appUi);
  }
}
