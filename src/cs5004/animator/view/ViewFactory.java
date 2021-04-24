package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;

/** Factory method for creating a view. */
public class ViewFactory {

  /**
   * Create View method for initializing a view of any type.
   *
   * @param aModel A model object to create a view for.
   * @param aType The type of view (see ViewType Enum for values).
   * @param aOutFileName The output filename to write (txt and svg formats are supported, choose
   *     extension accordingly).
   * @param mSecsPTick The number of frames per second (or ticks per second) to animate.
   * @return A view object for the the provided model of the specified type.
   */
  public static ViewInterface createView(
      AnimatorModel aModel, ViewType aType, String aOutFileName, Long mSecsPTick) {

    ViewInterface rVal = null;

    switch (aType) {
      case TEXT:
        rVal = new TextView(aModel, aOutFileName);
        break;

      case SVG:
        rVal = new SVGView(aModel, aOutFileName, mSecsPTick);
        break;

      case VISUAL:
        rVal = new VisualView(aModel);
        break;

      case PLAYBACK:
        rVal = new Playback(aModel);
        break;

      case UNKNOWN:

      default:
        System.out.println("Unidentified View Type");
        System.exit(4);
        break;
    }

    return rVal;
  }
}
