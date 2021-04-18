package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;

public class ViewFactory {

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
    }

    return rVal;
  }

}

