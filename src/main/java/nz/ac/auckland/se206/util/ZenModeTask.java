package nz.ac.auckland.se206.util;

import static nz.ac.auckland.se206.ml.DoodlePrediction.printPredictions;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.controllers.CanvasController;

public class ZenModeTask extends TimerTask {

  private final Timer timer;
  private final CanvasController canvasController;

  public ZenModeTask() {
    timer = new Timer();
    canvasController = (CanvasController) App.getController("canvas");
  }

  /** Sets the update rate for the prediction list */
  public void scheduleTask() {
    timer.schedule(this, 0, 1000);
    canvasController.setTimerLabel("");
  }

  /** Runs on each tick of the timer. */
  @Override
  public void run() {
    // Run asynchronously
    Platform.runLater(
        () -> {
          try {
            // Set the prediction list
            canvasController.setPredictionList(
                printPredictions(
                    canvasController
                        .getModel()
                        .getPredictions(canvasController.getCurrentSnapshot(), 10)));
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }
}
