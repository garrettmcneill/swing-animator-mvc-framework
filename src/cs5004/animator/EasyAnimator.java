package cs5004.animator;

import cs5004.animator.model.AnimationModelBuilder;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.model.Point2D;
import cs5004.animator.model.ShapeType;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.TextView;
import cs5004.animator.view.ViewFactory;
import cs5004.animator.view.ViewInterface;
import cs5004.animator.view.ViewType;
import cs5004.animator.view.VisualView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Class that has the main method and methods for reading/parsing files as well as a method that
 * controls animation.
 */
public class EasyAnimator {

  // CONSTANTS
  private static final String IN_FILE_ARG = "-in";
  private static final String OUT_FILE_ARG = "-out";
  private static final String VIEW_TYPE_ARG = "-view";
  private static final String SPEED_ARG = "-speed";
  private static final String DEFAULT_IN_FILE = "default_in_file.txt";
  private static final String DEFAULT_OUT_FILE = "default_out_file.txt";
  private static final int DEFAULT_FPS = 30;
  private static final double msInSec = 1000.0;
  private static final ViewType DEFAULT_VIEW_TYPE = ViewType.TEXT;

  private static int currentTick;
  private static int startTick;
  private static int endTick;

  // class vars
  private static AnimatorModel theModel;

  // in File attrs
  private static boolean inFileRequired = true;
  private static String inFile = null;

  // view attrs
  private static boolean viewTypeRequired = true;
  private static ViewType modelViewType = ViewType.UNKNOWN;

  // out File attrs
  private static boolean outFileRequired = false;
  private static String outFile = null;

  // ticks per second attrs
  private static boolean fpsRequired = false;
  private static Integer fps = null;

  /**
   * Main method of the program.
   *
   * @param args takes various arguments per the README.md
   */
  public static void main(String[] args) {

    try {
      if (!unpackArguments(args)) {
        System.out.println("Unable to unpack arguments...");
        System.exit(1);
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Unpack Arguments threw an exception: " + e.toString());
      System.exit(1);
    }

    // call animationModelBuilder
    try {
      FileReader tmpFileReader = new FileReader(inFile);
      BufferedReader tmpReader = new BufferedReader(tmpFileReader);
      AnimationBuilder<AnimatorModel> tmpBuilder = new AnimationModelBuilder();
      theModel = AnimationReader.parseFile(tmpReader, tmpBuilder);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(
          null,
          "Unable to construct" + " Animation Model from input file: " + ex.toString(),
          "Invalid Input File",
          JOptionPane.ERROR_MESSAGE);

      System.out.println("Unable to construct Animation Model from input file: " + ex.toString());
      System.exit(2);
    }

    // create view
    ViewInterface theView =
        ViewFactory.createView(theModel, modelViewType, outFile, Long.valueOf(fps));

    /** Switch Statement to activate either a Text or SVG view. */
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
        runTempController((VisualView) theView, fps, theModel);
        break;

      default:
        System.out.println("Unidentified View Type " + modelViewType);
        System.exit(4);
    }
  }

  private static void runTempController(VisualView view, int fps, AnimatorModel model) {

    view.makeVisible();

    startTick = model.getModelStartTime();
    endTick = model.getModelEndTime();
    int delay = (int) (msInSec / ((double) fps));
    currentTick = startTick;
    final Timer timer = new Timer(delay, null);

    timer.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (endTick <= currentTick) {
              timer.stop();
            }
            view.setPanelShapes(model.getShapesAtTick(currentTick));
            view.refresh();
            currentTick++;
          }
        });
    timer.start();
  }

  private static boolean unpackArguments(String args[]) {

    // set defaults
    inFile = DEFAULT_IN_FILE;
    outFile = DEFAULT_OUT_FILE;
    modelViewType = DEFAULT_VIEW_TYPE;
    fps = DEFAULT_FPS;
    int argCount = args.length;
    int argIdx;

    // loop through args
    for (argIdx = 0; argIdx < argCount; ++argIdx) {
      if (IN_FILE_ARG.equalsIgnoreCase(args[argIdx]) && argIdx + 1 < argCount) {
        inFile = args[argIdx + 1];
        fpsRequired = true;
      } else if (OUT_FILE_ARG.equalsIgnoreCase(args[argIdx]) && argIdx + 1 < argCount) {
        outFile = args[argIdx + 1];
        outFileRequired = true;
      } else if (VIEW_TYPE_ARG.equalsIgnoreCase(args[argIdx]) && argIdx + 1 < argCount) {
        modelViewType = ViewType.fromString(args[argIdx + 1]);
      } else if (SPEED_ARG.equalsIgnoreCase(args[argIdx]) && argIdx + 1 < argCount) {
        fpsRequired = true;
        fps = Integer.parseInt(args[argIdx + 1]);
      }
    }

    // Validate

    if (inFileRequired && inFile == null) {
      JOptionPane.showMessageDialog(
          null, "Infile is required", "Invalid Command Arguments", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("In file is required.");
    }

    if (outFileRequired && outFile == null) {
      JOptionPane.showMessageDialog(
          null, "Out file is required", "Invalid Command Arguments", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Out file is required.");
    }

    if (viewTypeRequired && modelViewType == ViewType.UNKNOWN) {
      JOptionPane.showMessageDialog(
          null,
          "Valid view type is required." + " Must be TEXT, SVG, or VISUAL.",
          "Invalid Command Arguments",
          JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException(
          "Valid view type is required. Must be TEXT, SVG, or VISUAL.");
    }
    return true;
  }
}
