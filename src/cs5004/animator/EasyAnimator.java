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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class EasyAnimator {

  // CONSTANTS
  private static final String IN_FILE_ARG = "-in";
  private static final String OUT_FILE_ARG = "-out";
  private static final String VIEW_TYPE_ARG = "-view";
  private static final String SPEED_ARG = "-speed";
  private static final String DEFAULT_IN_FILE = "default_in_file.txt";
  private static final String DEFAULT_OUT_FILE = "default_out_file.txt";
  private static final int DEFAULT_FPS = 30;
  private static final ViewType DEFAULT_VIEW_TYPE = ViewType.TEXT;

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
      System.out.println(
          "Unable to construct Animation Model from input file: " + ex.toString());
      System.exit(2);
    }

    /* ADDED BY GUY FOR TESTING

        AnimatorModelImpl testModel;
        testModel = new AnimatorModelImpl();

        testModel.setBoundingBoxLoc(0,0);
        testModel.setBoundingBoxHeight(350);
        testModel.setBoundingBoxWidth(350);

        testModel.registerObject(
                "First Object", ShapeType.RECTANGLE, new Point2D(50, 50), 2000, 2000,
                0, 0, 102, 0, 10);

        testModel.registerObject(
                "Second Object", ShapeType.ELLIPSE, new Point2D(50, 50), 2000, 2000,
                0, 0, 102, 0, 40);


        ViewInterface test;
        test=ViewFactory.createView( testModel, ViewType.VISUAL, "", 2);
        test.makeVisible();
    */

    // create view
    ViewFactory.createView(theModel, modelViewType, outFile, fps);

    // run animation

    /** Stubbed method for running an animation. Todo in the next pass. */
    // todo: Guy BL comment this should belong to the controller that we'll located this time
    // in main.
    // public void runAnimation() {

    // todo: init clock

    // todo: setNextClockTic()
    // --> take the current clock and add a number of seconds to it to create the next
    // tic

    // todo: for each object, handleClockTic(0)

    // todo: loop until finished
    // --> wait until next tic
    // --> for each object, handleClockTic(n)

    // }


    TextView tmpView = new TextView(theModel, outFile);

    try{
      tmpView.activateView(outFile, fps);
    } catch (Exception e){
      System.out.println("Unable to write file: " + e.toString());
      System.exit(3);
    }

  }

  private static boolean unpackArguments(String args[]) {
    int argCount = args.length;

    int argIdx;

    // set defaults
    inFile = DEFAULT_IN_FILE;
    outFile = DEFAULT_OUT_FILE;
    modelViewType = DEFAULT_VIEW_TYPE;
    fps = DEFAULT_FPS;

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
      throw new IllegalArgumentException("In file is required.");
    }

    if (outFileRequired && outFile == null) {
      throw new IllegalArgumentException("Out file is required.");
    }

    if (viewTypeRequired && modelViewType == ViewType.UNKNOWN) {
      throw new IllegalArgumentException(
          "Valid view type is required. Must be TEXT, SVG, or VISUAL.");
    }

    return true;
  }



}
