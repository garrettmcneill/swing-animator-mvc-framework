package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.Playback;
import cs5004.animator.view.ViewFactory;
import cs5004.animator.view.ViewInterface;
import cs5004.animator.view.ViewType;

/**
 * Controller class acts as an interface between our model and our views. Includes functionality for
 * playback controls that are implemented in the "playback" view type.
 */
public class Controller implements IController, ActionListener {

  private static int currentTick;
  private static int startTick;
  private static int endTick;
  private static final double msInSec = 1000.0;
  private static final int speedChange = 10;
  private static boolean pauseFlag = false;
  private static boolean restartFlag = false;
  private static boolean loopEnabled = true;

  private ViewInterface theView;
  private Timer timer;

  /**
   * Controller Constructor.
   *
   * @param theModel Instance of the model.
   * @param modelViewType View type (enum).
   * @param outFile Desired Location of the output file.
   * @param fps Initial desired ticks or frames per second.
   */
  public Controller(AnimatorModel theModel, ViewType modelViewType, String outFile, int fps) {

    // attrs
    AnimatorModel mainModel;

    mainModel = theModel;
    this.theView = ViewFactory.createView(theModel, modelViewType, outFile, (long) fps);

    switch (modelViewType) {
      case TEXT:
      case SVG:
        try {
          theView.activateView(outFile, fps);
        } catch (Exception e) {
          System.out.println("Unable to write file: " + e.toString());
          System.exit(3);
        }
        break;

      case VISUAL:
        visualController(theView, fps, theModel);
        break;

      case PLAYBACK:
        visualController(theView, fps, theModel);
        ((Playback) theView).setListener(this);
        break;

      default:
        System.out.println("Unidentified View Type " + modelViewType);
        System.exit(4);
    }
  }

  /**
   * Method that runs the controller.
   *
   * @param view Takes an instance of an Interface.
   * @param fps Desired initial ticks per second of the animation.
   * @param model Model that is the single source of truth required to run the animation and show it
   *     on the view.
   */
  private void visualController(ViewInterface view, int fps, AnimatorModel model) {

    view.makeVisible();

    startTick = model.getModelStartTime();
    endTick = model.getModelEndTime();
    int initDelay = (int) (msInSec / ((double) fps));
    currentTick = startTick;
    this.timer = new Timer(initDelay, null);

    this.timer.addActionListener(
        new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            if (endTick <= currentTick && loopEnabled) {
              currentTick = startTick;
              restart();
            } else if (endTick <= currentTick) {
              timer.stop();
            }
            view.setPanelShapes(model.getShapesAtTick(currentTick));
            theView.refresh();

            if (!pauseFlag) {
              currentTick++;
            }

            if (restartFlag) {
              currentTick = startTick;
              restart();
            }
          }
        });

    this.timer.start();
  }

  @Override
  public void togglePlay() {
    pauseFlag = false;
  }

  @Override
  public void togglePause() {
    pauseFlag = true;
  }

  @Override
  public void toggleLoop() {
    loopEnabled = true;
  }

  @Override
  public void toggleDisableLoop() {
    loopEnabled = false;
  }

  @Override
  public void restart() {
    restartFlag = !restartFlag;
  }

  @Override
  public void decreaseSpeed() {
    this.timer.setDelay(this.timer.getDelay() + speedChange);
  }

  @Override
  public void increaseSpeed() {
    if ((this.timer.getDelay() - speedChange) < 0) {
      this.timer.setDelay(1);
    } else {
      this.timer.setDelay(this.timer.getDelay() - speedChange);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "REPLAY":
        this.timer.restart();
        restart();
        break;
      case "DECREASE SPEED":
        decreaseSpeed();
        break;
      case "INCREASE SPEED":
        increaseSpeed();
        break;
      case "DISABLE LOOP":
        toggleDisableLoop();
        break;
      case "ENABLE LOOP":
        toggleLoop();
        break;
      case "PLAY":
        togglePlay();
        break;
      case "PAUSE":
        togglePause();
        break;
      default:
        throw new IllegalStateException("Error: Unknown Action");
    }
  }
}
