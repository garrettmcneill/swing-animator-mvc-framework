package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.view.Playback;
import cs5004.animator.view.ViewFactory;
import cs5004.animator.view.ViewInterface;
import cs5004.animator.view.ViewType;

public class Controller implements ActionListener{

  private static int currentTick;
  private static int startTick;
  private static int endTick;
  private static final double msInSec = 1000.0;
  private static final int speedChange = 10;
  private static boolean pauseFlag = false;
  private static boolean restartFlag = false;
  private static boolean loopEnabled = true;

  private AnimatorModel theModel;
  private ViewInterface theView;
  private Timer timer;


  public Controller(AnimatorModel theModel, ViewType modelViewType, String outFile, int fps) {
    this.theModel= theModel;
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

  public void setPause() {

  }


  public void resetPause() {

  }

  /**
   * Method that runs the temporary controller ( placeholder for the controller of the next
   * assignment).
   *
   * @param view Takes an instance of a VisualView.
   * @param fps Desired ticks per second of the animation.
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

    this.timer.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        if (endTick <= currentTick && loopEnabled) {
          currentTick=startTick;
          restart();
        }
        else if (endTick <= currentTick) {
          timer.stop();
        }
        view.setPanelShapes(model.getShapesAtTick(currentTick));
        theView.refresh();

        if (!pauseFlag) {
          currentTick++;
        }

        if (restartFlag){
          currentTick=startTick;
          restart();
        }




      }
    });

    this.timer.start();
  }

  public static void togglePlay() {
    pauseFlag = false;
  }

  public static void togglePause() {
    pauseFlag = true;
  }

  public static void toggleLoop() {
    loopEnabled = true;
  }

  public static void toggleDisableLoop() {
    loopEnabled = false;
  }

  public static void restart() {
    restartFlag = !restartFlag;
  }

  public void decreaseSpeed() {
    this.timer.setDelay(this.timer.getDelay()+speedChange);
  }

  public void increaseSpeed() {
    if ((this.timer.getDelay()-speedChange)<0) {
      this.timer.setDelay(1);
    }
    else {
      this.timer.setDelay(this.timer.getDelay()-speedChange);
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
      case "EXIT":
        System.exit(0);
        break;
      default:
        throw new IllegalStateException("Error: Unknown Action");
    }

  }


}


