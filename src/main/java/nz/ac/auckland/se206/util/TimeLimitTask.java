package nz.ac.auckland.se206.util;

import static nz.ac.auckland.se206.ml.DoodlePrediction.printPredictions;

import ai.djl.translate.TranslateException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.controllers.CanvasController;
import nz.ac.auckland.se206.controllers.ResultsController;

/** Handles the clock and time limit functionality. */
public class TimeLimitTask extends TimerTask {
  private final long startTime;
  private long timeElapsed;
  private final Timer timer;
  private final CanvasController canvasController;
  private final ResultsController resultsController;
  private final JsonParser jsonParser;

  /**
   * Constructs the TimeLimitTask object.
   *
   * @param canvasController the canvas controller
   */
  public TimeLimitTask(CanvasController canvasController) {
    this.canvasController = canvasController;
    startTime = System.currentTimeMillis(); // Get the current time in milliseconds.
    resultsController = (ResultsController) App.getController("results");
    jsonParser = App.getJsonParser();
    timer = new Timer();
  }

  /** Starts the clock and task with 1-second period. */
  public void scheduleTask() {
    timer.schedule(this, 0, 1000);
  }

  /**
   * Handles the time limit functionality. If the time limit is reached, the program will terminate.
   *
   * <p>TODO: implement fastest time
   */
  public void run() {
    timeElapsed = (System.currentTimeMillis() - startTime) / 1000; // time elapsed in seconds
    if (timeElapsed == 60) { // If time runs out, move to results scene.
      timer.cancel();
      resultsController.setResultLabel("You ran out of time.");
      Platform.runLater(resultsController::setSketchImage);
      jsonParser.incrementLosses(App.getCurrentUser());
      canvasController.results();

    } else if (timeElapsed
        < 60) { // If time is still remaining, update the time label and prediction list.
      Platform.runLater(
          () -> {
            canvasController.setTimerLabel(String.valueOf((59L - timeElapsed) % 60));
            try {
              canvasController.setPredictionList( // Set the prediction list
                  printPredictions(
                      canvasController
                          .getModel()
                          .getPredictions(canvasController.getCurrentSnapshot(), 10)));
              if (canvasController
                  .isCorrect()) { // Check if the prompt is in the top 3 predictions.
                timer.cancel();
                resultsController.setResultLabel( // If so, move to results scene.
                    "Good job! You finished in " + timeElapsed + " seconds!");
                resultsController.setSketchImage();
                jsonParser.incrementWins(App.getCurrentUser());
                jsonParser.setFastestTime(App.getCurrentUser(), Long.toString(timeElapsed));
                canvasController.results();
              }
            } catch (TranslateException e) {
              throw new RuntimeException(e);
            }
          });
    }
  }
}
