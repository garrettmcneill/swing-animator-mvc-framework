package cs5004.animator.model;

/** Shape Type Enum defines which shape types are available in our model. */
public enum ShapeType {
  ELLIPSE,
  RECTANGLE,
  UNKNOWN;

  /**
   * FromString method for converting a string representation of an enum value to the enum value.
   *
   * @param aName A string selection for the enum value.
   * @return A ShapeType.ENUMVALUE for the provided string.
   */
  public static ShapeType fromString(String aName) {
    for (ShapeType tmpType : ShapeType.values()) {
      if (tmpType.name().equalsIgnoreCase(aName)) {
        return tmpType;
      }
    }
    return ShapeType.UNKNOWN;
  }
}
