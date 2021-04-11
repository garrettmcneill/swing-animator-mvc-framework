package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;

public class ViewFactory {

  public ViewInterface createView(
      AnimatorModel aModel, ViewType aType, String aOutFileName, long aTickMsecs) {

    ViewInterface rVal = null;

    switch (aType) {
      case TEXT:
        rVal = new TextView(aModel, aOutFileName);
        break;

      case SVG:
        rVal = new SVGView(aModel, aOutFileName);

      case VISUAL:
        rVal = new VisualView(aModel, aTickMsecs);
    }
    return rVal;
  }

}
