package cs5004.animator;

import cs5004.animator.model.AnimatorModel;
import cs5004.animator.model.AnimatorModelImpl;
import cs5004.animator.view.ViewFactory;
import cs5004.animator.view.ViewType;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class EasyAnimator {

  // CONSTANTS
  private static final String IN_FILE_ARG = "-in";
  private static final String OUT_FILE_ARG = "-out";
  private static final String VIEW_TYPE_ARG = "-view";
  private static final String SPEED_ARG = "-speed";

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

    // construct jPanel
    JPanel tmpPanel = new JPanel();


    // call animationModelBuilder
      //todo: modelbuilder saves model to attr theModel

    // create view
    ViewFactory.createView(theModel, modelViewType, outFile, fps);
  }


  private static boolean unpackArguments(String args[]) {
    int argCount = args.length;

    int argIdx;

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
