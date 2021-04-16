package cs5004.animator.model;

import cs5004.animator.view.ViewType;
import java.awt.Shape;

/**
 * Shape Type Enum defines which shape types are available in our model. */
public enum ShapeType {
  ELLIPSE,
  RECTANGLE,
  UNKNOWN;

  public static ShapeType fromString(String aName) {
    for (ShapeType tmpType : ShapeType.values()) {
      if (tmpType.name().equalsIgnoreCase(aName)) {
        return tmpType;
      }
    }
    return ShapeType.UNKNOWN;
  }


}
