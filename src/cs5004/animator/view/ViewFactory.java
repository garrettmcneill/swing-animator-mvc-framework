package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;

public class ViewFactory {

  public static StaticViewInterface createView(
      AnimatorModel aModel, ViewType aType, String aOutFileName, long aTickMsecs) {

    StaticViewInterface rVal = null;

    switch (aType) {
      case TEXT:
        rVal = new TextView(aModel, aOutFileName);
        break;

      case SVG:
        rVal = new SVGView(aModel, aOutFileName);

      case VISUAL:
        rVal = new DynamicView(aModel, aTickMsecs);
    }
    return rVal;
  }

}
