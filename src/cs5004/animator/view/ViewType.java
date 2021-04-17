package cs5004.animator.view;

import javax.swing.text.View;

public enum ViewType {
  TEXT,
  SVG,
  VISUAL,
  UNKNOWN;

  public static ViewType fromString(String aName) {
    for (ViewType tmpType : ViewType.values()) {
      if (tmpType.name().equalsIgnoreCase(aName)) {
        return tmpType;
      }
    }2
    return ViewType.UNKNOWN;
  }

  public String toString() {
    return this.name();
  }
}
