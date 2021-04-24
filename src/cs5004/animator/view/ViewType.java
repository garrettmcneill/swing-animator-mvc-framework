package cs5004.animator.view;

/**
 * Represents the types of views that are supported by our application.
 */
public enum ViewType {
  TEXT,
  SVG,
  VISUAL,
  PLAYBACK,
  UNKNOWN;

  /**
   * fromString method for converting a String into an Enum type.
   *
   * @param aName the desired enum value as a string.
   * @return The ViewType enum for the provided string.
   */
  public static ViewType fromString(String aName) {
    for (ViewType tmpType : ViewType.values()) {
      if (tmpType.name().equalsIgnoreCase(aName)) {
        return tmpType;
      }
    }
    return ViewType.UNKNOWN;
  }

  @Override
  public String toString() {
    return this.name();
  }
}
